package me.spacetoastdev.honeymod.core.commands.subcommands;

import org.bukkit.command.CommandSender;

import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

class HelpCommand extends SubCommand {

    HelpCommand(HoneymodPlugin plugin, HoneymodCommand cmd) {
        super(plugin, cmd, "help", false);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        cmd.sendHelp(sender);
    }

}
