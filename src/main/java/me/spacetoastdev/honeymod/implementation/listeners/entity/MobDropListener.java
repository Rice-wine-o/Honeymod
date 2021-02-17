package me.spacetoastdev.honeymod.implementation.listeners.entity;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.core.attributes.RandomMobDrop;
import me.spacetoastdev.honeymod.core.handlers.EntityKillHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.misc.BasicCircuitBoard;

/**
 * This {@link Listener} is responsible for handling any custom mob drops.
 * These drops can also be randomized using the interface {@link RandomMobDrop}, otherwise
 * they will be handled via {@link RecipeType}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see RandomMobDrop
 *
 */
public class MobDropListener implements Listener {

    public MobDropListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityKill(EntityDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            Player p = e.getEntity().getKiller();
            ItemStack item = p.getInventory().getItemInMainHand();

            Set<ItemStack> customDrops = HoneymodPlugin.getRegistry().getMobDrops().get(e.getEntityType());

            if (customDrops != null && !customDrops.isEmpty()) {
                for (ItemStack drop : customDrops) {
                    if (canDrop(p, drop)) {
                        e.getDrops().add(drop.clone());
                    }
                }
            }

            if (item.getType() != Material.AIR) {
                SlimefunItem sfItem = SlimefunItem.getByItem(item);

                if (sfItem != null && sfItem.canUse(p, true)) {
                    sfItem.callItemHandler(EntityKillHandler.class, handler -> handler.onKill(e, e.getEntity(), p, item));
                }
            }
        }
    }

    private boolean canDrop(@Nonnull Player p, @Nonnull ItemStack item) {
        SlimefunItem sfItem = SlimefunItem.getByItem(item);

        if (sfItem == null) {
            return true;
        } else if (sfItem.canUse(p, true)) {
            if (sfItem instanceof RandomMobDrop) {
                int random = ThreadLocalRandom.current().nextInt(100);

                if (((RandomMobDrop) sfItem).getMobDropChance() <= random) {
                    return false;
                }
            }

            if (sfItem instanceof BasicCircuitBoard) {
                return ((BasicCircuitBoard) sfItem).isDroppedFromGolems();
            }

            return true;
        } else {
            return false;
        }
    }
}
