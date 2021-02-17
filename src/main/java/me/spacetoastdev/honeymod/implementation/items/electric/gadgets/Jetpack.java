package me.spacetoastdev.honeymod.implementation.items.electric.gadgets;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.Rechargeable;
import me.spacetoastdev.honeymod.implementation.tasks.JetpackTask;

/**
 * {@link JetBoots} allow you to fly up into the air.
 * You can find the actual behaviour in the {@link JetpackTask} class.
 * 
 * @author TheBusyBiscuit
 * 
 * @see JetBoots
 * @see JetpackTask
 *
 */
public class Jetpack extends SlimefunItem implements Rechargeable {

    private final double thrust;
    private final float capacity;

    @ParametersAreNonnullByDefault
    public Jetpack(Category category, SlimefunItemStack item, ItemStack[] recipe, double thrust, float capacity) {
        super(category, item, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);

        this.thrust = thrust;
        this.capacity = capacity;
    }

    public double getThrust() {
        return thrust;
    }

    @Override
    public float getMaxItemCharge(ItemStack item) {
        return capacity;
    }

}
