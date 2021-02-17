package me.spacetoastdev.honeymod.implementation.items.electric;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.EnergyNetComponent;
import me.spacetoastdev.honeymod.core.networks.energy.EnergyNet;
import me.spacetoastdev.honeymod.core.networks.energy.EnergyNetComponentType;

/**
 * A {@link Capacitor} is an {@link EnergyNetComponent} that serves as the energy
 * storage of an {@link EnergyNet}.
 * 
 * It is represented by {@code EnergyNetComponentType.CAPACITOR}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see EnergyNet
 * @see EnergyNetComponent
 *
 */
public class Capacitor extends SlimefunItem implements EnergyNetComponent {

    private final int capacity;

    @ParametersAreNonnullByDefault
    public Capacitor(Category category, int capacity, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        this.capacity = capacity;
    }

    @Override
    @Nonnull
    public final EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CAPACITOR;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

}
