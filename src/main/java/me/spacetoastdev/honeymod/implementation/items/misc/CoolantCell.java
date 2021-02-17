package me.spacetoastdev.honeymod.implementation.items.misc;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.implementation.items.blocks.UnplaceableBlock;
import me.spacetoastdev.honeymod.implementation.items.cargo.ReactorAccessPort;
import me.spacetoastdev.honeymod.implementation.items.electric.reactors.NetherStarReactor;
import me.spacetoastdev.honeymod.implementation.items.electric.reactors.NuclearReactor;
import me.spacetoastdev.honeymod.implementation.items.electric.reactors.Reactor;

/**
 * A {@link CoolantCell} is an {@link ItemStack} that is used to cool a {@link Reactor}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see Reactor
 * @see ReactorAccessPort
 * @see NuclearReactor
 * @see NetherStarReactor
 *
 */
public class CoolantCell extends UnplaceableBlock {

    @ParametersAreNonnullByDefault
    public CoolantCell(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        this(category, item, recipeType, recipe, null);
    }

    @ParametersAreNonnullByDefault
    public CoolantCell(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);
    }

}
