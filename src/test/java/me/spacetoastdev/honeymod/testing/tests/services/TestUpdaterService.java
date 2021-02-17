package me.spacetoastdev.honeymod.testing.tests.services;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import me.spacetoastdev.honeymod.api.HoneymodBranch;
import me.spacetoastdev.honeymod.core.services.UpdaterService;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

class TestUpdaterService {

    private static HoneymodPlugin plugin;

    private final File file = new File("test.jar");

    @BeforeAll
    public static void load() {
        MockBukkit.mock();
        plugin = MockBukkit.load(HoneymodPlugin.class);
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @Test
    @DisplayName("Test if the development branch is recognized correctly")
    void testDevelopmentBuilds() {
        UpdaterService service = new UpdaterService(plugin, "DEV - 131 (git 123456)", file);
        Assertions.assertEquals(HoneymodBranch.DEVELOPMENT, service.getBranch());
        Assertions.assertTrue(service.getBranch().isOfficial());
        // Cannot currently be tested... yay
        // Assertions.assertEquals(131, service.getBuildNumber());
    }

    @Test
    @DisplayName("Test if the stable branch is recognized correctly")
    void testStableBuilds() {
        UpdaterService service = new UpdaterService(plugin, "RC - 6 (git 123456)", file);
        Assertions.assertEquals(HoneymodBranch.STABLE, service.getBranch());
        Assertions.assertTrue(service.getBranch().isOfficial());
        // Cannot currently be tested... yay
        // Assertions.assertEquals(6, service.getBuildNumber());
    }

    @Test
    @DisplayName("Test if an unofficial build is recognized correctly")
    void testUnofficialBuilds() {
        UpdaterService service = new UpdaterService(plugin, "4.20 UNOFFICIAL", file);
        Assertions.assertEquals(HoneymodBranch.UNOFFICIAL, service.getBranch());
        Assertions.assertFalse(service.getBranch().isOfficial());
        Assertions.assertEquals(-1, service.getBuildNumber());
    }

    @Test
    @DisplayName("Test if unknown builds are caught")
    void testUnknownBuilds() {
        UpdaterService service = new UpdaterService(plugin, "I am special", file);
        Assertions.assertEquals(HoneymodBranch.UNKNOWN, service.getBranch());
        Assertions.assertFalse(service.getBranch().isOfficial());
        Assertions.assertEquals(-1, service.getBuildNumber());
    }
}
