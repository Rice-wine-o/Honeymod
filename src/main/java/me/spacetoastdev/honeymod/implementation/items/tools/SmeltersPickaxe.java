package me.spacetoastdev.honeymod.implementation.items.tools;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.DamageableItem;
import me.spacetoastdev.honeymod.core.handlers.ToolUseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;
import me.spacetoastdev.honeymod.utils.tags.HoneymodTag;

/**
 * The {@link SmeltersPickaxe} automatically smelts any ore you mine.
 * 
 * @author TheBusyBiscuit
 *
 */
public class SmeltersPickaxe extends SimpleHoneymodItem<ToolUseHandler> implements DamageableItem {

    @ParametersAreNonnullByDefault
    public SmeltersPickaxe(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            Block b = e.getBlock();

            if (HoneymodTag.SMELTERS_PICKAXE_BLOCKS.isTagged(b.getType()) && !BlockStorage.hasBlockInfo(b)) {
                Collection<ItemStack> blockDrops = b.getDrops(getItem());

                for (ItemStack drop : blockDrops) {
                    if (drop != null && drop.getType() != Material.AIR) {
                        smelt(b, drop, fortune);
                        drops.add(drop);
                    }
                }

                damageItem(e.getPlayer(), tool);
            }
        };
    }

    @ParametersAreNonnullByDefault
    private void smelt(Block b, ItemStack drop, int fortune) {
        Optional<ItemStack> furnaceOutput = HoneymodPlugin.getMinecraftRecipeService().getFurnaceOutput(drop);

        if (furnaceOutput.isPresent()) {
            b.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
            drop.setType(furnaceOutput.get().getType());
            drop.setAmount(fortune);
        }
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

}
