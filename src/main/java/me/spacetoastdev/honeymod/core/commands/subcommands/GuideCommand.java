package me.spacetoastdev.honeymod.core.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideMode;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

class GuideCommand extends SubCommand {

    GuideCommand(HoneymodPlugin plugin, HoneymodCommand cmd) {
        super(plugin, cmd, "guide", false);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("slimefun.command.guide")) {
                HoneymodGuideMode design = HoneymodGuide.getDefaultMode();
                ((Player) sender).getInventory().addItem(HoneymodGuide.getItem(design).clone());
            } else {
                HoneymodPlugin.getLocalization().sendMessage(sender, "messages.no-permission", true);
            }
        } else {
            HoneymodPlugin.getLocalization().sendMessage(sender, "messages.only-players", true);
        }
    }

}
