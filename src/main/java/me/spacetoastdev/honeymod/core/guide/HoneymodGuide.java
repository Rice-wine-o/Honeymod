package me.spacetoastdev.honeymod.core.guide;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.guide.SurvivalHoneymodGuide;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;
import me.spacetoastdev.honeymod.utils.itemstack.HoneymodGuideItem;

/**
 * This is a static utility class that provides convenient access to the methods
 * of {@link HoneymodGuideImplementation} that abstracts away the actual implementation.
 * 
 * @author TheBusyBiscuit
 * 
 * @see HoneymodGuideImplementation
 * @see SurvivalHoneymodGuide
 *
 */
public final class HoneymodGuide {

    private HoneymodGuide() {}

    @Nonnull
    public static ItemStack getItem(@Nonnull HoneymodGuideMode design) {
        return HoneymodPlugin.getRegistry().getHoneymodGuide(design).getItem();
    }

    public static void openCheatMenu(@Nonnull Player p) {
        openMainMenuAsync(p, HoneymodGuideMode.CHEAT_MODE, 1);
    }

    public static void openGuide(@Nonnull Player p, @Nullable ItemStack guide) {
        if (getItem(HoneymodGuideMode.CHEAT_MODE).equals(guide)) {
            openGuide(p, HoneymodGuideMode.CHEAT_MODE);
        } else {
            /*
             * When using /hm cheat or /hm open_guide the ItemStack is null anyway,
             * so we don't even need to check here at this point.
             */
            openGuide(p, HoneymodGuideMode.SURVIVAL_MODE);
        }
    }

    public static void openGuide(@Nonnull Player p, @Nonnull HoneymodGuideMode mode) {
        if (!HoneymodPlugin.getWorldSettingsService().isWorldEnabled(p.getWorld())) {
            return;
        }

        Optional<PlayerProfile> optional = PlayerProfile.find(p);

        if (optional.isPresent()) {
            PlayerProfile profile = optional.get();
            HoneymodGuideImplementation guide = HoneymodPlugin.getRegistry().getHoneymodGuide(mode);
            profile.getGuideHistory().openLastEntry(guide);
        } else {
            openMainMenuAsync(p, mode, 1);
        }
    }

    @ParametersAreNonnullByDefault
    private static void openMainMenuAsync(Player player, HoneymodGuideMode mode, int selectedPage) {
        if (!PlayerProfile.get(player, profile -> HoneymodPlugin.runSync(() -> openMainMenu(profile, mode, selectedPage)))) {
            HoneymodPlugin.getLocalization().sendMessage(player, "messages.opening-guide");
        }
    }

    @ParametersAreNonnullByDefault
    public static void openMainMenu(PlayerProfile profile, HoneymodGuideMode mode, int selectedPage) {
        HoneymodPlugin.getRegistry().getHoneymodGuide(mode).openMainMenu(profile, selectedPage);
    }

    @ParametersAreNonnullByDefault
    public static void openCategory(PlayerProfile profile, Category category, HoneymodGuideMode mode, int selectedPage) {
        HoneymodPlugin.getRegistry().getHoneymodGuide(mode).openCategory(profile, category, selectedPage);
    }

    @ParametersAreNonnullByDefault
    public static void openSearch(PlayerProfile profile, String input, HoneymodGuideMode mode, boolean addToHistory) {
        HoneymodGuideImplementation guide = HoneymodPlugin.getRegistry().getHoneymodGuide(mode);
        guide.openSearch(profile, input, addToHistory);
    }

    @ParametersAreNonnullByDefault
    public static void displayItem(PlayerProfile profile, ItemStack item, boolean addToHistory) {
        HoneymodPlugin.getRegistry().getHoneymodGuide(HoneymodGuideMode.SURVIVAL_MODE).displayItem(profile, item, 0, addToHistory);
    }

    @ParametersAreNonnullByDefault
    public static void displayItem(PlayerProfile profile, SlimefunItem item, boolean addToHistory) {
        HoneymodPlugin.getRegistry().getHoneymodGuide(HoneymodGuideMode.SURVIVAL_MODE).displayItem(profile, item, addToHistory);
    }

    /**
     * This method checks if a given {@link ItemStack} is a {@link HoneymodGuide}.
     * 
     * @param item
     *            The {@link ItemStack} to check
     * 
     * @return Whether this {@link ItemStack} represents a {@link HoneymodGuide}
     */
    public static boolean isGuideItem(@Nullable ItemStack item) {
        if (item == null || item.getType() != Material.ENCHANTED_BOOK) {
            return false;
        } else if (item instanceof HoneymodGuideItem) {
            return true;
        } else {
            return HoneymodUtils.isItemSimilar(item, getItem(HoneymodGuideMode.SURVIVAL_MODE), true) || HoneymodUtils.isItemSimilar(item, getItem(HoneymodGuideMode.CHEAT_MODE), true);
        }
    }

    /**
     * Get the default mode for the Slimefun guide.
     * Currently this is only {@link HoneymodGuideMode#SURVIVAL_MODE}.
     *
     * @return The default {@link HoneymodGuideMode}.
     */
    @Nonnull
    public static HoneymodGuideMode getDefaultMode() {
        return HoneymodGuideMode.SURVIVAL_MODE;
    }
}
