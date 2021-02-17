package me.spacetoastdev.honeymod.implementation.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.core.handlers.BowShootHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.weapons.HoneymodBow;

/**
 * This {@link Listener} is responsible for tracking {@link Arrow Arrows} fired from a
 * {@link HoneymodBow}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see HoneymodBow
 *
 */
public class HoneymodBowListener implements Listener {

    private final Map<UUID, HoneymodBow> projectiles = new HashMap<>();

    public void register(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * This returns a {@link HashMap} holding the {@link UUID} of a {@link Arrow} and the
     * associated {@link HoneymodBow} with which this {@link Arrow} was fired from.
     * 
     * @return A {@link HashMap} with all actively tracked {@link Arrow Arrows}
     */
    @Nonnull
    public Map<UUID, HoneymodBow> getProjectileData() {
        return projectiles;
    }

    @EventHandler
    public void onBowUse(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player && e.getProjectile() instanceof Arrow) {
            SlimefunItem bow = SlimefunItem.getByItem(e.getBow());

            if (bow instanceof HoneymodBow) {
                projectiles.put(e.getProjectile().getUniqueId(), (HoneymodBow) bow);
            }
        }
    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent e) {
        HoneymodPlugin.runSync(() -> {
            if (e.getEntity().isValid() && e.getEntity() instanceof Arrow) {
                projectiles.remove(e.getEntity().getUniqueId());
            }
        }, 4L);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onArrowSuccessfulHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Arrow && e.getEntity() instanceof LivingEntity && e.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            HoneymodBow bow = projectiles.remove(e.getDamager().getUniqueId());

            if (!e.isCancelled() && bow != null) {
                bow.callItemHandler(BowShootHandler.class, handler -> handler.onHit(e, (LivingEntity) e.getEntity()));
            }
        }
    }

}
