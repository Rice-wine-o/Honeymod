package me.spacetoastdev.honeymod.testing.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import me.spacetoastdev.honeymod.api.MinecraftVersion;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

class TestPluginClass {

    private static HoneymodPlugin plugin;

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
    @DisplayName("Verify that we are in a UNIT_TEST environment")
    void verifyTestEnvironment() {
        MinecraftVersion version = HoneymodPlugin.getMinecraftVersion();

        Assertions.assertTrue(plugin.isUnitTest());
        Assertions.assertEquals(MinecraftVersion.UNIT_TEST, version);
        Assertions.assertEquals("Unit Test Environment", version.getName());
    }

    @Test
    @DisplayName("Verify that config files were loaded")
    void testConfigs() {
        Assertions.assertNotNull(HoneymodPlugin.getCfg());
        Assertions.assertNotNull(HoneymodPlugin.getResearchCfg());
        Assertions.assertNotNull(HoneymodPlugin.getItemCfg());
    }

    @Test
    @DisplayName("Test some static Getters")
    void testGetters() {
        Assertions.assertNotNull(HoneymodPlugin.getTickerTask());
        Assertions.assertNotNull(HoneymodPlugin.getVersion());
        Assertions.assertNotNull(HoneymodPlugin.getRegistry());
        Assertions.assertNotNull(HoneymodPlugin.getCommand());
        Assertions.assertNotNull(HoneymodPlugin.getGPSNetwork());
        Assertions.assertNotNull(HoneymodPlugin.getNetworkManager());
        Assertions.assertNotNull(HoneymodPlugin.getProfiler());
    }

    @Test
    @DisplayName("Test some Services being not-null")
    void testServicesNotNull() {
        Assertions.assertNotNull(HoneymodPlugin.getLocalization());
        Assertions.assertNotNull(HoneymodPlugin.getMinecraftRecipeService());
        Assertions.assertNotNull(HoneymodPlugin.getItemDataService());
        Assertions.assertNotNull(HoneymodPlugin.getItemTextureService());
        Assertions.assertNotNull(HoneymodPlugin.getPermissionsService());
        Assertions.assertNotNull(HoneymodPlugin.getBlockDataService());
        Assertions.assertNotNull(HoneymodPlugin.getIntegrations());
        Assertions.assertNotNull(HoneymodPlugin.getWorldSettingsService());
        Assertions.assertNotNull(HoneymodPlugin.getGitHubService());
        Assertions.assertNotNull(HoneymodPlugin.getUpdater());
        Assertions.assertNotNull(HoneymodPlugin.getMetricsService());
    }

    @Test
    @DisplayName("Test some Listeners being not-null")
    void testListenersNotNull() {
        Assertions.assertNotNull(HoneymodPlugin.getGrapplingHookListener());
        Assertions.assertNotNull(HoneymodPlugin.getBackpackListener());
        Assertions.assertNotNull(HoneymodPlugin.getBowListener());
    }

}
