package me.spacetoastdev.honeymod.implementation.listeners;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

/**
 * This {@link Listener} listens to the {@link EntityDeathEvent} to automatically
 * create a waypoint for a {@link Player} who carries an Emergency Transmitter.
 * 
 * @author TheBusyBiscuit
 *
 */
public class DeathpointListener implements Listener {

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("(MMM dd, yyyy @ hh:mm)", Locale.ROOT);

    public DeathpointListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        if (HoneymodUtils.containsSimilarItem(p.getInventory(), HoneymodItems.GPS_EMERGENCY_TRANSMITTER, true)) {
            HoneymodPlugin.getGPSNetwork().addWaypoint(p, "player:death " + HoneymodPlugin.getLocalization().getMessage(p, "gps.deathpoint").replace("%date%", format.format(LocalDateTime.now())), p.getLocation().getBlock().getLocation());
        }
    }
}
