package me.spacetoastdev.honeymod.testing.tests.guide;

import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.core.guide.GuideHistory;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideImplementation;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.testing.TestUtilities;

class TestGuideOpening {

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

    private PlayerProfile prepare(HoneymodGuideImplementation guide, Consumer<GuideHistory> consumer) throws InterruptedException {
        Player player = server.addPlayer();
        PlayerProfile profile = TestUtilities.awaitProfile(player);
        GuideHistory history = profile.getGuideHistory();
        consumer.accept(history);
        history.openLastEntry(guide);
        return profile;
    }

    @Test
    @DisplayName("Test if the Slimefun Guide Main Menu can be opened from the History")
    void testOpenMainMenu() throws InterruptedException {
        HoneymodGuideImplementation guide = Mockito.mock(HoneymodGuideImplementation.class);
        PlayerProfile profile = prepare(guide, history -> {});
        Mockito.verify(guide).openMainMenu(profile, 1);
    }

    @Test
    @DisplayName("Test if a Category can be opened from the History")
    void testOpenCategory() throws InterruptedException {
        Category category = new Category(new NamespacedKey(plugin, "history_category"), new CustomItem(Material.BLUE_TERRACOTTA, "&9Testy test"));

        HoneymodGuideImplementation guide = Mockito.mock(HoneymodGuideImplementation.class);
        PlayerProfile profile = prepare(guide, history -> history.add(category, 1));
        Mockito.verify(guide).openCategory(profile, category, 1);
    }

    @Test
    @DisplayName("Test if a SlimefunItem can be viewed from the History")
    void testOpenSlimefunItem() throws InterruptedException {
        SlimefunItem item = TestUtilities.mockSlimefunItem(plugin, "OPEN_SLIMEFUN_ITEM", new CustomItem(Material.PRISMARINE_SHARD, "&5Some Shard I guess"));

        HoneymodGuideImplementation guide = Mockito.mock(HoneymodGuideImplementation.class);
        PlayerProfile profile = prepare(guide, history -> history.add(item));
        Mockito.verify(guide).displayItem(profile, item, false);
    }

    @Test
    @DisplayName("Test if an ItemStack can be viewed from the History")
    void testOpenItemStack() throws InterruptedException {
        ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);

        HoneymodGuideImplementation guide = Mockito.mock(HoneymodGuideImplementation.class);
        PlayerProfile profile = prepare(guide, history -> history.add(item, 1));
        Mockito.verify(guide).displayItem(profile, item, 1, false);
    }

    @Test
    @DisplayName("Test if the Slimefun Search can be opened from the History")
    void testOpenSearch() throws InterruptedException {
        String query = "electric";

        HoneymodGuideImplementation guide = Mockito.mock(HoneymodGuideImplementation.class);
        PlayerProfile profile = prepare(guide, history -> history.add(query));
        Mockito.verify(guide).openSearch(profile, query, false);
    }

    @Test
    @DisplayName("Test if the Back button works")
    void testGoBack() throws InterruptedException {
        Player player = server.addPlayer();

        String query = "electric";
        String query2 = "cargo";

        HoneymodGuideImplementation guide = Mockito.mock(HoneymodGuideImplementation.class);

        PlayerProfile profile = TestUtilities.awaitProfile(player);
        GuideHistory history = profile.getGuideHistory();
        history.add(query);
        history.add(query2);
        history.goBack(guide);
        Mockito.verify(guide).openSearch(profile, query, false);
    }

}
