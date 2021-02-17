package me.spacetoastdev.honeymod.implementation.listeners;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.spacetoastdev.honeymod.core.attributes.NotHopperable;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;

/**
 * This {@link Listener} prevents item from being transferred to
 * and from {@link AContainer} using a hopper.
 *
 * @author CURVX
 *
 * @see NotHopperable
 *
 */
public class HopperListener implements Listener {

    public HopperListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onHopperInsert(InventoryMoveItemEvent e) {
        Location loc = e.getDestination().getLocation();
        if (e.getSource().getType() == InventoryType.HOPPER && loc != null && BlockStorage.check(loc.getBlock()) instanceof NotHopperable) {
            e.setCancelled(true);
        }
    }
}
