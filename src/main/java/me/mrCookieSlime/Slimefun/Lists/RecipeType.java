package me.mrCookieSlime.Slimefun.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.BiConsumer;

import org.bukkit.ChatColor;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.cscorelib2.recipes.MinecraftRecipe;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.MinecraftVersion;
import me.spacetoastdev.honeymod.core.multiblocks.MultiBlockMachine;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.altar.AltarRecipe;
import me.spacetoastdev.honeymod.implementation.items.altar.AncientAltar;

public class RecipeType implements Keyed {

    public static final RecipeType MULTIBLOCK = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "multiblock"), new CustomItem(Material.BRICKS, "&bMultiBlock", "", "&a&oBuild it in the World"));
    public static final RecipeType ARMOR_FORGE = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "armor_forge"), HoneymodItems.ARMOR_FORGE, "", "&a&oCraft it in an Armor Forge");
    public static final RecipeType GRIND_STONE = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "grind_stone"), HoneymodItems.GRIND_STONE, "", "&a&oGrind it using the Grind Stone");
    public static final RecipeType SMELTERY = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "smeltery"), HoneymodItems.SMELTERY, "", "&a&oSmelt it using a Smeltery");
    public static final RecipeType ORE_CRUSHER = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "ore_crusher"), HoneymodItems.ORE_CRUSHER, "", "&a&oCrush it using the Ore Crusher");
    public static final RecipeType GOLD_PAN = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "gold_pan"), HoneymodItems.GOLD_PAN, "", "&a&oUse a Gold Pan on Gravel to obtain this Item");
    public static final RecipeType COMPRESSOR = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "compressor"), HoneymodItems.COMPRESSOR, "", "&a&oCompress it using the Compressor");
    public static final RecipeType PRESSURE_CHAMBER = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "pressure_chamber"), HoneymodItems.PRESSURE_CHAMBER, "", "&a&oCompress it using the Pressure Chamber");
    public static final RecipeType MAGIC_WORKBENCH = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "magic_workbench"), HoneymodItems.MAGIC_WORKBENCH, "", "&a&oCraft it in a Magic Workbench");
    public static final RecipeType ORE_WASHER = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "ore_washer"), HoneymodItems.ORE_WASHER, "", "&a&oWash it in an Ore Washer");
    public static final RecipeType ENHANCED_CRAFTING_TABLE = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "enhanced_crafting_table"), HoneymodItems.ENHANCED_CRAFTING_TABLE, "", "&a&oA regular Crafting Table cannot", "&a&ohold this massive Amount of Power...");
    public static final RecipeType JUICER = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "juicer"), HoneymodItems.JUICER, "", "&a&oUsed for Juice Creation");

    public static final RecipeType ANCIENT_ALTAR = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "ancient_altar"), HoneymodItems.ANCIENT_ALTAR, (recipe, output) -> {
        AltarRecipe altarRecipe = new AltarRecipe(Arrays.asList(recipe), output);
        AncientAltar altar = ((AncientAltar) HoneymodItems.ANCIENT_ALTAR.getItem());
        altar.getRecipes().add(altarRecipe);
    });

    public static final RecipeType MOB_DROP = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "mob_drop"), new CustomItem(Material.IRON_SWORD, "&bMob Drop"), RecipeType::registerMobDrop, "", "&rKill the specified Mob to obtain this Item");
    public static final RecipeType BARTER_DROP = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "barter_drop"), new CustomItem(Material.GOLD_INGOT, "&bBarter Drop"), RecipeType::registerBarterDrop, "&aBarter with piglins for a chance", "&ato obtain this item");

    public static final RecipeType HEATED_PRESSURE_CHAMBER = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "heated_pressure_chamber"), HoneymodItems.HEATED_PRESSURE_CHAMBER);
    public static final RecipeType FOOD_FABRICATOR = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "food_fabricator"), HoneymodItems.FOOD_FABRICATOR);
    public static final RecipeType FOOD_COMPOSTER = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "food_composter"), HoneymodItems.FOOD_COMPOSTER);
    public static final RecipeType FREEZER = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "freezer"), HoneymodItems.FREEZER);
    public static final RecipeType REFINERY = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "refinery"), HoneymodItems.REFINERY);

    public static final RecipeType GEO_MINER = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "geo_miner"), HoneymodItems.GEO_MINER);
    public static final RecipeType NUCLEAR_REACTOR = new RecipeType(new NamespacedKey(HoneymodPlugin.instance(), "nuclear_reactor"), HoneymodItems.NUCLEAR_REACTOR);

    public static final RecipeType NULL = new RecipeType();

    private final ItemStack item;
    private final NamespacedKey key;
    private final String machine;
    private BiConsumer<ItemStack[], ItemStack> consumer;

    private RecipeType() {
        this.item = null;
        this.machine = "";
        this.key = new NamespacedKey(HoneymodPlugin.instance(), "null");
    }

    public RecipeType(ItemStack item, String machine) {
        this.item = item;
        this.machine = machine;

        if (machine.length() > 0) {
            this.key = new NamespacedKey(HoneymodPlugin.instance(), machine.toLowerCase(Locale.ROOT));
        } else {
            this.key = new NamespacedKey(HoneymodPlugin.instance(), "unknown");
        }
    }

    public RecipeType(NamespacedKey key, SlimefunItemStack slimefunItem, String... lore) {
        this(key, slimefunItem, null, lore);
    }

    public RecipeType(NamespacedKey key, ItemStack item, BiConsumer<ItemStack[], ItemStack> callback, String... lore) {
        this.item = new CustomItem(item, null, lore);
        this.key = key;
        this.consumer = callback;

        if (item instanceof SlimefunItemStack) {
            this.machine = ((SlimefunItemStack) item).getItemId();
        } else {
            this.machine = "";
        }
    }

    public RecipeType(NamespacedKey key, ItemStack item) {
        this.key = key;
        this.item = item;
        this.machine = item instanceof SlimefunItemStack ? ((SlimefunItemStack) item).getItemId() : "";
    }

    public RecipeType(MinecraftRecipe<?> recipe) {
        this.item = new ItemStack(recipe.getMachine());
        this.machine = "";
        this.key = NamespacedKey.minecraft(recipe.getRecipeClass().getSimpleName().toLowerCase(Locale.ROOT).replace("recipe", ""));
    }

    public void register(ItemStack[] recipe, ItemStack result) {
        if (consumer != null) {
            consumer.accept(recipe, result);
        } else {
            SlimefunItem slimefunItem = SlimefunItem.getByID(this.machine);

            if (slimefunItem instanceof MultiBlockMachine) {
                ((MultiBlockMachine) slimefunItem).addRecipe(recipe, result);
            }
        }
    }

    public ItemStack toItem() {
        return this.item;
    }

    public ItemStack getItem(Player p) {
        return HoneymodPlugin.getLocalization().getRecipeTypeItem(p, this);
    }

    public SlimefunItem getMachine() {
        return SlimefunItem.getByID(machine);
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }

    private static void registerBarterDrop(ItemStack[] recipe, ItemStack output) {
        if (HoneymodPlugin.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_16)) {
            HoneymodPlugin.getRegistry().getBarteringDrops().add(output);
        }
    }

    private static void registerMobDrop(ItemStack[] recipe, ItemStack output) {
        String mob = ChatColor.stripColor(recipe[4].getItemMeta().getDisplayName()).toUpperCase(Locale.ROOT).replace(' ', '_');
        EntityType entity = EntityType.valueOf(mob);
        Set<ItemStack> dropping = HoneymodPlugin.getRegistry().getMobDrops().getOrDefault(entity, new HashSet<>());
        dropping.add(output);
        HoneymodPlugin.getRegistry().getMobDrops().put(entity, dropping);
    }

    public static List<ItemStack> getRecipeInputs(MultiBlockMachine machine) {
        if (machine == null) {
            return new ArrayList<>();
        }

        List<ItemStack[]> recipes = machine.getRecipes();
        List<ItemStack> convertible = new ArrayList<>();

        for (int i = 0; i < recipes.size(); i++) {
            if (i % 2 == 0) {
                convertible.add(recipes.get(i)[0]);
            }
        }

        return convertible;
    }

    public static List<ItemStack[]> getRecipeInputList(MultiBlockMachine machine) {
        if (machine == null) {
            return new ArrayList<>();
        }

        List<ItemStack[]> recipes = machine.getRecipes();
        List<ItemStack[]> convertible = new ArrayList<>();

        for (int i = 0; i < recipes.size(); i++) {
            if (i % 2 == 0) {
                convertible.add(recipes.get(i));
            }
        }

        convertible.sort(Comparator.comparing(recipe -> {
            int emptySlots = 9;

            for (ItemStack ingredient : recipe) {
                if (ingredient != null) {
                    emptySlots--;
                }
            }

            return emptySlots;
        }));

        return convertible;
    }

    public static ItemStack getRecipeOutput(MultiBlockMachine machine, ItemStack input) {
        List<ItemStack[]> recipes = machine.getRecipes();
        return recipes.get(((getRecipeInputs(machine).indexOf(input) * 2) + 1))[0].clone();
    }

    public static ItemStack getRecipeOutputList(MultiBlockMachine machine, ItemStack[] input) {
        List<ItemStack[]> recipes = machine.getRecipes();
        return recipes.get((recipes.indexOf(input) + 1))[0];
    }
}
