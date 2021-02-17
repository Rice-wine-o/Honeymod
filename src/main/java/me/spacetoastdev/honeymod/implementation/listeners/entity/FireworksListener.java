package me.spacetoastdev.honeymod.implementation.listeners.entity;

import javax.annotation.Nonnull;

import org.bukkit.ChatColor;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import me.spacetoastdev.honeymod.core.researching.Research;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This {@link Listener} makes sure that any {@link Firework} caused by a {@link Player}
 * unlocking a {@link Research} does not cause damage to be dealt.
 * 
 * @author TheBusyBiscuit
 *
 */
public class FireworksListener implements Listener {

    public FireworksListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onResearchFireworkDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Firework) {
            Firework firework = (Firework) e.getDamager();
            FireworkMeta meta = firework.getFireworkMeta();

            /**
             * We could use Peristent Data for this in the future, but ItemMeta display names
             * work pretty reliably too and they don't cause any memory leaks like metadata.
             * 
             * Entity display names do not work either as Firework cannot be named.
             */
            if (meta.hasDisplayName() && meta.getDisplayName().equals(ChatColor.GREEN + "Honeymod Research")) {
                e.setCancelled(true);
            }
        }
    }

}
