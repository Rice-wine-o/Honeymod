package me.spacetoastdev.honeymod.implementation.items.misc;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.Cow;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.events.PlayerRightClickEvent;
import me.spacetoastdev.honeymod.core.handlers.EntityInteractHandler;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;

/**
 * The {@link SteelThruster} is a pretty basic crafting component.
 * However... as it is actually a bucket. We need to make sure that
 * Cows cannot be milked using it.
 * 
 * @author TheBusyBiscuit
 *
 */
public class SteelThruster extends SlimefunItem {

    @ParametersAreNonnullByDefault
    public SteelThruster(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemHandler(onRightClickBlock(), onRightClickEntity());
    }

    @Nonnull
    private ItemUseHandler onRightClickBlock() {
        return PlayerRightClickEvent::cancel;
    }

    @Nonnull
    private EntityInteractHandler onRightClickEntity() {
        return (e, item, hand) -> {
            // Milking cows with a rocket engine? Yeah, that would be weird.
            if (e.getRightClicked() instanceof Cow) {
                e.setCancelled(true);
            }
        };
    }

}