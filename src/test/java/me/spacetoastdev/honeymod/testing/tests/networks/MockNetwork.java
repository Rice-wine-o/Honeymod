package me.spacetoastdev.honeymod.testing.tests.networks;

import java.util.Map;

import org.bukkit.Location;

import me.spacetoastdev.honeymod.api.network.Network;
import me.spacetoastdev.honeymod.api.network.NetworkComponent;
import me.spacetoastdev.honeymod.core.networks.NetworkManager;

class MockNetwork extends Network {

    private final int range;
    private final Map<Location, NetworkComponent> locations;

    protected MockNetwork(NetworkManager manager, Location regulator, int range, Map<Location, NetworkComponent> locations) {
        super(manager, regulator);
        this.range = range;
        this.locations = locations;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public NetworkComponent classifyLocation(Location l) {
        if (l.equals(regulator)) {
            return NetworkComponent.REGULATOR;
        } else {
            return locations.get(l);
        }
    }

    @Override
    public void onClassificationChange(Location l, NetworkComponent from, NetworkComponent to) {
        // Do nothing
    }

}