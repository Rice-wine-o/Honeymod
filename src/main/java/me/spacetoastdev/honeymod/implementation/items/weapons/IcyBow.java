package me.spacetoastdev.honeymod.implementation.items.weapons;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.BowShootHandler;

/**
 * The {@link IcyBow} is a special kind of bow which slows down any
 * {@link LivingEntity} it hits.
 * 
 * @author TheBusyBiscuit
 *
 */
public class IcyBow extends HoneymodBow {

    @ParametersAreNonnullByDefault
    public IcyBow(Category category, SlimefunItemStack item, ItemStack[] recipe) {
        super(category, item, recipe);
    }

    @Override
    public BowShootHandler onShoot() {
        return (e, n) -> {
            n.getWorld().playEffect(n.getLocation(), Effect.STEP_SOUND, Material.ICE);
            n.getWorld().playEffect(n.getEyeLocation(), Effect.STEP_SOUND, Material.ICE);
            n.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 2, 10));
            n.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 2, -10));
        };
    }

}
