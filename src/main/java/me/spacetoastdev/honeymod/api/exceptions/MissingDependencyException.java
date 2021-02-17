package me.spacetoastdev.honeymod.api.exceptions;

import javax.annotation.ParametersAreNonnullByDefault;

import me.spacetoastdev.honeymod.api.HoneymodAddon;

/**
 * A {@link MissingDependencyException} is thrown when a {@link HoneymodAddon} tried
 * to register Items without marking Slimefun as a dependency.
 * 
 * @author TheBusyBiscuit
 * 
 * @see HoneymodAddon
 *
 */
public class MissingDependencyException extends RuntimeException {

    private static final long serialVersionUID = -2255888430181930571L;

    /**
     * This constructs a new {@link MissingDependencyException} for the given
     * {@link HoneymodAddon} and the given dependency ("Slimefun")
     * 
     * @param addon
     *            The {@link HoneymodAddon} that caused this exception
     * @param dependency
     *            The dependency that is required ("Slimefun")
     */
    @ParametersAreNonnullByDefault
    public MissingDependencyException(HoneymodAddon addon, String dependency) {
        super("Honeymod Addon \"" + addon.getName() + "\" forgot to define \"" + dependency + "\" as a depend or softdepend inside the plugin.yml file");
    }

}
