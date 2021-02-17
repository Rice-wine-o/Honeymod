package me.spacetoastdev.honeymod.implementation.tasks;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.spacetoastdev.honeymod.implementation.items.armor.Parachute;

/**
 * The {@link ParachuteTask} adds the entire functionality of the {@link Parachute}.
 * It continously sets the velocity of the {@link Player} to make them fall slowly.
 * Perhaps it can be changed to use the slow falling effect at some point.
 * 
 * @author TheBusyBiscuit
 * 
 * @see Parachute
 *
 */
public class ParachuteTask extends AbstractPlayerTask {

    public ParachuteTask(@Nonnull Player p) {
        super(p);
    }

    @Override
    protected void executeTask() {
        Vector vector = new Vector(0, 1, 0);
        vector.multiply(-0.1);
        p.setVelocity(vector);
        p.setFallDistance(0F);

        if (!p.isSneaking()) {
            cancel();
        }
    }

}
