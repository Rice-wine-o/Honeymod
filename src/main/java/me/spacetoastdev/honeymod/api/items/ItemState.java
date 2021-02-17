package me.spacetoastdev.honeymod.api.items;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.implementation.items.VanillaItem;

/**
 * Defines whether a SlimefunItem is enabled, disabled or fall-back to its vanilla behavior.
 *
 * @author Poslovitch
 * 
 * @see SlimefunItem
 * 
 */
public enum ItemState {

    /**
     * This {@link SlimefunItem} has not been registered (yet).
     */
    UNREGISTERED,

    /**
     * This {@link SlimefunItem} is enabled.
     */
    ENABLED,

    /**
     * This {@link SlimefunItem} is disabled and is not a {@link VanillaItem}.
     */
    DISABLED,

    /**
     * This {@link SlimefunItem} has fallen back to its vanilla behavior, because it is disabled and an instance of
     * {@link VanillaItem}.
     */
    VANILLA_FALLBACK
}