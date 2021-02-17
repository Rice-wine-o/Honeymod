package me.spacetoastdev.honeymod.api.gps;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.papermc.lib.PaperLib;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.gps.Teleporter;
import me.spacetoastdev.honeymod.utils.ChestMenuUtils;
import me.spacetoastdev.honeymod.utils.HeadTexture;
import me.spacetoastdev.honeymod.utils.NumberUtils;

/**
 * The {@link TeleportationManager} handles the process of teleportation for a {@link Player}
 * who is using a {@link Teleporter}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see GPSNetwork
 * @see Teleporter
 *
 */
public final class TeleportationManager {

    private final int[] teleporterBorder = { 0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
    private final int[] teleporterInventory = { 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43 };

    /**
     * This {@link Set} holds the {@link UUID} of all Players that are
     * teleporting right now.
     */
    private final Set<UUID> teleporterUsers = new HashSet<>();

    @ParametersAreNonnullByDefault
    public void openTeleporterGUI(Player p, UUID uuid, Block b, int complexity) {
        if (teleporterUsers.add(p.getUniqueId())) {
            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1F, 1F);

            PlayerProfile.fromUUID(uuid, profile -> {
                ChestMenu menu = new ChestMenu("&3Teleporter");
                menu.addMenuCloseHandler(pl -> teleporterUsers.remove(pl.getUniqueId()));

                for (int slot : teleporterBorder) {
                    menu.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
                }

                menu.addItem(4, new CustomItem(HeadTexture.GLOBE_OVERWORLD.getAsItemStack(), ChatColor.YELLOW + HoneymodPlugin.getLocalization().getMessage(p, "machines.TELEPORTER.gui.title")));
                menu.addMenuClickHandler(4, ChestMenuUtils.getEmptyClickHandler());

                Location source = new Location(b.getWorld(), b.getX() + 0.5D, b.getY() + 2D, b.getZ() + 0.5D);
                int index = 0;

                for (Waypoint waypoint : profile.getWaypoints()) {
                    if (index >= teleporterInventory.length) {
                        break;
                    }

                    int slot = teleporterInventory[index];
                    Location l = waypoint.getLocation();
                    double time = NumberUtils.reparseDouble(0.5 * getTeleportationTime(complexity, source, l));

                    // @formatter:off
                    String[] lore = {
                        "",
                        "&8\u21E8 &7" + HoneymodPlugin.getLocalization().getResourceString(p, "tooltips.world") + ": &f" + l.getWorld().getName(),
                        "&8\u21E8 &7X: &f" + l.getX(),
                        "&8\u21E8 &7Y: &f" + l.getY(),
                        "&8\u21E8 &7Z: &f" + l.getZ(),
                        "&8\u21E8 &7" + HoneymodPlugin.getLocalization().getMessage(p, "machines.TELEPORTER.gui.time") + ": &f" + time + "s",
                        "",
                        "&8\u21E8 &c" + HoneymodPlugin.getLocalization().getMessage(p, "machines.TELEPORTER.gui.tooltip")
                    };
                    // @formatter:on

                    menu.addItem(slot, new CustomItem(waypoint.getIcon(), waypoint.getName().replace("player:death ", ""), lore));
                    menu.addMenuClickHandler(slot, (pl, s, item, action) -> {
                        pl.closeInventory();
                        teleport(pl.getUniqueId(), complexity, source, l, false);
                        return false;
                    });

                    index++;
                }

                HoneymodPlugin.runSync(() -> menu.open(p));
            });
        }
    }

    @ParametersAreNonnullByDefault
    public void teleport(UUID uuid, int complexity, Location source, Location destination, boolean resistance) {
        teleporterUsers.add(uuid);

        int time = getTeleportationTime(complexity, source, destination);
        updateProgress(uuid, Math.max(1, 100 / time), 0, source, destination, resistance);
    }

    /**
     * This returns the amount of time it will take to teleport from the source {@link Location}
     * to the destination {@link Location}, given the specified complexity.
     * <p>
     * The returned time will be measured in 500ms intervals.
     * 
     * <ul>
     * <li>A returned time of {@literal 100} will mean 50,000ms (50s) of real-life time.</li>
     * <li>A returned time of {@literal 10} will mean 5,000ms (5s) of real-life time.</li>
     * <li>A returned time of {@literal 2} will mean 1,000ms (1s) of real-life time.</li>
     * <li>and so on...</li>
     * </ul>
     * 
     * @param complexity
     *            The complexity of the {@link GPSNetwork}
     * @param source
     *            The source {@link Location}
     * @param destination
     *            The destination {@link Location}
     * 
     * @return The amount of time the teleportation will take
     */
    public int getTeleportationTime(int complexity, @Nonnull Location source, @Nonnull Location destination) {
        Validate.notNull(source, "Source cannot be null");
        Validate.notNull(source, "Destination cannot be null");

        if (complexity < 100) {
            return 100;
        }

        int speed = 50_000 + complexity * complexity;
        return 1 + Math.min(4 * distanceSquared(source, destination) / speed, 40);
    }

    @ParametersAreNonnullByDefault
    private int distanceSquared(Location source, Location destination) {
        if (source.getWorld().getUID().equals(destination.getWorld().getUID())) {
            int distance = (int) source.distanceSquared(destination);
            return Math.min(distance, 100_000_000);
        } else {
            return 150_000_000;
        }
    }

    private boolean isValid(@Nullable Player p, @Nonnull Location source) {
        return p != null && p.isValid() && p.getWorld().getUID().equals(source.getWorld().getUID()) && p.getLocation().distanceSquared(source) < 2.0;
    }

    private void cancel(@Nonnull UUID uuid, @Nullable Player p) {
        teleporterUsers.remove(uuid);

        if (p != null) {
            p.sendTitle(ChatColors.color(HoneymodPlugin.getLocalization().getMessage(p, "machines.TELEPORTER.cancelled")), ChatColors.color("&c&k40&f&c%"), 20, 60, 20);
        }
    }

    @ParametersAreNonnullByDefault
    private void updateProgress(UUID uuid, int speed, int progress, Location source, Location destination, boolean resistance) {
        Player p = Bukkit.getPlayer(uuid);

        if (isValid(p, source)) {
            if (progress > 99) {
                p.sendTitle(ChatColors.color(HoneymodPlugin.getLocalization().getMessage(p, "machines.TELEPORTER.teleported")), ChatColors.color("&b100%"), 20, 60, 20);
                PaperLib.teleportAsync(p, destination).thenAccept(success -> onTeleport(p, destination, success, resistance));
            } else {
                p.sendTitle(ChatColors.color(HoneymodPlugin.getLocalization().getMessage(p, "machines.TELEPORTER.teleporting")), ChatColors.color("&b" + progress + "%"), 0, 60, 0);

                source.getWorld().spawnParticle(Particle.PORTAL, source, progress * 2, 0.2F, 0.8F, 0.2F);
                source.getWorld().playSound(source, Sound.BLOCK_BEACON_AMBIENT, 1F, 0.6F);

                HoneymodPlugin.runSync(() -> updateProgress(uuid, speed, progress + speed, source, destination, resistance), 10L);
            }
        } else {
            cancel(uuid, p);
        }
    }

    @ParametersAreNonnullByDefault
    private void onTeleport(Player p, Location destination, boolean success, boolean resistance) {
        /*
         * This needs to run on the main Thread so we force it, as
         * the async teleportation might happen on a separate Thread.
         */
        HoneymodPlugin.runSync(() -> {
            if (success) {
                // Apply Resistance Effect, if enabled
                if (resistance) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 20));
                    HoneymodPlugin.getLocalization().sendMessage(p, "machines.TELEPORTER.invulnerability");
                }

                // Spawn some particles for aesthetic reasons.
                Location loc = new Location(destination.getWorld(), destination.getX(), destination.getY() + 1, destination.getZ());
                destination.getWorld().spawnParticle(Particle.PORTAL, loc, 200, 0.2F, 0.8F, 0.2F);
                destination.getWorld().playSound(destination, Sound.BLOCK_BEACON_ACTIVATE, 1F, 1F);
                teleporterUsers.remove(p.getUniqueId());
            } else {
                /*
                 * Make sure the Player is removed from the actively teleporting
                 * users and notified about the failed teleportation
                 */
                cancel(p.getUniqueId(), p);
            }
        });
    }

}