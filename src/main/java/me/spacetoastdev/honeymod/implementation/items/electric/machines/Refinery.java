package me.spacetoastdev.honeymod.implementation.items.electric.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.RecipeDisplayItem;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;

public class Refinery extends AContainer implements RecipeDisplayItem {

    public Refinery(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(40, HoneymodItems.OIL_BUCKET, HoneymodItems.FUEL_BUCKET);
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public String getMachineIdentifier() {
        return "REFINERY";
    }

}
