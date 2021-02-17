package me.spacetoastdev.honeymod.testing.tests.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideMode;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.VanillaItem;
import me.spacetoastdev.honeymod.implementation.listeners.crafting.GrindstoneListener;
import me.spacetoastdev.honeymod.testing.TestUtilities;

public class TestGrindstoneListener {

    private static HoneymodPlugin plugin;
    private static GrindstoneListener listener;
    private static ServerMock server;

    @BeforeAll
    public static void load() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(HoneymodPlugin.class);
        listener = new GrindstoneListener(plugin);
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    private InventoryClickEvent mockGrindStoneEvent(ItemStack item) {
        Player player = server.addPlayer();
        Inventory inv = TestUtilities.mockInventory(InventoryType.GRINDSTONE, item, null);
        InventoryView view = player.openInventory(inv);
        InventoryClickEvent event = new InventoryClickEvent(view, SlotType.CONTAINER, 2, ClickType.LEFT, InventoryAction.PICKUP_ONE);

        listener.onGrindstone(event);
        return event;
    }

    @Test
    public void testGrindStoneWithoutSlimefunItems() {
        InventoryClickEvent event = mockGrindStoneEvent(new ItemStack(Material.ENCHANTED_BOOK));
        Assertions.assertEquals(Result.DEFAULT, event.getResult());
    }

    @Test
    public void testGrindStoneWithSlimefunItem() {
        SlimefunItem item = TestUtilities.mockSlimefunItem(plugin, "ENCHANTED_MOCK_BOOK", new CustomItem(Material.ENCHANTED_BOOK, "&6Mock"));
        item.register(plugin);

        InventoryClickEvent event = mockGrindStoneEvent(item.getItem());
        Assertions.assertEquals(Result.DENY, event.getResult());
    }

    @Test
    public void testGrindStoneWithVanillaItem() {
        VanillaItem item = TestUtilities.mockVanillaItem(plugin, Material.ENCHANTED_BOOK, true);
        item.register(plugin);

        InventoryClickEvent event = mockGrindStoneEvent(item.getItem());
        Assertions.assertEquals(Result.DEFAULT, event.getResult());
    }

    @ParameterizedTest
    @EnumSource(HoneymodGuideMode.class)
    public void testGrindStoneWithSlimefunGuide(HoneymodGuideMode layout) {
        InventoryClickEvent event = mockGrindStoneEvent(HoneymodGuide.getItem(layout));
        Assertions.assertEquals(Result.DENY, event.getResult());
    }

}
