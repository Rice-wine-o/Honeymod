package me.spacetoastdev.honeymod.implementation.items.electric.machines;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.spacetoastdev.honeymod.core.attributes.RecipeDisplayItem;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.items.tools.GoldPan;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

public class ElectricGoldPan extends AContainer implements RecipeDisplayItem {

    private final GoldPan goldPan = (GoldPan) HoneymodItems.GOLD_PAN.getItem();
    private final GoldPan netherGoldPan = (GoldPan) HoneymodItems.NETHER_GOLD_PAN.getItem();

    public ElectricGoldPan(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> recipes = new ArrayList<>();

        recipes.addAll(goldPan.getDisplayRecipes());
        recipes.addAll(netherGoldPan.getDisplayRecipes());

        return recipes;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.DIAMOND_SHOVEL);
    }

    @Override
    protected MachineRecipe findNextRecipe(BlockMenu menu) {
        if (!hasFreeSlot(menu)) {
            return null;
        }

        for (int slot : getInputSlots()) {
            if (HoneymodUtils.isItemSimilar(menu.getItemInSlot(slot), new ItemStack(Material.GRAVEL), true, false)) {
                ItemStack output = goldPan.getRandomOutput();
                MachineRecipe recipe = new MachineRecipe(3 / getSpeed(), new ItemStack[0], new ItemStack[] { output });

                if (output.getType() != Material.AIR && menu.fits(output, getOutputSlots())) {
                    menu.consumeItem(slot);
                    return recipe;
                }
            } else if (HoneymodUtils.isItemSimilar(menu.getItemInSlot(slot), new ItemStack(Material.SOUL_SAND), true, false)) {
                ItemStack output = netherGoldPan.getRandomOutput();
                MachineRecipe recipe = new MachineRecipe(4 / getSpeed(), new ItemStack[0], new ItemStack[] { output });

                if (output.getType() != Material.AIR && menu.fits(output, getOutputSlots())) {
                    menu.consumeItem(slot);
                    return recipe;
                }

            }
        }

        return null;
    }

    private boolean hasFreeSlot(BlockMenu menu) {
        for (int slot : getOutputSlots()) {
            if (menu.getItemInSlot(slot) == null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getMachineIdentifier() {
        return "ELECTRIC_GOLD_PAN";
    }

}
