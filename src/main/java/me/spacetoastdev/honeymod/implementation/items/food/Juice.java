package me.spacetoastdev.honeymod.implementation.items.food;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ItemConsumptionHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;
import me.spacetoastdev.honeymod.implementation.items.backpacks.Cooler;
import me.spacetoastdev.honeymod.implementation.listeners.CoolerListener;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

/**
 * This class represents a {@link SlimefunItem} that can be stored inside
 * of a {@link Cooler}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see Cooler
 * @see CoolerListener
 *
 */
public class Juice extends SimpleHoneymodItem<ItemConsumptionHandler> {

    private final List<PotionEffect> effects;

    @ParametersAreNonnullByDefault
    public Juice(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        this(category, item, recipeType, recipe, null);
    }

    @ParametersAreNonnullByDefault
    public Juice(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, @Nullable ItemStack recipeOutput) {
        super(category, item, recipeType, recipe, recipeOutput);

        ItemMeta meta = item.getItemMeta();

        if (meta instanceof PotionMeta) {
            effects = ((PotionMeta) meta).getCustomEffects();
        } else {
            effects = new ArrayList<>();
        }
    }

    @Override
    public ItemConsumptionHandler getItemHandler() {
        return (e, p, item) -> {
            /*
             * Fix for Saturation on potions is no longer working,
             * Minecraft has been broken when it comes to Saturation potions for a long time
             */
            for (PotionEffect effect : effects) {
                if (effect.getType().equals(PotionEffectType.SATURATION)) {
                    p.addPotionEffect(effect);
                    break;
                }
            }

            removeGlassBottle(p, item);
        };
    }

    /**
     * Determines from which hand the juice is being drunk, and its amount
     * 
     * @param p
     *            The {@link Player} that triggered this
     * @param item
     *            The {@link ItemStack} in question
     */
    @ParametersAreNonnullByDefault
    private void removeGlassBottle(Player p, ItemStack item) {
        if (HoneymodUtils.isItemSimilar(item, p.getInventory().getItemInMainHand(), true)) {
            if (p.getInventory().getItemInMainHand().getAmount() == 1) {
                HoneymodPlugin.runSync(() -> p.getEquipment().getItemInMainHand().setAmount(0));
            } else {
                HoneymodPlugin.runSync(() -> p.getInventory().removeItem(new ItemStack(Material.GLASS_BOTTLE, 1)));
            }
        } else if (HoneymodUtils.isItemSimilar(item, p.getInventory().getItemInOffHand(), true)) {
            if (p.getInventory().getItemInOffHand().getAmount() == 1) {
                HoneymodPlugin.runSync(() -> p.getEquipment().getItemInOffHand().setAmount(0));
            } else {
                HoneymodPlugin.runSync(() -> p.getInventory().removeItem(new ItemStack(Material.GLASS_BOTTLE, 1)));
            }
        }
    }

}
