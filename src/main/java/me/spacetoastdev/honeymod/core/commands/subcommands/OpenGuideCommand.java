package me.spacetoastdev.honeymod.core.commands.subcommands;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideMode;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

class OpenGuideCommand extends SubCommand {

    OpenGuideCommand(HoneymodPlugin plugin, HoneymodCommand cmd) {
        super(plugin, cmd, "open_guide", false);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onExecute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("slimefun.command.open_guide")) {
                HoneymodGuide.openGuide((Player) sender, HoneymodGuideMode.SURVIVAL_MODE);
            } else {
                HoneymodPlugin.getLocalization().sendMessage(sender, "messages.no-permission", true);
            }
        } else {
            HoneymodPlugin.getLocalization().sendMessage(sender, "messages.only-players", true);
        }
    }

}
