package me.spacetoastdev.honeymod.implementation.listeners;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.armor.Parachute;
import me.spacetoastdev.honeymod.implementation.items.electric.gadgets.JetBoots;
import me.spacetoastdev.honeymod.implementation.items.electric.gadgets.Jetpack;
import me.spacetoastdev.honeymod.implementation.items.magical.InfusedMagnet;
import me.spacetoastdev.honeymod.implementation.tasks.InfusedMagnetTask;
import me.spacetoastdev.honeymod.implementation.tasks.JetBootsTask;
import me.spacetoastdev.honeymod.implementation.tasks.JetpackTask;
import me.spacetoastdev.honeymod.implementation.tasks.ParachuteTask;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

/**
 * This {@link Listener} is responsible for listening to the {@link PlayerToggleSneakEvent}
 * to start tasks for various gadgets that are activated by pressing shift,
 * like the {@link Jetpack} or {@link JetBoots}
 * 
 * @author TheBusyBiscuit
 * 
 * @see JetpackTask
 * @see JetBootsTask
 * @see ParachuteTask
 * @see InfusedMagnetTask
 *
 */
public class GadgetsListener implements Listener {

    public GadgetsListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onToggleSneak(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {
            Player p = e.getPlayer();

            if (p.getInventory().getChestplate() != null) {
                SlimefunItem chestplate = SlimefunItem.getByItem(p.getInventory().getChestplate());
                handleChestplate(p, chestplate);
            }

            if (p.getInventory().getBoots() != null) {
                SlimefunItem boots = SlimefunItem.getByItem(p.getInventory().getBoots());
                handleBoots(p, boots);
            }

            if (HoneymodUtils.containsSimilarItem(p.getInventory(), HoneymodItems.INFUSED_MAGNET, true)) {
                InfusedMagnet magnet = (InfusedMagnet) HoneymodItems.INFUSED_MAGNET.getItem();

                if (magnet.canUse(p, true)) {
                    new InfusedMagnetTask(p, magnet.getRadius()).scheduleRepeating(0, 8);
                }
            }
        }
    }

    private void handleChestplate(@Nonnull Player p, @Nullable SlimefunItem chestplate) {
        if (chestplate == null || !chestplate.canUse(p, true)) {
            return;
        }

        if (chestplate instanceof Jetpack) {
            double thrust = ((Jetpack) chestplate).getThrust();

            if (thrust > 0.2) {
                new JetpackTask(p, (Jetpack) chestplate).scheduleRepeating(0, 3);
            }
        } else if (chestplate instanceof Parachute) {
            new ParachuteTask(p).scheduleRepeating(0, 3);
        }
    }

    private void handleBoots(@Nonnull Player p, @Nullable SlimefunItem boots) {
        if (boots instanceof JetBoots && boots.canUse(p, true)) {
            double speed = ((JetBoots) boots).getSpeed();

            if (speed > 0.2) {
                new JetBootsTask(p, (JetBoots) boots).scheduleRepeating(0, 2);
            }
        }
    }
}
