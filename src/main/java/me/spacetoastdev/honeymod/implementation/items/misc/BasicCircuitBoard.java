package me.spacetoastdev.honeymod.implementation.items.misc;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.IronGolem;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.events.PlayerRightClickEvent;
import me.spacetoastdev.honeymod.api.items.ItemSetting;
import me.spacetoastdev.honeymod.api.items.settings.IntRangeSetting;
import me.spacetoastdev.honeymod.core.attributes.NotPlaceable;
import me.spacetoastdev.honeymod.core.attributes.RandomMobDrop;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

/**
 * The {@link BasicCircuitBoard} is a basic crafting component which can be
 * obtained by killing an {@link IronGolem}.
 * 
 * @author TheBusyBiscuit
 * @author dniym
 *
 */
public class BasicCircuitBoard extends SimpleHoneymodItem<ItemUseHandler> implements NotPlaceable, RandomMobDrop {

    private final ItemSetting<Boolean> dropSetting = new ItemSetting<>("drop-from-golems", true);
    private final ItemSetting<Integer> chance = new IntRangeSetting("golem-drop-chance", 0, 75, 100);

    @ParametersAreNonnullByDefault
    public BasicCircuitBoard(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemSetting(dropSetting);
        addItemSetting(chance);
    }

    @Override
    public int getMobDropChance() {
        return chance.getValue();
    }

    public boolean isDroppedFromGolems() {
        return dropSetting.getValue();
    }

    @Override
    public ItemUseHandler getItemHandler() {
        return PlayerRightClickEvent::cancel;
    }

}