package me.spacetoastdev.honeymod.implementation.items.tools;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.blocks.Vein;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.cscorelib2.protection.ProtectableAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.items.ItemSetting;
import me.spacetoastdev.honeymod.api.items.settings.IntRangeSetting;
import me.spacetoastdev.honeymod.core.handlers.ToolUseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;
import me.spacetoastdev.honeymod.utils.tags.HoneymodTag;

/**
 * The {@link PickaxeOfVeinMining} is a powerful tool which allows you to mine an entire vein of ores
 * at once. It even works with the fortune {@link Enchantment}.
 * 
 * @author TheBusyBiscuit
 * @author Linox
 *
 */
public class PickaxeOfVeinMining extends SimpleHoneymodItem<ToolUseHandler> {

    private final ItemSetting<Integer> maxBlocks = new IntRangeSetting("max-blocks", 1, 16, Integer.MAX_VALUE);

    @ParametersAreNonnullByDefault
    public PickaxeOfVeinMining(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        addItemSetting(maxBlocks);
    }

    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            if (HoneymodTag.PICKAXE_OF_VEIN_MINING_BLOCKS.isTagged(e.getBlock().getType())) {
                List<Block> blocks = Vein.find(e.getBlock(), maxBlocks.getValue(), b -> HoneymodTag.PICKAXE_OF_VEIN_MINING_BLOCKS.isTagged(b.getType()));
                breakBlocks(e.getPlayer(), blocks, fortune, tool);
            }
        };
    }

    @ParametersAreNonnullByDefault
    private void breakBlocks(Player p, List<Block> blocks, int fortune, ItemStack tool) {
        for (Block b : blocks) {
            if (HoneymodPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.BREAK_BLOCK)) {
                b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType());

                if (tool.containsEnchantment(Enchantment.SILK_TOUCH)) {
                    b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(b.getType()));
                } else {
                    for (ItemStack drop : b.getDrops(tool)) {
                        b.getWorld().dropItemNaturally(b.getLocation(), drop.getType().isBlock() ? drop : new CustomItem(drop, fortune));
                    }
                }

                b.setType(Material.AIR);
            }
        }
    }

}
