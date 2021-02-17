package me.spacetoastdev.honeymod.implementation.items.electric.reactors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.Radioactive;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;

/**
 * The {@link NuclearReactor} is an implementation of {@link Reactor} that uses
 * any {@link Radioactive} material to generate energy.
 * It needs water coolant as well as a steady supply of Reactor Coolant Cells
 * 
 * @author TheBusyBiscuit
 * 
 * @see NetherStarReactor
 *
 */
public abstract class NuclearReactor extends Reactor {

    @ParametersAreNonnullByDefault
    public NuclearReactor(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultFuelTypes() {
        registerFuel(new MachineFuel(1200, HoneymodItems.URANIUM, HoneymodItems.NEPTUNIUM));
        registerFuel(new MachineFuel(600, HoneymodItems.NEPTUNIUM, HoneymodItems.PLUTONIUM));
        registerFuel(new MachineFuel(1500, HoneymodItems.BOOSTED_URANIUM, null));
    }

    @Override
    public ItemStack getProgressBar() {
        return HoneymodItems.LAVA_CRYSTAL;
    }

    @Override
    public ItemStack getCoolant() {
        return HoneymodItems.REACTOR_COOLANT_CELL;
    }

    @Override
    public ItemStack getFuelIcon() {
        return HoneymodItems.URANIUM;
    }

    @Override
    public void extraTick(@Nonnull Location l) {
        // This machine does not need to perform anything while ticking
        // The Nether Star Reactor uses this method to generate the Wither Effect
    }

}
