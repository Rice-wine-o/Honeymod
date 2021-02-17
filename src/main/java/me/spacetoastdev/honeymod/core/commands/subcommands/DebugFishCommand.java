package me.spacetoastdev.honeymod.core.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

class DebugFishCommand extends SubCommand {

    DebugFishCommand(HoneymodPlugin plugin, HoneymodCommand cmd) {
        super(plugin, cmd, "debug_fish", true);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (sender instanceof Player && sender.hasPermission("slimefun.debugging")) {
            ((Player) sender).getInventory().addItem(HoneymodItems.DEBUG_FISH.clone());
        } else {
            HoneymodPlugin.getLocalization().sendMessage(sender, "messages.no-permission", true);
        }
    }

}
