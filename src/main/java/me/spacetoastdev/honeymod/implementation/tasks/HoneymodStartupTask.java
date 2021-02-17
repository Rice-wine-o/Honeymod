package me.spacetoastdev.honeymod.implementation.tasks;

import java.util.logging.Level;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.World;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.listeners.TeleporterListener;
import me.spacetoastdev.honeymod.implementation.setup.PostSetup;

/**
 * This Task initializes all items, some listeners and various other stuff.
 * This has been moved to its own class to make timings log easier to read, so
 * they say "HoneymodStartupTask" instead of "Slimefun:lambda:123456789".
 * 
 * @author TheBusyBiscuit
 *
 */
public class HoneymodStartupTask implements Runnable {

    private final HoneymodPlugin plugin;
    private final Runnable runnable;

    /**
     * This initializes our {@link HoneymodStartupTask} for the given {@link HoneymodPlugin}.
     * 
     * @param plugin
     *            The main instance of our {@link HoneymodPlugin}
     * @param runnable
     *            A {@link Runnable} containing additional operations that need to be run
     */
    public HoneymodStartupTask(@Nonnull HoneymodPlugin plugin, @Nonnull Runnable runnable) {
        this.plugin = plugin;
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();

        // Load all items
        PostSetup.loadItems();

        // Load all worlds
        HoneymodPlugin.getWorldSettingsService().load(Bukkit.getWorlds());

        for (World world : Bukkit.getWorlds()) {
            try {
                new BlockStorage(world);
            } catch (Exception x) {
                HoneymodPlugin.logger().log(Level.SEVERE, x, () -> "An Error occurred while trying to load World \"" + world.getName() + "\" for Slimefun v" + HoneymodPlugin.getVersion());
            }
        }

        // Only load this Listener if the corresponding items are enabled
        if (isEnabled("ELEVATOR_PLATE", "GPS_ACTIVATION_DEVICE_SHARED", "GPS_ACTIVATION_DEVICE_PERSONAL")) {
            new TeleporterListener(plugin);
        }
    }

    private boolean isEnabled(String... itemIds) {
        for (String id : itemIds) {
            SlimefunItem item = SlimefunItem.getByID(id);

            if (item != null && !item.isDisabled()) {
                return true;
            }
        }

        return false;
    }

}
