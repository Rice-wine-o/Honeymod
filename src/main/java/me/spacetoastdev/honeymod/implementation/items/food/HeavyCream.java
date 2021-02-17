package me.spacetoastdev.honeymod.implementation.items.food;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

/**
 * This {@link SlimefunItem} can be obtained by crafting, it's
 * used for various foods and recipes.
 *
 * @author TheSilentPro
 * 
 */
public class HeavyCream extends SimpleHoneymodItem<ItemUseHandler> {

    @ParametersAreNonnullByDefault
    public HeavyCream(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Optional<Block> block = e.getClickedBlock();

            if (!block.isPresent() || !block.get().getType().isInteractable()) {
                e.cancel();
            }
        };
    }

}