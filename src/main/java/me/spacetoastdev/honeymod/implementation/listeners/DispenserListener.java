package me.spacetoastdev.honeymod.implementation.listeners;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

import io.papermc.lib.PaperLib;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.spacetoastdev.honeymod.core.handlers.BlockDispenseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This {@link Listener} listens to the {@link BlockDispenseEvent} and calls the
 * {@link BlockDispenseHandler} as a result of that.
 * 
 * @author TheBusyBiscuit
 * @author MisterErwin
 * 
 * @see BlockDispenseHandler
 *
 */
public class DispenserListener implements Listener {

    public DispenserListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockDispensing(BlockDispenseEvent e) {
        Block b = e.getBlock();

        if (b.getType() == Material.DISPENSER && b.getRelative(BlockFace.DOWN).getType() != Material.HOPPER) {
            SlimefunItem machine = BlockStorage.check(b);

            if (machine != null) {
                machine.callItemHandler(BlockDispenseHandler.class, handler -> {
                    BlockState state = PaperLib.getBlockState(b, false).getState();

                    if (state instanceof Dispenser) {
                        Dispenser dispenser = (Dispenser) state;
                        BlockFace face = ((Directional) b.getBlockData()).getFacing();
                        Block block = b.getRelative(face);
                        handler.onBlockDispense(e, dispenser, block, machine);
                    }
                });
            }
        }
    }
}
