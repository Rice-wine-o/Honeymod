package me.spacetoastdev.honeymod.implementation.items.backpacks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.player.PlayerBackpack;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;
import me.spacetoastdev.honeymod.implementation.listeners.BackpackListener;
import me.spacetoastdev.honeymod.utils.tags.HoneymodTag;

/**
 * This class represents a {@link SlimefunItem} that is considered a Backpack.
 * Right-Clicking will open the {@link Inventory} of the currently held Backpack.
 * 
 * @author TheBusyBiscuit
 * 
 * @see BackpackListener
 * @see PlayerBackpack
 *
 */
public class HoneymodBackpack extends SimpleHoneymodItem<ItemUseHandler> {

    private final int size;

    @ParametersAreNonnullByDefault
    public HoneymodBackpack(int size, Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        this.size = size;
    }

    /**
     * This returns the size of this {@link HoneymodBackpack}.
     * 
     * @return The size of this backpack
     */
    public int getSize() {
        return size;
    }

    /**
     * This method returns whether a given {@link ItemStack} is allowed to be stored
     * in this {@link HoneymodBackpack}.
     * 
     * @param item
     *            The {@link ItemStack} to check for
     * 
     * @param itemAsSlimefunItem
     *            The same {@link ItemStack} as a {@link SlimefunItem}, might be null
     * 
     * @return Whether the given {@link ItemStack} is allowed to be put into this {@link HoneymodBackpack}
     */
    public boolean isItemAllowed(@Nonnull ItemStack item, @Nullable SlimefunItem itemAsSlimefunItem) {
        // Shulker Boxes are not allowed!
        if (HoneymodTag.SHULKER_BOXES.isTagged(item.getType())) {
            return false;
        }

        return !(itemAsSlimefunItem instanceof HoneymodBackpack);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();

            BackpackListener listener = HoneymodPlugin.getBackpackListener();

            if (listener != null) {
                listener.openBackpack(e.getPlayer(), e.getItem(), this);
            }
        };
    }

}
