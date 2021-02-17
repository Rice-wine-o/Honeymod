package me.spacetoastdev.honeymod.implementation.items.multiblocks;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.inventory.InvUtils;
import io.papermc.lib.PaperLib;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.multiblocks.MultiBlockMachine;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

/**
 * An abstract super class for the {@link Smeltery} and {@link MakeshiftSmeltery}.
 * 
 * @author TheBusyBiscuit
 *
 */
abstract class AbstractSmeltery extends MultiBlockMachine {

    public AbstractSmeltery(Category category, SlimefunItemStack item, ItemStack[] recipe, BlockFace trigger) {
        super(category, item, recipe, trigger);
    }

    @Override
    public void onInteract(Player p, Block b) {
        Block dispBlock = b.getRelative(BlockFace.DOWN);
        BlockState state = PaperLib.getBlockState(dispBlock, false).getState();

        if (state instanceof Dispenser) {
            Dispenser disp = (Dispenser) state;
            Inventory inv = disp.getInventory();
            List<ItemStack[]> inputs = RecipeType.getRecipeInputList(this);

            for (int i = 0; i < inputs.size(); i++) {
                if (canCraft(inv, inputs, i)) {
                    ItemStack output = RecipeType.getRecipeOutputList(this, inputs.get(i)).clone();

                    if (HoneymodUtils.canPlayerUseItem(p, output, true)) {
                        Inventory outputInv = findOutputInventory(output, dispBlock, inv);

                        if (outputInv != null) {
                            craft(p, b, inv, inputs.get(i), output, outputInv);
                        } else {
                            HoneymodPlugin.getLocalization().sendMessage(p, "machines.full-inventory", true);
                        }
                    }

                    return;
                }
            }

            HoneymodPlugin.getLocalization().sendMessage(p, "machines.unknown-material", true);
        }
    }

    private boolean canCraft(Inventory inv, List<ItemStack[]> inputs, int i) {
        for (ItemStack expectedInput : inputs.get(i)) {
            if (expectedInput != null) {
                for (int j = 0; j < inv.getContents().length; j++) {
                    if (j == (inv.getContents().length - 1) && !HoneymodUtils.isItemSimilar(inv.getContents()[j], expectedInput, true)) {
                        return false;
                    } else if (HoneymodUtils.isItemSimilar(inv.getContents()[j], expectedInput, true)) {
                        break;
                    }
                }
            }
        }

        return true;
    }

    protected void craft(Player p, Block b, Inventory inv, ItemStack[] recipe, ItemStack output, Inventory outputInv) {
        for (ItemStack removing : recipe) {
            if (removing != null) {
                InvUtils.removeItem(inv, removing.getAmount(), true, stack -> HoneymodUtils.isItemSimilar(stack, removing, true));
            }
        }

        outputInv.addItem(output);
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
        p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
    }

}
