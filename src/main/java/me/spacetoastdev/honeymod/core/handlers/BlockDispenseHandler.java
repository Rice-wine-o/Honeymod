package me.spacetoastdev.honeymod.core.handlers;

import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.event.block.BlockDispenseEvent;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import me.spacetoastdev.honeymod.api.exceptions.IncompatibleItemHandlerException;
import me.spacetoastdev.honeymod.core.attributes.NotPlaceable;
import me.spacetoastdev.honeymod.implementation.items.blocks.BlockPlacer;

/**
 * This {@link ItemHandler} is triggered when the {@link SlimefunItem} it was assigned to
 * is a {@link Dispenser} and was triggered.
 * 
 * This {@link ItemHandler} is used for the {@link BlockPlacer}.
 * 
 * @author TheBusyBiscuit
 *
 * @see ItemHandler
 * @see BlockPlacer
 * 
 */
@FunctionalInterface
public interface BlockDispenseHandler extends ItemHandler {

    @Override
    default Optional<IncompatibleItemHandlerException> validate(SlimefunItem item) {
        if (item instanceof NotPlaceable || item.getItem().getType() != Material.DISPENSER) {
            return Optional.of(new IncompatibleItemHandlerException("Only dispensers that are not marked as 'NotPlaceable' can have a BlockDispenseHandler.", item, this));
        }

        return Optional.empty();
    }

    void onBlockDispense(BlockDispenseEvent e, Dispenser dispenser, Block facedBlock, SlimefunItem machine);

    @Override
    default Class<? extends ItemHandler> getIdentifier() {
        return BlockDispenseHandler.class;
    }
}
