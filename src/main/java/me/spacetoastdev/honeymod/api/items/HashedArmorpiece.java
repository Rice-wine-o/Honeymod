package me.spacetoastdev.honeymod.api.items;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.implementation.items.armor.HoneymodArmorPiece;
import me.spacetoastdev.honeymod.implementation.tasks.ArmorTask;

/**
 * This class serves as a way of checking whether a {@link Player} has changed their armor
 * between ticks. We do that by storing the hash of their armor and a reference to the
 * corresponding {@link HoneymodArmorPiece} if such a correlation exists.
 * 
 * This gives us a significant performance improvement as we only need to check for a
 * {@link HoneymodArmorPiece} if the item diverged in the first place.
 * 
 * @author TheBusyBiscuit
 *
 * @see HoneymodArmorPiece
 * @see ArmorTask
 */
public final class HashedArmorpiece {

    private int hash;
    private Optional<HoneymodArmorPiece> item;

    /**
     * This initializes a new {@link HashedArmorpiece} with no {@link HoneymodArmorPiece}
     * and a zero hash.
     */
    public HashedArmorpiece() {
        this.hash = 0;
        this.item = Optional.empty();
    }

    /**
     * This will update this {@link HashedArmorpiece} with the given {@link ItemStack}
     * and the corresponding {@link SlimefunItem}
     * 
     * @param stack
     *            The new armorpiece to be stored in this {@link HashedArmorpiece}
     * @param item
     *            The {@link SlimefunItem} corresponding to the provided {@link ItemStack}, may be null
     */
    public void update(@Nullable ItemStack stack, @Nullable SlimefunItem item) {
        if (stack == null || stack.getType() == Material.AIR) {
            this.hash = 0;
        } else {
            ItemStack copy = stack.clone();
            ItemMeta meta = copy.getItemMeta();
            ((Damageable) meta).setDamage(0);
            copy.setItemMeta(meta);
            this.hash = copy.hashCode();
        }

        if (item instanceof HoneymodArmorPiece) {
            this.item = Optional.of((HoneymodArmorPiece) item);
        } else {
            this.item = Optional.empty();
        }
    }

    /**
     * This method checks whether the given {@link ItemStack} is no longer similar to the
     * one represented by this {@link HashedArmorpiece}.
     * 
     * @param stack
     *            The {@link ItemStack} to compare
     * @return Whether the {@link HashedArmorpiece} and the given {@link ItemStack} mismatch
     */
    public boolean hasDiverged(@Nullable ItemStack stack) {
        if (stack == null || stack.getType() == Material.AIR) {
            return hash != 0;
        } else {
            ItemStack copy = stack.clone();
            ItemMeta meta = copy.getItemMeta();
            ((Damageable) meta).setDamage(0);
            copy.setItemMeta(meta);
            return copy.hashCode() != hash;
        }
    }

    /**
     * Returns the {@link HoneymodArmorPiece} that corresponds to this {@link HashedArmorpiece},
     * or an empty {@link Optional}
     * 
     * @return An {@link Optional} describing the result
     */
    @Nonnull
    public Optional<HoneymodArmorPiece> getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "HashedArmorpiece {hash=" + hash + ",item=" + item.map(SlimefunItem::getId).orElse("null") + '}';
    }

}
