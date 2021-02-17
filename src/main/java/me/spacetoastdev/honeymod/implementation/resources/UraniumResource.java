package me.spacetoastdev.honeymod.implementation.resources;

import org.bukkit.World.Environment;
import org.bukkit.block.Biome;

import me.spacetoastdev.honeymod.api.geo.GEOResource;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;

/**
 * A {@link GEOResource} which consists of small chunks of Uranium.
 * 
 * @author TheBusyBiscuit
 *
 */
class UraniumResource extends HoneymodResource {

    UraniumResource() {
        super("uranium", "Small Chunks of Uranium", HoneymodItems.SMALL_URANIUM, 2, true);
    }

    @Override
    public int getDefaultSupply(Environment environment, Biome biome) {
        if (environment == Environment.NORMAL) {
            return 5;
        }

        return 0;
    }

}
