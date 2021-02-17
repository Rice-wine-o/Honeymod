package me.spacetoastdev.honeymod.implementation.listeners.crafting;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This {@link Listener} prevents any {@link SlimefunItem} from being used in a
 * crafting table.
 * 
 * @author TheBusyBiscuit
 *
 */
public class CraftingTableListener implements HoneymodCraftingListener {

    public CraftingTableListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        for (ItemStack item : e.getInventory().getContents()) {
            SlimefunItem sfItem = SlimefunItem.getByItem(item);

            if (sfItem != null && !sfItem.isUseableInWorkbench()) {
                e.setResult(Result.DENY);
                HoneymodPlugin.getLocalization().sendMessage((Player) e.getWhoClicked(), "workbench.not-enhanced", true);
                break;
            }
        }
    }

    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent e) {
        if (e.getInventory().getResult() != null) {
            for (ItemStack item : e.getInventory().getContents()) {
                SlimefunItem sfItem = SlimefunItem.getByItem(item);

                if (sfItem != null && !sfItem.isUseableInWorkbench()) {
                    e.getInventory().setResult(null);
                    break;
                }
            }
        }
    }

}
