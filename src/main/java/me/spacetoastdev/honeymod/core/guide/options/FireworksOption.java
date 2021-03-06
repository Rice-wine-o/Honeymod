package me.spacetoastdev.honeymod.core.guide.options;

import java.util.Optional;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.data.PersistentDataAPI;
import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.spacetoastdev.honeymod.api.HoneymodAddon;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

class FireworksOption implements HoneymodGuideOption<Boolean> {

    @Override
    public HoneymodAddon getAddon() {
        return HoneymodPlugin.instance();
    }

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(HoneymodPlugin.instance(), "research_fireworks");
    }

    @Override
    public Optional<ItemStack> getDisplayItem(Player p, ItemStack guide) {
        if (HoneymodPlugin.getRegistry().isResearchFireworkEnabled()) {
            boolean enabled = getSelectedOption(p, guide).orElse(true);
            ItemStack item = new CustomItem(Material.FIREWORK_ROCKET, "&bFireworks: &" + (enabled ? "aYes" : "4No"), "", "&7You can now toggle whether you", "&7will be presented with a big firework", "&7upon researching an item.", "", "&7\u21E8 &eClick to " + (enabled ? "disable" : "enable") + " your fireworks");
            return Optional.of(item);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void onClick(Player p, ItemStack guide) {
        setSelectedOption(p, guide, !getSelectedOption(p, guide).orElse(true));
        HoneymodGuideSettings.openSettings(p, guide);
    }

    @Override
    public Optional<Boolean> getSelectedOption(Player p, ItemStack guide) {
        NamespacedKey key = getKey();
        boolean value = !PersistentDataAPI.hasByte(p, key) || PersistentDataAPI.getByte(p, key) == (byte) 1;
        return Optional.of(value);
    }

    @Override
    public void setSelectedOption(Player p, ItemStack guide, Boolean value) {
        PersistentDataAPI.setByte(p, getKey(), value.booleanValue() ? (byte) 1 : (byte) 0);
    }

}
