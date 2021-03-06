package me.spacetoastdev.honeymod.implementation.items.magical.staves;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

/**
 * The {@link WaterStaff} is a magical {@link SlimefunItem}.
 * When you right click it, any fire on your {@link Player} will be extinguished.
 * 
 * @author TheBusyBiscuit
 *
 */
public class WaterStaff extends SimpleHoneymodItem<ItemUseHandler> {

    public WaterStaff(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();

            p.setFireTicks(0);
            HoneymodPlugin.getLocalization().sendMessage(p, "messages.fire-extinguish", true);
        };
    }

}
