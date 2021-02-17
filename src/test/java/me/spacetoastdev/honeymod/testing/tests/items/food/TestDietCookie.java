package me.spacetoastdev.honeymod.testing.tests.items.food;

import org.bukkit.Material;
import org.bukkit.Sound;
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
import me.spacetoastdev.honeymod.implementation.items.food.DietCookie;
import me.spacetoastdev.honeymod.testing.TestUtilities;
import me.spacetoastdev.honeymod.testing.interfaces.SlimefunItemTest;

class TestDietCookie implements SlimefunItemTest<DietCookie> {

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
    public DietCookie registerSlimefunItem(HoneymodPlugin plugin, String id) {
        SlimefunItemStack item = new SlimefunItemStack(id, Material.COOKIE, "&5Test Cookie");
        DietCookie cookie = new DietCookie(TestUtilities.getCategory(plugin, "diet_cookie"), item, RecipeType.NULL, new ItemStack[9]);
        cookie.register(plugin);
        return cookie;
    }

    @Test
    @DisplayName("Test Diet Cookies giving Levitation Effect")
    void testConsumptionBehaviour() {
        PlayerMock player = server.addPlayer();
        DietCookie cookie = registerSlimefunItem(plugin, "TEST_DIET_COOKIE");

        simulateConsumption(player, cookie);

        player.assertSoundHeard(Sound.ENTITY_GENERIC_EAT);
        Assertions.assertTrue(player.hasPotionEffect(PotionEffectType.LEVITATION));
    }

}
