package me.spacetoastdev.honeymod.implementation.items.backpacks;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.Soulbound;

/**
 * This implementation of {@link HoneymodBackpack} is also {@link Soulbound}.
 * 
 * @author TheBusyBiscuit
 *
 */
public class SoulboundBackpack extends HoneymodBackpack implements Soulbound {

    public SoulboundBackpack(int size, Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(size, category, item, recipeType, recipe);
    }

}
