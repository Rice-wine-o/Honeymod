package me.spacetoastdev.honeymod.testing.tests.listeners;

import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import me.spacetoastdev.honeymod.api.events.WaypointCreateEvent;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.listeners.DeathpointListener;
import me.spacetoastdev.honeymod.testing.TestUtilities;

class TestDeathpointListener {

    private static HoneymodPlugin plugin;
    private static ServerMock server;

    @BeforeAll
    public static void load() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(HoneymodPlugin.class);
        new DeathpointListener(plugin);
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @Test
    @DisplayName("Test Deathpoint not triggering when no Emergency Transmitter is found")
    void testNoTransmitter() throws InterruptedException {
        Player player = server.addPlayer();
        TestUtilities.awaitProfile(player);

        player.setHealth(0);
        Assertions.assertThrows(AssertionError.class, () -> server.getPluginManager().assertEventFired(WaypointCreateEvent.class, event -> event.getPlayer() == player && event.isDeathpoint()));
    }

    @Test
    @DisplayName("Test Emergency Transmitter creating a new Waypoint")
    void testTransmitter() throws InterruptedException {
        Player player = server.addPlayer();
        TestUtilities.awaitProfile(player);
        player.getInventory().setItem(8, HoneymodItems.GPS_EMERGENCY_TRANSMITTER.clone());

        player.setHealth(0);
        server.getPluginManager().assertEventFired(WaypointCreateEvent.class, event -> event.getPlayer() == player && event.isDeathpoint());
    }

}
