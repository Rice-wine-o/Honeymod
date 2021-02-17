package me.spacetoastdev.honeymod.testing.tests.tags;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import me.spacetoastdev.honeymod.api.exceptions.TagMisconfigurationException;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.tags.HoneymodTag;

class TestSlimefunTags {

    @BeforeAll
    public static void load() {
        MockBukkit.mock();
        MockBukkit.load(HoneymodPlugin.class);
    }

    @AfterAll
    public static void unload() {
        MockBukkit.unmock();
    }

    @Test
    @DisplayName("Test for Exceptions with Slimefun Tags")
    void testTags() {
        for (HoneymodTag tag : HoneymodTag.values()) {
            Assertions.assertDoesNotThrow(tag::reload);
        }
    }

    @Test
    @DisplayName("Test for infinite loops with Slimefun Tags")
    void testForInfiniteLoops() throws TagMisconfigurationException {
        HoneymodTag.reloadAll();

        for (HoneymodTag tag : HoneymodTag.values()) {
            assertNotCyclic(tag);
        }
    }

    @Test
    @DisplayName("Test HoneymodTag#isTagged()")
    void testIsTagged() throws TagMisconfigurationException {
        HoneymodTag.reloadAll();

        // Direct inclusion
        Assertions.assertTrue(HoneymodTag.SENSITIVE_MATERIALS.isTagged(Material.CAKE));

        // Inclusion through a Minecraft Tag
        Assertions.assertTrue(HoneymodTag.SENSITIVE_MATERIALS.isTagged(Material.OAK_SAPLING));

        // Inclusion through a Slimefun Tag
        Assertions.assertTrue(HoneymodTag.SENSITIVE_MATERIALS.isTagged(Material.TORCH));
        Assertions.assertTrue(HoneymodTag.SENSITIVE_MATERIALS.isTagged(Material.OAK_PRESSURE_PLATE));
    }

    @Test
    @DisplayName("Test HoneymodTag#toArray()")
    void testToArray() throws TagMisconfigurationException {
        HoneymodTag.reloadAll();

        for (HoneymodTag tag : HoneymodTag.values()) {
            Set<Material> values = tag.getValues();
            Assertions.assertArrayEquals(values.toArray(new Material[0]), tag.toArray());
        }
    }

    @Test
    @DisplayName("Test HoneymodTag#getValues()")
    void testGetValues() throws TagMisconfigurationException {
        HoneymodTag.reloadAll();

        for (HoneymodTag tag : HoneymodTag.values()) {
            Set<Material> values = tag.getValues();

            Assertions.assertFalse(values.isEmpty());

            for (Material value : tag.getValues()) {
                // All values of our tag must be tagged
                Assertions.assertTrue(tag.isTagged(value));
            }

            for (Tag<Material> sub : tag.getSubTags()) {
                for (Material value : sub.getValues()) {
                    // All values of sub tags should be tagged by our tag too
                    Assertions.assertTrue(tag.isTagged(value));
                }
            }
        }
    }

    @Test
    @DisplayName("Test HoneymodTag#stream()")
    void testStream() throws TagMisconfigurationException {
        HoneymodTag.reloadAll();

        for (HoneymodTag tag : HoneymodTag.values()) {
            Set<Material> values = tag.getValues();
            Stream<Material> stream = tag.stream();

            Assertions.assertEquals(values, stream.collect(Collectors.toSet()));
        }
    }

    @Test
    @DisplayName("Test static HoneymodTag accessors")
    void testGetTag() {
        Assertions.assertEquals(HoneymodTag.GLASS_BLOCKS, HoneymodTag.getTag("GLASS_BLOCKS"));
        Assertions.assertEquals(HoneymodTag.ORES, HoneymodTag.getTag("ORES"));
        Assertions.assertEquals(HoneymodTag.SHULKER_BOXES, HoneymodTag.getTag("SHULKER_BOXES"));
        Assertions.assertNull(HoneymodTag.getTag("hello"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> HoneymodTag.getTag(null));
    }

    private void assertNotCyclic(@Nonnull HoneymodTag tag) {
        Set<HoneymodTag> visiting = new HashSet<>();
        Set<HoneymodTag> visited = new HashSet<>();

        if (isCyclic(visiting, visited, tag)) {
            System.out.println("Currently visiting: " + visiting);
            System.out.println("Previously visited" + visiting);
            Assertions.fail("Tag '" + tag.getKey() + "' is cyclic!");
        }
    }

    @ParametersAreNonnullByDefault
    private boolean isCyclic(Set<HoneymodTag> visiting, Set<HoneymodTag> visited, HoneymodTag tag) {
        visiting.add(tag);

        for (Tag<Material> sub : tag.getSubTags()) {
            if (sub instanceof HoneymodTag) {
                if (visiting.contains(sub)) {
                    return true;
                } else if (!visited.contains(sub) && isCyclic(visiting, visited, (HoneymodTag) sub)) {
                    return true;
                }
            }
        }

        visiting.remove(tag);
        visited.add(tag);
        return false;
    }

}
