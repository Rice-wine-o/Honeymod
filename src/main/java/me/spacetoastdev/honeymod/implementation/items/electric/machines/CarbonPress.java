package me.spacetoastdev.honeymod.implementation.items.electric.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.RecipeDisplayItem;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;

public class CarbonPress extends AContainer implements RecipeDisplayItem {

    public CarbonPress(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(15, new ItemStack[] { new ItemStack(Material.CHARCOAL, 4) }, new ItemStack[] { new ItemStack(Material.COAL) });
        registerRecipe(20, new ItemStack[] { new ItemStack(Material.COAL, 8) }, new ItemStack[] { HoneymodItems.CARBON });
        registerRecipe(30, new ItemStack[] { new CustomItem(HoneymodItems.CARBON, 4) }, new ItemStack[] { HoneymodItems.COMPRESSED_CARBON });
        registerRecipe(60, new ItemStack[] { HoneymodItems.CARBON_CHUNK, HoneymodItems.SYNTHETIC_DIAMOND }, new ItemStack[] { HoneymodItems.RAW_CARBONADO });
        registerRecipe(60, new ItemStack[] { HoneymodItems.CARBON_CHUNK }, new ItemStack[] { HoneymodItems.SYNTHETIC_DIAMOND });
        registerRecipe(90, new ItemStack[] { HoneymodItems.RAW_CARBONADO }, new ItemStack[] { HoneymodItems.CARBONADO });
    }

    @Override
    public String getMachineIdentifier() {
        return "CARBON_PRESS";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.DIAMOND_PICKAXE);
    }

}
