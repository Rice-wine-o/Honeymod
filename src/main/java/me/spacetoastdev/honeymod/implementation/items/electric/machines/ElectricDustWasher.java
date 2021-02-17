package me.spacetoastdev.honeymod.implementation.items.electric.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.OreWasher;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

public class ElectricDustWasher extends AContainer {

    private OreWasher oreWasher;
    private final boolean legacyMode;

    public ElectricDustWasher(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        legacyMode = HoneymodPlugin.getCfg().getBoolean("options.legacy-dust-washer");
    }

    @Override
    public void preRegister() {
        super.preRegister();

        oreWasher = (OreWasher) HoneymodItems.ORE_WASHER.getItem();
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.GOLDEN_SHOVEL);
    }

    @Override
    protected MachineRecipe findNextRecipe(BlockMenu menu) {
        for (int slot : getInputSlots()) {
            if (HoneymodUtils.isItemSimilar(menu.getItemInSlot(slot), HoneymodItems.SIFTED_ORE, true, false)) {
                if (!legacyMode && !hasFreeSlot(menu)) {
                    return null;
                }

                ItemStack dust = oreWasher.getRandomDust();
                MachineRecipe recipe = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] { dust });

                if (!legacyMode || menu.fits(recipe.getOutput()[0], getOutputSlots())) {
                    menu.consumeItem(slot);
                    return recipe;
                }
            } else if (HoneymodUtils.isItemSimilar(menu.getItemInSlot(slot), HoneymodItems.PULVERIZED_ORE, true)) {
                MachineRecipe recipe = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] { HoneymodItems.PURE_ORE_CLUSTER });

                if (menu.fits(recipe.getOutput()[0], getOutputSlots())) {
                    menu.consumeItem(slot);
                    return recipe;
                }
            }
        }

        return null;
    }

    private boolean hasFreeSlot(BlockMenu menu) {
        for (int slot : getOutputSlots()) {
            ItemStack item = menu.getItemInSlot(slot);

            if (item == null || item.getType() == Material.AIR) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getMachineIdentifier() {
        return "ELECTRIC_DUST_WASHER";
    }

}
