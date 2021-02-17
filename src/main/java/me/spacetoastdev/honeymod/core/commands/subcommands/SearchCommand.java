package me.spacetoastdev.honeymod.core.commands.subcommands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideMode;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

class SearchCommand extends SubCommand {

    SearchCommand(HoneymodPlugin plugin, HoneymodCommand cmd) {
        super(plugin, cmd, "search", false);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("slimefun.command.search")) {
                if (args.length > 1) {
                    String query = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    PlayerProfile.get((Player) sender, profile -> HoneymodGuide.openSearch(profile, query, HoneymodGuideMode.SURVIVAL_MODE, true));
                } else {
                    HoneymodPlugin.getLocalization().sendMessage(sender, "messages.usage", true, msg -> msg.replace("%usage%", "/hm search <SearchTerm>"));
                }
            } else {
                HoneymodPlugin.getLocalization().sendMessage(sender, "messages.no-permission", true);
            }
        } else {
            HoneymodPlugin.getLocalization().sendMessage(sender, "messages.only-players", true);
        }
    }

}
