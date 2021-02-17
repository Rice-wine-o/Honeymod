package me.spacetoastdev.honeymod.core.guide.options;

import java.util.Optional;

import org.bukkit.Keyed;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.spacetoastdev.honeymod.api.HoneymodAddon;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;

/**
 * This interface represents an option in the {@link HoneymodGuide}.
 * 
 * @author TheBusyBiscuit
 *
 * @param <T>
 *            The type of value for this option
 */
public interface HoneymodGuideOption<T> extends Keyed {

    /**
     * This returns the {@link HoneymodAddon} which added this {@link HoneymodGuideOption}.
     * 
     * @return The registering {@link HoneymodAddon}
     */
    HoneymodAddon getAddon();

    Optional<ItemStack> getDisplayItem(Player p, ItemStack guide);

    void onClick(Player p, ItemStack guide);

    Optional<T> getSelectedOption(Player p, ItemStack guide);

    void setSelectedOption(Player p, ItemStack guide, T value);

}
