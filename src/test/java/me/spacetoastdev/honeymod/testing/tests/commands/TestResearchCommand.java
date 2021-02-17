package me.spacetoastdev.honeymod.testing.tests.commands;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.core.researching.Research;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.testing.TestUtilities;

class TestResearchCommand {

    private static ServerMock server;

    private static Research research;
    private static Research research2;

    @BeforeAll
    public static void load() {
        server = MockBukkit.mock();

        HoneymodPlugin plugin = MockBukkit.load(HoneymodPlugin.class);
        research = new Research(new NamespacedKey(plugin, "command_test"), 999, "Test", 10);
        research.register();

        research2 = new Research(new NamespacedKey(plugin, "command_test_two"), 1000, "Test Two", 10);
        research2.register();
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @Test
    @DisplayName("Test /hm research all")
    void testResearchAll() throws InterruptedException {
        HoneymodPlugin.getRegistry().setResearchingEnabled(true);
        Player player = server.addPlayer();
        PlayerProfile profile = TestUtilities.awaitProfile(player);

        server.executeConsole("honeymod", "research", player.getName(), "all").assertSucceeded();

        Assertions.assertTrue(profile.hasUnlocked(research));
        Assertions.assertTrue(profile.hasUnlocked(research2));
    }

    @Test
    @DisplayName("Test /hm research <research id>")
    void testResearchSpecific() throws InterruptedException {
        HoneymodPlugin.getRegistry().setResearchingEnabled(true);
        Player player = server.addPlayer();
        PlayerProfile profile = TestUtilities.awaitProfile(player);

        server.executeConsole("honeymod", "research", player.getName(), research.getKey().toString()).assertSucceeded();

        Assertions.assertTrue(profile.hasUnlocked(research));
        Assertions.assertFalse(profile.hasUnlocked(research2));
    }

    @Test
    @DisplayName("Test /hm research reset")
    void testResearchReset() throws InterruptedException {
        HoneymodPlugin.getRegistry().setResearchingEnabled(true);
        Player player = server.addPlayer();
        PlayerProfile profile = TestUtilities.awaitProfile(player);

        server.executeConsole("honeymod", "research", player.getName(), "all").assertSucceeded();

        Assertions.assertTrue(profile.hasUnlocked(research));
        Assertions.assertTrue(profile.hasUnlocked(research2));

        server.executeConsole("honeymod", "research", player.getName(), "reset").assertSucceeded();

        Assertions.assertFalse(profile.hasUnlocked(research));
        Assertions.assertFalse(profile.hasUnlocked(research2));
    }
}
