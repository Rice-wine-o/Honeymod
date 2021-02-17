package me.spacetoastdev.honeymod.testing.tests.items.backpacks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.backpacks.EnderBackpack;
import me.spacetoastdev.honeymod.testing.TestUtilities;
import me.spacetoastdev.honeymod.testing.interfaces.SlimefunItemTest;

class TestEnderBackpack implements SlimefunItemTest<EnderBackpack> {

    private static ServerMock server;
    private static HoneymodPlugin plugin;

    @BeforeAll
    public static void load() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(HoneymodPlugin.class);
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @Override
    public EnderBackpack registerSlimefunItem(HoneymodPlugin plugin, String id) {
        SlimefunItemStack item = new SlimefunItemStack(id, Material.ENDER_CHEST, "&5Test Ender Backpack");
        EnderBackpack backpack = new EnderBackpack(TestUtilities.getCategory(plugin, "ender_backpack"), item, RecipeType.NULL, new ItemStack[9]);
        backpack.register(plugin);
        return backpack;
    }

    @Test
    @DisplayName("Test Ender Backpack opening Enderchest")
    void testRightClickBehaviour() {
        Player player = server.addPlayer();
        EnderBackpack backpack = registerSlimefunItem(plugin, "TEST_ENDER_BACKPACK");

        simulateRightClick(player, backpack);

        // We expect the Enderchest to be open now
        Assertions.assertEquals(player.getEnderChest(), player.getOpenInventory().getTopInventory());
    }

}
