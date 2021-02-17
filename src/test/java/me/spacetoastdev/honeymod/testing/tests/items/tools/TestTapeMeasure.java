package me.spacetoastdev.honeymod.testing.tests.items.tools;

import java.util.Optional;
import java.util.OptionalDouble;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.block.BlockMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.tools.TapeMeasure;
import me.spacetoastdev.honeymod.testing.TestUtilities;
import me.spacetoastdev.honeymod.testing.interfaces.SlimefunItemTest;

class TestTapeMeasure implements SlimefunItemTest<TapeMeasure> {

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
    public TapeMeasure registerSlimefunItem(HoneymodPlugin plugin, String id) {
        SlimefunItemStack item = new SlimefunItemStack(id, Material.PLAYER_HEAD, "&5Test Pick", id);
        TapeMeasure tapeMeasure = new TapeMeasure(TestUtilities.getCategory(plugin, "tape_measure"), item, RecipeType.NULL, new ItemStack[9]);
        tapeMeasure.register(plugin);
        return tapeMeasure;
    }

    @Test
    @DisplayName("Test setting anchor")
    void testAnchor() {
        TapeMeasure tm = registerSlimefunItem(plugin, "TEST_ANCHOR");
        Player player = server.addPlayer();
        ItemStack item = tm.getItem().clone();
        Location loc = new Location(player.getWorld(), 10, 60, 1000);
        BlockMock block = new BlockMock(loc);

        player.setSneaking(true);
        simulateRightClickBlock(player, tm, item, block, BlockFace.UP);

        Optional<Location> anchor = tm.getAnchor(player, item);
        Assertions.assertTrue(anchor.isPresent());
        Assertions.assertEquals(loc, anchor.get());
    }

    @Test
    @DisplayName("Test measuring distance")
    void testMeasuring() {
        TapeMeasure tm = registerSlimefunItem(plugin, "TEST_MEASURE_DISTANCE");
        Player player = server.addPlayer();
        ItemStack item = tm.getItem().clone();

        Location loc1 = new Location(player.getWorld(), 10, 60, 1000);
        BlockMock block1 = new BlockMock(loc1);

        Location loc2 = new Location(player.getWorld(), 10, 60, 1080);
        BlockMock block2 = new BlockMock(loc2);

        player.setSneaking(true);
        simulateRightClickBlock(player, tm, item, block1, BlockFace.UP);

        OptionalDouble distance = tm.getDistance(player, item, block2);
        Assertions.assertTrue(distance.isPresent());
        Assertions.assertEquals(loc1.distance(loc2), distance.getAsDouble());
    }

    @Test
    @DisplayName("Test measuring player")
    void testMeasuringFeedback() {
        TapeMeasure tm = registerSlimefunItem(plugin, "TEST_MEASURE_FEEDBACK");
        PlayerMock player = server.addPlayer();
        ItemStack item = tm.getItem().clone();

        Location loc1 = new Location(player.getWorld(), 10, 60, 1000);
        BlockMock block1 = new BlockMock(loc1);

        Location loc2 = new Location(player.getWorld(), 10, 60, 1080);
        BlockMock block2 = new BlockMock(loc2);

        player.setSneaking(true);
        simulateRightClickBlock(player, tm, item, block1, BlockFace.UP);

        player.setSneaking(false);
        simulateRightClickBlock(player, tm, item, block2, BlockFace.UP);

        player.assertSoundHeard(Sound.ITEM_BOOK_PUT);
    }

    @Test
    @DisplayName("Test measuring without anchor")
    void testNoAnchor() {
        TapeMeasure tm = registerSlimefunItem(plugin, "TEST_MEASURE_NO_ANCHOR");
        Player player = server.addPlayer();
        ItemStack item = tm.getItem().clone();

        Location loc = new Location(player.getWorld(), 10, 60, 50);
        BlockMock block = new BlockMock(loc);

        OptionalDouble distance = tm.getDistance(player, item, block);
        Assertions.assertFalse(distance.isPresent());
    }

    @Test
    @DisplayName("Test anchor in different world")
    void testOtherWorld() {
        TapeMeasure tm = registerSlimefunItem(plugin, "TEST_ANCHOR_DIFFERENT_WORLD");
        Player player = server.addPlayer();
        ItemStack item = tm.getItem().clone();

        Location loc1 = new Location(player.getWorld(), 10, 60, 1000);
        BlockMock block1 = new BlockMock(loc1);

        player.setSneaking(true);
        simulateRightClickBlock(player, tm, item, block1, BlockFace.UP);

        Assertions.assertTrue(tm.getAnchor(player, item).isPresent());

        Location loc2 = new Location(new WorldMock(), 10, 60, 1080);

        player.teleport(loc2);
        Assertions.assertFalse(tm.getAnchor(player, item).isPresent());
    }
}
