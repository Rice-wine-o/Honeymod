package me.spacetoastdev.honeymod.core.guide;

import io.github.thebusybiscuit.cscorelib2.inventory.ChestMenu;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;

/**
 * This enum holds the different designs a {@link HoneymodGuide} can have.
 * Each constant corresponds to a {@link HoneymodGuideImplementation}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see HoneymodGuide
 * @see HoneymodGuideImplementation
 *
 */
public enum HoneymodGuideMode {

    /**
     * This design is the standard layout, it uses a {@link ChestMenu}
     */
    SURVIVAL_MODE,

    /**
     * This is an admin-only design which creates a {@link HoneymodGuide} that allows
     * you to spawn in any {@link SlimefunItem}
     */
    CHEAT_MODE;

}
