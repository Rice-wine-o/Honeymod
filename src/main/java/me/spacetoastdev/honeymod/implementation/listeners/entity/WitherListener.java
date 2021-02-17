package me.spacetoastdev.honeymod.implementation.listeners.entity;

import javax.annotation.Nonnull;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.spacetoastdev.honeymod.core.attributes.WitherProof;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This {@link Listener} is responsible for implementing the functionality of blocks that
 * were marked as {@link WitherProof} to not be destroyed by a {@link Wither}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see WitherProof
 *
 */
public class WitherListener implements Listener {

    public WitherListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onWitherDestroy(EntityChangeBlockEvent e) {
        if (e.getEntity().getType() == EntityType.WITHER) {
            SlimefunItem item = BlockStorage.check(e.getBlock());

            // Hardened Glass is excluded from here
            if (item instanceof WitherProof && !item.getId().equals(HoneymodItems.HARDENED_GLASS.getItemId())) {
                e.setCancelled(true);
                ((WitherProof) item).onAttack(e.getBlock(), (Wither) e.getEntity());
            }
        }
    }

}
