package me.spacetoastdev.honeymod.integrations;

import java.util.Iterator;

import javax.annotation.Nonnull;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.minebuilders.clearlag.Clearlag;
import me.minebuilders.clearlag.events.EntityRemoveEvent;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

/**
 * This handles all integrations with {@link Clearlag}.
 * We don't want it to clear our altar items.
 * 
 * @author TheBusyBiscuit
 *
 */
class ClearLagIntegration implements Listener {

    private final HoneymodPlugin plugin;

    ClearLagIntegration(@Nonnull HoneymodPlugin plugin) {
        this.plugin = plugin;
    }

    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityRemove(EntityRemoveEvent e) {
        Iterator<Entity> iterator = e.getEntityList().iterator();

        while (iterator.hasNext()) {
            Entity n = iterator.next();

            if (n instanceof Item && HoneymodUtils.hasNoPickupFlag((Item) n)) {
                iterator.remove();
            }
        }
    }
}
