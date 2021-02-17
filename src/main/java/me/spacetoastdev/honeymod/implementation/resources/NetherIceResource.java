package me.spacetoastdev.honeymod.implementation.resources;

import org.bukkit.World.Environment;
import org.bukkit.block.Biome;

import me.spacetoastdev.honeymod.api.geo.GEOResource;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;

/**
 * A {@link GEOResource} which consists of nether ice.
 * It can only be found in the nether.
 * 
 * @author TheBusyBiscuit
 *
 */
class NetherIceResource extends HoneymodResource {

    NetherIceResource() {
        super("nether_ice", "Nether Ice", HoneymodItems.NETHER_ICE, 6, true);
    }

    @Override
    public int getDefaultSupply(Environment environment, Biome biome) {
        return environment == Environment.NETHER ? 32 : 0;
    }

}
