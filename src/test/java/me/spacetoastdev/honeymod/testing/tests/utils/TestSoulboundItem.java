package me.spacetoastdev.honeymod.testing.tests.utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.Soulbound;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

class TestSoulboundItem {

    private static HoneymodPlugin plugin;

    @BeforeAll
    public static void load() {
        MockBukkit.mock();
        plugin = MockBukkit.load(HoneymodPlugin.class);
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @Test
    @DisplayName("Verify that null and air throw Illegal Argument Exceptions")
    void testNullAndAir() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> HoneymodUtils.setSoulbound(null, true));

        ItemStack item = new ItemStack(Material.AIR);
        Assertions.assertThrows(IllegalArgumentException.class, () -> HoneymodUtils.setSoulbound(item, true));

        Assertions.assertFalse(HoneymodUtils.isSoulbound(null));
        Assertions.assertFalse(HoneymodUtils.isSoulbound(item));
    }

    @Test
    @DisplayName("Test whether an Item can be marked as soulbound")
    void testSetSoulbound() {
        ItemStack item = new CustomItem(Material.DIAMOND, "&cI wanna be soulbound!");

        Assertions.assertFalse(HoneymodUtils.isSoulbound(item));

        HoneymodUtils.setSoulbound(item, true);
        Assertions.assertTrue(HoneymodUtils.isSoulbound(item));
        Assertions.assertEquals(1, item.getItemMeta().getLore().size());

        HoneymodUtils.setSoulbound(item, false);
        Assertions.assertFalse(HoneymodUtils.isSoulbound(item));
        Assertions.assertEquals(0, item.getItemMeta().getLore().size());
    }

    @Test
    @DisplayName("Make sure that marking an item as soulbound twice has no effect")
    void testDoubleCalls() {
        ItemStack item = new CustomItem(Material.DIAMOND, "&cI wanna be soulbound!");

        HoneymodUtils.setSoulbound(item, true);
        HoneymodUtils.setSoulbound(item, true);
        Assertions.assertTrue(HoneymodUtils.isSoulbound(item));
        Assertions.assertEquals(1, item.getItemMeta().getLore().size());

        HoneymodUtils.setSoulbound(item, false);
        HoneymodUtils.setSoulbound(item, false);
        Assertions.assertFalse(HoneymodUtils.isSoulbound(item));
        Assertions.assertEquals(0, item.getItemMeta().getLore().size());
    }

    @Test
    @DisplayName("Test that soulbound Slimefun Items are soulbound")
    void testSoulboundSlimefunItem() {
        SlimefunItem item = new SoulboundMock(new Category(new NamespacedKey(plugin, "soulbound_category"), new CustomItem(Material.REDSTONE, "&4Walshrus forever")));
        item.register(plugin);

        Assertions.assertTrue(HoneymodUtils.isSoulbound(item.getItem()));
    }

    private class SoulboundMock extends SlimefunItem implements Soulbound {

        public SoulboundMock(Category category) {
            super(category, new SlimefunItemStack("MOCK_SOULBOUND", Material.REDSTONE, "&4Almighty Redstone"), RecipeType.NULL, new ItemStack[9]);
        }

    }

}
