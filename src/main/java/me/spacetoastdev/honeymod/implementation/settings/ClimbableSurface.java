package me.spacetoastdev.honeymod.implementation.settings;

import javax.annotation.Nonnull;

import org.bukkit.Material;

import me.spacetoastdev.honeymod.api.events.ClimbingPickLaunchEvent;
import me.spacetoastdev.honeymod.api.items.ItemSetting;
import me.spacetoastdev.honeymod.api.items.settings.DoubleRangeSetting;
import me.spacetoastdev.honeymod.implementation.items.tools.ClimbingPick;

/**
 * This is an {@link ItemSetting} that manages the efficiency of climbing
 * a certain {@link Material} with the {@link ClimbingPick}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see ClimbingPick
 * @see ClimbingPickLaunchEvent
 *
 */
public class ClimbableSurface extends DoubleRangeSetting {

    private final Material type;

    /**
     * This creates a new {@link ClimbableSurface} for the given {@link Material}.
     * 
     * @param surface
     *            The {@link Material} of this surface
     * @param defaultValue
     *            The default launch amount
     */
    public ClimbableSurface(@Nonnull Material surface, double defaultValue) {
        super("launch-amounts." + surface.name(), 0, defaultValue, Double.MAX_VALUE);

        this.type = surface;
    }

    /**
     * This returns the {@link Material} of this surface.
     * 
     * @return The {@link Material} of this surface
     */
    @Nonnull
    public Material getType() {
        return type;
    }

}
