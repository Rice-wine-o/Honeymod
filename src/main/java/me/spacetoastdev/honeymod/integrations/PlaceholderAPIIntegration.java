package me.spacetoastdev.honeymod.integrations;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.core.researching.Research;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This is our integration for {@link PlaceholderAPI}.
 * 
 * @author TheBusyBiscuit
 *
 */
class PlaceholderAPIIntegration extends PlaceholderExpansion {

    private final String version;
    private final String author;

    public PlaceholderAPIIntegration(@Nonnull HoneymodPlugin plugin) {
        this.version = plugin.getDescription().getVersion();
        this.author = plugin.getDescription().getAuthors().toString();
    }

    @Nonnull
    @Override
    public String getIdentifier() {
        return "slimefun";
    }

    @Nonnull
    @Override
    public String getVersion() {
        return version;
    }

    @Nonnull
    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    private boolean isPlaceholder(@Nullable OfflinePlayer p, boolean requiresProfile, @Nonnull String params, @Nonnull String placeholder) {
        if (requiresProfile) {
            if (p != null && placeholder.equals(params)) {
                PlayerProfile.request(p);
                return true;
            } else {
                return false;
            }
        } else {
            return placeholder.equals(params);
        }
    }

    @Override
    public String onRequest(@Nullable OfflinePlayer p, @Nonnull String params) {
        if (isPlaceholder(p, true, params, "researches_total_xp_levels_spent")) {
            Optional<PlayerProfile> profile = PlayerProfile.find(p);

            if (profile.isPresent()) {
                Stream<Research> stream = profile.get().getResearches().stream();
                return String.valueOf(stream.mapToInt(Research::getCost).sum());
            } else if (p instanceof Player) {
                return HoneymodPlugin.getLocalization().getMessage((Player) p, "placeholderapi.profile-loading");
            }
        }

        if (isPlaceholder(p, true, params, "researches_total_researches_unlocked")) {
            Optional<PlayerProfile> profile = PlayerProfile.find(p);

            if (profile.isPresent()) {
                Set<Research> set = profile.get().getResearches();
                return String.valueOf(set.size());
            } else if (p instanceof Player) {
                return HoneymodPlugin.getLocalization().getMessage((Player) p, "placeholderapi.profile-loading");
            }
        }

        if (isPlaceholder(p, false, params, "researches_total_researches")) {
            return String.valueOf(HoneymodPlugin.getRegistry().getResearches().size());
        }

        if (isPlaceholder(p, true, params, "researches_percentage_researches_unlocked")) {
            Optional<PlayerProfile> profile = PlayerProfile.find(p);

            if (profile.isPresent()) {
                Set<Research> set = profile.get().getResearches();
                return String.valueOf(Math.round(((set.size() * 100.0F) / HoneymodPlugin.getRegistry().getResearches().size()) * 100.0F) / 100.0F);
            } else if (p instanceof Player) {
                return HoneymodPlugin.getLocalization().getMessage((Player) p, "placeholderapi.profile-loading");
            }
        }

        if (isPlaceholder(p, true, params, "researches_title")) {
            Optional<PlayerProfile> profile = PlayerProfile.find(p);

            if (profile.isPresent()) {
                return profile.get().getTitle();
            } else if (p instanceof Player) {
                return HoneymodPlugin.getLocalization().getMessage((Player) p, "placeholderapi.profile-loading");
            }
        }

        if (isPlaceholder(p, false, params, "gps_complexity") && p != null) {
            return String.valueOf(HoneymodPlugin.getGPSNetwork().getNetworkComplexity(p.getUniqueId()));
        }

        if (isPlaceholder(p, false, params, "timings_lag")) {
            return HoneymodPlugin.getProfiler().getTime();
        }

        if (isPlaceholder(p, false, params, "language") && p instanceof Player) {
            Player player = (Player) p;
            return HoneymodPlugin.getLocalization().getLanguage(player).getName(player);
        }

        return null;
    }

}
