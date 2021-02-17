package me.spacetoastdev.honeymod.implementation.listeners;

import javax.annotation.Nonnull;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.cargo.CargoNode;

/**
 * This {@link Listener} is solely responsible for preventing Cargo Nodes from being placed
 * on the top or bottom of a block.
 * 
 * @author TheBusyBiscuit
 *
 */
public class CargoNodeListener implements Listener {

    public CargoNodeListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onCargoNodePlace(BlockPlaceEvent e) {
        Block b = e.getBlock();

        if ((b.getY() != e.getBlockAgainst().getY() || !e.getBlockReplacedState().getType().isAir()) && isCargoNode(e.getItemInHand())) {
            HoneymodPlugin.getLocalization().sendMessage(e.getPlayer(), "machines.CARGO_NODES.must-be-placed", true);
            e.setCancelled(true);
        }
    }

    private boolean isCargoNode(@Nonnull ItemStack item) {
        return SlimefunItem.getByItem(item) instanceof CargoNode;
    }
}
