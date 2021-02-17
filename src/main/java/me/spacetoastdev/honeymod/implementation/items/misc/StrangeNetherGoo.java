package me.spacetoastdev.honeymod.implementation.items.misc;

import javax.annotation.Nonnull;
import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Sheep;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.cscorelib2.inventory.ItemUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.items.ItemSetting;
import me.spacetoastdev.honeymod.api.items.settings.IntRangeSetting;
import me.spacetoastdev.honeymod.core.attributes.PiglinBarterDrop;
import me.spacetoastdev.honeymod.core.handlers.EntityInteractHandler;
import me.spacetoastdev.honeymod.core.handlers.ItemUseHandler;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;
import me.spacetoastdev.honeymod.implementation.items.magical.runes.VillagerRune;

/**
 * This {@link SlimefunItem} can only be obtained via bartering with a {@link Piglin}, its
 * only current uses is the recipe for crafting the {@link VillagerRune}.
 *
 * @author dNiym
 *
 * @see VillagerRune
 * @see PiglinBarterDrop
 *
 */
public class StrangeNetherGoo extends SimpleHoneymodItem<ItemUseHandler> implements PiglinBarterDrop {

    private final ItemSetting<Integer> chance = new IntRangeSetting("barter-chance", 0, 7, 100);

    public StrangeNetherGoo(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemSetting(chance);
        addItemHandler(onRightClickEntity());
    }

    @Override
    public int getBarteringLootChance() {
        return chance.getValue();
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            Optional<Block> block = e.getClickedBlock();

            if (block.isPresent() && Tag.SIGNS.isTagged(block.get().getType())) {
                e.cancel();
            }
        };
    }

    private EntityInteractHandler onRightClickEntity() {
        return (e, item, hand) -> {
            if (e.getRightClicked() instanceof Sheep) {
                Sheep s = (Sheep) e.getRightClicked();

                if (s.getCustomName() != null) {
                    e.setCancelled(true);
                    return;
                }

                if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    ItemUtils.consumeItem(item, false);
                }

                // Give Sheep color, name and effect
                s.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 2));
                s.setColor(DyeColor.PURPLE);
                s.setCustomName(ChatColor.DARK_PURPLE + "Tainted Sheep");
                e.setCancelled(true);

            }
        };
    }
}
