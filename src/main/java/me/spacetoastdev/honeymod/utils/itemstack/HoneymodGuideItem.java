package me.spacetoastdev.honeymod.utils.itemstack;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import io.github.thebusybiscuit.cscorelib2.data.PersistentDataAPI;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuide;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideImplementation;
import me.spacetoastdev.honeymod.core.guide.HoneymodGuideMode;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This is just a helper {@link ItemStack} class for the {@link HoneymodGuide} {@link ItemStack}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see HoneymodGuide
 * @see HoneymodGuideImplementation
 *
 */
public class HoneymodGuideItem extends ItemStack {

    public HoneymodGuideItem(@Nonnull HoneymodGuideImplementation implementation, @Nonnull String name) {
        super(Material.ENCHANTED_BOOK);

        ItemMeta meta = getItemMeta();
        meta.setDisplayName(ChatColors.color(name));

        List<String> lore = new ArrayList<>();
        HoneymodGuideMode type = implementation.getMode();
        lore.add(type == HoneymodGuideMode.CHEAT_MODE ? ChatColors.color("&4&lOnly openable by Admins") : "");
        lore.add(ChatColors.color("&eRight Click &8\u21E8 &7Browse Items"));
        lore.add(ChatColors.color("&eShift + Right Click &8\u21E8 &7Open Settings / Credits"));

        meta.setLore(lore);

        PersistentDataAPI.setString(meta, HoneymodPlugin.getRegistry().getGuideDataKey(), type.name());
        HoneymodPlugin.getItemTextureService().setTexture(meta, "HONEYMOD_GUIDE");

        setItemMeta(meta);
    }

}
