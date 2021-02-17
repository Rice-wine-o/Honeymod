package me.spacetoastdev.honeymod.implementation.listeners;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;

import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.weapons.VampireBlade;

/**
 * This {@link Listener} is exclusively used for the {@link VampireBlade}.
 * It handles the {@link PotionEffect}
 * 
 * @author TheBusyBiscuit
 * 
 * @see VampireBlade
 *
 */
public class VampireBladeListener implements Listener {

    private final VampireBlade blade;

    public VampireBladeListener(@Nonnull HoneymodPlugin plugin, @Nonnull VampireBlade blade) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.blade = blade;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (blade.isDisabled()) {
            return;
        }

        if (e.getDamager() instanceof Player && ThreadLocalRandom.current().nextInt(100) < blade.getChance()) {
            Player p = (Player) e.getDamager();

            if (blade.isItem(p.getInventory().getItemInMainHand())) {
                if (blade.canUse(p, true)) {
                    blade.heal(p);
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

}
