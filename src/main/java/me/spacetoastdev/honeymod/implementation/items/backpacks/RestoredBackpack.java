package me.spacetoastdev.honeymod.implementation.items.backpacks;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.spacetoastdev.honeymod.api.player.PlayerBackpack;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This class represents a {@link HoneymodBackpack} that has been restored via /hm backpack for retrieving items if the
 * original has been lost.
 * This backpack cannot be crafted nor crafted into other items. Its purpose is exclusively that of restoring
 * the lost inventory and shouldn't be used as a backpack replacement.
 * Right-Clicking will open the {@link Inventory} of the restored Backpack.
 *
 * @author Sfiguz7
 *
 * @see PlayerBackpack
 */
public class RestoredBackpack extends HoneymodBackpack {

    /**
     * This will create a new {@link HoneymodBackpack} with the given arguments.
     *
     * @param category
     *            the category to bind this {@link HoneymodBackpack} to
     */
    public RestoredBackpack(Category category) {
        super(54, category, HoneymodItems.RESTORED_BACKPACK, RecipeType.NULL, new ItemStack[9]);

        this.hidden = true;
    }
}
