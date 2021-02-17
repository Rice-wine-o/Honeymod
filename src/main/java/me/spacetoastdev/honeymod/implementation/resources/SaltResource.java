package me.spacetoastdev.honeymod.implementation.resources;

import org.bukkit.World.Environment;
import org.bukkit.block.Biome;

import me.spacetoastdev.honeymod.api.geo.GEOResource;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;

/**
 * A {@link GEOResource} which consists of Salt.
 * 
 * @author TheBusyBiscuit
 *
 */
class SaltResource extends HoneymodResource {

    SaltResource() {
        super("salt", "Salt", HoneymodItems.SALT, 18, true);
    }

    @Override
    public int getDefaultSupply(Environment environment, Biome biome) {

        if (environment != Environment.NORMAL) {
            return 0;
        }

        switch (biome) {
            case SNOWY_BEACH:
            case STONE_SHORE:
            case BEACH:
            case DESERT_LAKES:
            case RIVER:
            case ICE_SPIKES:
            case FROZEN_RIVER:
                return 40;

            case DEEP_OCEAN:
            case OCEAN:
            case COLD_OCEAN:
            case DEEP_COLD_OCEAN:
            case DEEP_FROZEN_OCEAN:
            case DEEP_LUKEWARM_OCEAN:
            case DEEP_WARM_OCEAN:
            case FROZEN_OCEAN:
            case LUKEWARM_OCEAN:
            case WARM_OCEAN:
                return 60;

            case SWAMP:
            case SWAMP_HILLS:
                return 20;

            default:
                return 6;
        }
    }

}
