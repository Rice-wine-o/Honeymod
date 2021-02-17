package me.spacetoastdev.honeymod.implementation.items.magical;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.NotPlaceable;
import me.spacetoastdev.honeymod.core.attributes.Soulbound;
import me.spacetoastdev.honeymod.implementation.items.magical.runes.SoulboundRune;
import me.spacetoastdev.honeymod.implementation.listeners.SoulboundListener;

/**
 * Represents an Item that will not drop upon death.
 * 
 * @author TheBusyBiscuit
 * 
 * @see Soulbound
 * @see SoulboundRune
 * @see SoulboundListener
 *
 */
public class SoulboundItem extends SlimefunItem implements Soulbound, NotPlaceable {

    public SoulboundItem(Category category, SlimefunItemStack item, RecipeType type, ItemStack[] recipe) {
        super(category, item, type, recipe);
    }

}
