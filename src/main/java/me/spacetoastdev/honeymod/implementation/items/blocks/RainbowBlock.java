package me.spacetoastdev.honeymod.implementation.items.blocks;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.RainbowTickHandler;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

public class RainbowBlock extends SimpleHoneymodItem<RainbowTickHandler> {

    private final RainbowTickHandler ticker;

    public RainbowBlock(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput, RainbowTickHandler ticker) {
        super(category, item, recipeType, recipe, recipeOutput);

        this.ticker = ticker;
    }

    @Override
    public RainbowTickHandler getItemHandler() {
        return ticker;
    }

}
