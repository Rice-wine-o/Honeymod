package me.spacetoastdev.honeymod.implementation.items.tools;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import io.papermc.lib.PaperLib;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ToolUseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;
import me.spacetoastdev.honeymod.implementation.items.blocks.AbstractMonsterSpawner;
import me.spacetoastdev.honeymod.implementation.items.blocks.BrokenSpawner;
import me.spacetoastdev.honeymod.implementation.items.blocks.RepairedSpawner;

/**
 * The {@link PickaxeOfContainment} is a Pickaxe that allows you to break Spawners.
 * Upon breaking a Spawner, a {@link BrokenSpawner} will be dropped.
 * But it also allows you to break a {@link RepairedSpawner}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see BrokenSpawner
 * @see RepairedSpawner
 *
 */
public class PickaxeOfContainment extends SimpleHoneymodItem<ToolUseHandler> {

    public PickaxeOfContainment(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            Block b = e.getBlock();

            if (b.getType() == Material.SPAWNER) {
                ItemStack spawner = breakSpawner(b);
                b.getLocation().getWorld().dropItemNaturally(b.getLocation(), spawner);

                e.setExpToDrop(0);
                e.setDropItems(false);
            }
        };
    }

    @Nonnull
    private ItemStack breakSpawner(@Nonnull Block b) {
        AbstractMonsterSpawner spawner;

        /**
         * If the spawner's BlockStorage has BlockInfo, then it's not a vanilla spawner
         * and should not give a broken spawner but a repaired one instead.
         */
        if (BlockStorage.hasBlockInfo(b)) {
            spawner = (AbstractMonsterSpawner) HoneymodItems.REPAIRED_SPAWNER.getItem();
        } else {
            spawner = (AbstractMonsterSpawner) HoneymodItems.BROKEN_SPAWNER.getItem();
        }

        BlockState state = PaperLib.getBlockState(b, false).getState();

        if (state instanceof CreatureSpawner) {
            EntityType entityType = ((CreatureSpawner) state).getSpawnedType();
            return spawner.getItemForEntityType(entityType);
        }

        return new ItemStack(Material.SPAWNER);
    }

}