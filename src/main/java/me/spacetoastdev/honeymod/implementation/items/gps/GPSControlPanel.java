package me.spacetoastdev.honeymod.implementation.items.gps;

import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.protection.ProtectableAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.BlockUseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

public class GPSControlPanel extends SimpleHoneymodItem<BlockUseHandler> {

    public GPSControlPanel(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public BlockUseHandler getItemHandler() {
        return e -> {
            e.cancel();

            Player p = e.getPlayer();
            Optional<Block> block = e.getClickedBlock();

            if (block.isPresent()) {
                if (hasAccess(p, block.get().getLocation())) {
                    HoneymodPlugin.getGPSNetwork().openTransmitterControlPanel(p);
                } else {
                    HoneymodPlugin.getLocalization().sendMessage(p, "inventory.no-access", true);
                }
            }
        };
    }

    @ParametersAreNonnullByDefault
    private boolean hasAccess(Player p, Location l) {
        return p.hasPermission("slimefun.gps.bypass") || (HoneymodPlugin.getProtectionManager().hasPermission(p, l, ProtectableAction.INTERACT_BLOCK));
    }
}
