package me.spacetoastdev.honeymod.testing.tests.items.food;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.food.MeatJerky;
import me.spacetoastdev.honeymod.testing.TestUtilities;
import me.spacetoastdev.honeymod.testing.interfaces.SlimefunItemTest;

class TestMeatJerky implements SlimefunItemTest<MeatJerky> {

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
    public MeatJerky registerSlimefunItem(HoneymodPlugin plugin, String id) {
        SlimefunItemStack item = new SlimefunItemStack(id, Material.COOKED_BEEF, "&5Test Jerky");
        MeatJerky meat = new MeatJerky(TestUtilities.getCategory(plugin, "test_jerky"), item, RecipeType.NULL, new ItemStack[9]);
        meat.register(plugin);
        return meat;
    }

    @Test
    @DisplayName("Test Meat Jerky giving extra saturation")
    void testConsumptionBehaviour() {
        PlayerMock player = server.addPlayer();
        MeatJerky jerky = registerSlimefunItem(plugin, "TEST_MEAT_JERKY");
        float saturation = player.getSaturation();

        simulateConsumption(player, jerky);

        // Saturation should have increased
        Assertions.assertTrue(player.getSaturation() > saturation);
    }

}
