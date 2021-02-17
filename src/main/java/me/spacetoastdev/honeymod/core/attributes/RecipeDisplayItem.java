package me.spacetoastdev.honeymod.core.attributes;

import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.geo.GEOMiner;
import me.spacetoastdev.honeymod.implementation.items.tools.GoldPan;

/**
 * This interface, when attache to a {@link SlimefunItem} class will make additional items
 * appear in the {@link HoneymodGuide}.
 * These additional items can be used represent recipes or resources that are associated
 * with this {@link SlimefunItem}.
 * 
 * You can find a few examples below.
 * 
 * @author TheBusyBiscuit
 * 
 * @see GoldPan
 * @see GEOMiner
 * @see AGenerator
 *
 */
public interface RecipeDisplayItem extends ItemAttribute {

    /**
     * This is the list of items to display alongside this {@link SlimefunItem}.
     * Note that these items will be filled in from top to bottom first.
     * So if you want it to express a recipe, add your input {@link ItemStack}
     * and then your output {@link ItemStack}.
     * 
     * @return The recipes to display in the {@link HoneymodGuide}
     */
    @Nonnull
    List<ItemStack> getDisplayRecipes();

    @Nonnull
    default String getLabelLocalPath() {
        return "guide.tooltips.recipes.machine";
    }

    @Nonnull
    default String getRecipeSectionLabel(@Nonnull Player p) {
        return "&7\u21E9 " + HoneymodPlugin.getLocalization().getMessage(p, getLabelLocalPath()) + " \u21E9";
    }
}
