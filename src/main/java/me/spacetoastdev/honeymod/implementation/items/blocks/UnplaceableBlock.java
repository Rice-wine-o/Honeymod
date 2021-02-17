package me.spacetoastdev.honeymod.implementation.items.blocks;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.events.PlayerRightClickEvent;
import me.spacetoastdev.honeymod.core.attributes.NotPlaceable;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

public class UnplaceableBlock extends SimpleHoneymodItem<ItemUseHandler> implements NotPlaceable {

    public UnplaceableBlock(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public UnplaceableBlock(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return PlayerRightClickEvent::cancel;
    }

}
