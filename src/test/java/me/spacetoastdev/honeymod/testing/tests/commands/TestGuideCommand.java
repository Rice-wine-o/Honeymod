package me.spacetoastdev.honeymod.testing.tests.commands;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideMode;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

class TestGuideCommand {

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
    @DisplayName("Test if Slimefun Guide is given on /hm guide")
    @ValueSource(booleans = { true, false })
    void testCommand(boolean op) {
        Player player = server.addPlayer();
        player.setOp(op);
        server.execute("honeymod", player, "guide").assertSucceeded();

        ItemStack guide = HoneymodGuide.getItem(HoneymodGuideMode.SURVIVAL_MODE);
        Assertions.assertEquals(op, HoneymodUtils.containsSimilarItem(player.getInventory(), guide, true));
    }
}
