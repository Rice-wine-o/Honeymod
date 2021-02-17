package me.spacetoastdev.honeymod.implementation.setup;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.spacetoastdev.honeymod.core.researching.Research;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This static setup class is used to register all default implementations of
 * {@link Research} on startup.
 *
 * @see Research
 * @see HoneymodItems
 *
 */
public final class ResearchSetup {

    private static boolean alreadyRan = false;

    private ResearchSetup() {}

    public static void setupResearches() {
        if (alreadyRan) {
            throw new UnsupportedOperationException("Researches can only be registered once!");
        }

        alreadyRan = true;

        register("walking_sticks", 0, "Walking Sticks", 1, HoneymodItems.GRANDMAS_WALKING_STICK, HoneymodItems.GRANDPAS_WALKING_STICK);
        register("portable_crafter", 1, "Portable Crafter", 1, HoneymodItems.PORTABLE_CRAFTER);
        register("fortune_cookie", 2, "Fortune Cookie", 1, HoneymodItems.FORTUNE_COOKIE);
        register("portable_dustbin", 4, "Portable Dustbin", 2, HoneymodItems.PORTABLE_DUSTBIN);
        register("meat_jerky", 5, "Jerkys", 2, HoneymodItems.BEEF_JERKY, HoneymodItems.FISH_JERKY, HoneymodItems.RABBIT_JERKY, HoneymodItems.MUTTON_JERKY, HoneymodItems.CHICKEN_JERKY, HoneymodItems.PORK_JERKY);
        register("armor_forge", 6, "Armor Crafting", 2, HoneymodItems.ARMOR_FORGE);
        register("glowstone_armor", 7, "Glowstone Armor", 3, HoneymodItems.GLOWSTONE_HELMET, HoneymodItems.GLOWSTONE_CHESTPLATE, HoneymodItems.GLOWSTONE_LEGGINGS, HoneymodItems.GLOWSTONE_BOOTS);
        register("lumps", 8, "Lumps and Magic", 3, HoneymodItems.MAGIC_LUMP_1, HoneymodItems.MAGIC_LUMP_2, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.ENDER_LUMP_1, HoneymodItems.ENDER_LUMP_2, HoneymodItems.ENDER_LUMP_3);
        register("ender_backpack", 9, "Ender Backpack", 4, HoneymodItems.ENDER_BACKPACK);
        register("ender_armor", 10, "Ender Armor", 4, HoneymodItems.ENDER_HELMET, HoneymodItems.ENDER_CHESTPLATE, HoneymodItems.ENDER_LEGGINGS, HoneymodItems.ENDER_BOOTS);
        register("magic_eye_of_ender", 11, "Magic Eye of Ender", 4, HoneymodItems.MAGIC_EYE_OF_ENDER);
        register("magic_sugar", 12, "Magic Sugar", 4, HoneymodItems.MAGIC_SUGAR);
        register("monster_jerky", 13, "Monster Jerky", 5, HoneymodItems.MONSTER_JERKY);
        register("slime_armor", 14, "Slime Armor", 5, HoneymodItems.SLIME_HELMET, HoneymodItems.SLIME_CHESTPLATE, HoneymodItems.SLIME_LEGGINGS, HoneymodItems.SLIME_BOOTS);
        register("sword_of_beheading", 15, "Sword of Beheading", 6, HoneymodItems.SWORD_OF_BEHEADING);
        register("basic_circuit_board", 16, "Electric Work", 8, HoneymodItems.BASIC_CIRCUIT_BOARD);
        register("advanced_circuit_board", 17, "Advanced Electricity", 9, HoneymodItems.ADVANCED_CIRCUIT_BOARD);
        register("smeltery", 18, "Hot Smelting", 10, HoneymodItems.SMELTERY);
        register("steel", 19, "Steel Age", 11, HoneymodItems.STEEL_INGOT);
        register("misc_power_items", 20, "Important Power-Related Items", 12, HoneymodItems.SULFATE, HoneymodItems.POWER_CRYSTAL);
        register("battery", 21, "Your first Battery", 10, HoneymodItems.BATTERY);
        register("steel_plate", 22, "Steel Plating", 14, HoneymodItems.STEEL_PLATE);
        register("steel_thruster", 23, "Steel Thruster", 14, HoneymodItems.STEEL_THRUSTER);
        register("parachute", 24, "Parachute", 15, HoneymodItems.PARACHUTE);
        register("grappling_hook", 25, "Grappling Hook", 15, HoneymodItems.GRAPPLING_HOOK, HoneymodItems.HOOK, HoneymodItems.CHAIN);
        register("jetpacks", 26, "Jetpacks", 22, HoneymodItems.DURALUMIN_JETPACK, HoneymodItems.BILLON_JETPACK, HoneymodItems.SOLDER_JETPACK, HoneymodItems.STEEL_JETPACK, HoneymodItems.DAMASCUS_STEEL_JETPACK, HoneymodItems.REINFORCED_ALLOY_JETPACK);
        register("multitools", 27, "Multi Tools", 18, HoneymodItems.DURALUMIN_MULTI_TOOL, HoneymodItems.SOLDER_MULTI_TOOL, HoneymodItems.BILLON_MULTI_TOOL, HoneymodItems.STEEL_MULTI_TOOL, HoneymodItems.DAMASCUS_STEEL_MULTI_TOOL, HoneymodItems.REINFORCED_ALLOY_MULTI_TOOL);
        register("solar_panel_and_helmet", 28, "Solar Power", 17, HoneymodItems.SOLAR_PANEL, HoneymodItems.SOLAR_HELMET);
        register("elemental_staff", 29, "Elemental Staves", 17, HoneymodItems.STAFF_ELEMENTAL);
        register("grind_stone", 30, "Grind Stone", 4, HoneymodItems.GRIND_STONE);
        register("cactus_armor", 31, "Cactus Suit", 5, HoneymodItems.CACTUS_BOOTS, HoneymodItems.CACTUS_CHESTPLATE, HoneymodItems.CACTUS_HELMET, HoneymodItems.CACTUS_LEGGINGS);
        register("gold_pan", 32, "Gold Pan", 5, HoneymodItems.GOLD_PAN);
        register("magical_book_cover", 33, "Magical Book Binding", 5, HoneymodItems.MAGICAL_BOOK_COVER);
        register("honeymod_metals", 34, "New Metals", 6, HoneymodItems.COPPER_INGOT, HoneymodItems.TIN_INGOT, HoneymodItems.SILVER_INGOT, HoneymodItems.LEAD_INGOT, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.ZINC_INGOT, HoneymodItems.MAGNESIUM_INGOT);
        register("ore_crusher", 35, "Ore Doubling", 6, HoneymodItems.ORE_CRUSHER);
        register("bronze", 36, "Bronze Creation", 8, HoneymodItems.BRONZE_INGOT);
        register("alloys", 37, "Advanced Alloys", 12, HoneymodItems.BILLON_INGOT, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.ALUMINUM_BRASS_INGOT, HoneymodItems.ALUMINUM_BRONZE_INGOT, HoneymodItems.SOLDER_INGOT, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.BRASS_INGOT);
        register("compressor_and_carbon", 38, "Carbon Creation", 9, HoneymodItems.COMPRESSOR, HoneymodItems.CARBON);
        register("gilded_iron_armor", 40, "Gilded Iron Armor", 16, HoneymodItems.GILDED_IRON_HELMET, HoneymodItems.GILDED_IRON_CHESTPLATE, HoneymodItems.GILDED_IRON_LEGGINGS, HoneymodItems.GILDED_IRON_BOOTS);
        register("synthetic_diamond", 41, "Synthetic Diamonds", 10, HoneymodItems.COMPRESSED_CARBON, HoneymodItems.CARBON_CHUNK, HoneymodItems.SYNTHETIC_DIAMOND);
        register("pressure_chamber", 42, "Pressure Chamber", 14, HoneymodItems.PRESSURE_CHAMBER);
        register("synthetic_sapphire", 43, "Synthetic Sapphires", 16, HoneymodItems.SYNTHETIC_SAPPHIRE);
        register("damascus_steel", 45, "Damascus Steel", 17, HoneymodItems.DAMASCUS_STEEL_INGOT);
        register("damascus_steel_armor", 46, "Damascus Steel Armor", 18, HoneymodItems.DAMASCUS_STEEL_HELMET, HoneymodItems.DAMASCUS_STEEL_CHESTPLATE, HoneymodItems.DAMASCUS_STEEL_LEGGINGS, HoneymodItems.DAMASCUS_STEEL_BOOTS);
        register("reinforced_alloy", 47, "Reinforced Alloy", 22, HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.REINFORCED_ALLOY_INGOT);
        register("carbonado", 48, "Black Diamonds", 26, HoneymodItems.RAW_CARBONADO, HoneymodItems.CARBONADO);
        register("magic_workbench", 50, "Magic Workbench", 12, HoneymodItems.MAGIC_WORKBENCH);
        register("wind_staff", 51, "Wind Staff", 17, HoneymodItems.STAFF_WIND);
        register("reinforced_armor", 52, "Reinforced Armor", 26, HoneymodItems.REINFORCED_ALLOY_HELMET, HoneymodItems.REINFORCED_ALLOY_CHESTPLATE, HoneymodItems.REINFORCED_ALLOY_LEGGINGS, HoneymodItems.REINFORCED_ALLOY_BOOTS);
        register("ore_washer", 53, "Ore Washer", 5, HoneymodItems.ORE_WASHER, HoneymodItems.STONE_CHUNK, HoneymodItems.SIFTED_ORE);
        register("gold_carats", 54, "Pure Gold", 7, HoneymodItems.GOLD_4K, HoneymodItems.GOLD_6K, HoneymodItems.GOLD_8K, HoneymodItems.GOLD_10K, HoneymodItems.GOLD_12K, HoneymodItems.GOLD_14K, HoneymodItems.GOLD_16K, HoneymodItems.GOLD_18K, HoneymodItems.GOLD_20K, HoneymodItems.GOLD_22K, HoneymodItems.GOLD_24K);
        register("silicon", 55, "Silicon Valley", 12, HoneymodItems.SILICON, HoneymodItems.FERROSILICON);
        register("fire_staff", 56, "Fire Staff", 2, HoneymodItems.STAFF_FIRE);
        register("smelters_pickaxe", 57, "Smelters Pickaxe", 17, HoneymodItems.SMELTERS_PICKAXE);
        register("common_talisman", 58, "Common Talisman", 14, HoneymodItems.COMMON_TALISMAN);
        register("anvil_talisman", 59, "Talisman of the Anvil", 18, HoneymodItems.TALISMAN_ANVIL);
        register("miner_talisman", 60, "Talisman of the Miner", 18, HoneymodItems.TALISMAN_MINER);
        register("hunter_talisman", 61, "Talisman of the Hunter", 18, HoneymodItems.TALISMAN_HUNTER);
        register("lava_talisman", 62, "Talisman of the Lava Walker", 18, HoneymodItems.TALISMAN_LAVA);
        register("water_talisman", 63, "Talisman of the Water Breather", 18, HoneymodItems.TALISMAN_WATER);
        register("angel_talisman", 64, "Talisman of the Angel", 18, HoneymodItems.TALISMAN_ANGEL);
        register("fire_talisman", 65, "Talisman of the Firefighter", 18, HoneymodItems.TALISMAN_FIRE);
        register("lava_crystal", 67, "Firey Situation", 14, HoneymodItems.LAVA_CRYSTAL);
        register("magician_talisman", 68, "Talisman of the Magician", 20, HoneymodItems.TALISMAN_MAGICIAN);
        register("traveller_talisman", 69, "Talisman of the Traveller", 20, HoneymodItems.TALISMAN_TRAVELLER);
        register("warrior_talisman", 70, "Talisman of the Warrior", 20, HoneymodItems.TALISMAN_WARRIOR);
        register("knight_talisman", 71, "Talisman of the Knight", 20, HoneymodItems.TALISMAN_KNIGHT);
        register("gilded_iron", 72, "Shiny Iron", 11, HoneymodItems.GILDED_IRON);
        register("synthetic_emerald", 73, "Fake Gem", 17, HoneymodItems.SYNTHETIC_EMERALD);
        register("chainmail_armor", 74, "Chainmail Armor", 8, new ItemStack(Material.CHAINMAIL_HELMET), new ItemStack(Material.CHAINMAIL_CHESTPLATE), new ItemStack(Material.CHAINMAIL_LEGGINGS), new ItemStack(Material.CHAINMAIL_BOOTS));
        register("whirlwind_talisman", 75, "Talisman of the Whirlwind", 19, HoneymodItems.TALISMAN_WHIRLWIND);
        register("wizard_talisman", 76, "Talisman of the Wizard", 22, HoneymodItems.TALISMAN_WIZARD);
        register("lumber_axe", 77, "Lumber Axe", 21, HoneymodItems.LUMBER_AXE);
        register("hazmat_suit", 79, "Hazmat Suit", 21, HoneymodItems.SCUBA_HELMET, HoneymodItems.HAZMAT_CHESTPLATE, HoneymodItems.HAZMAT_LEGGINGS, HoneymodItems.HAZMAT_BOOTS);
        register("uranium", 80, "Radioactive", 30, HoneymodItems.TINY_URANIUM, HoneymodItems.SMALL_URANIUM, HoneymodItems.URANIUM);
        register("crushed_ore", 81, "Ore Purification", 25, HoneymodItems.CRUSHED_ORE, HoneymodItems.PULVERIZED_ORE, HoneymodItems.PURE_ORE_CLUSTER);
        register("redstone_alloy", 84, "Redstone Alloy", 16, HoneymodItems.REDSTONE_ALLOY);
        register("carbonado_tools", 85, "Top Tier Machines", 24, HoneymodItems.CARBONADO_MULTI_TOOL, HoneymodItems.CARBONADO_JETPACK, HoneymodItems.CARBONADO_JETBOOTS);
        register("first_aid", 86, "First Aid", 2, HoneymodItems.CLOTH, HoneymodItems.RAG, HoneymodItems.BANDAGE, HoneymodItems.SPLINT, HoneymodItems.TIN_CAN, HoneymodItems.VITAMINS, HoneymodItems.MEDICINE);
        register("gold_armor", 87, "Shiny Armor", 13, HoneymodItems.GOLDEN_HELMET_12K, HoneymodItems.GOLDEN_CHESTPLATE_12K, HoneymodItems.GOLDEN_LEGGINGS_12K, HoneymodItems.GOLDEN_BOOTS_12K);
        register("night_vision_googles", 89, "Night Vision Goggles", 10, HoneymodItems.NIGHT_VISION_GOGGLES);
        register("pickaxe_of_containment", 90, "Pickaxe of Containment", 14, HoneymodItems.PICKAXE_OF_CONTAINMENT, HoneymodItems.BROKEN_SPAWNER);
        register("hercules_pickaxe", 91, "Hercules Pickaxe", 28, HoneymodItems.HERCULES_PICKAXE);
        register("table_saw", 92, "Table Saw", 4, HoneymodItems.TABLE_SAW);
        register("slime_steel_armor", 93, "Slimy Steel Armor", 27, HoneymodItems.SLIME_HELMET_STEEL, HoneymodItems.SLIME_CHESTPLATE_STEEL, HoneymodItems.SLIME_LEGGINGS_STEEL, HoneymodItems.SLIME_BOOTS_STEEL);
        register("blade_of_vampires", 94, "Blade of Vampires", 26, HoneymodItems.BLADE_OF_VAMPIRES);
        register("water_staff", 96, "Water Staff", 8, HoneymodItems.STAFF_WATER);
        register("24k_gold_block", 97, "Golden City", 19, HoneymodItems.GOLD_24K_BLOCK);
        register("composter", 99, "Composting Dirt", 3, HoneymodItems.COMPOSTER);
        register("farmer_shoes", 100, "Farmer Shoes", 4, HoneymodItems.FARMER_SHOES);
        register("explosive_tools", 101, "Explosive Tools", 30, HoneymodItems.EXPLOSIVE_PICKAXE, HoneymodItems.EXPLOSIVE_SHOVEL);
        register("automated_panning_machine", 102, "Automated Gold Pan", 17, HoneymodItems.AUTOMATED_PANNING_MACHINE);
        register("boots_of_the_stomper", 103, "Boots of the Stomper", 19, HoneymodItems.BOOTS_OF_THE_STOMPER);
        register("pickaxe_of_the_seeker", 104, "Pickaxe of the Seeker", 19, HoneymodItems.PICKAXE_OF_THE_SEEKER);
        register("backpacks", 105, "Backpacks", 15, HoneymodItems.BACKPACK_LARGE, HoneymodItems.BACKPACK_MEDIUM, HoneymodItems.BACKPACK_SMALL);
        register("woven_backpack", 106, "Woven Backpack", 19, HoneymodItems.WOVEN_BACKPACK);
        register("crucible", 107, "Crucible", 13, HoneymodItems.CRUCIBLE);
        register("gilded_backpack", 108, "Gilded Backpack", 22, HoneymodItems.GILDED_BACKPACK);
        register("armored_jetpack", 111, "Armored Jetpack", 27, HoneymodItems.ARMORED_JETPACK);
        register("ender_talismans", 112, "Ender Talismans", 28);
        register("nickel_and_cobalt", 115, "Even more Ores", 10, HoneymodItems.NICKEL_INGOT, HoneymodItems.COBALT_INGOT);
        register("magnet", 116, "Magnetic Metals", 16, HoneymodItems.MAGNET);
        register("infused_magnet", 117, "Infused Magnets", 18, HoneymodItems.INFUSED_MAGNET);
        register("cobalt_pickaxe", 118, "Speedy Pickaxe", 14, HoneymodItems.COBALT_PICKAXE);
        register("essence_of_afterlife", 119, "Necromancy", 19, HoneymodItems.NECROTIC_SKULL, HoneymodItems.ESSENCE_OF_AFTERLIFE);
        register("bound_backpack", 120, "Soulbound Storage", 22, HoneymodItems.BOUND_BACKPACK);
        register("jetboots", 121, "Jet Boots", 25, HoneymodItems.DURALUMIN_JETBOOTS, HoneymodItems.BILLON_JETBOOTS, HoneymodItems.SOLDER_JETBOOTS, HoneymodItems.STEEL_JETBOOTS, HoneymodItems.DAMASCUS_STEEL_JETBOOTS, HoneymodItems.REINFORCED_ALLOY_JETBOOTS);
        register("armored_jetboots", 122, "Armored Jet Boots", 27, HoneymodItems.ARMORED_JETBOOTS);
        register("seismic_axe", 123, "Seismic Axe", 29, HoneymodItems.SEISMIC_AXE);
        register("pickaxe_of_vein_mining", 124, "Pickaxe of Vein Mining", 29, HoneymodItems.PICKAXE_OF_VEIN_MINING);
        register("bound_weapons", 125, "Soulbound Weapons", 29, HoneymodItems.SOULBOUND_SWORD, HoneymodItems.SOULBOUND_BOW, HoneymodItems.SOULBOUND_TRIDENT);
        register("bound_tools", 126, "Soulbound Tools", 29, HoneymodItems.SOULBOUND_PICKAXE, HoneymodItems.SOULBOUND_AXE, HoneymodItems.SOULBOUND_SHOVEL, HoneymodItems.SOULBOUND_HOE);
        register("bound_armor", 127, "Soulbound Armor", 29, HoneymodItems.SOULBOUND_HELMET, HoneymodItems.SOULBOUND_CHESTPLATE, HoneymodItems.SOULBOUND_LEGGINGS, HoneymodItems.SOULBOUND_BOOTS);
        register("juicer", 129, "Delicious Drinks", 29, HoneymodItems.JUICER, HoneymodItems.APPLE_JUICE, HoneymodItems.MELON_JUICE, HoneymodItems.CARROT_JUICE, HoneymodItems.PUMPKIN_JUICE, HoneymodItems.SWEET_BERRY_JUICE);
        register("repaired_spawner", 130, "Repairing Spawners", 15, HoneymodItems.REPAIRED_SPAWNER);
        register("enhanced_furnace", 132, "Enhanced Furnace", 7, HoneymodItems.ENHANCED_FURNACE, HoneymodItems.ENHANCED_FURNACE_2);
        register("more_enhanced_furnaces", 133, "Better Furnaces", 18, HoneymodItems.ENHANCED_FURNACE_3, HoneymodItems.ENHANCED_FURNACE_4, HoneymodItems.ENHANCED_FURNACE_5, HoneymodItems.ENHANCED_FURNACE_6, HoneymodItems.ENHANCED_FURNACE_7);
        register("high_tier_enhanced_furnaces", 134, "High Tier Furnace", 29, HoneymodItems.ENHANCED_FURNACE_8, HoneymodItems.ENHANCED_FURNACE_9, HoneymodItems.ENHANCED_FURNACE_10, HoneymodItems.ENHANCED_FURNACE_11);
        register("reinforced_furnace", 135, "Reinforced Furnace", 32, HoneymodItems.REINFORCED_FURNACE);
        register("carbonado_furnace", 136, "Carbonado Edged Furnace", 35, HoneymodItems.CARBONADO_EDGED_FURNACE);
        register("electric_motor", 137, "Heating up", 32, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.HEATING_COIL);
        register("block_placer", 138, "Block Placer", 17, HoneymodItems.BLOCK_PLACER);
        register("scroll_of_dimensional_teleposition", 142, "Turning things around", 38, HoneymodItems.SCROLL_OF_DIMENSIONAL_TELEPOSITION);
        register("special_bows", 143, "Robin Hood", 22, HoneymodItems.EXPLOSIVE_BOW, HoneymodItems.ICY_BOW);
        register("tome_of_knowledge_sharing", 144, "Sharing with friends", 30, HoneymodItems.TOME_OF_KNOWLEDGE_SHARING);
        register("flask_of_knowledge", 145, "XP Storage", 13, HoneymodItems.FLASK_OF_KNOWLEDGE);
        register("hardened_glass", 146, "Withstanding Explosions", 15, HoneymodItems.REINFORCED_PLATE, HoneymodItems.HARDENED_GLASS);
        register("golden_apple_juice", 149, "Golden potion", 24, HoneymodItems.GOLDEN_APPLE_JUICE);
        register("cooler", 150, "Portable Beverages", 24, HoneymodItems.COOLING_UNIT, HoneymodItems.COOLER);
        register("ancient_altar", 151, "Ancient Altar", 15, HoneymodItems.ANCIENT_PEDESTAL, HoneymodItems.ANCIENT_ALTAR);
        register("wither_proof_obsidian", 152, "Wither-Proof Obsidian", 21, HoneymodItems.WITHER_PROOF_OBSIDIAN);
        register("ancient_runes", 155, "Elemental Runes", 15, HoneymodItems.BLANK_RUNE, HoneymodItems.EARTH_RUNE, HoneymodItems.WATER_RUNE, HoneymodItems.AIR_RUNE, HoneymodItems.FIRE_RUNE);
        register("special_runes", 156, "Purple Runes", 18, HoneymodItems.ENDER_RUNE, HoneymodItems.RAINBOW_RUNE);
        register("infernal_bonemeal", 157, "Infernal Bonemeal", 12, HoneymodItems.INFERNAL_BONEMEAL);
        register("rainbow_blocks", 158, "Rainbow Blocks", 24, HoneymodItems.RAINBOW_CLAY, HoneymodItems.RAINBOW_GLASS, HoneymodItems.RAINBOW_GLASS_PANE, HoneymodItems.RAINBOW_WOOL, HoneymodItems.RAINBOW_CONCRETE, HoneymodItems.RAINBOW_GLAZED_TERRACOTTA);
        register("infused_hopper", 159, "Infused Hopper", 22, HoneymodItems.INFUSED_HOPPER);
        register("wither_proof_glass", 160, "Wither-Proof Glass", 20, HoneymodItems.WITHER_PROOF_GLASS);
        register("duct_tape", 161, "Duct Tape", 14, HoneymodItems.DUCT_TAPE);
        register("plastic_sheet", 162, "Plastic", 25, HoneymodItems.PLASTIC_SHEET);
        register("android_memory_core", 163, "Memory Core", 28, HoneymodItems.ANDROID_MEMORY_CORE);
        register("oil", 164, "Oil", 30, HoneymodItems.OIL_BUCKET, HoneymodItems.OIL_PUMP);
        register("fuel", 165, "Fuel", 30, HoneymodItems.FUEL_BUCKET, HoneymodItems.REFINERY);
        register("hologram_projector", 166, "Holograms", 36, HoneymodItems.HOLOGRAM_PROJECTOR);
        register("capacitors", 167, "Tier 1 Capacitors", 25, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.MEDIUM_CAPACITOR, HoneymodItems.BIG_CAPACITOR);
        register("high_tier_capacitors", 168, "Tier 2 Capacitors", 32, HoneymodItems.LARGE_CAPACITOR, HoneymodItems.CARBONADO_EDGED_CAPACITOR);
        register("solar_generators", 169, "Solar Power Plant", 14, HoneymodItems.SOLAR_GENERATOR);
        register("electric_furnaces", 170, "Powered Furnace", 15, HoneymodItems.ELECTRIC_FURNACE);
        register("electric_ore_grinding", 171, "Crushing and Grinding", 20, HoneymodItems.ELECTRIC_ORE_GRINDER, HoneymodItems.ELECTRIC_INGOT_PULVERIZER);
        register("heated_pressure_chamber", 172, "Heated Pressure Chamber", 22, HoneymodItems.HEATED_PRESSURE_CHAMBER);
        register("coal_generator", 173, "Coal Generator", 18, HoneymodItems.COAL_GENERATOR);
        register("bio_reactor", 173, "Bio-Reactor", 18, HoneymodItems.BIO_REACTOR);
        register("auto_enchanting", 174, "Automatic Enchanting and Disenchanting", 24, HoneymodItems.AUTO_ENCHANTER, HoneymodItems.AUTO_DISENCHANTER);
        register("auto_anvil", 175, "Automatic Anvil", 34, HoneymodItems.AUTO_ANVIL, HoneymodItems.AUTO_ANVIL_2);
        register("multimeter", 176, "Power Measurement", 10, HoneymodItems.MULTIMETER);
        register("gps_setup", 177, "Basic GPS Setup", 28, HoneymodItems.GPS_TRANSMITTER, HoneymodItems.GPS_CONTROL_PANEL, HoneymodItems.GPS_MARKER_TOOL);
        register("gps_emergency_transmitter", 178, "GPS Emergency Waypoint", 30, HoneymodItems.GPS_EMERGENCY_TRANSMITTER);
        register("programmable_androids", 179, "Programmable Androids", 50, HoneymodItems.PROGRAMMABLE_ANDROID, HoneymodItems.PROGRAMMABLE_ANDROID_FARMER, HoneymodItems.PROGRAMMABLE_ANDROID_BUTCHER, HoneymodItems.PROGRAMMABLE_ANDROID_FISHERMAN, HoneymodItems.PROGRAMMABLE_ANDROID_MINER, HoneymodItems.PROGRAMMABLE_ANDROID_WOODCUTTER);
        register("android_interfaces", 180, "Android Interfaces", 26, HoneymodItems.ANDROID_INTERFACE_FUEL, HoneymodItems.ANDROID_INTERFACE_ITEMS);
        register("geo_scanner", 181, "GEO-Scans", 30, HoneymodItems.GPS_GEO_SCANNER, HoneymodItems.PORTABLE_GEO_SCANNER);
        register("combustion_reactor", 182, "Combustion Reactor", 38, HoneymodItems.COMBUSTION_REACTOR);
        register("teleporter", 183, "Teleporter Base Components", 42, HoneymodItems.GPS_TELEPORTATION_MATRIX, HoneymodItems.GPS_TELEPORTER_PYLON);
        register("teleporter_activation_plates", 184, "Teleporter Activation", 36, HoneymodItems.GPS_ACTIVATION_DEVICE_PERSONAL, HoneymodItems.GPS_ACTIVATION_DEVICE_SHARED);
        register("better_solar_generators", 185, "Upgraded Solar Generators", 28, HoneymodItems.SOLAR_GENERATOR_2, HoneymodItems.SOLAR_GENERATOR_3);
        register("better_gps_transmitters", 186, "Upgraded Transmitters", 36, HoneymodItems.GPS_TRANSMITTER_2, HoneymodItems.GPS_TRANSMITTER_3);
        register("elevator", 187, "Elevators", 28, HoneymodItems.ELEVATOR_PLATE);
        register("energized_solar_generator", 188, "Full-Time Solar Power", 44, HoneymodItems.SOLAR_GENERATOR_4);
        register("energized_gps_transmitter", 189, "Top Tier Transmitter", 44, HoneymodItems.GPS_TRANSMITTER_4);
        register("energy_regulator", 190, "Energy Networks 101", 6, HoneymodItems.ENERGY_REGULATOR);
        register("butcher_androids", 191, "Butcher Androids", 32, HoneymodItems.PROGRAMMABLE_ANDROID_BUTCHER);
        register("organic_food", 192, "Organic Food", 25, HoneymodItems.FOOD_FABRICATOR, HoneymodItems.WHEAT_ORGANIC_FOOD, HoneymodItems.CARROT_ORGANIC_FOOD, HoneymodItems.POTATO_ORGANIC_FOOD, HoneymodItems.SEEDS_ORGANIC_FOOD, HoneymodItems.BEETROOT_ORGANIC_FOOD, HoneymodItems.MELON_ORGANIC_FOOD, HoneymodItems.APPLE_ORGANIC_FOOD, HoneymodItems.SWEET_BERRIES_ORGANIC_FOOD, HoneymodItems.KELP_ORGANIC_FOOD, HoneymodItems.COCOA_ORGANIC_FOOD);
        register("auto_breeder", 193, "Automated Feeding", 25, HoneymodItems.AUTO_BREEDER);
        register("advanced_android", 194, "Advanced Androids", 60, HoneymodItems.PROGRAMMABLE_ANDROID_2);
        register("advanced_butcher_android", 195, "Advanced Androids - Butcher", 30, HoneymodItems.PROGRAMMABLE_ANDROID_2_BUTCHER);
        register("advanced_fisherman_android", 196, "Advanced Androids - Fisherman", 30, HoneymodItems.PROGRAMMABLE_ANDROID_2_FISHERMAN);
        register("animal_growth_accelerator", 197, "Animal Growth Manipulation", 32, HoneymodItems.ANIMAL_GROWTH_ACCELERATOR);
        register("xp_collector", 198, "XP Collector", 36, HoneymodItems.EXP_COLLECTOR);
        register("organic_fertilizer", 199, "Organic Fertilizer", 36, HoneymodItems.FOOD_COMPOSTER, HoneymodItems.WHEAT_FERTILIZER, HoneymodItems.CARROT_FERTILIZER, HoneymodItems.POTATO_FERTILIZER, HoneymodItems.SEEDS_FERTILIZER, HoneymodItems.BEETROOT_FERTILIZER, HoneymodItems.MELON_FERTILIZER, HoneymodItems.APPLE_FERTILIZER, HoneymodItems.SWEET_BERRIES_FERTILIZER, HoneymodItems.KELP_FERTILIZER, HoneymodItems.COCOA_FERTILIZER);
        register("crop_growth_accelerator", 200, "Crop Growth Acceleration", 40, HoneymodItems.CROP_GROWTH_ACCELERATOR);
        register("better_crop_growth_accelerator", 201, "Upgraded Crop Growth Accelerator", 44, HoneymodItems.CROP_GROWTH_ACCELERATOR_2);
        register("reactor_essentials", 202, "Reactor Essentials", 36, HoneymodItems.REACTOR_COOLANT_CELL, HoneymodItems.NEPTUNIUM, HoneymodItems.PLUTONIUM);
        register("nuclear_reactor", 203, "Nuclear Power Plant", 48, HoneymodItems.NUCLEAR_REACTOR);
        register("freezer", 204, "Mr Freeze", 20, HoneymodItems.FREEZER);
        register("cargo_basics", 205, "Cargo Basics", 30, HoneymodItems.CARGO_MOTOR, HoneymodItems.CARGO_MANAGER, HoneymodItems.CARGO_CONNECTOR_NODE);
        register("cargo_nodes", 206, "Cargo Setup", 30, HoneymodItems.CARGO_INPUT_NODE, HoneymodItems.CARGO_OUTPUT_NODE);
        register("electric_ingot_machines", 207, "Electric Ingot Fabrication", 18, HoneymodItems.ELECTRIC_GOLD_PAN, HoneymodItems.ELECTRIC_DUST_WASHER, HoneymodItems.ELECTRIC_INGOT_FACTORY);
        register("high_tier_electric_ingot_machines", 209, "Super Fast Ingot Fabrication", 32, HoneymodItems.ELECTRIC_GOLD_PAN_3, HoneymodItems.ELECTRIC_DUST_WASHER_3, HoneymodItems.ELECTRIC_INGOT_FACTORY_3, HoneymodItems.ELECTRIC_ORE_GRINDER_2, HoneymodItems.ELECTRIC_ORE_GRINDER_3);
        register("automated_crafting_chamber", 210, "Automated Crafting", 20, HoneymodItems.AUTOMATED_CRAFTING_CHAMBER);
        register("better_food_fabricator", 211, "Upgraded Food Fabrication", 28, HoneymodItems.FOOD_FABRICATOR_2, HoneymodItems.FOOD_COMPOSTER_2);
        register("reactor_access_port", 212, "Reactor Interaction", 18, HoneymodItems.REACTOR_ACCESS_PORT);
        register("fluid_pump", 213, "Fluid Pump", 28, HoneymodItems.FLUID_PUMP);
        register("better_freezer", 214, "Upgraded Freezer", 29, HoneymodItems.FREEZER_2);
        register("boosted_uranium", 215, "Never-Ending Circle", 30, HoneymodItems.BOOSTED_URANIUM);
        register("trash_can", 216, "Trash", 8, HoneymodItems.TRASH_CAN);
        register("advanced_output_node", 217, "Advanced Output Node", 24, HoneymodItems.CARGO_OUTPUT_NODE_2);
        register("carbon_press", 218, "Carbon Press", 28, HoneymodItems.CARBON_PRESS);
        register("electric_smeltery", 219, "Electric Smeltery", 28, HoneymodItems.ELECTRIC_SMELTERY);
        register("better_electric_furnace", 220, "Upgraded Electric Furnace", 20, HoneymodItems.ELECTRIC_FURNACE_2, HoneymodItems.ELECTRIC_FURNACE_3);
        register("better_carbon_press", 221, "Upgraded Carbon Press", 26, HoneymodItems.CARBON_PRESS_2);
        register("empowered_android", 222, "Empowered Androids", 60, HoneymodItems.PROGRAMMABLE_ANDROID_3);
        register("empowered_butcher_android", 223, "Empowered Androids - Butcher", 30, HoneymodItems.PROGRAMMABLE_ANDROID_3_BUTCHER);
        register("empowered_fisherman_android", 224, "Empowered Androids - Fisherman", 30, HoneymodItems.PROGRAMMABLE_ANDROID_3_FISHERMAN);
        register("high_tier_carbon_press", 225, "Ultimate Carbon Press", 32, HoneymodItems.CARBON_PRESS_3);
        register("wither_assembler", 226, "Automated Wither Killer", 35, HoneymodItems.WITHER_ASSEMBLER);
        register("better_heated_pressure_chamber", 227, "Upgraded Heated Pressure Chamber", 20, HoneymodItems.HEATED_PRESSURE_CHAMBER_2);
        register("elytra", 228, "Elytras", 20, HoneymodItems.ELYTRA_SCALE, new ItemStack(Material.ELYTRA));
        register("special_elytras", 229, "Special Elytras", 30, HoneymodItems.INFUSED_ELYTRA, HoneymodItems.SOULBOUND_ELYTRA);
        register("electric_crucible", 230, "Electrified Crucible", 26, HoneymodItems.ELECTRIFIED_CRUCIBLE);
        register("better_electric_crucibles", 231, "Hot Crucibles", 30, HoneymodItems.ELECTRIFIED_CRUCIBLE_2, HoneymodItems.ELECTRIFIED_CRUCIBLE_3);
        register("advanced_electric_smeltery", 232, "Advanced Electric Smeltery", 28, HoneymodItems.ELECTRIC_SMELTERY_2);
        register("advanced_farmer_android", 233, "Advanced Androids - Farmer", 30, HoneymodItems.PROGRAMMABLE_ANDROID_2_FARMER);
        register("lava_generator", 234, "Lava Generator", 38, HoneymodItems.LAVA_GENERATOR);
        register("nether_ice", 235, "Nether Ice Coolant", 45, HoneymodItems.NETHER_ICE, HoneymodItems.ENRICHED_NETHER_ICE, HoneymodItems.NETHER_ICE_COOLANT_CELL);
        register("nether_star_reactor", 236, "Nether Star Reactor", 60, HoneymodItems.NETHER_STAR_REACTOR);
        register("blistering_ingots", 237, "Blistering Radioactivity", 38, HoneymodItems.BLISTERING_INGOT, HoneymodItems.BLISTERING_INGOT_2, HoneymodItems.BLISTERING_INGOT_3);
        register("automatic_ignition_chamber", 239, "Automatic Ignition Chamber", 12, HoneymodItems.IGNITION_CHAMBER);
        register("output_chest", 240, "Basic machinery output chest", 20, HoneymodItems.OUTPUT_CHEST);
        register("copper_wire", 241, "Thinned-down Conductivity", 15, HoneymodItems.COPPER_WIRE);
        register("radiant_backpack", 242, "Radiant Backpack", 25, HoneymodItems.RADIANT_BACKPACK);
        register("auto_drier", 243, "A Dry Day", 15, HoneymodItems.AUTO_DRIER);
        register("diet_cookie", 244, "Diet Cookie", 3, HoneymodItems.DIET_COOKIE);
        register("storm_staff", 245, "Storm Staff", 30, HoneymodItems.STAFF_STORM);
        register("soulbound_rune", 246, "Soulbound Rune", 60, HoneymodItems.SOULBOUND_RUNE);
        register("geo_miner", 247, "GEO-Miner", 24, HoneymodItems.GEO_MINER);
        register("lightning_rune", 248, "Lightning Rune", 24, HoneymodItems.LIGHTNING_RUNE);
        register("totem_of_undying", 249, "Totem of Undying", 36, new ItemStack(Material.TOTEM_OF_UNDYING));
        register("charging_bench", 250, "Charging Bench", 8, HoneymodItems.CHARGING_BENCH);
        register("nether_gold_pan", 251, "Nether Gold Pan", 8, HoneymodItems.NETHER_GOLD_PAN);
        register("electric_press", 252, "Electric Press", 16, HoneymodItems.ELECTRIC_PRESS, HoneymodItems.ELECTRIC_PRESS_2);
        register("magnesium_generator", 253, "Power from Magnesium", 20, HoneymodItems.MAGNESIUM_SALT, HoneymodItems.MAGNESIUM_GENERATOR);
        register("kelp_cookie", 254, "Tasty Kelp", 4, HoneymodItems.KELP_COOKIE);
        register("makeshift_smeltery", 255, "Improvised Smeltery", 6, HoneymodItems.MAKESHIFT_SMELTERY);
        register("tree_growth_accelerator", 256, "Faster Trees", 18, HoneymodItems.TREE_GROWTH_ACCELERATOR);
        register("industrial_miner", 95, "Industrial Mining", 28, HoneymodItems.INDUSTRIAL_MINER);
        register("advanced_industrial_miner", 98, "Better Mining", 36, HoneymodItems.ADVANCED_INDUSTRIAL_MINER);
        register("magical_zombie_pills", 257, "De-Zombification", 22, HoneymodItems.MAGICAL_ZOMBIE_PILLS);
        register("auto_brewer", 258, "Industrial Brewery", 30, HoneymodItems.AUTO_BREWER);
        register("enchantment_rune", 259, "Ancient Enchanting", 24, HoneymodItems.MAGICAL_GLASS, HoneymodItems.ENCHANTMENT_RUNE);
        register("lead_clothing", 260, "Lead Clothing", 14, HoneymodItems.REINFORCED_CLOTH);
        register("tape_measure", 261, "Tape Measure", 7, HoneymodItems.TAPE_MEASURE);
        register("iron_golem_assembler", 262, "Automated Iron Golems", 30, HoneymodItems.IRON_GOLEM_ASSEMBLER);
        register("shulker_shell", 263, "Synthetic Shulkers", 30, HoneymodItems.SYNTHETIC_SHULKER_SHELL);
        register("villager_rune", 264, "Reset Villager Trades", 26, HoneymodItems.VILLAGER_RUNE, HoneymodItems.STRANGE_NETHER_GOO);
        register("climbing_pick", 265, "Block Raider", 20, HoneymodItems.CLIMBING_PICK);
        register("even_higher_tier_capacitors", 266, "Tier 3 Capacitors", 40, HoneymodItems.ENERGIZED_CAPACITOR);
        register("caveman_talisman", 267, "Talisman of the Caveman", 20, HoneymodItems.TALISMAN_CAVEMAN);
        register("elytra_cap", 268, "Crash Gear", 20, HoneymodItems.ELYTRA_CAP);
        register("energy_connectors", 269, "Wired Connections", 12, HoneymodItems.ENERGY_CONNECTOR);
        register("bee_armor", 270, "Bee Armor", 24, HoneymodItems.BEE_HELMET, HoneymodItems.BEE_WINGS, HoneymodItems.BEE_LEGGINGS, HoneymodItems.BEE_BOOTS);
        register("wise_talisman", 271, "Talisman of the Wise", 20, HoneymodItems.TALISMAN_WISE);
        register("book_binder", 272, "Enchantment Book Binding", 26, HoneymodItems.BOOK_BINDER);
    }

    @ParametersAreNonnullByDefault
    private static void register(String key, int id, String name, int defaultCost, ItemStack... items) {
        Research research = new Research(new NamespacedKey(HoneymodPlugin.instance(), key), id, name, defaultCost);

        for (ItemStack item : items) {
            SlimefunItem sfItem = SlimefunItem.getByItem(item);

            if (sfItem != null) {
                research.addItems(sfItem);
            }
        }

        research.register();
    }
}
