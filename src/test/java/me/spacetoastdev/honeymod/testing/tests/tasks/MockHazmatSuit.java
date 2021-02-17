package me.spacetoastdev.honeymod.testing.tests.tasks;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.ProtectionType;
import me.spacetoastdev.honeymod.core.attributes.ProtectiveArmor;
import me.spacetoastdev.honeymod.implementation.items.armor.HoneymodArmorPiece;

class MockHazmatSuit extends HoneymodArmorPiece implements ProtectiveArmor {

    public MockHazmatSuit(Category category, SlimefunItemStack item) {
        super(category, item, RecipeType.NULL, new ItemStack[9], new PotionEffect[0]);
    }

    @Override
    public ProtectionType[] getProtectionTypes() {
        return new ProtectionType[] { ProtectionType.RADIATION };
    }

    @Override
    public boolean isFullSetRequired() {
        return false;
    }

    @Override
    public NamespacedKey getArmorSetId() {
        return new NamespacedKey(getAddon().getJavaPlugin(), "mock_hazmat");
    }

}
