package me.spacetoastdev.honeymod.testing.tests.items.tools;

import java.util.stream.Stream;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.block.BlockMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.events.ClimbingPickLaunchEvent;
import me.spacetoastdev.honeymod.api.exceptions.TagMisconfigurationException;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.tools.ClimbingPick;
import me.spacetoastdev.honeymod.testing.TestUtilities;
import me.spacetoastdev.honeymod.testing.interfaces.SlimefunItemTest;
import me.spacetoastdev.honeymod.utils.tags.HoneymodTag;

class TestClimbingPick implements SlimefunItemTest<ClimbingPick> {

    private static final double STRONG_SURFACE_DEFAULT = 1.0;
    private static final double WEAK_SURFACE_DEFAULT = 0.6;

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
    public ClimbingPick registerSlimefunItem(HoneymodPlugin plugin, String id) {
        SlimefunItemStack item = new SlimefunItemStack(id, Material.IRON_PICKAXE, "&5Test Pick", id);
        ClimbingPick pick = new ClimbingPick(TestUtilities.getCategory(plugin, "climbing_pick"), item, RecipeType.NULL, new ItemStack[9]) {

            @Override
            public boolean isDualWieldingEnabled() {
                return false;
            }

        };

        pick.register(plugin);
        Assertions.assertFalse(pick.getClimbableSurfaces().isEmpty());
        return pick;
    }

    @ParameterizedTest
    @DisplayName("Test Climbing Pick on strong surfaces")
    @MethodSource("getStrongSurfaces")
    void testStrongSurfaces(Material surface) {
        ClimbingPick pick = registerSlimefunItem(plugin, "STRONG_CLIMBING_PICK_" + surface.name());
        double speed = pick.getClimbingSpeed(surface);

        Assertions.assertTrue(HoneymodTag.CLIMBING_PICK_STRONG_SURFACES.isTagged(surface));
        Assertions.assertEquals(STRONG_SURFACE_DEFAULT, speed);
        Assertions.assertEquals(1, pick.getClimbableSurfaces().stream().filter(s -> s.getType() == surface).count());
    }

    private static Stream<Arguments> getStrongSurfaces() throws TagMisconfigurationException {
        HoneymodTag.reloadAll();
        return HoneymodTag.CLIMBING_PICK_STRONG_SURFACES.getValues().stream().map(Arguments::of);
    }

    @ParameterizedTest
    @DisplayName("Test Climbing Pick on weak surfaces")
    @MethodSource("getWeakSurfaces")
    void testWeakSurfaces(Material surface) {
        ClimbingPick pick = registerSlimefunItem(plugin, "WEAK_CLIMBING_PICK_" + surface.name());
        double speed = pick.getClimbingSpeed(surface);

        Assertions.assertTrue(HoneymodTag.CLIMBING_PICK_WEAK_SURFACES.isTagged(surface));
        Assertions.assertEquals(WEAK_SURFACE_DEFAULT, speed);
        Assertions.assertEquals(1, pick.getClimbableSurfaces().stream().filter(s -> s.getType() == surface).count());
    }

    private static Stream<Arguments> getWeakSurfaces() throws TagMisconfigurationException {
        HoneymodTag.reloadAll();
        return HoneymodTag.CLIMBING_PICK_WEAK_SURFACES.getValues().stream().map(Arguments::of);
    }

    @Test
    @DisplayName("Test Climbing Pick on climbable surface")
    void testClimbable() {
        ClimbingPick pick = registerSlimefunItem(plugin, "WEAK_CLIMBING_PICK");
        double speed = pick.getClimbingSpeed(Material.ICE);

        Assertions.assertTrue(HoneymodTag.CLIMBING_PICK_SURFACES.isTagged(Material.ICE));
        Assertions.assertTrue(speed > 0);
    }

    @Test
    @DisplayName("Test Climbing Pick on non-climbable surface")
    void testNonClimbable() {
        ClimbingPick pick = registerSlimefunItem(plugin, "NOT_CLIMBING_PICK");
        double speed = pick.getClimbingSpeed(Material.DRAGON_EGG);

        Assertions.assertFalse(HoneymodTag.CLIMBING_PICK_SURFACES.isTagged(Material.DRAGON_EGG));
        Assertions.assertEquals(0, speed);
    }

    @ParameterizedTest
    @DisplayName("Test Climbing Pick on various Block Faces")
    @EnumSource(value = BlockFace.class, names = { "UP", "DOWN", "NORTH", "EAST", "SOUTH", "WEST" })
    void testItemUse(BlockFace face) {
        PlayerMock player = server.addPlayer();
        ClimbingPick pick = registerSlimefunItem(plugin, "TEST_CLIMBING_PICK_" + face.name());
        Location blockLocation = new Location(player.getLocation().getWorld(), player.getLocation().getBlockX() + 1, player.getLocation().getBlockY(), player.getLocation().getBlockZ());

        boolean shouldFireEvent = face != BlockFace.DOWN && face != BlockFace.UP;

        BlockMock block = new BlockMock(Material.ICE, blockLocation);
        simulateRightClickBlock(player, pick, pick.getItem().clone(), block, face);

        if (shouldFireEvent) {
            Assertions.assertTrue(pick.getClimbingSpeed(block.getType()) > 0);
            Assertions.assertTrue(player.getVelocity().length() > 0);
            server.getPluginManager().assertEventFired(ClimbingPickLaunchEvent.class, e -> e.getPlayer() == player && e.getPick() == pick);
        } else {
            Assertions.assertEquals(0, player.getVelocity().length());
        }
    }

    @Test
    @DisplayName("Test Climbing Pick Efficiency modifier")
    void testEfficiency() {
        Material surface = Material.ICE;

        ClimbingPick pick = registerSlimefunItem(plugin, "TEST_CLIMBING_PICK_EFFICIENCY");
        ItemStack efficiency0 = pick.getItem().clone();
        ItemStack efficiency1 = getPickWithEfficiency(pick, 1);
        ItemStack efficiency2 = getPickWithEfficiency(pick, 2);
        ItemStack efficiency3 = getPickWithEfficiency(pick, 3);

        Assertions.assertEquals(pick.getClimbingSpeed(surface), pick.getClimbingSpeed(efficiency0, surface));
        Assertions.assertTrue(pick.getClimbingSpeed(efficiency1, surface) > pick.getClimbingSpeed(efficiency0, surface));
        Assertions.assertTrue(pick.getClimbingSpeed(efficiency2, surface) > pick.getClimbingSpeed(efficiency1, surface));
        Assertions.assertTrue(pick.getClimbingSpeed(efficiency3, surface) > pick.getClimbingSpeed(efficiency2, surface));
    }

    private ItemStack getPickWithEfficiency(@Nonnull ClimbingPick pick, int level) {
        ItemStack item = pick.getItem().clone();
        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, level);
        return item;
    }
}
