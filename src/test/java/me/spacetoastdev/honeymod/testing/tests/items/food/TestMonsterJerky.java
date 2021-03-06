package me.spacetoastdev.honeymod.testing.tests.items.food;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
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
import me.spacetoastdev.honeymod.implementation.items.food.MonsterJerky;
import me.spacetoastdev.honeymod.testing.TestUtilities;
import me.spacetoastdev.honeymod.testing.interfaces.SlimefunItemTest;

class TestMonsterJerky implements SlimefunItemTest<MonsterJerky> {

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
    public MonsterJerky registerSlimefunItem(HoneymodPlugin plugin, String id) {
        SlimefunItemStack item = new SlimefunItemStack(id, Material.ROTTEN_FLESH, "&5Test Monster Jerky");
        MonsterJerky jerky = new MonsterJerky(TestUtilities.getCategory(plugin, "monster_jerky"), item, RecipeType.NULL, new ItemStack[9]);
        jerky.register(plugin);
        return jerky;
    }

    @Test
    @DisplayName("Test Monster Jerky giving Saturation and removing Hunger")
    void testConsumptionBehaviour() {
        PlayerMock player = server.addPlayer();
        player.addPotionEffect(PotionEffectType.HUNGER.createEffect(20, 2));
        MonsterJerky jerky = registerSlimefunItem(plugin, "TEST_MONSTER_JERKY");

        simulateConsumption(player, jerky);

        Assertions.assertFalse(player.hasPotionEffect(PotionEffectType.HUNGER));
        Assertions.assertTrue(player.hasPotionEffect(PotionEffectType.SATURATION));
    }

}
