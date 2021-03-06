package me.spacetoastdev.honeymod.implementation.items.cargo;

import javax.annotation.Nonnull;

import org.bukkit.block.Block;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

/**
 * This interface marks a {@link SlimefunItem} as a {@link CargoNode}.
 * <p>
 * Do not implement this interface yourself, it will not have any effect.
 * 
 * @author TheBusyBiscuit
 *
 */
@FunctionalInterface
public interface CargoNode {

    /**
     * This returns the selected channel for the given {@link Block}.
     * 
     * @param b
     *            The {@link Block}
     * 
     * @return The channel which this {@link CargoNode} is currently on
     */
    int getSelectedChannel(@Nonnull Block b);

}
