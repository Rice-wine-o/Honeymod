package me.spacetoastdev.honeymod.integrations;

import javax.annotation.Nonnull;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.events.skills.salvage.McMMOPlayerSalvageCheckEvent;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.api.events.BlockPlacerPlaceEvent;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.VanillaItem;

/**
 * This handles all integrations with {@link mcMMO}.
 * 
 * @author TheBusyBiscuit
 *
 */
class McMMOIntegration implements Listener {

    private final HoneymodPlugin plugin;

    McMMOIntegration(@Nonnull HoneymodPlugin plugin) {
        this.plugin = plugin;
    }

    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlacerPlace(BlockPlacerPlaceEvent e) {
        // This registers blocks placed by the BlockPlacer as "player-placed"
        mcMMO.getPlaceStore().setTrue(e.getBlock());
    }

    @EventHandler(ignoreCancelled = true)
    public void onItemSalvage(McMMOPlayerSalvageCheckEvent e) {
        // Prevent Slimefun items from being salvaged
        if (!isSalvageable(e.getSalvageItem())) {
            e.setCancelled(true);
            HoneymodPlugin.getLocalization().sendMessage(e.getPlayer(), "anvil.mcmmo-salvaging");
        }
    }

    /**
     * This method checks if an {@link ItemStack} can be salvaged or not.
     * We basically don't want players to salvage any {@link SlimefunItem} unless
     * it is a {@link VanillaItem}.
     * 
     * @param item
     *            The {@link ItemStack} to check
     * 
     * @return Whether this item can be safely salvaged
     */
    private boolean isSalvageable(@Nonnull ItemStack item) {
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        return sfItem == null || sfItem instanceof VanillaItem;
    }

}
