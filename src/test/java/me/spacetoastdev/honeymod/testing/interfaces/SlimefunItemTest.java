package me.spacetoastdev.honeymod.testing.interfaces;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import be.seeseemelk.mockbukkit.block.BlockMock;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.api.events.PlayerRightClickEvent;
import me.spacetoastdev.honeymod.core.handlers.ItemConsumptionHandler;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This is a convenient interface for us to use in unit test classes
 * that test the functionality of a particular {@link SlimefunItem}.
 * 
 * @author TheBusyBiscuit
 *
 * @param <T>
 *            The class type of {@link SlimefunItem} you want to test
 */
@FunctionalInterface
public interface SlimefunItemTest<T extends SlimefunItem> {

    /**
     * This method should construct a new {@link SlimefunItem} of type T.
     * 
     * 
     * @param plugin
     *            The current instance of {@link HoneymodPlugin}
     * @param id
     *            An id for this {@link SlimefunItem}
     * 
     * @return A newly constructed {@link SlimefunItem}
     */
    T registerSlimefunItem(HoneymodPlugin plugin, String id);

    @ParametersAreNonnullByDefault
    default void simulateRightClick(Player player, T item) {
        PlayerInteractEvent e = new PlayerInteractEvent(player, Action.RIGHT_CLICK_AIR, item.getItem().clone(), null, null, EquipmentSlot.HAND);
        PlayerRightClickEvent event = new PlayerRightClickEvent(e);
        item.callItemHandler(ItemUseHandler.class, handler -> handler.onRightClick(event));
    }

    @ParametersAreNonnullByDefault
    default void simulateRightClickBlock(Player player, T item, ItemStack heldItem, BlockMock block, BlockFace face) {
        PlayerInteractEvent e = new PlayerInteractEvent(player, Action.RIGHT_CLICK_BLOCK, heldItem, block, face, EquipmentSlot.HAND);
        PlayerRightClickEvent event = new PlayerRightClickEvent(e);
        item.callItemHandler(ItemUseHandler.class, handler -> handler.onRightClick(event));
    }

    @ParametersAreNonnullByDefault
    default void simulateConsumption(Player player, T item) {
        PlayerItemConsumeEvent event = new PlayerItemConsumeEvent(player, item.getItem().clone());
        item.callItemHandler(ItemConsumptionHandler.class, handler -> handler.onConsume(event, player, event.getItem()));
    }

}
