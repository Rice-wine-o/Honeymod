package me.spacetoastdev.honeymod.utils.itemstack;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.inventory.meta.FireworkEffectMeta;

import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;

public class ColoredFireworkStar extends CustomItem {

    public ColoredFireworkStar(Color color, String name, String... lore) {
        super(Material.FIREWORK_STAR, im -> {
            if (name != null) {
                im.setDisplayName(ChatColors.color(name));
            }

            ((FireworkEffectMeta) im).setEffect(FireworkEffect.builder().with(Type.BURST).withColor(color).build());

            if (lore.length > 0) {
                List<String> lines = new ArrayList<>();

                for (String line : lore) {
                    lines.add(ChatColors.color(line));
                }

                im.setLore(lines);
            }
        });
    }

}
