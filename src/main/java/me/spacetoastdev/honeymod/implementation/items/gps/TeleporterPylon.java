package me.spacetoastdev.honeymod.implementation.items.gps;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.RainbowTickHandler;
import me.spacetoastdev.honeymod.implementation.items.blocks.RainbowBlock;

/**
 * The {@link TeleporterPylon} is a special kind of {@link RainbowBlock} which is required
 * for the {@link Teleporter}.
 * 
 * @author TheBusyBiscuit
 *
 * @see Teleporter
 * @see RainbowBlock
 * @see RainbowTickHandler
 */
public class TeleporterPylon extends RainbowBlock {

    public TeleporterPylon(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput, new RainbowTickHandler(Material.CYAN_STAINED_GLASS, Material.PURPLE_STAINED_GLASS));
    }

}
