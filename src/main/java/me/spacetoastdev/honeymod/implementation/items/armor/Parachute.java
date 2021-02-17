package me.spacetoastdev.honeymod.implementation.items.armor;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.implementation.items.electric.gadgets.Jetpack;
import me.spacetoastdev.honeymod.implementation.tasks.ParachuteTask;

/**
 * The {@link Parachute} is a {@link SlimefunItem} that can be equipped as a chestplate.
 * It allows you slowly glide to the ground while holding shift.
 * 
 * This class does not contain much code to see, check our the {@link ParachuteTask} class
 * for the actual logic behind this.
 * 
 * @author TheBusyBiscuit
 * 
 * @see ParachuteTask
 * @see Jetpack
 *
 */
public class Parachute extends SlimefunItem {

    @ParametersAreNonnullByDefault
    public Parachute(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
    }

}
