package me.spacetoastdev.honeymod.implementation.items.seasonal;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.inventory.ItemUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.attributes.NotPlaceable;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;
import me.spacetoastdev.honeymod.utils.FireworkUtils;

/**
 * The {@link ChristmasPresent} is a seasonal {@link SlimefunItem} that drops a random
 * gift when being placed down.
 * 
 * @author TheBusyBiscuit
 * 
 * @see EasterEgg
 *
 */
public class ChristmasPresent extends SimpleHoneymodItem<ItemUseHandler> implements NotPlaceable {

    private final ItemStack[] gifts;

    @ParametersAreNonnullByDefault
    public ChristmasPresent(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack... gifts) {
        super(category, item, recipeType, recipe);

        this.gifts = gifts;
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();

            e.getClickedBlock().ifPresent(block -> {
                if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    ItemUtils.consumeItem(e.getItem(), false);
                }

                FireworkUtils.launchRandom(e.getPlayer(), 3);

                Block b = block.getRelative(e.getClickedFace());
                ItemStack gift = gifts[ThreadLocalRandom.current().nextInt(gifts.length)].clone();
                b.getWorld().dropItemNaturally(b.getLocation(), gift);
            });
        };
    }

}
