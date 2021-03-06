package me.spacetoastdev.honeymod.testing.tests.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import be.seeseemelk.mockbukkit.MockBukkit;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.Radioactivity;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.RadioactiveItem;
import me.spacetoastdev.honeymod.testing.TestUtilities;

class TestRadioactiveItem {

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

    @ParameterizedTest
    @EnumSource(value = Radioactivity.class)
    @DisplayName("Test radioactive items being radioactive")
    void testWikiPages(Radioactivity radioactivity) {
        Category category = TestUtilities.getCategory(plugin, "radioactivity_test");
        SlimefunItemStack stack = new SlimefunItemStack("RADIOACTIVE_" + radioactivity.name(), Material.EMERALD, "&4Radioactive!!!", "Imagine dragons");
        RadioactiveItem item = new RadioactiveItem(category, radioactivity, stack, RecipeType.NULL, new ItemStack[9]);

        Assertions.assertEquals(radioactivity, item.getRadioactivity());
    }

}
