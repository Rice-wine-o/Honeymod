package me.spacetoastdev.honeymod.implementation.items.magical;

import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

public class MagicEyeOfEnder extends SimpleHoneymodItem<ItemUseHandler> {

    public MagicEyeOfEnder(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();

            Player p = e.getPlayer();

            if (hasArmor(p.getInventory())) {
                p.launchProjectile(EnderPearl.class);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            }
        };
    }

    private boolean hasArmor(PlayerInventory inv) {
        return HoneymodUtils.isItemSimilar(inv.getHelmet(), HoneymodItems.ENDER_HELMET, true) && HoneymodUtils.isItemSimilar(inv.getChestplate(), HoneymodItems.ENDER_CHESTPLATE, true) && HoneymodUtils.isItemSimilar(inv.getLeggings(), HoneymodItems.ENDER_LEGGINGS, true) && HoneymodUtils.isItemSimilar(inv.getBoots(), HoneymodItems.ENDER_BOOTS, true);
    }
}
