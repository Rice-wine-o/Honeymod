package me.spacetoastdev.honeymod.core.handlers;

import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import me.spacetoastdev.honeymod.api.exceptions.IncompatibleItemHandlerException;
import me.spacetoastdev.honeymod.implementation.items.weapons.HoneymodBow;

/**
 * This {@link ItemHandler} is triggered when the {@link SlimefunItem} it was assigned to
 * is a {@link HoneymodBow} and an Arrow fired from this bow hit a {@link LivingEntity}.
 * 
 * @author TheBusyBiscuit
 *
 * @see ItemHandler
 * @see HoneymodBow
 * 
 */
@FunctionalInterface
public interface BowShootHandler extends ItemHandler {

    void onHit(EntityDamageByEntityEvent e, LivingEntity n);

    @Override
    default Optional<IncompatibleItemHandlerException> validate(SlimefunItem item) {
        if (item.getItem().getType() != Material.BOW) {
            return Optional.of(new IncompatibleItemHandlerException("Only bows can have a BowShootHandler.", item, this));
        }

        return Optional.empty();
    }

    @Override
    default Class<? extends ItemHandler> getIdentifier() {
        return BowShootHandler.class;
    }
}
