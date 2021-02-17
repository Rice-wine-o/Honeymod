package me.spacetoastdev.honeymod.implementation.items.medical;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.cscorelib2.inventory.ItemUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

public class Splint extends SimpleHoneymodItem<ItemUseHandler> {

    @ParametersAreNonnullByDefault
    public Splint(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Player p = e.getPlayer();

            // Player is neither burning nor injured
            if (p.getFireTicks() <= 0 && p.getHealth() >= p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
                return;
            }

            if (p.getGameMode() != GameMode.CREATIVE) {
                ItemUtils.consumeItem(e.getItem(), false);
            }

            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SKELETON_HURT, 1, 1);
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0));

            e.cancel();
        };
    }

}
