package me.spacetoastdev.honeymod.core.guide;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.core.researching.Research;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.guide.SurvivalHoneymodGuide;

/**
 * This interface is used for the different implementations that add behaviour
 * to the {@link HoneymodGuide}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see HoneymodGuideMode
 * @see SurvivalHoneymodGuide
 *
 */
public interface HoneymodGuideImplementation {

    /**
     * Every {@link HoneymodGuideImplementation} can be associated with a
     * {@link HoneymodGuideMode}.
     * 
     * @return The mode this {@link HoneymodGuideImplementation} represents
     */
    @Nonnull
    HoneymodGuideMode getMode();

    /**
     * Returns the {@link ItemStack} representation for this {@link HoneymodGuideImplementation}.
     * In other words: The {@link ItemStack} you hold in your hand and that you use to
     * open your {@link HoneymodGuide}
     * 
     * @return The {@link ItemStack} representation for this {@link HoneymodGuideImplementation}
     */
    @Nonnull
    ItemStack getItem();

    void openMainMenu(PlayerProfile profile, int page);

    void openCategory(PlayerProfile profile, Category category, int page);

    void openSearch(PlayerProfile profile, String input, boolean addToHistory);

    void displayItem(PlayerProfile profile, ItemStack item, int index, boolean addToHistory);

    void displayItem(PlayerProfile profile, SlimefunItem item, boolean addToHistory);

    default void unlockItem(Player p, SlimefunItem sfitem, Consumer<Player> callback) {
        Research research = sfitem.getResearch();

        if (p.getGameMode() == GameMode.CREATIVE && HoneymodPlugin.getRegistry().isFreeCreativeResearchingEnabled()) {
            research.unlock(p, true, callback);
        } else {
            p.setLevel(p.getLevel() - research.getCost());
            research.unlock(p, false, callback);
        }
    }

}
