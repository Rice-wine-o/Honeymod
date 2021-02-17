package me.spacetoastdev.honeymod.core.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

class TeleporterCommand extends SubCommand {

    TeleporterCommand(HoneymodPlugin plugin, HoneymodCommand cmd) {
        super(plugin, cmd, "teleporter", false);
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("slimefun.command.teleporter")) {
                if (args.length == 1) {
                    Player p = (Player) sender;
                    HoneymodPlugin.getGPSNetwork().getTeleportationManager().openTeleporterGUI(p, p.getUniqueId(), p.getLocation().getBlock().getRelative(BlockFace.DOWN), 999999999);
                } else if (args.length == 2) {

                    @SuppressWarnings("deprecation")
                    OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);

                    if (player.getName() != null) {
                        HoneymodPlugin.getGPSNetwork().getTeleportationManager().openTeleporterGUI((Player) sender, player.getUniqueId(), ((Player) sender).getLocation().getBlock().getRelative(BlockFace.DOWN), 999999999);
                    } else {
                        HoneymodPlugin.getLocalization().sendMessage(sender, "messages.unknown-player", msg -> msg.replace("%player%", args[1]));
                    }
                } else {
                    HoneymodPlugin.getLocalization().sendMessage(sender, "messages.usage", msg -> msg.replace("%usage%", "/hm teleporter [Player]"));
                }
            } else {
                HoneymodPlugin.getLocalization().sendMessage(sender, "messages.no-permission");
            }
        } else {
            HoneymodPlugin.getLocalization().sendMessage(sender, "messages.only-players");
        }
    }

}
