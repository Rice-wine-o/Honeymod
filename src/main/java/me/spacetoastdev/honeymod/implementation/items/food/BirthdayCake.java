package me.spacetoastdev.honeymod.implementation.items.food;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.NotPlaceable;

/**
 * The {@link BirthdayCake} is a seasonal item which only appears in october.
 * This is just a nod to me, TheBusyBiscuit, being born on October 26th.
 * 
 * @author TheBusyBiscuit
 * 
 */
public class BirthdayCake extends SlimefunItem implements NotPlaceable {

    @ParametersAreNonnullByDefault
    public BirthdayCake(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

}
