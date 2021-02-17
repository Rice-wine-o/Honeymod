package me.spacetoastdev.honeymod.implementation.items.food;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.core.handlers.ItemConsumptionHandler;
import me.spacetoastdev.honeymod.core.services.LocalizationService;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.SimpleHoneymodItem;

/**
 * The {@link FortuneCookie} is a rather simple {@link SlimefunItem}, it's a cookie which
 * sends the {@link Player} who ate it a random text message.
 * The messages can be defined in the {@link LocalizationService}.
 * 
 * @author TheBusyBiscuit
 *
 */
public class FortuneCookie extends SimpleHoneymodItem<ItemConsumptionHandler> {

    @ParametersAreNonnullByDefault
    public FortuneCookie(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemConsumptionHandler getItemHandler() {
        return (e, p, item) -> {
            List<String> messages = HoneymodPlugin.getLocalization().getMessages(p, "messages.fortune-cookie");
            String message = messages.get(ThreadLocalRandom.current().nextInt(messages.size()));

            p.sendMessage(ChatColors.color(message));
        };
    }

}
