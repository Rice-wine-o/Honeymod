package me.spacetoastdev.honeymod.core.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.core.attributes.Rechargeable;
import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * {@link ChargeCommand} adds an in game command which charges any {@link Rechargeable}
 * item to maximum charge, defined by {@link Rechargeable#getMaxItemCharge(ItemStack)}.
 *
 * @author FluffyBear
 *
 */
class ChargeCommand extends SubCommand {

    ChargeCommand(HoneymodPlugin plugin, HoneymodCommand cmd) {
        super(plugin, cmd, "charge", false);
    }

    @Override
    protected String getDescription() {
        return "commands.charge.description";
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("slimefun.charge.command")) {
                Player p = (Player) sender;
                ItemStack item = p.getInventory().getItemInMainHand();
                SlimefunItem slimefunItem = SlimefunItem.getByItem(item);
                if (slimefunItem instanceof Rechargeable) {
                    Rechargeable rechargeableItem = (Rechargeable) slimefunItem;
                    rechargeableItem.setItemCharge(item, rechargeableItem.getMaxItemCharge(item));
                    HoneymodPlugin.getLocalization().sendMessage(sender, "commands.charge.charge-success", true);
                } else {
                    HoneymodPlugin.getLocalization().sendMessage(sender, "commands.charge.not-rechargeable", true);
                }
            } else {
                HoneymodPlugin.getLocalization().sendMessage(sender, "messages.no-permission", true);
            }
        } else {
            HoneymodPlugin.getLocalization().sendMessage(sender, "messages.only-players", true);
        }
    }
}
