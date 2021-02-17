package me.spacetoastdev.honeymod.implementation.items.electric.gadgets;

import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.EnergyNetComponent;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.core.networks.energy.EnergyNet;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;
import me.spacetoastdev.honeymod.utils.NumberUtils;

/**
 * The {@link Multimeter} is used to measure charge and capacity of any {@link EnergyNetComponent}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see EnergyNet
 * @see EnergyNetComponent
 *
 */
public class Multimeter extends SimpleHoneymodItem<ItemUseHandler> {

    @ParametersAreNonnullByDefault
    public Multimeter(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Optional<SlimefunItem> block = e.getSlimefunBlock();

            if (e.getClickedBlock().isPresent() && block.isPresent()) {
                SlimefunItem item = block.get();

                if (item instanceof EnergyNetComponent) {
                    EnergyNetComponent component = (EnergyNetComponent) item;

                    if (component.isChargeable()) {
                        e.cancel();

                        Location l = e.getClickedBlock().get().getLocation();
                        String stored = NumberUtils.getCompactDouble(component.getCharge(l)) + " J";
                        String capacity = NumberUtils.getCompactDouble(component.getCapacity()) + " J";

                        Player p = e.getPlayer();
                        p.sendMessage("");
                        HoneymodPlugin.getLocalization().sendMessage(p, "messages.multimeter", false, str -> str.replace("%stored%", stored).replace("%capacity%", capacity));
                        p.sendMessage("");
                    }
                }
            }
        };
    }
}
