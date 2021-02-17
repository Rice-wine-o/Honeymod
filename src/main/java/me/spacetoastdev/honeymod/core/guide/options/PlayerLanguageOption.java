package me.spacetoastdev.honeymod.core.guide.options;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.data.PersistentDataAPI;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.spacetoastdev.honeymod.api.HoneymodAddon;
import me.spacetoastdev.honeymod.api.events.PlayerLanguageChangeEvent;
import me.spacetoastdev.honeymod.core.services.localization.Language;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.ChatUtils;
import me.spacetoastdev.honeymod.utils.ChestMenuUtils;
import me.spacetoastdev.honeymod.utils.HeadTexture;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

class PlayerLanguageOption implements HoneymodGuideOption<String> {

    @Override
    public HoneymodAddon getAddon() {
        return HoneymodPlugin.instance();
    }

    @Override
    public NamespacedKey getKey() {
        return HoneymodPlugin.getLocalization().getKey();
    }

    @Override
    public Optional<ItemStack> getDisplayItem(Player p, ItemStack guide) {
        if (HoneymodPlugin.getLocalization().isEnabled()) {
            Language language = HoneymodPlugin.getLocalization().getLanguage(p);
            String languageName = language.isDefault() ? (HoneymodPlugin.getLocalization().getMessage(p, "languages.default") + ChatColor.DARK_GRAY + " (" + language.getName(p) + ")") : HoneymodPlugin.getLocalization().getMessage(p, "languages." + language.getId());

            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("&e&o" + HoneymodPlugin.getLocalization().getMessage(p, "guide.work-in-progress"));
            lore.add("");
            lore.addAll(HoneymodPlugin.getLocalization().getMessages(p, "guide.languages.description", msg -> msg.replace("%contributors%", String.valueOf(HoneymodPlugin.getGitHubService().getContributors().size()))));
            lore.add("");
            lore.add("&7\u21E8 &e" + HoneymodPlugin.getLocalization().getMessage(p, "guide.languages.change"));

            ItemStack item = new CustomItem(language.getItem(), "&7" + HoneymodPlugin.getLocalization().getMessage(p, "guide.languages.selected-language") + " &a" + languageName, lore.toArray(new String[0]));
            return Optional.of(item);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void onClick(Player p, ItemStack guide) {
        openLanguageSelection(p, guide);
    }

    @Override
    public Optional<String> getSelectedOption(Player p, ItemStack guide) {
        return Optional.of(HoneymodPlugin.getLocalization().getLanguage(p).getId());
    }

    @Override
    public void setSelectedOption(Player p, ItemStack guide, String value) {
        if (value == null) {
            PersistentDataAPI.remove(p, getKey());
        } else {
            PersistentDataAPI.setString(p, getKey(), value);
        }
    }

    private void openLanguageSelection(Player p, ItemStack guide) {
        ChestMenu menu = new ChestMenu(HoneymodPlugin.getLocalization().getMessage(p, "guide.title.languages"));

        menu.setEmptySlotsClickable(false);
        menu.addMenuOpeningHandler(pl -> pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 0.7F, 0.7F));

        for (int i = 0; i < 9; i++) {
            if (i == 1) {
                menu.addItem(1, ChestMenuUtils.getBackButton(p, "", "&7" + HoneymodPlugin.getLocalization().getMessage(p, "guide.back.settings")), (pl, slot, item, action) -> {
                    HoneymodGuideSettings.openSettings(pl, guide);
                    return false;
                });
            } else if (i == 7) {
                menu.addItem(7, new CustomItem(HoneymodUtils.getCustomHead(HeadTexture.ADD_NEW_LANGUAGE.getTexture()), HoneymodPlugin.getLocalization().getMessage(p, "guide.languages.translations.name"), "", "&7\u21E8 &e" + HoneymodPlugin.getLocalization().getMessage(p, "guide.languages.translations.lore")), (pl, slot, item, action) -> {
                    ChatUtils.sendURL(pl, "https://github.com/Slimefun/Slimefun4/wiki/Translating-Slimefun");
                    pl.closeInventory();
                    return false;
                });
            } else {
                menu.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
            }
        }

        Language defaultLanguage = HoneymodPlugin.getLocalization().getDefaultLanguage();
        String defaultLanguageString = HoneymodPlugin.getLocalization().getMessage(p, "languages.default");

        menu.addItem(9, new CustomItem(defaultLanguage.getItem(), ChatColor.GRAY + defaultLanguageString + ChatColor.DARK_GRAY + " (" + defaultLanguage.getName(p) + ")", "", "&7\u21E8 &e" + HoneymodPlugin.getLocalization().getMessage(p, "guide.languages.select-default")), (pl, i, item, action) -> {
            HoneymodPlugin.instance().getServer().getPluginManager().callEvent(new PlayerLanguageChangeEvent(pl, HoneymodPlugin.getLocalization().getLanguage(pl), defaultLanguage));
            setSelectedOption(pl, guide, null);

            HoneymodPlugin.getLocalization().sendMessage(pl, "guide.languages.updated", msg -> msg.replace("%lang%", defaultLanguageString));

            HoneymodGuideSettings.openSettings(pl, guide);
            return false;
        });

        int slot = 10;

        for (Language language : HoneymodPlugin.getLocalization().getLanguages()) {
            menu.addItem(slot, new CustomItem(language.getItem(), ChatColor.GREEN + language.getName(p), "&b" + language.getTranslationProgress() + '%', "", "&7\u21E8 &e" + HoneymodPlugin.getLocalization().getMessage(p, "guide.languages.select")), (pl, i, item, action) -> {
                HoneymodPlugin.instance().getServer().getPluginManager().callEvent(new PlayerLanguageChangeEvent(pl, HoneymodPlugin.getLocalization().getLanguage(pl), language));
                setSelectedOption(pl, guide, language.getId());

                String name = language.getName(pl);
                HoneymodPlugin.getLocalization().sendMessage(pl, "guide.languages.updated", msg -> msg.replace("%lang%", name));

                HoneymodGuideSettings.openSettings(pl, guide);
                return false;
            });

            slot++;
        }

        menu.open(p);
    }

}
