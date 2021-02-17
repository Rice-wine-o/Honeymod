package me.spacetoastdev.honeymod.core.guide.options;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.spacetoastdev.honeymod.api.HoneymodAddon;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideMode;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.ChatUtils;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

class GuideModeOption implements HoneymodGuideOption<HoneymodGuideMode> {

    @Nonnull
    @Override
    public HoneymodAddon getAddon() {
        return HoneymodPlugin.instance();
    }

    @Nonnull
    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(HoneymodPlugin.instance(), "guide_mode");
    }

    @Nonnull
    @Override
    public Optional<ItemStack> getDisplayItem(Player p, ItemStack guide) {
        if (!p.hasPermission("slimefun.cheat.items")) {
            // Only Players with the appropriate permission can access the cheat sheet
            return Optional.empty();
        }

        Optional<HoneymodGuideMode> current = getSelectedOption(p, guide);

        if (current.isPresent()) {
            HoneymodGuideMode selectedMode = current.get();
            ItemStack item = new ItemStack(Material.AIR);

            if (selectedMode == HoneymodGuideMode.SURVIVAL_MODE) {
                item.setType(Material.CHEST);
            } else {
                item.setType(Material.COMMAND_BLOCK);
            }

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GRAY + "Honeymod Guide Type: " + ChatColor.YELLOW + ChatUtils.humanize(selectedMode.name()));
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add((selectedMode == HoneymodGuideMode.SURVIVAL_MODE ? ChatColor.GREEN : ChatColor.GRAY) + "Survival Mode");
            lore.add((selectedMode == HoneymodGuideMode.CHEAT_MODE ? ChatColor.GREEN : ChatColor.GRAY) + "Cheat Sheet");

            lore.add("");
            lore.add(ChatColor.GRAY + "\u21E8 " + ChatColor.YELLOW + "Click to change the type");
            meta.setLore(lore);
            item.setItemMeta(meta);

            return Optional.of(item);
        }

        return Optional.empty();
    }

    @Override
    public void onClick(@Nonnull Player p, @Nonnull ItemStack guide) {
        Optional<HoneymodGuideMode> current = getSelectedOption(p, guide);

        if (current.isPresent()) {
            HoneymodGuideMode next = getNextMode(p, current.get());
            setSelectedOption(p, guide, next);
        }

        HoneymodGuideSettings.openSettings(p, guide);
    }

    @Nonnull
    private HoneymodGuideMode getNextMode(@Nonnull Player p, @Nonnull HoneymodGuideMode mode) {
        if (p.hasPermission("slimefun.cheat.items")) {
            if (mode == HoneymodGuideMode.SURVIVAL_MODE) {
                return HoneymodGuideMode.CHEAT_MODE;
            } else {
                return HoneymodGuideMode.SURVIVAL_MODE;
            }
        } else {
            return HoneymodGuideMode.SURVIVAL_MODE;
        }
    }

    @Nonnull
    @Override
    public Optional<HoneymodGuideMode> getSelectedOption(@Nonnull Player p, @Nonnull ItemStack guide) {
        if (HoneymodUtils.isItemSimilar(guide, HoneymodGuide.getItem(HoneymodGuideMode.CHEAT_MODE), true, false)) {
            return Optional.of(HoneymodGuideMode.CHEAT_MODE);
        } else {
            return Optional.of(HoneymodGuideMode.SURVIVAL_MODE);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void setSelectedOption(Player p, ItemStack guide, HoneymodGuideMode value) {
        guide.setItemMeta(HoneymodGuide.getItem(value).getItemMeta());
    }

}