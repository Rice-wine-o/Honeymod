package me.spacetoastdev.honeymod.implementation.items;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;

/**
 * This is basically a quickstart class for your very first {@link SlimefunItem}.
 * This class easily allows you to add one {@link ItemHandler} to your {@link SlimefunItem}.
 * 
 * You could use an {@link ItemUseHandler} for example to give your {@link SlimefunItem}
 * very basic right-click functionalities.
 * 
 * @author TheBusyBiscuit
 * 
 * @see ItemHandler
 * @see ItemUseHandler
 * @see SlimefunItem
 *
 * @param <T>
 *            The Type of {@link ItemHandler} to add to this {@link SlimefunItem}
 */
public abstract class SimpleHoneymodItem<T extends ItemHandler> extends SlimefunItem {

    @ParametersAreNonnullByDefault
    public SimpleHoneymodItem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @ParametersAreNonnullByDefault
    public SimpleHoneymodItem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);
    }

    @Override
    public void preRegister() {
        addItemHandler(getItemHandler());
    }

    /**
     * This returns the {@link ItemHandler} that will be added to this {@link SlimefunItem}.
     * 
     * @return The {@link ItemHandler} that should be added to this {@link SlimefunItem}
     */
    @Nonnull
    public abstract T getItemHandler();

}
