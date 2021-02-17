package me.spacetoastdev.honeymod.implementation.listeners.crafting;

import javax.annotation.Nonnull;

import org.bukkit.block.BrewingStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This {@link Listener} prevents any {@link SlimefunItem} from being used in a
 * brewing stand.
 * 
 * @author VoidAngel
 * @author SoSeDiK
 * @author CURVX
 *
 */
public class BrewingStandListener implements HoneymodCraftingListener {

    public BrewingStandListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPreBrew(InventoryClickEvent e) {
        Inventory clickedInventory = e.getClickedInventory();
        Inventory topInventory = e.getView().getTopInventory();

        if (clickedInventory != null && topInventory.getType() == InventoryType.BREWING && topInventory.getHolder() instanceof BrewingStand) {
            if (e.getAction() == InventoryAction.HOTBAR_SWAP) {
                e.setCancelled(true);
                return;
            }

            if (clickedInventory.getType() == InventoryType.BREWING) {
                e.setCancelled(isUnallowed(SlimefunItem.getByItem(e.getCursor())));
            } else {
                e.setCancelled(isUnallowed(SlimefunItem.getByItem(e.getCurrentItem())));
            }

            if (e.getResult() == Result.DENY) {
                HoneymodPlugin.getLocalization().sendMessage((Player) e.getWhoClicked(), "brewing_stand.not-working", true);
            }
        }
    }

    @EventHandler
    public void hopperOnBrew(InventoryMoveItemEvent e) {
        if (e.getDestination().getType() == InventoryType.BREWING && isUnallowed(e.getItem())) {
            e.setCancelled(true);
        }
    }
}
