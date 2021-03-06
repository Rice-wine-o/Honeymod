package me.spacetoastdev.honeymod.implementation.items.weapons;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.Arrow;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.BowShootHandler;

/**
 * This class represents a {@link SlimefunItem} that is a Bow.
 * It comes with a {@link BowShootHandler} to handle actions that shall be performed
 * whenever an {@link Arrow} fired from this {@link HoneymodBow} hits a target.
 * 
 * @author TheBusyBiscuit
 *
 */
public abstract class HoneymodBow extends SlimefunItem {

    @ParametersAreNonnullByDefault
    public HoneymodBow(Category category, SlimefunItemStack item, ItemStack[] recipe) {
        super(category, item, RecipeType.MAGIC_WORKBENCH, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler(onShoot());
    }

    @Nonnull
    public abstract BowShootHandler onShoot();

}
