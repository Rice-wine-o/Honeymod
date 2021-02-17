package me.spacetoastdev.honeymod.core.handlers;

import java.util.Optional;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import me.spacetoastdev.honeymod.api.events.PlayerRightClickEvent;
import me.spacetoastdev.honeymod.api.exceptions.IncompatibleItemHandlerException;
import me.spacetoastdev.honeymod.core.attributes.NotPlaceable;

@FunctionalInterface
public interface BlockUseHandler extends ItemHandler {

    void onRightClick(PlayerRightClickEvent e);

    @Override
    default Optional<IncompatibleItemHandlerException> validate(SlimefunItem item) {
        if (item instanceof NotPlaceable || !item.getItem().getType().isBlock()) {
            return Optional.of(new IncompatibleItemHandlerException("Only blocks that are not marked as 'NotPlaceable' can have a BlockUseHandler.", item, this));
        }

        return Optional.empty();
    }

    @Override
    default Class<? extends ItemHandler> getIdentifier() {
        return BlockUseHandler.class;
    }

}
