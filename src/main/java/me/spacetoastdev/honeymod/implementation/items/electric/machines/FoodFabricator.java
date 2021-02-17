package me.spacetoastdev.honeymod.implementation.items.electric.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.items.misc.OrganicFood;

public class FoodFabricator extends AContainer {

    public FoodFabricator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(12, new ItemStack[] { HoneymodItems.TIN_CAN, new ItemStack(Material.WHEAT) }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.WHEAT_ORGANIC_FOOD, OrganicFood.OUTPUT) });
        registerRecipe(12, new ItemStack[] { HoneymodItems.TIN_CAN, new ItemStack(Material.CARROT) }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.CARROT_ORGANIC_FOOD, OrganicFood.OUTPUT) });
        registerRecipe(12, new ItemStack[] { HoneymodItems.TIN_CAN, new ItemStack(Material.POTATO) }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.POTATO_ORGANIC_FOOD, OrganicFood.OUTPUT) });
        registerRecipe(12, new ItemStack[] { HoneymodItems.TIN_CAN, new ItemStack(Material.WHEAT_SEEDS) }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.SEEDS_ORGANIC_FOOD, OrganicFood.OUTPUT) });
        registerRecipe(12, new ItemStack[] { HoneymodItems.TIN_CAN, new ItemStack(Material.BEETROOT) }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.BEETROOT_ORGANIC_FOOD, OrganicFood.OUTPUT) });
        registerRecipe(12, new ItemStack[] { HoneymodItems.TIN_CAN, new ItemStack(Material.MELON_SLICE) }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.MELON_ORGANIC_FOOD, OrganicFood.OUTPUT) });
        registerRecipe(12, new ItemStack[] { HoneymodItems.TIN_CAN, new ItemStack(Material.APPLE) }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.APPLE_ORGANIC_FOOD, OrganicFood.OUTPUT) });
        registerRecipe(12, new ItemStack[] { HoneymodItems.TIN_CAN, new ItemStack(Material.DRIED_KELP) }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.KELP_ORGANIC_FOOD, OrganicFood.OUTPUT) });
        registerRecipe(12, new ItemStack[] { HoneymodItems.TIN_CAN, new ItemStack(Material.COCOA_BEANS) }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.COCOA_ORGANIC_FOOD, OrganicFood.OUTPUT) });
        registerRecipe(12, new ItemStack[] { HoneymodItems.TIN_CAN, new ItemStack(Material.SWEET_BERRIES) }, new ItemStack[] { new SlimefunItemStack(HoneymodItems.SWEET_BERRIES_ORGANIC_FOOD, OrganicFood.OUTPUT) });
    }

    @Override
    public String getMachineIdentifier() {
        return "FOOD_FABRICATOR";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.GOLDEN_HOE);
    }

}
