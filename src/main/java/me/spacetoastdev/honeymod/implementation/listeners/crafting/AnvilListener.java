package me.spacetoastdev.honeymod.implementation.listeners.crafting;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This {@link Listener} prevents any {@link SlimefunItem} from being used in an
 * anvil.
 * 
 * @author TheBusyBiscuit
 *
 */
public class AnvilListener implements HoneymodCraftingListener {

    public AnvilListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onAnvil(InventoryClickEvent e) {
        if (e.getRawSlot() == 2 && e.getInventory().getType() == InventoryType.ANVIL && e.getWhoClicked() instanceof Player) {
            ItemStack item1 = e.getInventory().getContents()[0];
            ItemStack item2 = e.getInventory().getContents()[1];

            if (hasUnallowedItems(item1, item2)) {
                e.setResult(Result.DENY);
                HoneymodPlugin.getLocalization().sendMessage((Player) e.getWhoClicked(), "anvil.not-working", true);
            }
        }
    }

}
