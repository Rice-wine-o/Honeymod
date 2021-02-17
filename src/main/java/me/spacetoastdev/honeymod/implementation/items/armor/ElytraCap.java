package me.spacetoastdev.honeymod.implementation.items.armor;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.DamageableItem;
import me.spacetoastdev.honeymod.core.attributes.ProtectionType;
import me.spacetoastdev.honeymod.core.attributes.ProtectiveArmor;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.listeners.ElytraImpactListener;

/**
 * The {@link ElytraCap} negates damage taken when crashing into a wall using an elytra.
 *
 * @author Seggan
 *
 * @see ElytraImpactListener
 */
public class ElytraCap extends HoneymodArmorPiece implements DamageableItem, ProtectiveArmor {

    private final NamespacedKey key;

    @ParametersAreNonnullByDefault
    public ElytraCap(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe, null);

        key = new NamespacedKey(HoneymodPlugin.instance(), "elytra_armor");
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public void damageItem(Player p, ItemStack item) {
        if (p.getGameMode() != GameMode.CREATIVE) {
            DamageableItem.super.damageItem(p, item);
        }
    }

    @Nonnull
    @Override
    public ProtectionType[] getProtectionTypes() {
        return new ProtectionType[] { ProtectionType.FLYING_INTO_WALL };
    }

    @Override
    public boolean isFullSetRequired() {
        return false;
    }

    @Nonnull
    @Override
    public NamespacedKey getArmorSetId() {
        return key;
    }
}
