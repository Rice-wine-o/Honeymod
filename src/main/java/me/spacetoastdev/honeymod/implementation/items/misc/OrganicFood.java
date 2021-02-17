package me.spacetoastdev.honeymod.implementation.items.misc;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.AnimalGrowthAccelerator;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.FoodFabricator;

/**
 * {@link OrganicFood} is created using a {@link FoodFabricator} and can
 * be used to fuel an {@link AnimalGrowthAccelerator}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see AnimalGrowthAccelerator
 *
 */
public class OrganicFood extends SlimefunItem {

    public static final int OUTPUT = 2;

    @ParametersAreNonnullByDefault
    public OrganicFood(Category category, SlimefunItemStack item, Material ingredient) {
        super(category, item, RecipeType.FOOD_FABRICATOR, new ItemStack[] { HoneymodItems.TIN_CAN, new ItemStack(ingredient), null, null, null, null, null, null, null }, new SlimefunItemStack(item, OUTPUT));
    }

}
