package me.spacetoastdev.honeymod.core.handlers;

import org.bukkit.entity.Player;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import me.spacetoastdev.honeymod.api.events.PlayerRightClickEvent;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

/**
 * This {@link ItemHandler} is triggered when the {@link SlimefunItem} it was assigned to
 * is right-clicked.
 * 
 * @author TheBusyBiscuit
 *
 * @see ItemHandler
 * @see SimpleHoneymodItem
 * 
 */
@FunctionalInterface
public interface ItemUseHandler extends ItemHandler {

    /**
     * This function is triggered when a {@link Player} right clicks with the assigned {@link SlimefunItem}
     * in his hand.
     * 
     * @param e
     *            The {@link PlayerRightClickEvent} that was triggered
     */
    void onRightClick(PlayerRightClickEvent e);

    @Override
    default Class<? extends ItemHandler> getIdentifier() {
        return ItemUseHandler.class;
    }

}
