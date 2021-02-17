package me.spacetoastdev.honeymod.core.commands.subcommands;

import java.util.Optional;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.cscorelib2.players.PlayerList;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

class StatsCommand extends SubCommand {

    StatsCommand(HoneymodPlugin plugin, HoneymodCommand cmd) {
        super(plugin, cmd, "stats", false);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (args.length > 1) {
            if (sender.hasPermission("slimefun.stats.others") || sender instanceof ConsoleCommandSender) {
                Optional<Player> player = PlayerList.findByName(args[1]);

                if (player.isPresent()) {
                    PlayerProfile.get(player.get(), profile -> profile.sendStats(sender));
                } else {
                    HoneymodPlugin.getLocalization().sendMessage(sender, "messages.not-online", true, msg -> msg.replace("%player%", args[1]));
                }
            } else {
                HoneymodPlugin.getLocalization().sendMessage(sender, "messages.no-permission", true);
            }
        } else if (sender instanceof Player) {
            PlayerProfile.get((Player) sender, profile -> profile.sendStats(sender));
        } else {
            HoneymodPlugin.getLocalization().sendMessage(sender, "messages.only-players", true);
        }
    }

}
