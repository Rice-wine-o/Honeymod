package me.spacetoastdev.honeymod.implementation.items.tools;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.protection.ProtectableAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.items.ItemSetting;
import me.spacetoastdev.honeymod.core.attributes.DamageableItem;
import me.spacetoastdev.honeymod.core.attributes.NotPlaceable;
import me.spacetoastdev.honeymod.core.handlers.ToolUseHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;
import me.spacetoastdev.honeymod.utils.tags.HoneymodTag;

/**
 * This {@link SlimefunItem} is a super class for items like the {@link ExplosivePickaxe} or {@link ExplosiveShovel}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see ExplosivePickaxe
 * @see ExplosiveShovel
 *
 */
class ExplosiveTool extends SimpleHoneymodItem<ToolUseHandler> implements NotPlaceable, DamageableItem {

    private final ItemSetting<Boolean> damageOnUse = new ItemSetting<>("damage-on-use", true);
    private final ItemSetting<Boolean> callExplosionEvent = new ItemSetting<>("call-explosion-event", false);

    @ParametersAreNonnullByDefault
    public ExplosiveTool(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemSetting(damageOnUse, callExplosionEvent);
    }

    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            Player p = e.getPlayer();
            Block b = e.getBlock();

            b.getWorld().createExplosion(b.getLocation(), 0);
            b.getWorld().playSound(b.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.2F, 1F);

            List<Block> blocks = findBlocks(b);
            breakBlocks(p, tool, b, blocks, drops);
        };
    }

    @ParametersAreNonnullByDefault
    private void breakBlocks(Player p, ItemStack item, Block b, List<Block> blocks, List<ItemStack> drops) {
        if (callExplosionEvent.getValue().booleanValue()) {
            BlockExplodeEvent blockExplodeEvent = new BlockExplodeEvent(b, blocks, 0);
            Bukkit.getServer().getPluginManager().callEvent(blockExplodeEvent);

            if (!blockExplodeEvent.isCancelled()) {
                for (Block block : blockExplodeEvent.blockList()) {
                    if (canBreak(p, block)) {
                        breakBlock(p, item, block, drops);
                    }
                }
            }
        } else {
            for (Block block : blocks) {
                if (canBreak(p, block)) {
                    breakBlock(p, item, block, drops);
                }
            }
        }
    }

    private List<Block> findBlocks(Block b) {
        List<Block> blocks = new ArrayList<>(26);

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    // We can skip the center block since that will break as usual
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }

                    blocks.add(b.getRelative(x, y, z));
                }
            }
        }

        return blocks;
    }

    @Override
    public boolean isDamageable() {
        return damageOnUse.getValue();
    }

    protected boolean canBreak(Player p, Block b) {
        if (b.isEmpty() || b.isLiquid()) {
            return false;
        } else if (HoneymodTag.UNBREAKABLE_MATERIALS.isTagged(b.getType())) {
            return false;
        } else if (!b.getWorld().getWorldBorder().isInside(b.getLocation())) {
            return false;
        } else if (HoneymodPlugin.getIntegrations().isCustomBlock(b)) {
            return false;
        } else {
            return HoneymodPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.BREAK_BLOCK);
        }
    }

    @ParametersAreNonnullByDefault
    private void breakBlock(Player p, ItemStack item, Block b, List<ItemStack> drops) {
        HoneymodPlugin.getProtectionManager().logAction(p, b, ProtectableAction.BREAK_BLOCK);
        Material material = b.getType();

        b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, material);
        SlimefunItem sfItem = BlockStorage.check(b);

        if (sfItem != null && !sfItem.useVanillaBlockBreaking()) {
            SlimefunBlockHandler handler = HoneymodPlugin.getRegistry().getBlockHandlers().get(sfItem.getId());

            if (handler != null && !handler.onBreak(p, b, sfItem, UnregisterReason.PLAYER_BREAK)) {
                drops.add(BlockStorage.retrieve(b));
            }
        } else {
            b.breakNaturally(item);
        }

        damageItem(p, item);
    }

}
