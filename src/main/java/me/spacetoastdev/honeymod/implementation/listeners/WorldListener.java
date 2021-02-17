package me.spacetoastdev.honeymod.implementation.listeners;

import java.util.logging.Level;

import javax.annotation.Nonnull;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

public class WorldListener implements Listener {

    public WorldListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        HoneymodPlugin.getWorldSettingsService().load(e.getWorld());
        BlockStorage.getOrCreate(e.getWorld());
    }

    @EventHandler
    public void onWorldUnload(WorldUnloadEvent e) {
        BlockStorage storage = BlockStorage.getStorage(e.getWorld());

        if (storage != null) {
            storage.saveAndRemove();
        } else {
            HoneymodPlugin.logger().log(Level.SEVERE, "Could not save Honeymod Blocks for World \"{0}\"", e.getWorld().getName());
        }
    }

}
