package me.spacetoastdev.honeymod.implementation.listeners;

import javax.annotation.Nonnull;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.spacetoastdev.honeymod.api.network.Network;
import me.spacetoastdev.honeymod.core.networks.NetworkManager;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This {@link Listener} is responsible for all updates to a {@link Network}.
 * 
 * @author meiamsome
 * 
 * @see Network
 * @see NetworkManager
 *
 */
public class NetworkListener implements Listener {

    private final NetworkManager manager;

    public NetworkListener(@Nonnull HoneymodPlugin plugin, @Nonnull NetworkManager manager) {
        this.manager = manager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        manager.updateAllNetworks(e.getBlock().getLocation());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent e) {
        manager.updateAllNetworks(e.getBlock().getLocation());
    }
}
