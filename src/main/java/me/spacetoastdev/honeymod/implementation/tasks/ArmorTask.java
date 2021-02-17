package me.spacetoastdev.honeymod.implementation.tasks;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.items.HashedArmorpiece;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.core.attributes.ProtectionType;
import me.spacetoastdev.honeymod.core.attributes.Radioactive;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.armor.HoneymodArmorPiece;
import me.spacetoastdev.honeymod.implementation.items.electric.gadgets.SolarHelmet;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;
import me.spacetoastdev.honeymod.utils.itemstack.ItemStackWrapper;

/**
 * The {@link ArmorTask} is responsible for handling {@link PotionEffect PotionEffects} for
 * {@link Radioactive} items or any {@link HoneymodArmorPiece}.
 * It also handles the prevention of radiation through a Hazmat Suit
 * 
 * @author TheBusyBiscuit
 *
 */
public class ArmorTask implements Runnable {

    private final Set<PotionEffect> radiationEffects;
    private final boolean radioactiveFire;

    /**
     * This creates a new {@link ArmorTask}.
     * 
     * @param radioactiveFire
     *            Whether radiation also causes a {@link Player} to burn
     */
    public ArmorTask(boolean radioactiveFire) {
        this.radioactiveFire = radioactiveFire;

        Set<PotionEffect> effects = new HashSet<>();
        effects.add(new PotionEffect(PotionEffectType.WITHER, 400, 2));
        effects.add(new PotionEffect(PotionEffectType.BLINDNESS, 400, 3));
        effects.add(new PotionEffect(PotionEffectType.CONFUSION, 400, 3));
        effects.add(new PotionEffect(PotionEffectType.WEAKNESS, 400, 2));
        effects.add(new PotionEffect(PotionEffectType.SLOW, 400, 1));
        effects.add(new PotionEffect(PotionEffectType.SLOW_DIGGING, 400, 1));
        radiationEffects = Collections.unmodifiableSet(effects);
    }

    /**
     * This returns a {@link Set} of {@link PotionEffect PotionEffects} which get applied to
     * a {@link Player} when they are exposed to deadly radiation.
     * 
     * @return The {@link Set} of {@link PotionEffect PotionEffects} applied upon radioactive contact
     */
    @Nonnull
    public Set<PotionEffect> getRadiationEffects() {
        return radiationEffects;
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.isValid() || p.isDead()) {
                continue;
            }

            PlayerProfile.get(p, profile -> {
                ItemStack[] armor = p.getInventory().getArmorContents();
                HashedArmorpiece[] cachedArmor = profile.getArmor();

                handleSlimefunArmor(p, armor, cachedArmor);

                if (hasSunlight(p)) {
                    checkForSolarHelmet(p);
                }

                checkForRadiation(p, profile);
            });
        }
    }

    @ParametersAreNonnullByDefault
    private void handleSlimefunArmor(Player p, ItemStack[] armor, HashedArmorpiece[] cachedArmor) {
        for (int slot = 0; slot < 4; slot++) {
            ItemStack item = armor[slot];
            HashedArmorpiece armorpiece = cachedArmor[slot];

            if (armorpiece.hasDiverged(item)) {
                SlimefunItem sfItem = SlimefunItem.getByItem(item);

                if (!(sfItem instanceof HoneymodArmorPiece)) {
                    // If it isn't actually Armor, then we won't care about it.
                    sfItem = null;
                }

                armorpiece.update(item, sfItem);
            }

            if (item != null && armorpiece.getItem().isPresent()) {
                HoneymodPlugin.runSync(() -> {
                    HoneymodArmorPiece slimefunArmor = armorpiece.getItem().get();

                    if (slimefunArmor.canUse(p, true)) {
                        for (PotionEffect effect : slimefunArmor.getPotionEffects()) {
                            p.removePotionEffect(effect.getType());
                            p.addPotionEffect(effect);
                        }
                    }
                });
            }
        }
    }

    private void checkForSolarHelmet(@Nonnull Player p) {
        ItemStack helmet = p.getInventory().getHelmet();

        if (HoneymodPlugin.getRegistry().isBackwardsCompatible() && !HoneymodUtils.isItemSimilar(helmet, HoneymodItems.SOLAR_HELMET, true, false)) {
            // Performance saver for slow backwards-compatible versions of Slimefun
            return;
        }

        SlimefunItem item = SlimefunItem.getByItem(helmet);

        if (item instanceof SolarHelmet && item.canUse(p, true)) {
            ((SolarHelmet) item).rechargeItems(p);
        }
    }

    private boolean hasSunlight(@Nonnull Player p) {
        World world = p.getWorld();

        if (world.getEnvironment() != Environment.NORMAL) {
            // The End and Nether have no sunlight
            return false;
        }

        return (world.getTime() < 12300 || world.getTime() > 23850) && p.getEyeLocation().getBlock().getLightFromSky() == 15;
    }

    private void checkForRadiation(@Nonnull Player p, @Nonnull PlayerProfile profile) {
        if (!profile.hasFullProtectionAgainst(ProtectionType.RADIATION)) {
            for (ItemStack item : p.getInventory()) {
                if (checkAndApplyRadiation(p, item)) {
                    break;
                }
            }
        }
    }

    private boolean checkAndApplyRadiation(@Nonnull Player p, @Nullable ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return false;
        }

        Set<SlimefunItem> radioactiveItems = HoneymodPlugin.getRegistry().getRadioactiveItems();
        ItemStack subject = item;

        if (!(item instanceof SlimefunItemStack) && radioactiveItems.size() > 1) {
            // Performance optimization to reduce ItemMeta calls
            subject = new ItemStackWrapper(item);
        }

        for (SlimefunItem radioactiveItem : radioactiveItems) {
            if (radioactiveItem.isItem(subject) && Slimefun.isEnabled(p, radioactiveItem, true)) {
                // If the item is enabled in the world, then make radioactivity do its job
                HoneymodPlugin.getLocalization().sendMessage(p, "messages.radiation");

                HoneymodPlugin.runSync(() -> {
                    p.addPotionEffects(radiationEffects);

                    // if radiative fire is enabled
                    if (radioactiveFire) {
                        p.setFireTicks(400);
                    }
                });

                return true;
            }
        }

        return false;
    }
}
