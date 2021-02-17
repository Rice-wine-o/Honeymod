package me.spacetoastdev.honeymod.implementation.guide;

import javax.annotation.Nonnull;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.core.categories.FlexCategory;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideMode;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.ChestMenuUtils;
import me.spacetoastdev.honeymod.utils.itemstack.HoneymodGuideItem;

/**
 * This is an admin-variant of the {@link SurvivalHoneymodGuide} which allows a {@link Player}
 * to spawn in a {@link SlimefunItem} via click rather than showing their {@link Recipe}.
 * 
 * @author TheBusyBiscuit
 *
 */
public class CheatSheetHoneymodGuide extends SurvivalHoneymodGuide {

    private final ItemStack item;

    public CheatSheetHoneymodGuide() {
        super(false);

        item = new HoneymodGuideItem(this, "&cSlimefun Guide &4(Cheat Sheet)");
    }

    /**
     * Returns a {@link List} of visible {@link Category} instances that the {@link HoneymodGuide} would display.
     *
     * @param p
     *            The {@link Player} who opened his {@link HoneymodGuide}
     * @param profile
     *            The {@link PlayerProfile} of the {@link Player}
     * @return a {@link List} of visible {@link Category} instances
     */
    @Nonnull
    @Override
    protected List<Category> getVisibleCategories(@Nonnull Player p, @Nonnull PlayerProfile profile) {
        List<Category> categories = new LinkedList<>();

        for (Category category : HoneymodPlugin.getRegistry().getCategories()) {
            if (!(category instanceof FlexCategory)) {
                categories.add(category);
            }
        }

        return categories;
    }

    @Nonnull
    @Override
    public HoneymodGuideMode getMode() {
        return HoneymodGuideMode.CHEAT_MODE;
    }

    @Nonnull
    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    protected void createHeader(Player p, PlayerProfile profile, ChestMenu menu) {
        super.createHeader(p, profile, menu);

        // Remove Settings Panel
        menu.addItem(1, ChestMenuUtils.getBackground());
        menu.addMenuClickHandler(1, ChestMenuUtils.getEmptyClickHandler());
    }
}
