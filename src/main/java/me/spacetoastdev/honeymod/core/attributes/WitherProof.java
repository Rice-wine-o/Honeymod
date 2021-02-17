package me.spacetoastdev.honeymod.core.attributes;

import javax.annotation.Nonnull;

import org.bukkit.block.Block;
import org.bukkit.entity.Wither;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.implementation.items.blocks.WitherProofBlock;

/**
 * This Interface, when attached to a class that inherits from {@link SlimefunItem}, marks
 * the Item as "Wither-Proof".
 * Wither-Proof blocks cannot be destroyed by a {@link Wither}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see WitherProofBlock
 *
 */
public interface WitherProof extends ItemAttribute {

    /**
     * This method is called when a {@link Wither} tried to attack the given {@link Block}.
     * You can use this method to play particles or even damage the {@link Wither}.
     * 
     * @param block
     *            The {@link Block} which was attacked.
     * @param wither
     *            The {@link Wither} who attacked.
     */
    void onAttack(@Nonnull Block block, @Nonnull Wither wither);

}
