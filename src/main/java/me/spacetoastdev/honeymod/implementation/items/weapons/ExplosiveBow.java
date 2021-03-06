package me.spacetoastdev.honeymod.implementation.items.weapons;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.items.ItemSetting;
import me.spacetoastdev.honeymod.api.items.settings.IntRangeSetting;
import me.spacetoastdev.honeymod.core.handlers.BowShootHandler;

/**
 * The {@link ExplosiveBow} is a {@link HoneymodBow} which creates a fake explosion when it hits
 * a {@link LivingEntity}. Any nearby {@link LivingEntity LivingEntities} get pushed away and
 * take a little bit of damage, similar to an "Area of Effect" damage.
 * 
 * @author TheBusyBiscuit
 * @author Linox
 * 
 * @see HoneymodBow
 *
 */
public class ExplosiveBow extends HoneymodBow {

    private final ItemSetting<Integer> range = new IntRangeSetting("explosion-range", 1, 3, Integer.MAX_VALUE);

    @ParametersAreNonnullByDefault
    public ExplosiveBow(Category category, SlimefunItemStack item, ItemStack[] recipe) {
        super(category, item, recipe);

        addItemSetting(range);
    }

    @Override
    public BowShootHandler onShoot() {
        return (e, target) -> {
            target.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, target.getLocation(), 1);
            target.getWorld().playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
            int radius = range.getValue();

            Collection<Entity> entites = target.getWorld().getNearbyEntities(target.getLocation(), radius, radius, radius, this::canDamage);
            for (Entity nearby : entites) {
                LivingEntity entity = (LivingEntity) nearby;

                Vector distanceVector = entity.getLocation().toVector().subtract(target.getLocation().toVector());
                distanceVector.setY(distanceVector.getY() + 0.6);

                Vector velocity = entity.getVelocity();

                double distanceSquared = distanceVector.lengthSquared();
                double damage = calculateDamage(distanceSquared, e.getDamage());

                if (!entity.getUniqueId().equals(target.getUniqueId())) {
                    EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(e.getDamager(), entity, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, damage);
                    Bukkit.getPluginManager().callEvent(event);

                    if (!event.isCancelled()) {
                        Vector knockback = velocity.add(distanceVector.normalize().multiply((int) (e.getDamage() / damage)));
                        entity.setVelocity(knockback);
                        entity.damage(event.getDamage());
                    }
                }
            }
        };
    }

    private boolean canDamage(@Nonnull Entity n) {
        return n instanceof LivingEntity && !(n instanceof ArmorStand) && n.isValid();
    }

    private double calculateDamage(double distanceSquared, double originalDamage) {
        if (distanceSquared <= 0.05) {
            return originalDamage;
        }

        double damage = originalDamage * (1 - (distanceSquared / (range.getValue() * range.getValue())));
        return Math.min(Math.max(1, damage), originalDamage);
    }

}
