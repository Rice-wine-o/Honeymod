package me.spacetoastdev.honeymod.implementation.items.medical;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ItemConsumptionHandler;

public class Medicine extends MedicalSupply<ItemConsumptionHandler> {

    @ParametersAreNonnullByDefault
    public Medicine(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, 8, item, recipeType, recipe);
    }

    @Override
    public ItemConsumptionHandler getItemHandler() {
        return (e, p, item) -> {
            p.setFireTicks(0);
            clearNegativeEffects(p);
            heal(p);
        };
    }

}
