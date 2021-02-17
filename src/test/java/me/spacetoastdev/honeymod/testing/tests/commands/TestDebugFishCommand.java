package me.spacetoastdev.honeymod.testing.tests.commands;

import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

class TestDebugFishCommand {

    private static ServerMock server;

    @BeforeAll
    public static void load() {
        server = MockBukkit.mock();
        MockBukkit.load(HoneymodPlugin.class);
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    @DisplayName("Test if Debug Fish is given on /hm debug_fish")
    void testCommand(boolean op) {
        Player player = server.addPlayer();
        player.setOp(op);
        server.execute("honeymod", player, "debug_fish").assertSucceeded();

        Assertions.assertEquals(op, HoneymodUtils.containsSimilarItem(player.getInventory(), HoneymodItems.DEBUG_FISH, true));
    }
}
