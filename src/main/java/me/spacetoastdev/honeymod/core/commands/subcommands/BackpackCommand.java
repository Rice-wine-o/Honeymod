package me.spacetoastdev.honeymod.core.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.PatternUtils;

class BackpackCommand extends SubCommand {

    BackpackCommand(HoneymodPlugin plugin, HoneymodCommand cmd) {
        super(plugin, cmd, "backpack", false);
    }

    @Override
    protected String getDescription() {
        return "commands.backpack.description";
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("slimefun.command.backpack")) {
                if (args.length != 3) {
                    HoneymodPlugin.getLocalization().sendMessage(sender, "messages.usage", true, msg -> msg.replace("%usage%", "/hm backpack <Player> <ID>"));
                    return;
                }

                if (!PatternUtils.NUMERIC.matcher(args[2]).matches()) {
                    HoneymodPlugin.getLocalization().sendMessage(sender, "commands.backpack.invalid-id");
                    return;
                }

                @SuppressWarnings("deprecation")
                OfflinePlayer backpackOwner = Bukkit.getOfflinePlayer(args[1]);

                if (!(backpackOwner instanceof Player) && !backpackOwner.hasPlayedBefore()) {
                    HoneymodPlugin.getLocalization().sendMessage(sender, "commands.backpack.player-never-joined");
                    return;
                }

                int id = Integer.parseInt(args[2]);

                PlayerProfile.get(backpackOwner, profile -> {
                    if (!profile.getBackpack(id).isPresent()) {
                        HoneymodPlugin.getLocalization().sendMessage(sender, "commands.backpack.backpack-does-not-exist");
                        return;
                    }

                    HoneymodPlugin.runSync(() -> {
                        ItemStack item = HoneymodItems.RESTORED_BACKPACK.clone();
                        HoneymodPlugin.getBackpackListener().setBackpackId(backpackOwner, item, 2, id);
                        ((Player) sender).getInventory().addItem(item);
                        HoneymodPlugin.getLocalization().sendMessage(sender, "commands.backpack.restored-backpack-given");
                    });
                });
            } else {
                HoneymodPlugin.getLocalization().sendMessage(sender, "messages.no-permission", true);
            }
        } else {
            HoneymodPlugin.getLocalization().sendMessage(sender, "messages.only-players", true);
        }
    }
}
