package me.spacetoastdev.honeymod.implementation.listeners.crafting;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.tags.HoneymodTag;

/**
 * This {@link Listener} prevents any {@link SlimefunItem} from being used in a
 * Cauldron.
 * This is mainly used to prevent the discoloring of leather armor.
 * 
 * @author TheBusyBiscuit
 *
 */
public class CauldronListener implements HoneymodCraftingListener {

    public CauldronListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onCauldronUse(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = e.getClickedBlock();

            if (block.getType() == Material.CAULDRON) {
                ItemStack item = e.getItem();

                if (item != null && HoneymodTag.LEATHER_ARMOR.isTagged(item.getType())) {
                    SlimefunItem sfItem = SlimefunItem.getByItem(item);

                    if (isUnallowed(sfItem)) {
                        e.setCancelled(true);
                        HoneymodPlugin.getLocalization().sendMessage(e.getPlayer(), "cauldron.no-discoloring");
                    }
                }
            }
        }
    }

}
