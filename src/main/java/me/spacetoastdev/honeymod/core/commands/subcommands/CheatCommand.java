package me.spacetoastdev.honeymod.core.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

class CheatCommand extends SubCommand {

    CheatCommand(HoneymodPlugin plugin, HoneymodCommand cmd) {
        super(plugin, cmd, "cheat", false);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("slimefun.cheat.items")) {
                HoneymodGuide.openCheatMenu((Player) sender);
            } else {
                HoneymodPlugin.getLocalization().sendMessage(sender, "messages.no-permission", true);
            }
        } else {
            HoneymodPlugin.getLocalization().sendMessage(sender, "messages.only-players", true);
        }
    }

}
