package me.spacetoastdev.honeymod.implementation.items.armor;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.ProtectionType;
import me.spacetoastdev.honeymod.core.attributes.ProtectiveArmor;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * Represents 1 {@link HoneymodArmorPiece} of the Hazmat armor set.
 * One of the very few utilisations of {@link ProtectiveArmor}.
 *
 * @author Linox
 *
 * @see HoneymodArmorPiece
 * @see ProtectiveArmor
 *
 */
public class HazmatArmorPiece extends HoneymodArmorPiece implements ProtectiveArmor {

    private final NamespacedKey namespacedKey;
    private final ProtectionType[] types;

    public HazmatArmorPiece(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, PotionEffect[] effects) {
        super(category, item, recipeType, recipe, effects);

        types = new ProtectionType[] { ProtectionType.BEES, ProtectionType.RADIATION };
        namespacedKey = new NamespacedKey(HoneymodPlugin.instance(), "hazmat_suit");
    }

    @Override
    public ProtectionType[] getProtectionTypes() {
        return types;
    }

    @Override
    public boolean isFullSetRequired() {
        return true;
    }

    @Override
    public NamespacedKey getArmorSetId() {
        return namespacedKey;
    }
}
