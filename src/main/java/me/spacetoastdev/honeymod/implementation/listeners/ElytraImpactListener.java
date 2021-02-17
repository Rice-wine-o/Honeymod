package me.spacetoastdev.honeymod.implementation.listeners;

import java.util.Optional;

import javax.annotation.Nonnull;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.core.attributes.DamageableItem;
import me.spacetoastdev.honeymod.core.attributes.ProtectionType;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.armor.ElytraCap;
import me.spacetoastdev.honeymod.implementation.items.armor.HoneymodArmorPiece;

/**
 * The {@link Listener} for the {@link ElytraCap}.
 *
 * @author Seggan
 * 
 * @see ElytraCap
 */
public class ElytraImpactListener implements Listener {

    public ElytraImpactListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerCrash(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            // We only wanna handle damaged Players
            return;
        }

        if (e.getCause() == DamageCause.FALL || e.getCause() == DamageCause.FLY_INTO_WALL) {
            Player p = (Player) e.getEntity();

            if (p.isGliding()) {
                Optional<PlayerProfile> optional = PlayerProfile.find(p);

                if (!optional.isPresent()) {
                    PlayerProfile.request(p);
                    return;
                }

                PlayerProfile profile = optional.get();
                Optional<HoneymodArmorPiece> helmet = profile.getArmor()[0].getItem();

                if (helmet.isPresent()) {
                    SlimefunItem item = helmet.get();

                    if (item.canUse(p, true) && profile.hasFullProtectionAgainst(ProtectionType.FLYING_INTO_WALL)) {
                        e.setDamage(0);
                        p.playSound(p.getLocation(), Sound.BLOCK_STONE_HIT, 20, 1);

                        if (item instanceof DamageableItem) {
                            ((DamageableItem) item).damageItem(p, p.getInventory().getHelmet());
                        }
                    }
                }
            }
        }
    }
}
