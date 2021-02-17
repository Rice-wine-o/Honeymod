package me.spacetoastdev.honeymod.implementation.items.electric.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.RecipeDisplayItem;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.items.misc.OrganicFertilizer;

public class FoodComposter extends AContainer implements RecipeDisplayItem {

    public FoodComposter(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(30, new ItemStack[] { HoneymodItems.WHEAT_ORGANIC_FOOD }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.WHEAT_FERTILIZER, OrganicFertilizer.OUTPUT) });
        registerRecipe(30, new ItemStack[] { HoneymodItems.CARROT_ORGANIC_FOOD }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.CARROT_FERTILIZER, OrganicFertilizer.OUTPUT) });
        registerRecipe(30, new ItemStack[] { HoneymodItems.POTATO_ORGANIC_FOOD }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.POTATO_FERTILIZER, OrganicFertilizer.OUTPUT) });
        registerRecipe(30, new ItemStack[] { HoneymodItems.SEEDS_ORGANIC_FOOD }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.SEEDS_FERTILIZER, OrganicFertilizer.OUTPUT) });
        registerRecipe(30, new ItemStack[] { HoneymodItems.BEETROOT_ORGANIC_FOOD }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.BEETROOT_FERTILIZER, OrganicFertilizer.OUTPUT) });
        registerRecipe(30, new ItemStack[] { HoneymodItems.MELON_ORGANIC_FOOD }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.MELON_FERTILIZER, OrganicFertilizer.OUTPUT) });
        registerRecipe(30, new ItemStack[] { HoneymodItems.APPLE_ORGANIC_FOOD }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.APPLE_FERTILIZER, OrganicFertilizer.OUTPUT) });
        registerRecipe(30, new ItemStack[] { HoneymodItems.KELP_ORGANIC_FOOD }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.KELP_FERTILIZER, OrganicFertilizer.OUTPUT) });
        registerRecipe(30, new ItemStack[] { HoneymodItems.COCOA_ORGANIC_FOOD }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.COCOA_FERTILIZER, OrganicFertilizer.OUTPUT) });
        registerRecipe(30, new ItemStack[] { HoneymodItems.SWEET_BERRIES_ORGANIC_FOOD }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.SWEET_BERRIES_FERTILIZER, OrganicFertilizer.OUTPUT) });
    }

    @Override
    public String getMachineIdentifier() {
        return "FOOD_COMPOSTER";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.GOLDEN_HOE);
    }

}
