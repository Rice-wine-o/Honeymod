package me.spacetoastdev.honeymod.implementation.items.electric;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.EnergyNetComponent;
import me.spacetoastdev.honeymod.core.attributes.HologramOwner;
import me.spacetoastdev.honeymod.core.handlers.BlockPlaceHandler;
import me.spacetoastdev.honeymod.core.networks.energy.EnergyNet;

/**
 * The {@link EnergyRegulator} is a special type of {@link SlimefunItem} which serves as the heart of every
 * {@link EnergyNet}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see EnergyNet
 * @see EnergyNetComponent
 *
 */
public class EnergyRegulator extends SlimefunItem implements HologramOwner {

    @ParametersAreNonnullByDefault
    public EnergyRegulator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        SlimefunItem.registerBlockHandler(getId(), (p, b, stack, reason) -> {
            removeHologram(b);
            return true;
        });
    }

    @Nonnull
    private BlockPlaceHandler onPlace() {
        return new BlockPlaceHandler(false) {

            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
                updateHologram(e.getBlock(), "&7Connecting...");
            }

        };
    }

    @Override
    public void preRegister() {
        addItemHandler(onPlace());

        addItemHandler(new BlockTicker() {

            @Override
            public boolean isSynchronized() {
                return false;
            }

            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                EnergyRegulator.this.tick(b);
            }
        });
    }

    private void tick(@Nonnull Block b) {
        EnergyNet network = EnergyNet.getNetworkFromLocationOrCreate(b.getLocation());
        network.tick(b);
    }

}
