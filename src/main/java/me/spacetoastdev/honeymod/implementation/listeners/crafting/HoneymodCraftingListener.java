package me.spacetoastdev.honeymod.implementation.listeners.crafting;

import javax.annotation.Nullable;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.implementation.items.VanillaItem;

interface HoneymodCraftingListener extends Listener {

    default boolean hasUnallowedItems(@Nullable ItemStack item1, @Nullable ItemStack item2) {
        if (HoneymodGuide.isGuideItem(item1) || HoneymodGuide.isGuideItem(item2)) {
            return true;
        } else {
            SlimefunItem sfItem1 = SlimefunItem.getByItem(item1);
            SlimefunItem sfItem2 = SlimefunItem.getByItem(item2);
            return isUnallowed(sfItem1) || isUnallowed(sfItem2);
        }
    }

    default boolean isUnallowed(@Nullable ItemStack item) {
        if (item == null) {
            return false;
        }

        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        return isUnallowed(sfItem);
    }

    default boolean isUnallowed(@Nullable SlimefunItem item) {
        return item != null && !(item instanceof VanillaItem) && !item.isDisabled();
    }

}
