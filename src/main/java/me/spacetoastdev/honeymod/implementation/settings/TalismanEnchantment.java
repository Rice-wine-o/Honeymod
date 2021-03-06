package me.spacetoastdev.honeymod.implementation.settings;

import javax.annotation.Nonnull;

import org.bukkit.enchantments.Enchantment;

import me.spacetoastdev.honeymod.api.items.ItemSetting;
import me.spacetoastdev.honeymod.implementation.items.magical.talismans.MagicianTalisman;
import me.spacetoastdev.honeymod.implementation.listeners.TalismanListener;

/**
 * This class is an extension of {@link ItemSetting} that holds an {@link Enchantment} and
 * a level. It is only used by the {@link TalismanListener} to handle the {@link MagicianTalisman}.
 * 
 * @author TheBusyBiscuit
 *
 */
public class TalismanEnchantment extends ItemSetting<Boolean> {

    private final Enchantment enchantment;
    private final int level;

    public TalismanEnchantment(@Nonnull Enchantment enchantment, int level) {
        super("allow-enchantments." + enchantment.getKey().getNamespace() + '.' + enchantment.getKey().getKey() + ".level." + level, true);

        this.enchantment = enchantment;
        this.level = level;
    }

    /**
     * This returns the actual {@link Enchantment} represented by this {@link ItemSetting}.
     * 
     * @return The associated {@link Enchantment}
     */
    @Nonnull
    public Enchantment getEnchantment() {
        return enchantment;
    }

    /**
     * This returns the level for this {@link Enchantment}.
     * 
     * @return The level
     */
    public int getLevel() {
        return level;
    }

}