package me.spacetoastdev.honeymod.testing.tests.listeners;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.events.CoolerFeedPlayerEvent;
import me.spacetoastdev.honeymod.api.player.PlayerBackpack;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.backpacks.Cooler;
import me.spacetoastdev.honeymod.implementation.items.food.Juice;
import me.spacetoastdev.honeymod.implementation.listeners.BackpackListener;
import me.spacetoastdev.honeymod.implementation.listeners.CoolerListener;
import me.spacetoastdev.honeymod.testing.TestUtilities;

class TestCoolerListener {

    private static ServerMock server;
    private static CoolerListener listener;

    private static Cooler cooler;
    private static Juice juice;

    @BeforeAll
    public static void load() {
        server = MockBukkit.mock();
        HoneymodPlugin plugin = MockBukkit.load(HoneymodPlugin.class);

        Category category = new Category(new NamespacedKey(plugin, "cooler_test"), new CustomItem(Material.SNOWBALL, "Mr. Freeze"));
        SlimefunItemStack item = new SlimefunItemStack("TEST_COOLER", Material.SNOWBALL, "&6Test Cooler", "", "&7ID: <ID>");
        cooler = new Cooler(18, category, item, RecipeType.NULL, new ItemStack[9]);
        cooler.register(plugin);

        SlimefunItemStack juiceItem = new SlimefunItemStack("TEST_JUICE", Color.RED, new PotionEffect(PotionEffectType.HEALTH_BOOST, 6, 0), "&4Test Juice");
        juice = new Juice(category, juiceItem, RecipeType.NULL, new ItemStack[9]);
        juice.register(plugin);

        listener = new CoolerListener(plugin, cooler);
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @Test
    @DisplayName("Test if Coolers only allow juices")
    void testOnlyJuiceAllowance() {
        Assertions.assertFalse(cooler.isItemAllowed(new ItemStack(Material.DIAMOND), null));
        Assertions.assertFalse(cooler.isItemAllowed(cooler.getItem(), cooler));
        Assertions.assertTrue(cooler.isItemAllowed(juice.getItem(), juice));
    }

    @Test
    @DisplayName("Test if Coolers consume Juices when hunger gets low")
    void testCoolerUsage() throws InterruptedException {
        Player player = server.addPlayer();
        PlayerProfile profile = TestUtilities.awaitProfile(player);
        PlayerBackpack backpack = profile.createBackpack(cooler.getSize());
        backpack.getInventory().setItem(0, juice.getItem().clone());

        ItemStack personalCooler = cooler.getItem().clone();
        new BackpackListener().setBackpackId(player, personalCooler, 1, backpack.getId());
        player.getInventory().setItem(7, personalCooler);

        FoodLevelChangeEvent event = new FoodLevelChangeEvent(player, 16);
        listener.onHungerLoss(event);

        Assertions.assertTrue(player.hasPotionEffect(PotionEffectType.HEALTH_BOOST));
        server.getPluginManager().assertEventFired(CoolerFeedPlayerEvent.class, e -> e.getPlayer() == player && e.getCooler() == cooler);
    }
}
