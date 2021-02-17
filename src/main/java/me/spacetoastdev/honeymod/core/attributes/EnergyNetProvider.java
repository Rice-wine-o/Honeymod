package me.spacetoastdev.honeymod.core.attributes;

import javax.annotation.Nonnull;

import org.bukkit.Location;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.spacetoastdev.honeymod.core.networks.energy.EnergyNet;
import me.spacetoastdev.honeymod.core.networks.energy.EnergyNetComponentType;
import me.spacetoastdev.honeymod.implementation.items.electric.AbstractEnergyProvider;
import me.spacetoastdev.honeymod.implementation.items.electric.reactors.Reactor;

/**
 * An {@link EnergyNetProvider} is an extension of {@link EnergyNetComponent} which provides
 * energy to an {@link EnergyNet}.
 * It must be implemented on any Generator or {@link Reactor}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see EnergyNet
 * @see EnergyNetComponent
 * @see AbstractEnergyProvider
 * @see AGenerator
 * @see Reactor
 *
 */
public interface EnergyNetProvider extends EnergyNetComponent {

    @Override
    @Nonnull
    default EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    /**
     * This method returns how much energy this {@link EnergyNetProvider} provides to the {@link EnergyNet}.
     * We call this method on every tick, so make sure to keep it light and fast.
     * Stored energy does not have to be handled in here.
     * 
     * @param l
     *            The {@link Location} of this {@link EnergyNetProvider}
     * @param data
     *            The stored block data
     * 
     * @return The generated output energy of this {@link EnergyNetProvider}.
     */
    int getGeneratedOutput(@Nonnull Location l, @Nonnull Config data);

    /**
     * This method returns whether the given {@link Location} is going to explode on the
     * next tick.
     * 
     * @param l
     *            The {@link Location} of this {@link EnergyNetProvider}
     * @param data
     *            The stored block data
     * 
     * @return Whether or not this {@link Location} will explode.
     */
    default boolean willExplode(@Nonnull Location l, @Nonnull Config data) {
        return false;
    }

}
