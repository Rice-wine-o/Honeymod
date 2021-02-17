package me.spacetoastdev.honeymod.core.guide.options;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideMode;
import me.spacetoastdev.honeymod.core.researching.Research;
import me.spacetoastdev.honeymod.core.services.LocalizationService;
import me.spacetoastdev.honeymod.core.services.github.GitHubService;
import me.spacetoastdev.honeymod.core.services.localization.Language;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.ChatUtils;
import me.spacetoastdev.honeymod.utils.ChestMenuUtils;
import me.spacetoastdev.honeymod.utils.NumberUtils;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

/**
 * This static utility class offers various methods that provide access to the
 * Settings menu of our {@link HoneymodGuide}.
 * 
 * This menu is used to allow a {@link Player} to change things such as the {@link Language}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see HoneymodGuide
 *
 */
public final class HoneymodGuideSettings {

    private static final int[] BACKGROUND_SLOTS = { 1, 3, 5, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 48, 50, 52, 53 };
    private static final List<HoneymodGuideOption<?>> options = new ArrayList<>();

    static {
        options.add(new GuideModeOption());
        options.add(new FireworksOption());
        options.add(new PlayerLanguageOption());
    }

    private HoneymodGuideSettings() {}

    public static <T> void addOption(@Nonnull HoneymodGuideOption<T> option) {
        options.add(option);
    }

    @ParametersAreNonnullByDefault
    public static void openSettings(Player p, ItemStack guide) {
        ChestMenu menu = new ChestMenu(HoneymodPlugin.getLocalization().getMessage(p, "guide.title.settings"));

        menu.setEmptySlotsClickable(false);
        menu.addMenuOpeningHandler(pl -> pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 0.7F, 0.7F));

        ChestMenuUtils.drawBackground(menu, BACKGROUND_SLOTS);

        addHeader(p, menu, guide);
        addConfigurableOptions(p, menu, guide);

        menu.open(p);
    }

    @ParametersAreNonnullByDefault
    private static void addHeader(Player p, ChestMenu menu, ItemStack guide) {
        LocalizationService locale = HoneymodPlugin.getLocalization();

        // @formatter:off
        menu.addItem(0, new CustomItem(HoneymodGuide.getItem(HoneymodGuideMode.SURVIVAL_MODE),
            "&e\u21E6 " + locale.getMessage(p, "guide.back.title"),
            "",
            "&7" + locale.getMessage(p, "guide.back.guide")
        ));
        // @formatter:on

        menu.addMenuClickHandler(0, (pl, slot, item, action) -> {
            HoneymodGuide.openGuide(pl, guide);
            return false;
        });

        GitHubService github = HoneymodPlugin.getGitHubService();

        List<String> contributorsLore = new ArrayList<>();
        contributorsLore.add("");
        contributorsLore.addAll(locale.getMessages(p, "guide.credits.description", msg -> msg.replace("%contributors%", String.valueOf(github.getContributors().size()))));
        contributorsLore.add("");
        contributorsLore.add("&7\u21E8 &e" + locale.getMessage(p, "guide.credits.open"));

        // @formatter:off
        menu.addItem(2, new CustomItem(HoneymodUtils.getCustomHead("e952d2b3f351a6b0487cc59db31bf5f2641133e5ba0006b18576e996a0293e52"),
            "&c" + locale.getMessage(p, "guide.title.credits"),
            contributorsLore.toArray(new String[0])
        ));
        // @formatter:on

        menu.addMenuClickHandler(2, (pl, slot, action, item) -> {
            ContributorsMenu.open(pl, 0);
            return false;
        });

        // @formatter:off
        menu.addItem(4, new CustomItem(Material.WRITABLE_BOOK, 
            ChatColor.GREEN + locale.getMessage(p, "guide.title.versions"),
            "&7&o" + locale.getMessage(p, "guide.tooltips.versions-notice"),
            "",
            "&fMinecraft: &a" + Bukkit.getBukkitVersion(),
            "&fSlimefun: &a" + HoneymodPlugin.getVersion()),
            ChestMenuUtils.getEmptyClickHandler()
        );
        // @formatter:on

        // @formatter:off
        menu.addItem(6, new CustomItem(Material.COMPARATOR, 
           "&e" + locale.getMessage(p, "guide.title.source"),
           "", "&7Last Activity: &a" + NumberUtils.getElapsedTime(github.getLastUpdate()) + " ago",
           "&7Forks: &e" + github.getForks(),
           "&7Stars: &e" + github.getStars(),
           "",
           "&7&oSlimefun 4 is a community project,",
           "&7&othe source code is available on GitHub",
           "&7&oand if you want to keep this Plugin alive,",
           "&7&othen please consider contributing to it",
           "",
           "&7\u21E8 &eClick to go to GitHub"
        ));
        // @formatter:on

        menu.addMenuClickHandler(6, (pl, slot, item, action) -> {
            pl.closeInventory();
            ChatUtils.sendURL(pl, "https://github.com/Slimefun/Slimefun4");
            return false;
        });

        // @formatter:off
        menu.addItem(8, new CustomItem(Material.KNOWLEDGE_BOOK,
            "&3" + locale.getMessage(p, "guide.title.wiki"),
            "", "&7Do you need help with an Item or machine?",
            "&7You cannot figure out what to do?",
            "&7Check out our community-maintained Wiki",
            "&7and become one of our Editors!",
            "",
            "&7\u21E8 &eClick to go to the official Slimefun Wiki"
        ));
        // @formatter:on

        menu.addMenuClickHandler(8, (pl, slot, item, action) -> {
            pl.closeInventory();
            ChatUtils.sendURL(pl, "https://github.com/Slimefun/Slimefun4/wiki");
            return false;
        });

        // @formatter:off
        menu.addItem(47, new CustomItem(Material.BOOKSHELF,
            "&3" + locale.getMessage(p, "guide.title.addons"),
            "",
            "&7Slimefun is huge. But its addons are what makes",
            "&7this plugin truly shine. Go check them out, some",
            "&7of them may be exactly what you were missing out on!",
            "",
            "&7Installed on this Server: &b" + HoneymodPlugin.getInstalledAddons().size(),
            "",
            "&7\u21E8 &eClick to see all available addons for Slimefun4"
        ));
        // @formatter:on

        menu.addMenuClickHandler(47, (pl, slot, item, action) -> {
            pl.closeInventory();
            ChatUtils.sendURL(pl, "https://github.com/Slimefun/Slimefun4/wiki/Addons");
            return false;
        });

        if (HoneymodPlugin.getUpdater().getBranch().isOfficial()) {
            // @formatter:off
            menu.addItem(49, new CustomItem(Material.REDSTONE_TORCH,
                "&4" + locale.getMessage(p, "guide.title.bugs"),
                "",
                "&7&oBug reports have to be made in English!",
                "",
                "&7Open Issues: &a" + github.getOpenIssues(),
                "&7Pending Pull Requests: &a" + github.getPendingPullRequests(),
                "",
                "&7\u21E8 &eClick to go to the Slimefun4 Bug Tracker"
            ));
            // @formatter:on

            menu.addMenuClickHandler(49, (pl, slot, item, action) -> {
                pl.closeInventory();
                ChatUtils.sendURL(pl, "https://github.com/Slimefun/Slimefun4/issues");
                return false;
            });
        } else {
            menu.addItem(49, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        menu.addItem(51, new CustomItem(Material.TOTEM_OF_UNDYING, ChatColor.RED + locale.getMessage(p, "guide.work-in-progress")), (pl, slot, item, action) -> {
            // Add something here
            return false;
        });
    }

    @ParametersAreNonnullByDefault
    private static void addConfigurableOptions(Player p, ChestMenu menu, ItemStack guide) {
        int i = 19;

        for (HoneymodGuideOption<?> option : options) {
            Optional<ItemStack> item = option.getDisplayItem(p, guide);

            if (item.isPresent()) {
                menu.addItem(i, item.get());
                menu.addMenuClickHandler(i, (pl, slot, stack, action) -> {
                    option.onClick(p, guide);
                    return false;
                });

                i++;
            }
        }
    }

    /**
     * This method checks if the given {@link Player} has enabled the {@link FireworksOption}
     * in their {@link HoneymodGuide}.
     * If they enabled this setting, they will see fireworks when they unlock a {@link Research}.
     * 
     * @param p
     *            The {@link Player}
     * 
     * @return Whether this {@link Player} wants to see fireworks when unlocking a {@link Research}
     */
    public static boolean hasFireworksEnabled(@Nonnull Player p) {
        for (HoneymodGuideOption<?> option : options) {
            if (option instanceof FireworksOption) {
                FireworksOption fireworks = (FireworksOption) option;
                ItemStack guide = HoneymodGuide.getItem(HoneymodGuideMode.SURVIVAL_MODE);
                return fireworks.getSelectedOption(p, guide).orElse(true);
            }
        }

        return true;
    }

}
