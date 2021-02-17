package me.spacetoastdev.honeymod.implementation.listeners;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import me.spacetoastdev.honeymod.api.events.PlayerRightClickEvent;
import me.spacetoastdev.honeymod.api.events.HoneymodGuideOpenEvent;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideMode;
import me.spacetoastdev.honeymod.core.guide.options.HoneymodGuideSettings;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

public class HoneymodGuideListener implements Listener {

    private final boolean giveOnFirstJoin;

    public HoneymodGuideListener(@Nonnull HoneymodPlugin plugin, boolean giveOnFirstJoin) {
        this.giveOnFirstJoin = giveOnFirstJoin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (giveOnFirstJoin && !e.getPlayer().hasPlayedBefore()) {
            Player p = e.getPlayer();

            if (!HoneymodPlugin.getWorldSettingsService().isWorldEnabled(p.getWorld())) {
                return;
            }

            HoneymodGuideMode type = HoneymodGuide.getDefaultMode();
            p.getInventory().addItem(HoneymodGuide.getItem(type).clone());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerRightClickEvent e) {
        Player p = e.getPlayer();

        if (tryOpenGuide(p, e, HoneymodGuideMode.SURVIVAL_MODE) == Result.ALLOW) {
            if (p.isSneaking()) {
                HoneymodGuideSettings.openSettings(p, e.getItem());
            } else {
                openGuide(p, e, HoneymodGuideMode.SURVIVAL_MODE);
            }
        } else if (tryOpenGuide(p, e, HoneymodGuideMode.CHEAT_MODE) == Result.ALLOW) {
            if (p.isSneaking()) {
                HoneymodGuideSettings.openSettings(p, e.getItem());
            } else {
                /*
                 * We rather just run the command here, all
                 * necessary permission checks will be handled there.
                 */
                p.chat("/hm cheat");
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void openGuide(Player p, PlayerRightClickEvent e, HoneymodGuideMode layout) {
        HoneymodGuideOpenEvent event = new HoneymodGuideOpenEvent(p, e.getItem(), layout);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            e.cancel();
            HoneymodGuide.openGuide(p, event.getGuideLayout());
        }
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    private Result tryOpenGuide(Player p, PlayerRightClickEvent e, HoneymodGuideMode layout) {
        ItemStack item = e.getItem();
        if (HoneymodUtils.isItemSimilar(item, HoneymodGuide.getItem(layout), true, false)) {

            if (!HoneymodPlugin.getWorldSettingsService().isWorldEnabled(p.getWorld())) {
                HoneymodPlugin.getLocalization().sendMessage(p, "messages.disabled-item", true);
                return Result.DENY;
            }

            return Result.ALLOW;
        }

        return Result.DEFAULT;
    }

}
