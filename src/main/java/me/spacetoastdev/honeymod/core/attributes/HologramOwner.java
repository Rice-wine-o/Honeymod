package me.spacetoastdev.honeymod.core.attributes;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import me.spacetoastdev.honeymod.core.services.holograms.HologramsService;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.blocks.HologramProjector;

/**
 * This {@link ItemAttribute} manages holograms.
 * 
 * @author TheBusyBiscuit
 * 
 * @see HologramProjector
 * @see HologramsService
 *
 */
public interface HologramOwner extends ItemAttribute {

    /**
     * This will update the hologram text for the given {@link Block}.
     * 
     * @param b
     *            The {@link Block} to which the hologram belongs
     * 
     * @param text
     *            The nametag for the hologram
     */
    default void updateHologram(@Nonnull Block b, @Nonnull String text) {
        Location loc = b.getLocation().add(getHologramOffset(b));
        HoneymodPlugin.getHologramsService().setHologramLabel(loc, ChatColors.color(text));
    }

    /**
     * This will remove the hologram for the given {@link Block}.
     * 
     * @param b
     *            The {@link Block} to which the hologram blocks
     */
    default void removeHologram(@Nonnull Block b) {
        Location loc = b.getLocation().add(getHologramOffset(b));
        HoneymodPlugin.getHologramsService().removeHologram(loc);
    }

    /**
     * This returns the offset of the hologram as a {@link Vector}.
     * This offset is applied to {@link Block#getLocation()} when spawning
     * the hologram.
     * 
     * @param block
     *            The {@link Block} which serves as the origin point
     * 
     * @return The hologram offset
     */
    @Nonnull
    default Vector getHologramOffset(@Nonnull Block block) {
        return HoneymodPlugin.getHologramsService().getDefaultOffset();
    }

}
