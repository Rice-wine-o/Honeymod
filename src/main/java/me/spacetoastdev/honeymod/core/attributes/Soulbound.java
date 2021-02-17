package me.spacetoastdev.honeymod.core.attributes;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.implementation.items.magical.SoulboundItem;

/**
 * This Interface, when attached to a class that inherits from {@link SlimefunItem}, marks
 * the Item as soulbound.
 * This Item will then not be dropped upon death.
 * 
 * @author TheBusyBiscuit
 * 
 * @see SoulboundItem
 *
 */
public interface Soulbound extends ItemAttribute {

}
