package me.spacetoastdev.honeymod.implementation.setup;

import java.time.Month;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.spacetoastdev.honeymod.core.categories.LockedCategory;
import me.spacetoastdev.honeymod.core.categories.SeasonalCategory;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.utils.ChatUtils;
import me.spacetoastdev.honeymod.utils.HeadTexture;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

/**
 * This class holds a reference to every {@link Category}
 * found in Slimefun itself.
 * 
 * Addons should use their own {@link Category} hence why the visible of this class was now
 * changed to package-private. Only {@link HoneymodItemSetup} has access to this class.
 * 
 * @author TheBusyBiscuit
 * 
 * @see Category
 * @see LockedCategory
 * @see SeasonalCategory
 *
 */
public class DefaultCategories {

    // Standard Categories
    public final Category weapons = new Category(new NamespacedKey(HoneymodPlugin.instance(), "weapons"), new CustomItem(HoneymodItems.BLADE_OF_VAMPIRES, "&7Weapons"), 1);
    public final Category tools = new Category(new NamespacedKey(HoneymodPlugin.instance(), "tools"), new CustomItem(HoneymodItems.SMELTERS_PICKAXE, "&7Tools"), 1);
    public final Category usefulItems = new Category(new NamespacedKey(HoneymodPlugin.instance(), "items"), new CustomItem(HoneymodItems.BACKPACK_MEDIUM, "&7Useful Items"), 1);
    public final Category basicMachines = new Category(new NamespacedKey(HoneymodPlugin.instance(), "basic_machines"), new CustomItem(HoneymodItems.ENHANCED_CRAFTING_TABLE, "&7Basic Machines"), 1);
    public final Category food = new Category(new NamespacedKey(HoneymodPlugin.instance(), "food"), new CustomItem(HoneymodItems.FORTUNE_COOKIE, "&7Food"), 2);
    public final Category armor = new Category(new NamespacedKey(HoneymodPlugin.instance(), "armor"), new CustomItem(HoneymodItems.DAMASCUS_STEEL_CHESTPLATE, "&7Armor"), 2);

    // Magical
    public final Category magicalResources = new Category(new NamespacedKey(HoneymodPlugin.instance(), "magical_items"), new CustomItem(HoneymodItems.ENDER_RUNE, "&7Magical Items"), 2);
    public final Category magicalGadgets = new Category(new NamespacedKey(HoneymodPlugin.instance(), "magical_gadgets"), new CustomItem(HoneymodItems.INFUSED_ELYTRA, "&7Magical Gadgets"), 3);
    public final Category magicalArmor = new Category(new NamespacedKey(HoneymodPlugin.instance(), "magical_armor"), new CustomItem(HoneymodItems.ENDER_HELMET, "&7Magical Armor"), 2);

    // Resources and tech stuff
    public final Category misc = new Category(new NamespacedKey(HoneymodPlugin.instance(), "misc"), new CustomItem(HoneymodItems.TIN_CAN, "&7Miscellaneous"), 2);
    public final Category technicalComponents = new Category(new NamespacedKey(HoneymodPlugin.instance(), "tech_misc"), new CustomItem(HoneymodItems.HEATING_COIL, "&7Technical Components"), 2);
    public final Category technicalGadgets = new Category(new NamespacedKey(HoneymodPlugin.instance(), "technical_gadgets"), new CustomItem(HoneymodItems.STEEL_JETPACK, "&7Technical Gadgets"), 3);
    public final Category resources = new Category(new NamespacedKey(HoneymodPlugin.instance(), "resources"), new CustomItem(HoneymodItems.SYNTHETIC_SAPPHIRE, "&7Resources"), 1);

    // Locked Categories
    public final LockedCategory electricity = new LockedCategory(new NamespacedKey(HoneymodPlugin.instance(), "electricity"), new CustomItem(HoneymodItems.NUCLEAR_REACTOR, "&bEnergy and Electricity"), 4, basicMachines.getKey());
    public final LockedCategory androids = new LockedCategory(new NamespacedKey(HoneymodPlugin.instance(), "androids"), new CustomItem(HoneymodItems.PROGRAMMABLE_ANDROID, "&cProgrammable Androids"), 4, basicMachines.getKey());
    public final Category cargo = new LockedCategory(new NamespacedKey(HoneymodPlugin.instance(), "cargo"), new CustomItem(HoneymodItems.CARGO_MANAGER, "&cCargo Management"), 4, basicMachines.getKey());
    public final LockedCategory gps = new LockedCategory(new NamespacedKey(HoneymodPlugin.instance(), "gps"), new CustomItem(HoneymodItems.GPS_TRANSMITTER, "&bGPS-based Machines"), 4, basicMachines.getKey());

    // Seasonal Categories
    public final SeasonalCategory christmas = new SeasonalCategory(new NamespacedKey(HoneymodPlugin.instance(), "christmas"), Month.DECEMBER, 1, new CustomItem(HoneymodUtils.getCustomHead("215ba31cde2671b8f176de6a9ffd008035f0590d63ee240be6e8921cd2037a45"), ChatUtils.christmas("Christmas") + " &7(December only)"));
    public final SeasonalCategory valentinesDay = new SeasonalCategory(new NamespacedKey(HoneymodPlugin.instance(), "valentines_day"), Month.FEBRUARY, 2, new CustomItem(HoneymodUtils.getCustomHead("55d89431d14bfef2060461b4a3565614dc51115c001fae2508e8684bc0ae6a80"), "&dValentine's Day" + " &7(14th February)"));
    public final SeasonalCategory easter = new SeasonalCategory(new NamespacedKey(HoneymodPlugin.instance(), "easter"), Month.APRIL, 2, new CustomItem(HeadTexture.EASTER_EGG.getAsItemStack(), "&6Easter" + " &7(April)"));
    public final SeasonalCategory birthday = new SeasonalCategory(new NamespacedKey(HoneymodPlugin.instance(), "birthday"), Month.OCTOBER, 1, new CustomItem(Material.FIREWORK_ROCKET, "&a&lTheBusyBiscuit's Birthday &7(26th October)"));
    public final SeasonalCategory halloween = new SeasonalCategory(new NamespacedKey(HoneymodPlugin.instance(), "halloween"), Month.OCTOBER, 1, new CustomItem(Material.JACK_O_LANTERN, "&6&lHalloween &7(31st October)"));

}
