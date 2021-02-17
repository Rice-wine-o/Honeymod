package me.spacetoastdev.honeymod.implementation.items.electric;

import javax.annotation.Nonnull;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.EnergyNetComponent;
import me.spacetoastdev.honeymod.core.handlers.BlockUseHandler;
import me.spacetoastdev.honeymod.core.networks.energy.EnergyNet;
import me.spacetoastdev.honeymod.core.networks.energy.EnergyNetComponentType;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

/**
 * This {@link EnergyNetComponent} is a connector for the {@link EnergyNet} networks.
 * They work similar to {@link Capacitor capacitors}.
 *
 * @author Linox
 *
 * @see EnergyNet
 * @see EnergyNetComponent
 *
 */
public class EnergyConnector extends SimpleHoneymodItem<BlockUseHandler> implements EnergyNetComponent {

    public EnergyConnector(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);
    }

    @Nonnull
    @Override
    public BlockUseHandler getItemHandler() {
        return e -> {
            if (!e.getClickedBlock().isPresent()) {
                return;
            }

            Player p = e.getPlayer();
            Block b = e.getClickedBlock().get();

            if (EnergyNet.getNetworkFromLocation(b.getLocation()) != null) {
                p.sendMessage(ChatColors.color("&7Connected: " + "&2\u2714"));
            } else {
                p.sendMessage(ChatColors.color("&7Connected: " + "&4\u2718"));
            }
        };
    }

    @Nonnull
    @Override
    public final EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONNECTOR;
    }

    @Override
    public int getCapacity() {
        return 0;
    }
}
