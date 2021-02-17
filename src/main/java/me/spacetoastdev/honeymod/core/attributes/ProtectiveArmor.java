package me.spacetoastdev.honeymod.core.attributes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import me.spacetoastdev.honeymod.implementation.items.armor.HazmatArmorPiece;
import me.spacetoastdev.honeymod.implementation.items.armor.HoneymodArmorPiece;

/**
 * Implement this interface to a {@link HoneymodArmorPiece} to protect
 * the {@link Player} who wears that {@link HoneymodArmorPiece} from
 * {@link ProtectionType} damage.
 *
 * <b>Important:</b> You need to specify which {@link ProtectionType} damages
 * to protect the {@link Player} from.
 *
 * @author Linox
 *
 * @see HoneymodArmorPiece
 * @see HazmatArmorPiece
 * @see ItemAttribute
 *
 */
public interface ProtectiveArmor extends ItemAttribute {

    /**
     * This returns which {@link ProtectionType} damages this {@link ItemAttribute}
     * will protect the {@link Player} from.
     *
     * @return The {@link ProtectionType}s.
     */
    @Nonnull
    ProtectionType[] getProtectionTypes();

    /**
     * This returns whether the full set is required for {@link Player}'s protection on
     * assigned {@link ProtectionType} damages.
     *
     * @return Whether or not he full set is required.
     */
    boolean isFullSetRequired();

    /**
     * This returns the armor set {@link NamespacedKey} of this {@link HoneymodArmorPiece}.
     *
     * @return The set {@link NamespacedKey}, <code>null</code> if none is found.
     */
    @Nullable
    NamespacedKey getArmorSetId();
}
