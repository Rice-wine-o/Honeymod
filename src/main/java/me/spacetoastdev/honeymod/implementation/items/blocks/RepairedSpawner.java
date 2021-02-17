package me.spacetoastdev.honeymod.implementation.items.blocks;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.events.BlockPlacerPlaceEvent;
import me.spacetoastdev.honeymod.api.items.ItemSetting;
import me.spacetoastdev.honeymod.core.handlers.BlockPlaceHandler;
import me.spacetoastdev.honeymod.core.handlers.BlockUseHandler;
import me.spacetoastdev.honeymod.utils.tags.HoneymodTag;

/**
 * A {@link RepairedSpawner} is the repaired variant of a {@link BrokenSpawner}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see BrokenSpawner
 *
 */
public class RepairedSpawner extends AbstractMonsterSpawner {

    private final ItemSetting<Boolean> allowSpawnEggs = new ItemSetting<>("allow-spawn-eggs", true);

    @ParametersAreNonnullByDefault
    public RepairedSpawner(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemSetting(allowSpawnEggs);

        addItemHandler(onInteract());
        addItemHandler(onPlace());
    }

    @Nonnull
    private BlockUseHandler onInteract() {
        return e -> {
            if (!allowSpawnEggs.getValue() && HoneymodTag.SPAWN_EGGS.isTagged(e.getItem().getType())) {
                // Disallow spawn eggs from being used on Reinforced Spawners if disabled
                e.cancel();
            }
        };
    }

    @Nonnull
    private BlockPlaceHandler onPlace() {
        return new BlockPlaceHandler(true) {

            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
                onPlace(e.getItemInHand(), e);
            }

            @Override
            public void onBlockPlacerPlace(BlockPlacerPlaceEvent e) {
                onPlace(e.getItemStack(), e);
            }

            @ParametersAreNonnullByDefault
            private void onPlace(ItemStack item, BlockEvent e) {
                /**
                 * This may no longer be needed at some point but for legacy items
                 * we still need to set the spawned EntityType manually
                 */
                if (e.getBlock().getType() == Material.SPAWNER) {
                    getEntityType(item).ifPresent(entity -> {
                        CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
                        spawner.setSpawnedType(entity);
                        spawner.update(true, false);
                    });
                }
            }
        };
    }

    @Override
    public Collection<ItemStack> getDrops() {
        /**
         * There should be no drops by default since drops are handled
         * by the Pickaxe of Containment exclusively.
         */
        return new ArrayList<>();
    }

}
