package me.spacetoastdev.honeymod.implementation.items.tools;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ToolUseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

public class HerculesPickaxe extends SimpleHoneymodItem<ToolUseHandler> {

    public HerculesPickaxe(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            if (e.getBlock().getType().toString().endsWith("_ORE")) {
                if (e.getBlock().getType() == Material.IRON_ORE) {
                    drops.add(new CustomItem(HoneymodItems.IRON_DUST, 2));
                } else if (e.getBlock().getType() == Material.GOLD_ORE) {
                    drops.add(new CustomItem(HoneymodItems.GOLD_DUST, 2));
                } else {
                    for (ItemStack drop : e.getBlock().getDrops(tool)) {
                        drops.add(new CustomItem(drop, drop.getAmount() * 2));
                    }
                }
            }
        };
    }

}
