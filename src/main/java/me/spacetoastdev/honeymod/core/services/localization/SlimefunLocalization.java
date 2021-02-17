package me.spacetoastdev.honeymod.core.services.localization;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import io.github.thebusybiscuit.cscorelib2.config.Localization;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.spacetoastdev.honeymod.api.MinecraftVersion;
import me.spacetoastdev.honeymod.api.HoneymodBranch;
import me.spacetoastdev.honeymod.core.services.LocalizationService;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * This is an abstract parent class of {@link LocalizationService}.
 * There is not really much more I can say besides that...
 * 
 * @author TheBusyBiscuit
 * 
 * @see LocalizationService
 *
 */
public abstract class SlimefunLocalization extends Localization implements Keyed {

    protected SlimefunLocalization(@Nonnull HoneymodPlugin plugin) {
        super(plugin);
    }

    /**
     * This method attempts to return the {@link Language} with the given
     * language code.
     * 
     * @param id
     *            The language code
     * 
     * @return A {@link Language} with the given id or null
     */
    @Nullable
    public abstract Language getLanguage(@Nonnull String id);

    /**
     * This method returns the currently selected {@link Language} of a {@link Player}.
     * 
     * @param p
     *            The {@link Player} to query
     * 
     * @return The {@link Language} that was selected by the given {@link Player}
     */
    public abstract Language getLanguage(@Nonnull Player p);

    /**
     * This method returns the default {@link Language} of this {@link Server}
     * 
     * @return The default {@link Language}
     */
    public abstract Language getDefaultLanguage();

    /**
     * This returns whether a {@link Language} with the given id exists within
     * the project resources.
     * 
     * @param id
     *            The {@link Language} id
     * 
     * @return Whether the project contains a {@link Language} with that id
     */
    protected abstract boolean hasLanguage(@Nonnull String id);

    /**
     * This method returns a full {@link Collection} of every {@link Language} that was
     * found.
     * 
     * @return A {@link Collection} that contains every installed {@link Language}
     */
    @Nonnull
    public abstract Collection<Language> getLanguages();

    /**
     * This method adds a new {@link Language} with the given id and texture.
     * 
     * @param id
     *            The {@link Language} id
     * @param texture
     *            The texture of how this {@link Language} should be displayed
     */
    protected abstract void addLanguage(@Nonnull String id, @Nonnull String texture);

    /**
     * This will load every {@link SupportedLanguage} into memory.
     * To be precise: It performs {@link #addLanguage(String, String)} for every
     * value of {@link SupportedLanguage}.
     */
    protected void loadEmbeddedLanguages() {
        for (SupportedLanguage lang : SupportedLanguage.values()) {
            if (lang.isReadyForRelease() || HoneymodPlugin.getUpdater().getBranch() != HoneymodBranch.STABLE) {
                addLanguage(lang.getLanguageId(), lang.getTexture());
            }
        }
    }

    public String getMessage(Player p, String key) {
        Language language = getLanguage(p);

        if (language == null) {
            return "NO LANGUAGE FOUND";
        }

        String message = language.getMessagesFile().getString(key);

        if (message == null) {
            Language fallback = getLanguage(SupportedLanguage.ENGLISH.getLanguageId());
            return fallback.getMessagesFile().getString(key);
        }

        return message;
    }

    public List<String> getMessages(Player p, String key) {
        Language language = getLanguage(p);

        if (language == null) {
            return Arrays.asList("NO LANGUAGE FOUND");
        }

        List<String> messages = language.getMessagesFile().getStringList(key);

        if (messages.isEmpty()) {
            Language fallback = getLanguage(SupportedLanguage.ENGLISH.getLanguageId());
            return fallback.getMessagesFile().getStringList(key);
        }

        return messages;
    }

    public List<String> getMessages(Player p, String key, UnaryOperator<String> function) {
        List<String> messages = getMessages(p, key);
        messages.replaceAll(function);

        return messages;
    }

    public String getResearchName(Player p, NamespacedKey key) {
        Language language = getLanguage(p);

        if (language.getResearchesFile() == null) {
            return null;
        }

        return language.getResearchesFile().getString(key.getNamespace() + "." + key.getKey());
    }

    public String getCategoryName(Player p, NamespacedKey key) {
        Language language = getLanguage(p);

        if (language.getCategoriesFile() == null) {
            return null;
        }

        return language.getCategoriesFile().getString(key.getNamespace() + "." + key.getKey());
    }

    public String getResourceString(Player p, String key) {
        Language language = getLanguage(p);

        String value = language.getResourcesFile() != null ? language.getResourcesFile().getString(key) : null;

        if (value != null) {
            return value;
        } else {
            Language fallback = getLanguage(SupportedLanguage.ENGLISH.getLanguageId());
            return fallback.getResourcesFile().getString(key);
        }
    }

    public ItemStack getRecipeTypeItem(Player p, RecipeType recipeType) {
        Language language = getLanguage(p);
        ItemStack item = recipeType.toItem();
        NamespacedKey key = recipeType.getKey();

        if (language.getRecipeTypesFile() == null || !language.getRecipeTypesFile().contains(key.getNamespace() + "." + key.getKey())) {
            language = getLanguage("en");
        }

        if (!language.getRecipeTypesFile().contains(key.getNamespace() + "." + key.getKey())) {
            return item;
        }

        FileConfiguration config = language.getRecipeTypesFile();

        return new CustomItem(item, meta -> {
            meta.setDisplayName(ChatColor.AQUA + config.getString(key.getNamespace() + "." + key.getKey() + ".name"));
            List<String> lore = config.getStringList(key.getNamespace() + "." + key.getKey() + ".lore");
            lore.replaceAll(line -> ChatColor.GRAY + line);
            meta.setLore(lore);

            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        });
    }

    @Override
    public void sendMessage(CommandSender recipient, String key, boolean addPrefix) {
        String prefix = addPrefix ? getPrefix() : "";

        if (recipient instanceof Player) {
            recipient.sendMessage(ChatColors.color(prefix + getMessage((Player) recipient, key)));
        } else {
            recipient.sendMessage(ChatColor.stripColor(ChatColors.color(prefix + getMessage(key))));
        }
    }

    public void sendActionbarMessage(@Nonnull Player player, @Nonnull String key, boolean addPrefix) {
        String prefix = addPrefix ? getPrefix() : "";
        String message = ChatColors.color(prefix + getMessage(player, key));

        BaseComponent[] components = TextComponent.fromLegacyText(message);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, components);
    }

    @Override
    public void sendMessage(CommandSender recipient, String key) {
        sendMessage(recipient, key, true);
    }

    public void sendMessage(CommandSender recipient, String key, UnaryOperator<String> function) {
        sendMessage(recipient, key, true, function);
    }

    @Override
    public void sendMessage(CommandSender recipient, String key, boolean addPrefix, UnaryOperator<String> function) {
        if (HoneymodPlugin.getMinecraftVersion() == MinecraftVersion.UNIT_TEST) {
            return;
        }

        String prefix = addPrefix ? getPrefix() : "";

        if (recipient instanceof Player) {
            recipient.sendMessage(ChatColors.color(prefix + function.apply(getMessage((Player) recipient, key))));
        } else {
            recipient.sendMessage(ChatColor.stripColor(ChatColors.color(prefix + function.apply(getMessage(key)))));
        }
    }

    @Override
    public void sendMessages(CommandSender recipient, String key) {
        String prefix = getPrefix();

        if (recipient instanceof Player) {
            for (String translation : getMessages((Player) recipient, key)) {
                String message = ChatColors.color(prefix + translation);
                recipient.sendMessage(message);
            }
        } else {
            for (String translation : getMessages(key)) {
                String message = ChatColors.color(prefix + translation);
                recipient.sendMessage(ChatColor.stripColor(message));
            }
        }
    }

    @Override
    public void sendMessages(CommandSender recipient, String key, boolean addPrefix, UnaryOperator<String> function) {
        String prefix = addPrefix ? getPrefix() : "";

        if (recipient instanceof Player) {
            for (String translation : getMessages((Player) recipient, key)) {
                String message = ChatColors.color(prefix + function.apply(translation));
                recipient.sendMessage(message);
            }
        } else {
            for (String translation : getMessages(key)) {
                String message = ChatColors.color(prefix + function.apply(translation));
                recipient.sendMessage(ChatColor.stripColor(message));
            }
        }
    }

    public void sendMessages(CommandSender recipient, String key, UnaryOperator<String> function) {
        sendMessages(recipient, key, true, function);
    }

}
