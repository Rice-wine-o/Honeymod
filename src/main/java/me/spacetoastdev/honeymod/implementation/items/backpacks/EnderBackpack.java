package me.spacetoastdev.honeymod.implementation.items.backpacks;

import org.bukkit.Sound;
import org.bukkit.block.EnderChest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.NotPlaceable;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

/**
 * The {@link EnderBackpack} is a pretty simple {@link SlimefunItem} which opens your
 * {@link EnderChest} upon right clicking.
 * 
 * @author TheBusyBiscuit
 *
 */
public class EnderBackpack extends SimpleHoneymodItem<ItemUseHandler> implements NotPlaceable {

    public EnderBackpack(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();
            p.openInventory(p.getEnderChest());
            p.playSound(p.getEyeLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, 1F);
            e.cancel();
        };
    }
}
