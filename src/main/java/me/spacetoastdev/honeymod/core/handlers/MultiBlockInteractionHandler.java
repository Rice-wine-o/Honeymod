package me.spacetoastdev.honeymod.core.handlers;

import java.util.Optional;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import me.spacetoastdev.honeymod.api.exceptions.IncompatibleItemHandlerException;
import me.spacetoastdev.honeymod.core.multiblocks.MultiBlock;
import me.spacetoastdev.honeymod.core.multiblocks.MultiBlockMachine;

/**
 * This {@link ItemHandler} is called whenever a {@link Player} interacts with
 * this {@link MultiBlock}.
 * Note that this {@link MultiBlockInteractionHandler} should be assigned to
 * a class that inherits from {@link MultiBlockMachine}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see ItemHandler
 * @see MultiBlock
 * @see MultiBlockMachine
 *
 */
@FunctionalInterface
public interface MultiBlockInteractionHandler extends ItemHandler {

    boolean onInteract(Player p, MultiBlock mb, Block b);

    @Override
    default Optional<IncompatibleItemHandlerException> validate(SlimefunItem item) {
        if (!(item instanceof MultiBlockMachine)) {
            return Optional.of(new IncompatibleItemHandlerException("Only classes inheriting 'MultiBlockMachine' can have a MultiBlockInteractionHandler", item, this));
        }

        return Optional.empty();
    }

    @Override
    default Class<? extends ItemHandler> getIdentifier() {
        return MultiBlockInteractionHandler.class;
    }
}
