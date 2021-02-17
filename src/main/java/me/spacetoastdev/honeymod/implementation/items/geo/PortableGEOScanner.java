package me.spacetoastdev.honeymod.implementation.items.geo;

import java.util.Optional;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

public class PortableGEOScanner extends SimpleHoneymodItem<ItemUseHandler> {

    public PortableGEOScanner(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Optional<Block> block = e.getClickedBlock();
            e.cancel();

            block.ifPresent(value -> HoneymodPlugin.getGPSNetwork().getResourceManager().scan(e.getPlayer(), value, 0));
        };
    }

}
