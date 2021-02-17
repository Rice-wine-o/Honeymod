package me.spacetoastdev.honeymod.implementation.items.multiblocks;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.inventory.ItemUtils;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.papermc.lib.PaperLib;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

public class ArmorForge extends AbstractCraftingTable {

    public ArmorForge(Category category, SlimefunItemStack item) {
        super(category, item, new ItemStack[] { null, null, null, null, new ItemStack(Material.ANVIL), null, null, new CustomItem(Material.DISPENSER, "Dispenser (Facing up)"), null }, BlockFace.SELF);
    }

    @Override
    public void onInteract(Player p, Block b) {
        Block dispenser = b.getRelative(BlockFace.DOWN);
        BlockState state = PaperLib.getBlockState(dispenser, false).getState();

        if (state instanceof Dispenser) {
            Dispenser disp = (Dispenser) state;
            Inventory inv = disp.getInventory();
            List<ItemStack[]> inputs = RecipeType.getRecipeInputList(this);

            for (int i = 0; i < inputs.size(); i++) {
                if (isCraftable(inv, inputs.get(i))) {
                    ItemStack output = RecipeType.getRecipeOutputList(this, inputs.get(i)).clone();

                    if (HoneymodUtils.canPlayerUseItem(p, output, true)) {
                        craft(p, output, inv, dispenser);
                    }

                    return;
                }
            }

            HoneymodPlugin.getLocalization().sendMessage(p, "machines.pattern-not-found", true);
        }
    }

    private boolean isCraftable(Inventory inv, ItemStack[] recipe) {
        for (int j = 0; j < inv.getContents().length; j++) {
            if (!HoneymodUtils.isItemSimilar(inv.getContents()[j], recipe[j], true)) {
                return false;
            }
        }

        return true;
    }

    @ParametersAreNonnullByDefault
    private void craft(Player p, ItemStack output, Inventory inv, Block dispenser) {
        Inventory fakeInv = createVirtualInventory(inv);
        Inventory outputInv = findOutputInventory(output, dispenser, inv, fakeInv);

        if (outputInv != null) {
            for (int j = 0; j < 9; j++) {
                ItemStack item = inv.getContents()[j];

                if (item != null && item.getType() != Material.AIR) {
                    ItemUtils.consumeItem(item, true);
                }
            }

            for (int j = 0; j < 4; j++) {
                int current = j;

                HoneymodPlugin.runSync(() -> {
                    if (current < 3) {
                        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 2F);
                    } else {
                        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1F, 1F);
                        outputInv.addItem(output);
                    }
                }, j * 20L);
            }

        } else {
            HoneymodPlugin.getLocalization().sendMessage(p, "machines.full-inventory", true);
        }
    }

}
