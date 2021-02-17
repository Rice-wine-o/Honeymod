package me.spacetoastdev.honeymod.implementation.items.food;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ItemConsumptionHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

/**
 * {@link MonsterJerky} is basically just Rotten Flesh but without the Hunger Effect.
 * 
 * @author TheBusyBiscuit
 * 
 * @see MeatJerky
 *
 */
public class MonsterJerky extends SimpleHoneymodItem<ItemConsumptionHandler> {

    @ParametersAreNonnullByDefault
    public MonsterJerky(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemConsumptionHandler getItemHandler() {
        return (e, p, item) -> HoneymodPlugin.runSync(() -> {
            if (p.hasPotionEffect(PotionEffectType.HUNGER)) {
                p.removePotionEffect(PotionEffectType.HUNGER);
            }

            p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 5, 0));
        }, 1L);
    }

}
