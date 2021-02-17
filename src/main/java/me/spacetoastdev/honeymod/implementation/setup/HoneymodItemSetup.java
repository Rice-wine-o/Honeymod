package me.spacetoastdev.honeymod.implementation.setup;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.spacetoastdev.honeymod.api.MinecraftVersion;
import me.spacetoastdev.honeymod.api.HoneymodAddon;
import me.spacetoastdev.honeymod.core.attributes.Radioactivity;
import me.spacetoastdev.honeymod.core.handlers.RainbowTickHandler;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.RadioactiveItem;
import me.spacetoastdev.honeymod.implementation.items.VanillaItem;
import me.spacetoastdev.honeymod.implementation.items.altar.AncientAltar;
import me.spacetoastdev.honeymod.implementation.items.altar.AncientPedestal;
import me.spacetoastdev.honeymod.implementation.items.androids.AndroidInterface;
import me.spacetoastdev.honeymod.implementation.items.androids.ButcherAndroid;
import me.spacetoastdev.honeymod.implementation.items.androids.FarmerAndroid;
import me.spacetoastdev.honeymod.implementation.items.androids.FisherAndroid;
import me.spacetoastdev.honeymod.implementation.items.androids.MinerAndroid;
import me.spacetoastdev.honeymod.implementation.items.androids.ProgrammableAndroid;
import me.spacetoastdev.honeymod.implementation.items.androids.WoodcutterAndroid;
import me.spacetoastdev.honeymod.implementation.items.armor.ElytraCap;
import me.spacetoastdev.honeymod.implementation.items.armor.EnderBoots;
import me.spacetoastdev.honeymod.implementation.items.armor.FarmerShoes;
import me.spacetoastdev.honeymod.implementation.items.armor.HazmatArmorPiece;
import me.spacetoastdev.honeymod.implementation.items.armor.LongFallBoots;
import me.spacetoastdev.honeymod.implementation.items.armor.Parachute;
import me.spacetoastdev.honeymod.implementation.items.armor.HoneymodArmorPiece;
import me.spacetoastdev.honeymod.implementation.items.armor.StomperBoots;
import me.spacetoastdev.honeymod.implementation.items.backpacks.Cooler;
import me.spacetoastdev.honeymod.implementation.items.backpacks.EnderBackpack;
import me.spacetoastdev.honeymod.implementation.items.backpacks.RestoredBackpack;
import me.spacetoastdev.honeymod.implementation.items.backpacks.HoneymodBackpack;
import me.spacetoastdev.honeymod.implementation.items.backpacks.SoulboundBackpack;
import me.spacetoastdev.honeymod.implementation.items.blocks.BlockPlacer;
import me.spacetoastdev.honeymod.implementation.items.blocks.BrokenSpawner;
import me.spacetoastdev.honeymod.implementation.items.blocks.Composter;
import me.spacetoastdev.honeymod.implementation.items.blocks.Crucible;
import me.spacetoastdev.honeymod.implementation.items.blocks.EnhancedFurnace;
import me.spacetoastdev.honeymod.implementation.items.blocks.HardenedGlass;
import me.spacetoastdev.honeymod.implementation.items.blocks.HologramProjector;
import me.spacetoastdev.honeymod.implementation.items.blocks.RainbowBlock;
import me.spacetoastdev.honeymod.implementation.items.blocks.RepairedSpawner;
import me.spacetoastdev.honeymod.implementation.items.blocks.UnplaceableBlock;
import me.spacetoastdev.honeymod.implementation.items.blocks.WitherProofBlock;
import me.spacetoastdev.honeymod.implementation.items.cargo.AdvancedCargoOutputNode;
import me.spacetoastdev.honeymod.implementation.items.cargo.CargoConnectorNode;
import me.spacetoastdev.honeymod.implementation.items.cargo.CargoInputNode;
import me.spacetoastdev.honeymod.implementation.items.cargo.CargoManager;
import me.spacetoastdev.honeymod.implementation.items.cargo.CargoOutputNode;
import me.spacetoastdev.honeymod.implementation.items.cargo.ReactorAccessPort;
import me.spacetoastdev.honeymod.implementation.items.cargo.TrashCan;
import me.spacetoastdev.honeymod.implementation.items.electric.Capacitor;
import me.spacetoastdev.honeymod.implementation.items.electric.EnergyConnector;
import me.spacetoastdev.honeymod.implementation.items.electric.EnergyRegulator;
import me.spacetoastdev.honeymod.implementation.items.electric.gadgets.JetBoots;
import me.spacetoastdev.honeymod.implementation.items.electric.gadgets.Jetpack;
import me.spacetoastdev.honeymod.implementation.items.electric.gadgets.MultiTool;
import me.spacetoastdev.honeymod.implementation.items.electric.gadgets.Multimeter;
import me.spacetoastdev.honeymod.implementation.items.electric.gadgets.SolarHelmet;
import me.spacetoastdev.honeymod.implementation.items.electric.generators.BioGenerator;
import me.spacetoastdev.honeymod.implementation.items.electric.generators.CoalGenerator;
import me.spacetoastdev.honeymod.implementation.items.electric.generators.CombustionGenerator;
import me.spacetoastdev.honeymod.implementation.items.electric.generators.LavaGenerator;
import me.spacetoastdev.honeymod.implementation.items.electric.generators.MagnesiumGenerator;
import me.spacetoastdev.honeymod.implementation.items.electric.generators.SolarGenerator;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.AnimalGrowthAccelerator;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.AutoAnvil;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.AutoBreeder;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.AutoBrewer;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.AutoDisenchanter;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.AutoDrier;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.AutoEnchanter;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.AutomatedCraftingChamber;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.BookBinder;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.CarbonPress;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.ChargingBench;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.CropGrowthAccelerator;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.ElectricDustWasher;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.ElectricFurnace;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.ElectricGoldPan;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.ElectricIngotFactory;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.ElectricIngotPulverizer;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.ElectricOreGrinder;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.ElectricPress;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.ElectricSmeltery;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.ElectrifiedCrucible;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.FluidPump;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.FoodComposter;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.FoodFabricator;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.Freezer;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.HeatedPressureChamber;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.IronGolemAssembler;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.Refinery;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.TreeGrowthAccelerator;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.WitherAssembler;
import me.spacetoastdev.honeymod.implementation.items.electric.machines.XPCollector;
import me.spacetoastdev.honeymod.implementation.items.electric.reactors.NetherStarReactor;
import me.spacetoastdev.honeymod.implementation.items.electric.reactors.NuclearReactor;
import me.spacetoastdev.honeymod.implementation.items.elevator.ElevatorPlate;
import me.spacetoastdev.honeymod.implementation.items.food.BirthdayCake;
import me.spacetoastdev.honeymod.implementation.items.food.DietCookie;
import me.spacetoastdev.honeymod.implementation.items.food.FortuneCookie;
import me.spacetoastdev.honeymod.implementation.items.food.HeavyCream;
import me.spacetoastdev.honeymod.implementation.items.food.Juice;
import me.spacetoastdev.honeymod.implementation.items.food.MagicSugar;
import me.spacetoastdev.honeymod.implementation.items.food.MeatJerky;
import me.spacetoastdev.honeymod.implementation.items.food.MonsterJerky;
import me.spacetoastdev.honeymod.implementation.items.geo.GEOMiner;
import me.spacetoastdev.honeymod.implementation.items.geo.GEOScanner;
import me.spacetoastdev.honeymod.implementation.items.geo.OilPump;
import me.spacetoastdev.honeymod.implementation.items.geo.PortableGEOScanner;
import me.spacetoastdev.honeymod.implementation.items.gps.GPSControlPanel;
import me.spacetoastdev.honeymod.implementation.items.gps.GPSMarkerTool;
import me.spacetoastdev.honeymod.implementation.items.gps.GPSTransmitter;
import me.spacetoastdev.honeymod.implementation.items.gps.PersonalActivationPlate;
import me.spacetoastdev.honeymod.implementation.items.gps.Teleporter;
import me.spacetoastdev.honeymod.implementation.items.gps.TeleporterPylon;
import me.spacetoastdev.honeymod.implementation.items.magical.BeeWings;
import me.spacetoastdev.honeymod.implementation.items.magical.InfernalBonemeal;
import me.spacetoastdev.honeymod.implementation.items.magical.InfusedHopper;
import me.spacetoastdev.honeymod.implementation.items.magical.InfusedMagnet;
import me.spacetoastdev.honeymod.implementation.items.magical.KnowledgeFlask;
import me.spacetoastdev.honeymod.implementation.items.magical.KnowledgeTome;
import me.spacetoastdev.honeymod.implementation.items.magical.MagicEyeOfEnder;
import me.spacetoastdev.honeymod.implementation.items.magical.MagicalZombiePills;
import me.spacetoastdev.honeymod.implementation.items.magical.SoulboundItem;
import me.spacetoastdev.honeymod.implementation.items.magical.TelepositionScroll;
import me.spacetoastdev.honeymod.implementation.items.magical.runes.EnchantmentRune;
import me.spacetoastdev.honeymod.implementation.items.magical.runes.SoulboundRune;
import me.spacetoastdev.honeymod.implementation.items.magical.runes.VillagerRune;
import me.spacetoastdev.honeymod.implementation.items.magical.staves.StormStaff;
import me.spacetoastdev.honeymod.implementation.items.magical.staves.WaterStaff;
import me.spacetoastdev.honeymod.implementation.items.magical.staves.WindStaff;
import me.spacetoastdev.honeymod.implementation.items.magical.talismans.MagicianTalisman;
import me.spacetoastdev.honeymod.implementation.items.magical.talismans.Talisman;
import me.spacetoastdev.honeymod.implementation.items.medical.Bandage;
import me.spacetoastdev.honeymod.implementation.items.medical.Medicine;
import me.spacetoastdev.honeymod.implementation.items.medical.Splint;
import me.spacetoastdev.honeymod.implementation.items.medical.Vitamins;
import me.spacetoastdev.honeymod.implementation.items.misc.BasicCircuitBoard;
import me.spacetoastdev.honeymod.implementation.items.misc.CoolantCell;
import me.spacetoastdev.honeymod.implementation.items.misc.OrganicFertilizer;
import me.spacetoastdev.honeymod.implementation.items.misc.OrganicFood;
import me.spacetoastdev.honeymod.implementation.items.misc.SteelThruster;
import me.spacetoastdev.honeymod.implementation.items.misc.StrangeNetherGoo;
import me.spacetoastdev.honeymod.implementation.items.misc.SyntheticEmerald;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.ArmorForge;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.AutomatedPanningMachine;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.Compressor;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.EnhancedCraftingTable;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.GrindStone;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.Juicer;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.MagicWorkbench;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.MakeshiftSmeltery;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.OreCrusher;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.OreWasher;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.PressureChamber;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.Smeltery;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.TableSaw;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.miner.AdvancedIndustrialMiner;
import me.spacetoastdev.honeymod.implementation.items.multiblocks.miner.IndustrialMiner;
import me.spacetoastdev.honeymod.implementation.items.seasonal.ChristmasPresent;
import me.spacetoastdev.honeymod.implementation.items.seasonal.EasterEgg;
import me.spacetoastdev.honeymod.implementation.items.tools.ClimbingPick;
import me.spacetoastdev.honeymod.implementation.items.tools.ExplosivePickaxe;
import me.spacetoastdev.honeymod.implementation.items.tools.ExplosiveShovel;
import me.spacetoastdev.honeymod.implementation.items.tools.GoldPan;
import me.spacetoastdev.honeymod.implementation.items.tools.GrapplingHook;
import me.spacetoastdev.honeymod.implementation.items.tools.HerculesPickaxe;
import me.spacetoastdev.honeymod.implementation.items.tools.LumberAxe;
import me.spacetoastdev.honeymod.implementation.items.tools.NetherGoldPan;
import me.spacetoastdev.honeymod.implementation.items.tools.PickaxeOfContainment;
import me.spacetoastdev.honeymod.implementation.items.tools.PickaxeOfTheSeeker;
import me.spacetoastdev.honeymod.implementation.items.tools.PickaxeOfVeinMining;
import me.spacetoastdev.honeymod.implementation.items.tools.PortableCrafter;
import me.spacetoastdev.honeymod.implementation.items.tools.PortableDustbin;
import me.spacetoastdev.honeymod.implementation.items.tools.SmeltersPickaxe;
import me.spacetoastdev.honeymod.implementation.items.tools.TapeMeasure;
import me.spacetoastdev.honeymod.implementation.items.weapons.ExplosiveBow;
import me.spacetoastdev.honeymod.implementation.items.weapons.IcyBow;
import me.spacetoastdev.honeymod.implementation.items.weapons.SeismicAxe;
import me.spacetoastdev.honeymod.implementation.items.weapons.SwordOfBeheading;
import me.spacetoastdev.honeymod.implementation.items.weapons.VampireBlade;
import me.spacetoastdev.honeymod.utils.ColoredMaterial;
import me.spacetoastdev.honeymod.utils.HeadTexture;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;

/**
 * This class holds the recipes of all items.
 * This is the place where all items from Slimefun are registered.
 *
 */
public final class HoneymodItemSetup {

    private static boolean registeredItems = false;

    private HoneymodItemSetup() {}

    public static void setup(@Nonnull HoneymodPlugin plugin) {
        if (registeredItems) {
            throw new UnsupportedOperationException("Slimefun Items can only be registered once!");
        }

        registeredItems = true;
        DefaultCategories categories = new DefaultCategories();

        // @formatter:off (We will need to refactor this one day)
        new SlimefunItem(categories.weapons, HoneymodItems.GRANDMAS_WALKING_STICK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.OAK_LOG), null, null, new ItemStack(Material.OAK_LOG), null, null, new ItemStack(Material.OAK_LOG), null})
        .register(plugin);
        
        new SlimefunItem(categories.weapons, HoneymodItems.GRANDPAS_WALKING_STICK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.LEATHER), new ItemStack(Material.OAK_LOG), new ItemStack(Material.LEATHER), null, new ItemStack(Material.OAK_LOG), null, null, new ItemStack(Material.OAK_LOG), null})
        .register(plugin);

        new PortableCrafter(categories.usefulItems, HoneymodItems.PORTABLE_CRAFTER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.BOOK), new ItemStack(Material.CRAFTING_TABLE), null, null, null, null, null, null, null})
        .register(plugin);

        new FortuneCookie(categories.food, HoneymodItems.FORTUNE_COOKIE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.COOKIE), new ItemStack(Material.PAPER), null, null, null, null, null, null, null})
        .register(plugin);

        new DietCookie(categories.food, HoneymodItems.DIET_COOKIE, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {new ItemStack(Material.COOKIE), HoneymodItems.ELYTRA_SCALE, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.basicMachines, HoneymodItems.OUTPUT_CHEST, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.LEAD_INGOT, new ItemStack(Material.HOPPER), HoneymodItems.LEAD_INGOT, HoneymodItems.LEAD_INGOT, new ItemStack(Material.CHEST), HoneymodItems.LEAD_INGOT, null, HoneymodItems.LEAD_INGOT, null})
        .register(plugin);
        
        new EnhancedCraftingTable(categories.basicMachines, HoneymodItems.ENHANCED_CRAFTING_TABLE).register(plugin);

        new PortableDustbin(categories.usefulItems, HoneymodItems.PORTABLE_DUSTBIN, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT)})
        .register(plugin);

        new MeatJerky(categories.food, HoneymodItems.BEEF_JERKY, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SALT, new ItemStack(Material.COOKED_BEEF), null, null, null, null, null, null, null})
        .register(plugin);

        new MeatJerky(categories.food, HoneymodItems.PORK_JERKY, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SALT, new ItemStack(Material.COOKED_PORKCHOP), null, null, null, null, null, null, null})
        .register(plugin);

        new MeatJerky(categories.food, HoneymodItems.CHICKEN_JERKY, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SALT, new ItemStack(Material.COOKED_CHICKEN), null, null, null, null, null, null, null})
        .register(plugin);

        new MeatJerky(categories.food, HoneymodItems.MUTTON_JERKY, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SALT, new ItemStack(Material.COOKED_MUTTON), null, null, null, null, null, null, null})
        .register(plugin);

        new MeatJerky(categories.food, HoneymodItems.RABBIT_JERKY, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SALT, new ItemStack(Material.COOKED_RABBIT), null, null, null, null, null, null, null})
        .register(plugin);

        new MeatJerky(categories.food, HoneymodItems.FISH_JERKY, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SALT, new ItemStack(Material.COOKED_COD), null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.food, HoneymodItems.KELP_COOKIE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.DRIED_KELP), null, new ItemStack(Material.DRIED_KELP), new ItemStack(Material.SUGAR), new ItemStack(Material.DRIED_KELP), null, new ItemStack(Material.DRIED_KELP), null},
        new SlimefunItemStack(HoneymodItems.KELP_COOKIE, 2))
        .register(plugin);

        new GrindStone(categories.basicMachines, HoneymodItems.GRIND_STONE).register(plugin);
        new ArmorForge(categories.basicMachines, HoneymodItems.ARMOR_FORGE).register(plugin);
        
        OreCrusher oreCrusher = new OreCrusher(categories.basicMachines, HoneymodItems.ORE_CRUSHER);
        oreCrusher.register(plugin);
        
        new Compressor(categories.basicMachines, HoneymodItems.COMPRESSOR).register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.MAGIC_LUMP_1, RecipeType.GRIND_STONE,
        new ItemStack[] {new ItemStack(Material.NETHER_WART), null, null, null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.MAGIC_LUMP_1, 2))
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.MAGIC_LUMP_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_1, HoneymodItems.MAGIC_LUMP_1, null, HoneymodItems.MAGIC_LUMP_1, HoneymodItems.MAGIC_LUMP_1, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.MAGIC_LUMP_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_2, HoneymodItems.MAGIC_LUMP_2, null, HoneymodItems.MAGIC_LUMP_2, HoneymodItems.MAGIC_LUMP_2, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.ENDER_LUMP_1, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, null, null, new ItemStack(Material.ENDER_EYE), null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.ENDER_LUMP_1, 2))
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.ENDER_LUMP_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_1, HoneymodItems.ENDER_LUMP_1, null, HoneymodItems.ENDER_LUMP_1, HoneymodItems.ENDER_LUMP_1, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.ENDER_LUMP_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_2, HoneymodItems.ENDER_LUMP_2, null, HoneymodItems.ENDER_LUMP_2, HoneymodItems.ENDER_LUMP_2, null, null, null, null})
        .register(plugin);

        new EnderBackpack(categories.magicalGadgets, HoneymodItems.ENDER_BACKPACK, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_2, new ItemStack(Material.LEATHER), HoneymodItems.ENDER_LUMP_2, new ItemStack(Material.LEATHER), new ItemStack(Material.CHEST), new ItemStack(Material.LEATHER), HoneymodItems.ENDER_LUMP_2, new ItemStack(Material.LEATHER), HoneymodItems.ENDER_LUMP_2})
        .register(plugin);

        new SlimefunItem(categories.magicalArmor, HoneymodItems.ENDER_HELMET, RecipeType.ARMOR_FORGE,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_1, new ItemStack(Material.ENDER_EYE), HoneymodItems.ENDER_LUMP_1, new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.OBSIDIAN), null, null, null})
        .register(plugin);

        new SlimefunItem(categories.magicalArmor, HoneymodItems.ENDER_CHESTPLATE, RecipeType.ARMOR_FORGE,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_1, null, HoneymodItems.ENDER_LUMP_1, new ItemStack(Material.OBSIDIAN), new ItemStack(Material.ENDER_EYE), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN)})
        .register(plugin);

        new SlimefunItem(categories.magicalArmor, HoneymodItems.ENDER_LEGGINGS, RecipeType.ARMOR_FORGE,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_1, new ItemStack(Material.ENDER_EYE), HoneymodItems.ENDER_LUMP_1, new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.OBSIDIAN)})
        .register(plugin);

        new EnderBoots(categories.magicalArmor, HoneymodItems.ENDER_BOOTS, RecipeType.ARMOR_FORGE,
        new ItemStack[] {null, null, null, HoneymodItems.ENDER_LUMP_1, null, HoneymodItems.ENDER_LUMP_1, new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.OBSIDIAN)})
        .register(plugin);

        new MagicEyeOfEnder(categories.magicalGadgets, HoneymodItems.MAGIC_EYE_OF_ENDER, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_2, new ItemStack(Material.ENDER_PEARL), HoneymodItems.ENDER_LUMP_2, new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.ENDER_EYE), new ItemStack(Material.ENDER_PEARL), HoneymodItems.ENDER_LUMP_2, new ItemStack(Material.ENDER_PEARL), HoneymodItems.ENDER_LUMP_2})
        .register(plugin);

        new MagicSugar(categories.food, HoneymodItems.MAGIC_SUGAR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.SUGAR), new ItemStack(Material.REDSTONE), new ItemStack(Material.GLOWSTONE_DUST), null, null, null, null, null, null})
        .register(plugin);

        new MonsterJerky(categories.food, HoneymodItems.MONSTER_JERKY, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SALT, new ItemStack(Material.ROTTEN_FLESH), null, null, null, null, null, null, null})
        .register(plugin);

        new HoneymodArmorPiece(categories.magicalArmor, HoneymodItems.SLIME_HELMET, RecipeType.ARMOR_FORGE,
        new ItemStack[] {new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT), null, null, null}, null)
        .register(plugin);

        new HoneymodArmorPiece(categories.magicalArmor, HoneymodItems.SLIME_CHESTPLATE, RecipeType.ARMOR_FORGE,
        new ItemStack[] {new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT)}, null)
        .register(plugin);

        new HoneymodArmorPiece(categories.magicalArmor, HoneymodItems.SLIME_LEGGINGS, RecipeType.ARMOR_FORGE,
        new ItemStack[] {new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT)},
        new PotionEffect[] {new PotionEffect(PotionEffectType.SPEED, 300, 2)})
        .register(plugin);

        new LongFallBoots(categories.magicalArmor, HoneymodItems.SLIME_BOOTS, RecipeType.ARMOR_FORGE,
        new ItemStack[] {null, null, null, new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.IRON_INGOT)},
        new PotionEffect[] {new PotionEffect(PotionEffectType.JUMP, 300, 5)})
        .register(plugin);

        new SwordOfBeheading(categories.weapons, HoneymodItems.SWORD_OF_BEHEADING, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.EMERALD), null, HoneymodItems.MAGIC_LUMP_2, new ItemStack(Material.EMERALD), HoneymodItems.MAGIC_LUMP_2, null, new ItemStack(Material.BLAZE_ROD), null})
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.MAGICAL_BOOK_COVER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.MAGIC_LUMP_2, null, HoneymodItems.MAGIC_LUMP_2, new ItemStack(Material.BOOK), HoneymodItems.MAGIC_LUMP_2, null, HoneymodItems.MAGIC_LUMP_2, null})
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.MAGICAL_GLASS, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_2, HoneymodItems.GOLD_DUST, HoneymodItems.MAGIC_LUMP_2, HoneymodItems.FILLED_FLASK_OF_KNOWLEDGE, new ItemStack(Material.GLASS_PANE), HoneymodItems.FILLED_FLASK_OF_KNOWLEDGE, HoneymodItems.MAGIC_LUMP_2, HoneymodItems.FILLED_FLASK_OF_KNOWLEDGE, HoneymodItems.MAGIC_LUMP_2})
        .register(plugin);

        new BasicCircuitBoard(categories.technicalComponents, HoneymodItems.BASIC_CIRCUIT_BOARD, RecipeType.MOB_DROP,
        new ItemStack[] {null, null, null, null, new CustomItem(HoneymodUtils.getCustomHead(HeadTexture.IRON_GOLEM.getTexture()), "&aIron Golem"), null, null, null, null})
        .register(plugin);

        new UnplaceableBlock(categories.technicalComponents, HoneymodItems.ADVANCED_CIRCUIT_BOARD, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.REDSTONE_BLOCK), HoneymodItems.BASIC_CIRCUIT_BOARD, new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK)})
        .register(plugin);

        new GoldPan(categories.tools, HoneymodItems.GOLD_PAN, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, null, new ItemStack(Material.STONE), new ItemStack(Material.BOWL), new ItemStack(Material.STONE), new ItemStack(Material.STONE), new ItemStack(Material.STONE), new ItemStack(Material.STONE)})
        .register(plugin);

        new NetherGoldPan(categories.tools, HoneymodItems.NETHER_GOLD_PAN, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, null, new ItemStack(Material.NETHER_BRICK), HoneymodItems.GOLD_PAN, new ItemStack(Material.NETHER_BRICK), new ItemStack(Material.NETHER_BRICK), new ItemStack(Material.NETHER_BRICK), new ItemStack(Material.NETHER_BRICK)})
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.SIFTED_ORE, RecipeType.GOLD_PAN,
        new ItemStack[] {new ItemStack(Material.GRAVEL), null, null, null, null, null, null, null, null})
        .register(plugin);

        new MakeshiftSmeltery(categories.basicMachines, HoneymodItems.MAKESHIFT_SMELTERY).register(plugin);
        new Smeltery(categories.basicMachines, HoneymodItems.SMELTERY).register(plugin);
        
        new SlimefunItem(categories.basicMachines, HoneymodItems.IGNITION_CHAMBER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.IRON_INGOT), new ItemStack(Material.FLINT_AND_STEEL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), HoneymodItems.BASIC_CIRCUIT_BOARD, new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.OBSERVER), null})
        .register(plugin);
        
        new PressureChamber(categories.basicMachines, HoneymodItems.PRESSURE_CHAMBER).register(plugin);

        new SlimefunItem(categories.technicalComponents, HoneymodItems.BATTERY, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.REDSTONE), null, HoneymodItems.ZINC_INGOT, HoneymodItems.SULFATE, HoneymodItems.COPPER_INGOT, HoneymodItems.ZINC_INGOT, HoneymodItems.SULFATE, HoneymodItems.COPPER_INGOT})
        .register(plugin);

        registerArmorSet(categories.magicalArmor, new ItemStack(Material.GLOWSTONE), new ItemStack[] {HoneymodItems.GLOWSTONE_HELMET, HoneymodItems.GLOWSTONE_CHESTPLATE, HoneymodItems.GLOWSTONE_LEGGINGS, HoneymodItems.GLOWSTONE_BOOTS}, "GLOWSTONE", false,
        new PotionEffect[][] {
            new PotionEffect[] {new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0)}, 
            new PotionEffect[] {new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0)}, 
            new PotionEffect[] {new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0)}, 
            new PotionEffect[] {new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0)}
        }, plugin);

        registerArmorSet(categories.armor, HoneymodItems.DAMASCUS_STEEL_INGOT, new ItemStack[] {HoneymodItems.DAMASCUS_STEEL_HELMET, HoneymodItems.DAMASCUS_STEEL_CHESTPLATE, HoneymodItems.DAMASCUS_STEEL_LEGGINGS, HoneymodItems.DAMASCUS_STEEL_BOOTS}, "DAMASCUS_STEEL", false, new PotionEffect[0][0], plugin);

        registerArmorSet(categories.armor, HoneymodItems.REINFORCED_ALLOY_INGOT, new ItemStack[] {HoneymodItems.REINFORCED_ALLOY_HELMET, HoneymodItems.REINFORCED_ALLOY_CHESTPLATE, HoneymodItems.REINFORCED_ALLOY_LEGGINGS, HoneymodItems.REINFORCED_ALLOY_BOOTS}, "REINFORCED_ALLOY", false, new PotionEffect[0][0], plugin);

        registerArmorSet(categories.armor, new ItemStack(Material.CACTUS), new ItemStack[] {HoneymodItems.CACTUS_HELMET, HoneymodItems.CACTUS_CHESTPLATE, HoneymodItems.CACTUS_LEGGINGS, HoneymodItems.CACTUS_BOOTS}, "CACTUS", false, new PotionEffect[0][0], plugin);

        new SlimefunItem(categories.resources, HoneymodItems.REINFORCED_ALLOY_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.SOLDER_INGOT, HoneymodItems.BILLON_INGOT, HoneymodItems.GOLD_24K, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.HARDENED_METAL_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.COMPRESSED_CARBON, HoneymodItems.ALUMINUM_BRONZE_INGOT, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.DAMASCUS_STEEL_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.STEEL_INGOT, HoneymodItems.IRON_DUST, HoneymodItems.CARBON, new ItemStack(Material.IRON_INGOT), null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.STEEL_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.IRON_DUST, HoneymodItems.CARBON, new ItemStack(Material.IRON_INGOT), null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.BRONZE_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.COPPER_DUST, HoneymodItems.TIN_DUST, HoneymodItems.COPPER_INGOT, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.DURALUMIN_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.ALUMINUM_DUST, HoneymodItems.COPPER_DUST, HoneymodItems.ALUMINUM_INGOT, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.BILLON_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.SILVER_DUST, HoneymodItems.COPPER_DUST, HoneymodItems.SILVER_INGOT, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.BRASS_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.COPPER_DUST, HoneymodItems.ZINC_DUST, HoneymodItems.COPPER_INGOT, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.ALUMINUM_BRASS_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.ALUMINUM_DUST, HoneymodItems.BRASS_INGOT, HoneymodItems.ALUMINUM_INGOT, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.ALUMINUM_BRONZE_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.ALUMINUM_DUST, HoneymodItems.BRONZE_INGOT, HoneymodItems.ALUMINUM_INGOT, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.CORINTHIAN_BRONZE_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.SILVER_DUST, HoneymodItems.GOLD_DUST, HoneymodItems.COPPER_DUST, HoneymodItems.BRONZE_INGOT, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.SOLDER_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.LEAD_DUST, HoneymodItems.TIN_DUST, HoneymodItems.LEAD_INGOT, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.SYNTHETIC_SAPPHIRE, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.ALUMINUM_DUST, new ItemStack(Material.GLASS), new ItemStack(Material.GLASS_PANE), HoneymodItems.ALUMINUM_INGOT, new ItemStack(Material.LAPIS_LAZULI), null, null, null, null})
        .setUseableInWorkbench(true)
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.SYNTHETIC_DIAMOND, RecipeType.PRESSURE_CHAMBER,
        new ItemStack[] {HoneymodItems.CARBON_CHUNK, null, null, null, null, null, null, null, null})
        .setUseableInWorkbench(true)
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.RAW_CARBONADO, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.SYNTHETIC_DIAMOND, HoneymodItems.CARBON_CHUNK, new ItemStack(Material.GLASS_PANE), null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.NICKEL_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.IRON_DUST, new ItemStack(Material.IRON_INGOT), HoneymodItems.COPPER_DUST, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.COBALT_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.IRON_DUST, HoneymodItems.COPPER_DUST, HoneymodItems.NICKEL_INGOT, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.CARBONADO, RecipeType.PRESSURE_CHAMBER,
        new ItemStack[] {HoneymodItems.RAW_CARBONADO, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.FERROSILICON, RecipeType.SMELTERY,
        new ItemStack[] {new ItemStack(Material.IRON_INGOT), HoneymodItems.IRON_DUST, HoneymodItems.SILICON, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.IRON_DUST, RecipeType.ORE_CRUSHER,
        new ItemStack[] {new ItemStack(Material.IRON_ORE), null, null, null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.IRON_DUST, oreCrusher.isOreDoublingEnabled() ? 2 : 1))
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_DUST, RecipeType.ORE_CRUSHER,
        new ItemStack[] {new ItemStack(Material.GOLD_ORE), null, null, null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.GOLD_DUST, oreCrusher.isOreDoublingEnabled() ? 2 : 1))
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.COPPER_DUST, RecipeType.ORE_WASHER,
        new ItemStack[] {HoneymodItems.SIFTED_ORE, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.TIN_DUST, RecipeType.ORE_WASHER,
        new ItemStack[] {HoneymodItems.SIFTED_ORE, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.LEAD_DUST, RecipeType.ORE_WASHER,
        new ItemStack[] {HoneymodItems.SIFTED_ORE, null, null, null, null, null, null, null, null})
        .register(plugin);
        
        new SlimefunItem(categories.resources, HoneymodItems.SILVER_DUST, RecipeType.ORE_WASHER,
        new ItemStack[] {HoneymodItems.SIFTED_ORE, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.ALUMINUM_DUST, RecipeType.ORE_WASHER,
        new ItemStack[] {HoneymodItems.SIFTED_ORE, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.ZINC_DUST, RecipeType.ORE_WASHER,
        new ItemStack[] {HoneymodItems.SIFTED_ORE, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.MAGNESIUM_DUST, RecipeType.ORE_WASHER,
        new ItemStack[] {HoneymodItems.SIFTED_ORE, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.COPPER_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.COPPER_DUST, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.TIN_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.TIN_DUST, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.SILVER_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.SILVER_DUST, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.LEAD_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.LEAD_DUST, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.ALUMINUM_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.ALUMINUM_DUST, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.ZINC_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.ZINC_DUST, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.MAGNESIUM_INGOT, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.MAGNESIUM_DUST, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.SULFATE, RecipeType.ORE_CRUSHER,
        new ItemStack[] {new ItemStack(Material.NETHERRACK, 16), null, null, null, null, null, null, null, null})
        .register(plugin);

        new UnplaceableBlock(categories.resources, HoneymodItems.CARBON, RecipeType.COMPRESSOR,
        new ItemStack[] {new ItemStack(Material.COAL, 8), null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.WHEAT_FLOUR, RecipeType.GRIND_STONE,
        new ItemStack[] {new ItemStack(Material.WHEAT), null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.STEEL_PLATE, RecipeType.COMPRESSOR,
        new ItemStack[] {new SlimefunItemStack(HoneymodItems.STEEL_INGOT, 8), null, null, null, null, null, null, null, null})
        .register(plugin);

        new UnplaceableBlock(categories.resources, HoneymodItems.COMPRESSED_CARBON, RecipeType.COMPRESSOR,
        new ItemStack[] {new SlimefunItemStack(HoneymodItems.CARBON, 4), null, null, null, null, null, null, null, null})
        .register(plugin);

        new UnplaceableBlock(categories.resources, HoneymodItems.CARBON_CHUNK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.COMPRESSED_CARBON, HoneymodItems.COMPRESSED_CARBON, HoneymodItems.COMPRESSED_CARBON, HoneymodItems.COMPRESSED_CARBON, new ItemStack(Material.FLINT), HoneymodItems.COMPRESSED_CARBON, HoneymodItems.COMPRESSED_CARBON, HoneymodItems.COMPRESSED_CARBON, HoneymodItems.COMPRESSED_CARBON})
        .register(plugin);

        new SteelThruster(categories.technicalComponents, HoneymodItems.STEEL_THRUSTER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.REDSTONE), null, HoneymodItems.ALUMINUM_BRONZE_INGOT, HoneymodItems.ALUMINUM_BRONZE_INGOT, HoneymodItems.ALUMINUM_BRONZE_INGOT, HoneymodItems.STEEL_PLATE, new ItemStack(Material.FIRE_CHARGE), HoneymodItems.STEEL_PLATE})
        .register(plugin);

        new SlimefunItem(categories.technicalComponents, HoneymodItems.POWER_CRYSTAL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.REDSTONE), HoneymodItems.SYNTHETIC_SAPPHIRE, new ItemStack(Material.REDSTONE), HoneymodItems.SYNTHETIC_SAPPHIRE, HoneymodItems.SYNTHETIC_DIAMOND, HoneymodItems.SYNTHETIC_SAPPHIRE, new ItemStack(Material.REDSTONE), HoneymodItems.SYNTHETIC_SAPPHIRE, new ItemStack(Material.REDSTONE)})
        .register(plugin);

        new Jetpack(categories.technicalGadgets, HoneymodItems.DURALUMIN_JETPACK,
        new ItemStack[] {HoneymodItems.DURALUMIN_INGOT, null, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.35, 20)
        .register(plugin);

        new Jetpack(categories.technicalGadgets, HoneymodItems.SOLDER_JETPACK,
        new ItemStack[] {HoneymodItems.SOLDER_INGOT, null, HoneymodItems.SOLDER_INGOT, HoneymodItems.SOLDER_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.SOLDER_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.4, 30)
        .register(plugin);

        new Jetpack(categories.technicalGadgets, HoneymodItems.BILLON_JETPACK,
        new ItemStack[] {HoneymodItems.BILLON_INGOT, null, HoneymodItems.BILLON_INGOT, HoneymodItems.BILLON_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.BILLON_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.45, 45)
        .register(plugin);

        new Jetpack(categories.technicalGadgets, HoneymodItems.STEEL_JETPACK,
        new ItemStack[] {HoneymodItems.STEEL_INGOT, null, HoneymodItems.STEEL_INGOT, HoneymodItems.STEEL_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.STEEL_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.5, 60)
        .register(plugin);

        new Jetpack(categories.technicalGadgets, HoneymodItems.DAMASCUS_STEEL_JETPACK,
        new ItemStack[] {HoneymodItems.DAMASCUS_STEEL_INGOT, null, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.55, 75)
        .register(plugin);

        new Jetpack(categories.technicalGadgets, HoneymodItems.REINFORCED_ALLOY_JETPACK,
        new ItemStack[] {HoneymodItems.REINFORCED_ALLOY_INGOT, null, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.MEDIUM_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.6, 100)
        .register(plugin);

        new Jetpack(categories.technicalGadgets, HoneymodItems.CARBONADO_JETPACK,
        new ItemStack[] {HoneymodItems.CARBON_CHUNK, null, HoneymodItems.CARBON_CHUNK, HoneymodItems.CARBONADO, HoneymodItems.POWER_CRYSTAL, HoneymodItems.CARBONADO, HoneymodItems.STEEL_THRUSTER, HoneymodItems.LARGE_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.7, 150)
        .register(plugin);

        new Parachute(categories.technicalGadgets, HoneymodItems.PARACHUTE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CLOTH, HoneymodItems.CLOTH, HoneymodItems.CLOTH, HoneymodItems.CHAIN, null, HoneymodItems.CHAIN, null, null, null})
        .register(plugin);

        new HologramProjector(categories.technicalGadgets, HoneymodItems.HOLOGRAM_PROJECTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.POWER_CRYSTAL, null, HoneymodItems.ALUMINUM_BRASS_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.ALUMINUM_BRASS_INGOT, null, HoneymodItems.ALUMINUM_BRASS_INGOT, null}, 
        new SlimefunItemStack(HoneymodItems.HOLOGRAM_PROJECTOR, 3))
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.CHAIN, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.STEEL_INGOT, null, null}, 
        new SlimefunItemStack(HoneymodItems.CHAIN, 8))
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.HOOK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.STEEL_INGOT, null, null, null})
        .register(plugin);

        new GrapplingHook(categories.tools, HoneymodItems.GRAPPLING_HOOK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, HoneymodItems.HOOK, null, HoneymodItems.CHAIN, null, HoneymodItems.CHAIN, null, null})
        .register(plugin);

        new MagicWorkbench(categories.basicMachines, HoneymodItems.MAGIC_WORKBENCH).register(plugin);

        new SlimefunItem(categories.magicalGadgets, HoneymodItems.STAFF_ELEMENTAL, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.MAGICAL_BOOK_COVER, HoneymodItems.MAGIC_LUMP_3, null, new ItemStack(Material.STICK), HoneymodItems.MAGICAL_BOOK_COVER, HoneymodItems.MAGIC_LUMP_3, null, null})
        .register(plugin);

        new WindStaff(categories.magicalGadgets, HoneymodItems.STAFF_WIND, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, new ItemStack(Material.FEATHER), HoneymodItems.ENDER_LUMP_3, null, HoneymodItems.STAFF_ELEMENTAL, new ItemStack(Material.FEATHER), HoneymodItems.STAFF_ELEMENTAL, null, null})
        .register(plugin);

        new WaterStaff(categories.magicalGadgets, HoneymodItems.STAFF_WATER, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, new ItemStack(Material.LILY_PAD), HoneymodItems.MAGIC_LUMP_2, null, HoneymodItems.STAFF_ELEMENTAL, new ItemStack(Material.LILY_PAD), HoneymodItems.STAFF_ELEMENTAL, null, null})
        .register(plugin);
        
        String[] multiToolItems = new String[] {"PORTABLE_CRAFTER", "MAGIC_EYE_OF_ENDER", "STAFF_ELEMENTAL_WIND", "GRAPPLING_HOOK"};

        new MultiTool(categories.technicalGadgets, HoneymodItems.DURALUMIN_MULTI_TOOL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.DURALUMIN_INGOT, null, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.DURALUMIN_INGOT, null, HoneymodItems.DURALUMIN_INGOT, null},
        20, multiToolItems)
        .register(plugin);

        new MultiTool(categories.technicalGadgets, HoneymodItems.SOLDER_MULTI_TOOL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SOLDER_INGOT, null, HoneymodItems.SOLDER_INGOT, HoneymodItems.SOLDER_INGOT, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.SOLDER_INGOT, null, HoneymodItems.SOLDER_INGOT, null},
        30, multiToolItems)
        .register(plugin);

        new MultiTool(categories.technicalGadgets, HoneymodItems.BILLON_MULTI_TOOL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.BILLON_INGOT, null, HoneymodItems.BILLON_INGOT, HoneymodItems.BILLON_INGOT, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.BILLON_INGOT, null, HoneymodItems.BILLON_INGOT, null},
        40, multiToolItems)
        .register(plugin);

        new MultiTool(categories.technicalGadgets, HoneymodItems.STEEL_MULTI_TOOL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.STEEL_INGOT, null, HoneymodItems.STEEL_INGOT, HoneymodItems.STEEL_INGOT, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.STEEL_INGOT, null, HoneymodItems.STEEL_INGOT, null},
        50, multiToolItems)
        .register(plugin);

        new MultiTool(categories.technicalGadgets, HoneymodItems.DAMASCUS_STEEL_MULTI_TOOL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.DAMASCUS_STEEL_INGOT, null, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.DAMASCUS_STEEL_INGOT, null, HoneymodItems.DAMASCUS_STEEL_INGOT, null},
        60, multiToolItems)
        .register(plugin);

        new MultiTool(categories.technicalGadgets, HoneymodItems.REINFORCED_ALLOY_MULTI_TOOL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.REINFORCED_ALLOY_INGOT, null, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.MEDIUM_CAPACITOR, HoneymodItems.REINFORCED_ALLOY_INGOT, null, HoneymodItems.REINFORCED_ALLOY_INGOT, null},
        75, multiToolItems)
        .register(plugin);

        new MultiTool(categories.technicalGadgets, HoneymodItems.CARBONADO_MULTI_TOOL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CARBONADO, null, HoneymodItems.CARBONADO, HoneymodItems.CARBONADO, HoneymodItems.LARGE_CAPACITOR, HoneymodItems.CARBONADO, null, HoneymodItems.CARBONADO, null},
        100, "PORTABLE_CRAFTER", "MAGIC_EYE_OF_ENDER", "STAFF_ELEMENTAL_WIND", "GRAPPLING_HOOK", "GOLD_PAN", "NETHER_GOLD_PAN")
        .register(plugin);

        new OreWasher(categories.basicMachines, HoneymodItems.ORE_WASHER).register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_24K, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_DUST, HoneymodItems.GOLD_22K, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_22K, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_DUST, HoneymodItems.GOLD_20K, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_20K, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_DUST, HoneymodItems.GOLD_18K, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_18K, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_DUST, HoneymodItems.GOLD_16K, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_16K, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_DUST, HoneymodItems.GOLD_14K, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_14K, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_DUST, HoneymodItems.GOLD_12K, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_12K, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_DUST, HoneymodItems.GOLD_10K, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_10K, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_DUST, HoneymodItems.GOLD_8K, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_8K, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_DUST, HoneymodItems.GOLD_6K, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_6K, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_DUST, HoneymodItems.GOLD_4K, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GOLD_4K, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_DUST, null, null, null, null, null, null, null, null})
        .setUseableInWorkbench(true)
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.STONE_CHUNK, RecipeType.ORE_WASHER,
        new ItemStack[] {HoneymodItems.SIFTED_ORE, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.SILICON, RecipeType.SMELTERY,
        new ItemStack[] {new ItemStack(Material.QUARTZ_BLOCK), null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.technicalComponents, HoneymodItems.SOLAR_PANEL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), HoneymodItems.SILICON, HoneymodItems.SILICON, HoneymodItems.SILICON, HoneymodItems.FERROSILICON, HoneymodItems.FERROSILICON, HoneymodItems.FERROSILICON})
        .register(plugin);

        new SolarHelmet(categories.technicalGadgets, HoneymodItems.SOLAR_HELMET, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.SOLAR_PANEL, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.REINFORCED_ALLOY_INGOT, null, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.MEDIUM_CAPACITOR, null, HoneymodItems.MEDIUM_CAPACITOR},
        0.1)
        .register(plugin);

        new UnplaceableBlock(categories.magicalResources, HoneymodItems.LAVA_CRYSTAL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_1, new ItemStack(Material.BLAZE_POWDER), HoneymodItems.MAGIC_LUMP_1, new ItemStack(Material.BLAZE_POWDER), HoneymodItems.FIRE_RUNE, new ItemStack(Material.BLAZE_POWDER), HoneymodItems.MAGIC_LUMP_1, new ItemStack(Material.BLAZE_POWDER), HoneymodItems.MAGIC_LUMP_1})
        .register(plugin);

        new SlimefunItem(categories.magicalGadgets, HoneymodItems.STAFF_FIRE, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, null, HoneymodItems.LAVA_CRYSTAL, null, HoneymodItems.STAFF_ELEMENTAL, null, HoneymodItems.STAFF_ELEMENTAL, null, null})
        .register(plugin);
        
        new StormStaff(categories.magicalGadgets, HoneymodItems.STAFF_STORM, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {HoneymodItems.LIGHTNING_RUNE, HoneymodItems.ENDER_LUMP_3, HoneymodItems.LIGHTNING_RUNE, HoneymodItems.STAFF_WATER, HoneymodItems.MAGIC_SUGAR, HoneymodItems.STAFF_WIND, HoneymodItems.LIGHTNING_RUNE, HoneymodItems.ENDER_LUMP_3, HoneymodItems.LIGHTNING_RUNE})
        .register(plugin);

        ItemStack weaknessPotion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) weaknessPotion.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.WEAKNESS, false, false));
        weaknessPotion.setItemMeta(meta);

        new MagicalZombiePills(categories.magicalGadgets, HoneymodItems.MAGICAL_ZOMBIE_PILLS, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {new ItemStack(Material.GOLD_INGOT), HoneymodItems.MAGIC_LUMP_2, new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.APPLE), weaknessPotion, new ItemStack(Material.APPLE), new ItemStack(Material.GOLD_INGOT), HoneymodItems.MAGIC_LUMP_2, new ItemStack(Material.GOLD_INGOT)},
        new SlimefunItemStack(HoneymodItems.MAGICAL_ZOMBIE_PILLS, 2))
        .register(plugin);

        new SmeltersPickaxe(categories.tools, HoneymodItems.SMELTERS_PICKAXE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.LAVA_CRYSTAL, HoneymodItems.LAVA_CRYSTAL, HoneymodItems.LAVA_CRYSTAL, null, HoneymodItems.FERROSILICON, null, null, HoneymodItems.FERROSILICON, null})
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.COMMON_TALISMAN, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_2, HoneymodItems.GOLD_8K, HoneymodItems.MAGIC_LUMP_2, null, new ItemStack(Material.EMERALD), null, HoneymodItems.MAGIC_LUMP_2, HoneymodItems.GOLD_8K, HoneymodItems.MAGIC_LUMP_2})
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_ANVIL,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, new ItemStack(Material.ANVIL), HoneymodItems.COMMON_TALISMAN, new ItemStack(Material.ANVIL), HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3},
        true, false, "anvil")
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_MINER,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.SYNTHETIC_SAPPHIRE, HoneymodItems.COMMON_TALISMAN, HoneymodItems.SIFTED_ORE, HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3},
        false, false, null, 20)
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_HUNTER,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.SYNTHETIC_SAPPHIRE, HoneymodItems.COMMON_TALISMAN, HoneymodItems.MONSTER_JERKY, HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3},
        false, false, "hunter", 20)
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_LAVA,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.LAVA_CRYSTAL, HoneymodItems.COMMON_TALISMAN, new ItemStack(Material.LAVA_BUCKET), HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3},
        true, true, "lava", new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 4))
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_WATER,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, new ItemStack(Material.WATER_BUCKET), HoneymodItems.COMMON_TALISMAN, new ItemStack(Material.FISHING_ROD), HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3},
        true, true, "water", new PotionEffect(PotionEffectType.WATER_BREATHING, 3600, 4))
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_ANGEL,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, new ItemStack(Material.FEATHER), HoneymodItems.COMMON_TALISMAN, new ItemStack(Material.FEATHER), HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3},
        false, true, "angel", 75)
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_FIRE,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.LAVA_CRYSTAL, HoneymodItems.COMMON_TALISMAN, HoneymodItems.LAVA_CRYSTAL, HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3},
        true, true, "fire", new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 4))
        .register(plugin);

        new MagicianTalisman(HoneymodItems.TALISMAN_MAGICIAN,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_3, null, HoneymodItems.ENDER_LUMP_3, new ItemStack(Material.ENCHANTING_TABLE), HoneymodItems.COMMON_TALISMAN, new ItemStack(Material.ENCHANTING_TABLE), HoneymodItems.ENDER_LUMP_3, null, HoneymodItems.ENDER_LUMP_3})
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_TRAVELLER,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.STAFF_WIND, HoneymodItems.TALISMAN_ANGEL, HoneymodItems.STAFF_WIND, HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3},
        false, false, "traveller", 60, new PotionEffect(PotionEffectType.SPEED, 3600, 2))
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_WARRIOR,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.COMMON_TALISMAN, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3},
        true, true, "warrior", new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 2))
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_KNIGHT,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.GILDED_IRON, HoneymodItems.TALISMAN_WARRIOR, HoneymodItems.GILDED_IRON, HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3},
        "knight", 30, new PotionEffect(PotionEffectType.REGENERATION, 100, 3))
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_CAVEMAN,
        new ItemStack[] { HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, new ItemStack(Material.GOLDEN_PICKAXE), HoneymodItems.TALISMAN_MINER, HoneymodItems.EARTH_RUNE, HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3},
        false, false, "caveman", 50, new PotionEffect(PotionEffectType.FAST_DIGGING, 800, 2))
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_WISE,
        new ItemStack[] { HoneymodItems.MAGIC_LUMP_3, HoneymodItems.MAGICAL_GLASS, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.FILLED_FLASK_OF_KNOWLEDGE, HoneymodItems.TALISMAN_MAGICIAN, HoneymodItems.FILLED_FLASK_OF_KNOWLEDGE, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.MAGICAL_GLASS, HoneymodItems.MAGIC_LUMP_3},
        false, false, "wise", 20)
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.GILDED_IRON, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.GOLD_24K, HoneymodItems.IRON_DUST, null, null, null, null, null, null, null})
        .register(plugin);

        new SyntheticEmerald(categories.resources, HoneymodItems.SYNTHETIC_EMERALD, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.SYNTHETIC_SAPPHIRE, HoneymodItems.ALUMINUM_DUST, HoneymodItems.ALUMINUM_INGOT, new ItemStack(Material.GLASS_PANE), null, null, null, null, null})
        .register(plugin);

        registerArmorSet(categories.armor, HoneymodItems.CHAIN, new ItemStack[] {
            new ItemStack(Material.CHAINMAIL_HELMET), new ItemStack(Material.CHAINMAIL_CHESTPLATE), new ItemStack(Material.CHAINMAIL_LEGGINGS), new ItemStack(Material.CHAINMAIL_BOOTS)
        }, "CHAIN", true, new PotionEffect[0][0], plugin);

        new Talisman(HoneymodItems.TALISMAN_WHIRLWIND,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.STAFF_WIND, HoneymodItems.TALISMAN_TRAVELLER, HoneymodItems.STAFF_WIND, HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3}
        , false, true, "whirlwind", 60)
        .register(plugin);

        new Talisman(HoneymodItems.TALISMAN_WIZARD,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_3, null, HoneymodItems.ENDER_LUMP_3, HoneymodItems.MAGIC_EYE_OF_ENDER, HoneymodItems.TALISMAN_MAGICIAN, HoneymodItems.MAGIC_EYE_OF_ENDER, HoneymodItems.ENDER_LUMP_3, null, HoneymodItems.ENDER_LUMP_3},
        false, false, "wizard", 60)
        .register(plugin);
        
        new LumberAxe(categories.tools, HoneymodItems.LUMBER_AXE, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {HoneymodItems.SYNTHETIC_DIAMOND, HoneymodItems.SYNTHETIC_DIAMOND, null, HoneymodItems.SYNTHETIC_EMERALD, HoneymodItems.GILDED_IRON, null, null, HoneymodItems.GILDED_IRON, null})
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.SALT, RecipeType.ORE_WASHER,
        new ItemStack[] {new ItemStack(Material.SAND, 2), null, null, null, null, null, null, null, null})
        .register(plugin);

        new HeavyCream(categories.misc, HoneymodItems.HEAVY_CREAM, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.MILK_BUCKET), null, null, null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.HEAVY_CREAM, 2))
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.CHEESE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.MILK_BUCKET), HoneymodItems.SALT, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.BUTTER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.HEAVY_CREAM, HoneymodItems.SALT, null, null, null, null, null, null, null})
        .register(plugin);

        registerArmorSet(categories.armor, HoneymodItems.GILDED_IRON, new ItemStack[] {
             HoneymodItems.GILDED_IRON_HELMET, HoneymodItems.GILDED_IRON_CHESTPLATE, HoneymodItems.GILDED_IRON_LEGGINGS, HoneymodItems.GILDED_IRON_BOOTS
        }, "GILDED_IRON", false, new PotionEffect[0][0], plugin);
        
        new SlimefunItem(categories.technicalComponents, HoneymodItems.REINFORCED_CLOTH, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.CLOTH, null, HoneymodItems.CLOTH, HoneymodItems.LEAD_INGOT, HoneymodItems.CLOTH, null, HoneymodItems.CLOTH, null}, new SlimefunItemStack(HoneymodItems.REINFORCED_CLOTH, 2))
        .register(plugin);

        new HazmatArmorPiece(categories.armor, HoneymodItems.SCUBA_HELMET, RecipeType.ARMOR_FORGE,
        new ItemStack[] {new ItemStack(Material.ORANGE_WOOL), HoneymodItems.REINFORCED_CLOTH, new ItemStack(Material.ORANGE_WOOL), HoneymodItems.REINFORCED_CLOTH, new ItemStack(Material.GLASS_PANE), HoneymodItems.REINFORCED_CLOTH, null, null, null},
        new PotionEffect[] {new PotionEffect(PotionEffectType.WATER_BREATHING, 300, 1)})
        .register(plugin);

        new HazmatArmorPiece(categories.armor, HoneymodItems.HAZMAT_CHESTPLATE, RecipeType.ARMOR_FORGE,
        new ItemStack[] {new ItemStack(Material.ORANGE_WOOL), null, new ItemStack(Material.ORANGE_WOOL), HoneymodItems.REINFORCED_CLOTH, HoneymodItems.REINFORCED_CLOTH, HoneymodItems.REINFORCED_CLOTH, new ItemStack(Material.BLACK_WOOL), HoneymodItems.REINFORCED_CLOTH, new ItemStack(Material.BLACK_WOOL)},
        new PotionEffect[] {new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 300, 1)})
        .register(plugin);

        new HazmatArmorPiece(categories.armor, HoneymodItems.HAZMAT_LEGGINGS, RecipeType.ARMOR_FORGE,
        new ItemStack[] {new ItemStack(Material.BLACK_WOOL), HoneymodItems.REINFORCED_CLOTH, new ItemStack(Material.BLACK_WOOL), HoneymodItems.REINFORCED_CLOTH, null, HoneymodItems.REINFORCED_CLOTH, HoneymodItems.REINFORCED_CLOTH, null, HoneymodItems.REINFORCED_CLOTH}, new PotionEffect[0])
        .register(plugin);

        new HazmatArmorPiece(categories.armor, HoneymodItems.HAZMAT_BOOTS, RecipeType.ARMOR_FORGE,
        new ItemStack[] {HoneymodItems.REINFORCED_CLOTH, null, HoneymodItems.REINFORCED_CLOTH, HoneymodItems.REINFORCED_CLOTH, null, HoneymodItems.REINFORCED_CLOTH, new ItemStack(Material.BLACK_WOOL), null, new ItemStack(Material.BLACK_WOOL)}, new PotionEffect[0])
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.CRUSHED_ORE, RecipeType.ORE_CRUSHER,
        new ItemStack[] {HoneymodItems.SIFTED_ORE, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.PULVERIZED_ORE, RecipeType.ORE_CRUSHER,
        new ItemStack[] {HoneymodItems.CRUSHED_ORE, null, null, null, null, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.PURE_ORE_CLUSTER, RecipeType.ORE_WASHER,
        new ItemStack[] {HoneymodItems.PULVERIZED_ORE, null, null, null, null, null, null, null, null})
        .register(plugin);

        new UnplaceableBlock(categories.misc, HoneymodItems.TINY_URANIUM, RecipeType.ORE_CRUSHER,
        new ItemStack[] {HoneymodItems.PURE_ORE_CLUSTER, null, null, null, null, null, null, null, null})
        .register(plugin);

        new RadioactiveItem(categories.misc, Radioactivity.MODERATE, HoneymodItems.SMALL_URANIUM, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.TINY_URANIUM, HoneymodItems.TINY_URANIUM, HoneymodItems.TINY_URANIUM, HoneymodItems.TINY_URANIUM, HoneymodItems.TINY_URANIUM, HoneymodItems.TINY_URANIUM, HoneymodItems.TINY_URANIUM, HoneymodItems.TINY_URANIUM, HoneymodItems.TINY_URANIUM})
        .register(plugin);

        new RadioactiveItem(categories.resources, Radioactivity.HIGH, HoneymodItems.URANIUM, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SMALL_URANIUM, HoneymodItems.SMALL_URANIUM, null, HoneymodItems.SMALL_URANIUM, HoneymodItems.SMALL_URANIUM, null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.REDSTONE_ALLOY, RecipeType.SMELTERY,
        new ItemStack[] {new ItemStack(Material.REDSTONE), new ItemStack(Material.REDSTONE_BLOCK), HoneymodItems.FERROSILICON, HoneymodItems.HARDENED_METAL_INGOT, null, null, null, null, null})
        .register(plugin);

        registerArmorSet(categories.armor, HoneymodItems.GOLD_12K, new ItemStack[] {
            HoneymodItems.GOLDEN_HELMET_12K, HoneymodItems.GOLDEN_CHESTPLATE_12K, HoneymodItems.GOLDEN_LEGGINGS_12K, HoneymodItems.GOLDEN_BOOTS_12K
        }, "GOLD_12K", false, new PotionEffect[0][0], plugin);

        new SlimefunItem(categories.misc, HoneymodItems.CLOTH, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.WHITE_WOOL), null, null, null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.CLOTH, 8))
        .register(plugin);

        new Bandage(categories.usefulItems, HoneymodItems.RAG, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CLOTH, HoneymodItems.CLOTH, HoneymodItems.CLOTH, new ItemStack(Material.STRING), null, new ItemStack(Material.STRING), HoneymodItems.CLOTH, HoneymodItems.CLOTH, HoneymodItems.CLOTH},
        new SlimefunItemStack(HoneymodItems.RAG, 2), 0)
        .register(plugin);

        new Bandage(categories.usefulItems, HoneymodItems.BANDAGE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.RAG, new ItemStack(Material.STRING), HoneymodItems.RAG, null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.BANDAGE, 4), 1)
        .register(plugin);

        new Splint(categories.usefulItems, HoneymodItems.SPLINT, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.STICK), new ItemStack(Material.STICK), new ItemStack(Material.STICK), null, new ItemStack(Material.IRON_INGOT), null}, 
        new SlimefunItemStack(HoneymodItems.SPLINT, 4))
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.TIN_CAN, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.TIN_INGOT, HoneymodItems.TIN_INGOT, HoneymodItems.TIN_INGOT, HoneymodItems.TIN_INGOT, null, HoneymodItems.TIN_INGOT, HoneymodItems.TIN_INGOT, HoneymodItems.TIN_INGOT, HoneymodItems.TIN_INGOT}, 
        new SlimefunItemStack(HoneymodItems.TIN_CAN, 8))
        .register(plugin);

        new Vitamins(categories.usefulItems, HoneymodItems.VITAMINS, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.TIN_CAN, new ItemStack(Material.APPLE), new ItemStack(Material.RED_MUSHROOM), new ItemStack(Material.SUGAR), null, null, null, null, null})
        .register(plugin);

        new Medicine(categories.usefulItems, HoneymodItems.MEDICINE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.VITAMINS, new ItemStack(Material.GLASS_BOTTLE), HoneymodItems.HEAVY_CREAM, null, null, null, null, null, null})
        .register(plugin);

        new HoneymodArmorPiece(categories.technicalGadgets, HoneymodItems.NIGHT_VISION_GOGGLES, RecipeType.ARMOR_FORGE,
        new ItemStack[] {new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.LIME_STAINED_GLASS_PANE), new ItemStack(Material.COAL_BLOCK), null, new ItemStack(Material.COAL_BLOCK)},
        new PotionEffect[] {new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 20)})
        .register(plugin);

        new PickaxeOfContainment(categories.tools, HoneymodItems.PICKAXE_OF_CONTAINMENT, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {HoneymodItems.FERROSILICON, HoneymodItems.FERROSILICON, HoneymodItems.FERROSILICON, null, HoneymodItems.GILDED_IRON, null, null, HoneymodItems.GILDED_IRON, null})
        .register(plugin);

        new HerculesPickaxe(categories.tools, HoneymodItems.HERCULES_PICKAXE, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.HARDENED_METAL_INGOT, null, HoneymodItems.FERROSILICON, null, null, HoneymodItems.FERROSILICON, null})
        .register(plugin);
        
        new TableSaw(categories.basicMachines, HoneymodItems.TABLE_SAW).register(plugin);

        new HoneymodArmorPiece(categories.magicalArmor, HoneymodItems.SLIME_HELMET_STEEL, RecipeType.ARMOR_FORGE,
        new ItemStack[] {new ItemStack(Material.SLIME_BALL), HoneymodItems.STEEL_PLATE, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), null, null, null}, null)
        .register(plugin);

        new HoneymodArmorPiece(categories.magicalArmor, HoneymodItems.SLIME_CHESTPLATE_STEEL, RecipeType.ARMOR_FORGE,
        new ItemStack[] {new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), HoneymodItems.STEEL_PLATE, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL)}, null)
        .register(plugin);

        new HoneymodArmorPiece(categories.magicalArmor, HoneymodItems.SLIME_LEGGINGS_STEEL, RecipeType.ARMOR_FORGE,
        new ItemStack[] {new ItemStack(Material.SLIME_BALL), HoneymodItems.STEEL_PLATE, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL)},
        new PotionEffect[] {new PotionEffect(PotionEffectType.SPEED, 300, 2)})
        .register(plugin);

        new LongFallBoots(categories.magicalArmor, HoneymodItems.SLIME_BOOTS_STEEL, RecipeType.ARMOR_FORGE,
        new ItemStack[] {null, null, null, new ItemStack(Material.SLIME_BALL), null, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), HoneymodItems.STEEL_PLATE, new ItemStack(Material.SLIME_BALL)},
        new PotionEffect[] {new PotionEffect(PotionEffectType.JUMP, 300, 5)})
        .register(plugin);

        new VampireBlade(categories.weapons, HoneymodItems.BLADE_OF_VAMPIRES, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, new ItemStack(Material.WITHER_SKELETON_SKULL), null, null, new ItemStack(Material.WITHER_SKELETON_SKULL), null, null, new ItemStack(Material.BLAZE_ROD), null})
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.GOLD_24K_BLOCK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.GOLD_24K, HoneymodItems.GOLD_24K, HoneymodItems.GOLD_24K, HoneymodItems.GOLD_24K, HoneymodItems.GOLD_24K, HoneymodItems.GOLD_24K, HoneymodItems.GOLD_24K, HoneymodItems.GOLD_24K, HoneymodItems.GOLD_24K})
        .register(plugin);

        new Composter(categories.basicMachines, HoneymodItems.COMPOSTER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.OAK_SLAB), null, new ItemStack(Material.OAK_SLAB), new ItemStack(Material.OAK_SLAB), null, new ItemStack(Material.OAK_SLAB), new ItemStack(Material.OAK_SLAB), new ItemStack(Material.CAULDRON), new ItemStack(Material.OAK_SLAB)})
        .register(plugin);

        new FarmerShoes(categories.magicalArmor, HoneymodItems.FARMER_SHOES, RecipeType.ARMOR_FORGE,
        new ItemStack[] {null, null, null, new ItemStack(Material.HAY_BLOCK), null, new ItemStack(Material.HAY_BLOCK), new ItemStack(Material.HAY_BLOCK), null, new ItemStack(Material.HAY_BLOCK)})
        .register(plugin);
        
        new ExplosivePickaxe(categories.tools, HoneymodItems.EXPLOSIVE_PICKAXE, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {new ItemStack(Material.TNT), HoneymodItems.SYNTHETIC_DIAMOND, new ItemStack(Material.TNT), null, HoneymodItems.FERROSILICON, null, null, HoneymodItems.FERROSILICON, null})
        .register(plugin);

        new ExplosiveShovel(categories.tools, HoneymodItems.EXPLOSIVE_SHOVEL, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.SYNTHETIC_DIAMOND, null, null, new ItemStack(Material.TNT), null, null, HoneymodItems.FERROSILICON, null})
        .register(plugin);

        new AutomatedPanningMachine(categories.basicMachines, HoneymodItems.AUTOMATED_PANNING_MACHINE).register(plugin);
        
        new IndustrialMiner(categories.basicMachines, HoneymodItems.INDUSTRIAL_MINER, Material.IRON_BLOCK, false, 3).register(plugin);
        new AdvancedIndustrialMiner(categories.basicMachines, HoneymodItems.ADVANCED_INDUSTRIAL_MINER).register(plugin);

        new StomperBoots(categories.magicalArmor, HoneymodItems.BOOTS_OF_THE_STOMPER, RecipeType.ARMOR_FORGE,
        new ItemStack[] {null, null, null, new ItemStack(Material.YELLOW_WOOL), null, new ItemStack(Material.YELLOW_WOOL), new ItemStack(Material.PISTON), null, new ItemStack(Material.PISTON)})
        .register(plugin);

        new PickaxeOfTheSeeker(categories.tools, HoneymodItems.PICKAXE_OF_THE_SEEKER, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {new ItemStack(Material.COMPASS), HoneymodItems.SYNTHETIC_DIAMOND, new ItemStack(Material.COMPASS), null, HoneymodItems.FERROSILICON, null, null, HoneymodItems.FERROSILICON, null})
        .register(plugin);

        new HoneymodBackpack(9, categories.usefulItems, HoneymodItems.BACKPACK_SMALL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.LEATHER), null, new ItemStack(Material.LEATHER), HoneymodItems.GOLD_6K, new ItemStack(Material.CHEST), HoneymodItems.GOLD_6K, new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER)})
        .register(plugin);

        new HoneymodBackpack(18, categories.usefulItems, HoneymodItems.BACKPACK_MEDIUM, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.LEATHER), null, new ItemStack(Material.LEATHER), HoneymodItems.GOLD_10K, HoneymodItems.BACKPACK_SMALL, HoneymodItems.GOLD_10K, new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER)})
        .register(plugin);

        new HoneymodBackpack(27, categories.usefulItems, HoneymodItems.BACKPACK_LARGE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.LEATHER), null, new ItemStack(Material.LEATHER), HoneymodItems.GOLD_14K, HoneymodItems.BACKPACK_MEDIUM, HoneymodItems.GOLD_14K, new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER), new ItemStack(Material.LEATHER)})
        .register(plugin);

        new HoneymodBackpack(36, categories.usefulItems, HoneymodItems.WOVEN_BACKPACK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CLOTH, null, HoneymodItems.CLOTH, HoneymodItems.GOLD_16K, HoneymodItems.BACKPACK_LARGE, HoneymodItems.GOLD_16K, HoneymodItems.CLOTH, HoneymodItems.CLOTH, HoneymodItems.CLOTH})
        .register(plugin);

        new Crucible(categories.basicMachines, HoneymodItems.CRUCIBLE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.TERRACOTTA), null, new ItemStack(Material.TERRACOTTA), new ItemStack(Material.TERRACOTTA), null, new ItemStack(Material.TERRACOTTA), new ItemStack(Material.TERRACOTTA), new ItemStack(Material.FLINT_AND_STEEL), new ItemStack(Material.TERRACOTTA)})
        .register(plugin);

        new HoneymodBackpack(45, categories.usefulItems, HoneymodItems.GILDED_BACKPACK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.GOLD_22K, null, HoneymodItems.GOLD_22K, new ItemStack(Material.LEATHER), HoneymodItems.WOVEN_BACKPACK, new ItemStack(Material.LEATHER), HoneymodItems.GOLD_22K, null, HoneymodItems.GOLD_22K})
        .register(plugin);

        new HoneymodBackpack(54, categories.usefulItems, HoneymodItems.RADIANT_BACKPACK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.GOLD_24K, null, HoneymodItems.GOLD_24K, new ItemStack(Material.LEATHER), HoneymodItems.GILDED_BACKPACK, new ItemStack(Material.LEATHER), HoneymodItems.GOLD_24K, null, HoneymodItems.GOLD_24K})
        .register(plugin);

        new RestoredBackpack(categories.usefulItems).register(plugin);

        new UnplaceableBlock(categories.technicalComponents, HoneymodItems.MAGNET, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.NICKEL_INGOT, HoneymodItems.ALUMINUM_DUST, HoneymodItems.IRON_DUST, HoneymodItems.COBALT_INGOT, null, null, null, null, null})
        .register(plugin);

        new InfusedMagnet(categories.magicalGadgets, HoneymodItems.INFUSED_MAGNET, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.ENDER_LUMP_2, HoneymodItems.MAGNET, HoneymodItems.ENDER_LUMP_2, HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3})
        .register(plugin);

        new SlimefunItem(categories.tools, HoneymodItems.COBALT_PICKAXE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.COBALT_INGOT, HoneymodItems.COBALT_INGOT, HoneymodItems.COBALT_INGOT, null, HoneymodItems.NICKEL_INGOT, null, null, HoneymodItems.NICKEL_INGOT, null})
        .register(plugin);

        new UnplaceableBlock(categories.magicalResources, HoneymodItems.NECROTIC_SKULL, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3, null, new ItemStack(Material.WITHER_SKELETON_SKULL), null, HoneymodItems.MAGIC_LUMP_3, null, HoneymodItems.MAGIC_LUMP_3})
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.ESSENCE_OF_AFTERLIFE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_3, HoneymodItems.AIR_RUNE, HoneymodItems.ENDER_LUMP_3, HoneymodItems.EARTH_RUNE, HoneymodItems.NECROTIC_SKULL, HoneymodItems.FIRE_RUNE, HoneymodItems.ENDER_LUMP_3, HoneymodItems.WATER_RUNE, HoneymodItems.ENDER_LUMP_3})
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.SYNTHETIC_SHULKER_SHELL, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_3, HoneymodItems.ENDER_RUNE, HoneymodItems.ENDER_LUMP_3, HoneymodItems.REINFORCED_PLATE, new ItemStack(Material.TURTLE_HELMET), HoneymodItems.REINFORCED_PLATE, HoneymodItems.ENDER_LUMP_3, HoneymodItems.ENDER_RUNE, HoneymodItems.ENDER_LUMP_3})
        .setUseableInWorkbench(true)
        .register(plugin);

        new SoulboundBackpack(36, categories.magicalGadgets, HoneymodItems.BOUND_BACKPACK, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_2, null, HoneymodItems.ENDER_LUMP_2, HoneymodItems.ESSENCE_OF_AFTERLIFE, HoneymodItems.WOVEN_BACKPACK, HoneymodItems.ESSENCE_OF_AFTERLIFE, HoneymodItems.ENDER_LUMP_2, null, HoneymodItems.ENDER_LUMP_2})
        .register(plugin);

        new JetBoots(categories.technicalGadgets, HoneymodItems.DURALUMIN_JETBOOTS,
        new ItemStack[] {null, null, null, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.35, 20)
        .register(plugin);

        new JetBoots(categories.technicalGadgets, HoneymodItems.SOLDER_JETBOOTS,
        new ItemStack[] {null, null, null, HoneymodItems.SOLDER_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.SOLDER_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.4, 30)
        .register(plugin);

        new JetBoots(categories.technicalGadgets, HoneymodItems.BILLON_JETBOOTS,
        new ItemStack[] {null, null, null, HoneymodItems.BILLON_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.BILLON_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.45, 40)
        .register(plugin);

        new JetBoots(categories.technicalGadgets, HoneymodItems.STEEL_JETBOOTS,
        new ItemStack[] {null, null, null, HoneymodItems.STEEL_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.STEEL_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.5, 50)
        .register(plugin);

        new JetBoots(categories.technicalGadgets, HoneymodItems.DAMASCUS_STEEL_JETBOOTS,
        new ItemStack[] {null, null, null, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.55, 75)
        .register(plugin);

        new JetBoots(categories.technicalGadgets, HoneymodItems.REINFORCED_ALLOY_JETBOOTS,
        new ItemStack[] {null, null, null, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.STEEL_THRUSTER, HoneymodItems.MEDIUM_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.6, 100)
        .register(plugin);

        new JetBoots(categories.technicalGadgets, HoneymodItems.CARBONADO_JETBOOTS,
        new ItemStack[] {null, null, null, HoneymodItems.CARBONADO, HoneymodItems.POWER_CRYSTAL, HoneymodItems.CARBONADO, HoneymodItems.STEEL_THRUSTER, HoneymodItems.LARGE_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.7, 125)
        .register(plugin);

        new JetBoots(categories.technicalGadgets, HoneymodItems.ARMORED_JETBOOTS,
        new ItemStack[] {null, null, null, HoneymodItems.STEEL_PLATE, HoneymodItems.POWER_CRYSTAL, HoneymodItems.STEEL_PLATE, HoneymodItems.STEEL_THRUSTER, HoneymodItems.MEDIUM_CAPACITOR, HoneymodItems.STEEL_THRUSTER},
        0.45, 50)
        .register(plugin);

        new SeismicAxe(categories.weapons, HoneymodItems.SEISMIC_AXE, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.HARDENED_METAL_INGOT, null, HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.STAFF_ELEMENTAL, null, null, HoneymodItems.STAFF_ELEMENTAL, null})
        .register(plugin);

        new PickaxeOfVeinMining(categories.tools, HoneymodItems.PICKAXE_OF_VEIN_MINING, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {new ItemStack(Material.EMERALD_ORE), HoneymodItems.SYNTHETIC_DIAMOND, new ItemStack(Material.EMERALD_ORE), null, HoneymodItems.GILDED_IRON, null, null, HoneymodItems.GILDED_IRON, null})
        .register(plugin);
      
        new ClimbingPick(categories.tools, HoneymodItems.CLIMBING_PICK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.STEEL_INGOT, HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.STEEL_INGOT, null, new ItemStack(Material.STICK), null, null, new ItemStack(Material.STICK), null})
        .register(plugin);

        new SoulboundItem(categories.weapons, HoneymodItems.SOULBOUND_SWORD, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_SWORD), null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);
        
        new SoulboundItem(categories.weapons, HoneymodItems.SOULBOUND_TRIDENT, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.TRIDENT), null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);

        new SoulboundItem(categories.weapons, HoneymodItems.SOULBOUND_BOW, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.BOW), null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);

        new SoulboundItem(categories.tools, HoneymodItems.SOULBOUND_PICKAXE, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_PICKAXE), null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);

        new SoulboundItem(categories.tools, HoneymodItems.SOULBOUND_AXE, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_AXE), null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);

        new SoulboundItem(categories.tools, HoneymodItems.SOULBOUND_SHOVEL, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_SHOVEL), null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);

        new SoulboundItem(categories.tools, HoneymodItems.SOULBOUND_HOE, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_HOE), null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);

        new SoulboundItem(categories.magicalArmor, HoneymodItems.SOULBOUND_HELMET, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_HELMET), null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);

        new SoulboundItem(categories.magicalArmor, HoneymodItems.SOULBOUND_CHESTPLATE, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_CHESTPLATE), null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);

        new SoulboundItem(categories.magicalArmor, HoneymodItems.SOULBOUND_LEGGINGS, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_LEGGINGS), null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);

        new SoulboundItem(categories.magicalArmor, HoneymodItems.SOULBOUND_BOOTS, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null, null, new ItemStack(Material.DIAMOND_BOOTS), null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);

        new Juicer(categories.basicMachines, HoneymodItems.JUICER).register(plugin);

        new Juice(categories.food, HoneymodItems.APPLE_JUICE, RecipeType.JUICER,
        new ItemStack[] {new ItemStack(Material.APPLE), null, null, null, null, null, null, null, null})
        .register(plugin);

        new Juice(categories.food, HoneymodItems.CARROT_JUICE, RecipeType.JUICER,
        new ItemStack[] {new ItemStack(Material.CARROT), null, null, null, null, null, null, null, null})
        .register(plugin);

        new Juice(categories.food, HoneymodItems.MELON_JUICE, RecipeType.JUICER,
        new ItemStack[] {new ItemStack(Material.MELON_SLICE), null, null, null, null, null, null, null, null})
        .register(plugin);

        new Juice(categories.food, HoneymodItems.PUMPKIN_JUICE, RecipeType.JUICER,
        new ItemStack[] {new ItemStack(Material.PUMPKIN), null, null, null, null, null, null, null, null})
        .register(plugin);

        new Juice(categories.food, HoneymodItems.SWEET_BERRY_JUICE, RecipeType.JUICER,
        new ItemStack[] {new ItemStack(Material.SWEET_BERRIES), null, null, null, null, null, null, null, null})
        .register(plugin);

        new Juice(categories.food, HoneymodItems.GOLDEN_APPLE_JUICE, RecipeType.JUICER,
        new ItemStack[] {new ItemStack(Material.GOLDEN_APPLE), null, null, null, null, null, null, null, null})
        .register(plugin);

        new BrokenSpawner(categories.magicalResources, HoneymodItems.BROKEN_SPAWNER, new RecipeType(new NamespacedKey(plugin, "pickaxe_of_containment"), HoneymodItems.PICKAXE_OF_CONTAINMENT),
        new ItemStack[] {null, null, null, null, new ItemStack(Material.SPAWNER), null, null, null, null})
        .register(plugin);

        new RepairedSpawner(categories.magicalGadgets, HoneymodItems.REPAIRED_SPAWNER, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {HoneymodItems.ENDER_RUNE, HoneymodItems.FILLED_FLASK_OF_KNOWLEDGE, HoneymodItems.ESSENCE_OF_AFTERLIFE, HoneymodItems.FILLED_FLASK_OF_KNOWLEDGE, HoneymodItems.BROKEN_SPAWNER, HoneymodItems.FILLED_FLASK_OF_KNOWLEDGE, HoneymodItems.ESSENCE_OF_AFTERLIFE, HoneymodItems.FILLED_FLASK_OF_KNOWLEDGE, HoneymodItems.ENDER_RUNE})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 1, 1, 1, HoneymodItems.ENHANCED_FURNACE,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.BASIC_CIRCUIT_BOARD, new ItemStack(Material.FURNACE), HoneymodItems.HEATING_COIL, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 2, 1, 1, HoneymodItems.ENHANCED_FURNACE_2,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.ENHANCED_FURNACE, HoneymodItems.HEATING_COIL, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 2, 2, 1, HoneymodItems.ENHANCED_FURNACE_3,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.ENHANCED_FURNACE_2, HoneymodItems.HEATING_COIL, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 3, 2, 1, HoneymodItems.ENHANCED_FURNACE_4,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.ENHANCED_FURNACE_3, HoneymodItems.HEATING_COIL, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 3, 2, 2, HoneymodItems.ENHANCED_FURNACE_5,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.ENHANCED_FURNACE_4, HoneymodItems.HEATING_COIL, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 3, 3, 2, HoneymodItems.ENHANCED_FURNACE_6,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.ENHANCED_FURNACE_5, HoneymodItems.HEATING_COIL, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 4, 3, 2, HoneymodItems.ENHANCED_FURNACE_7,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.ENHANCED_FURNACE_6, HoneymodItems.HEATING_COIL, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 4, 4, 2, HoneymodItems.ENHANCED_FURNACE_8,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.ENHANCED_FURNACE_7, HoneymodItems.HEATING_COIL, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 5, 4, 2, HoneymodItems.ENHANCED_FURNACE_9,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.ENHANCED_FURNACE_8, HoneymodItems.HEATING_COIL, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 5, 5, 2, HoneymodItems.ENHANCED_FURNACE_10,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.ENHANCED_FURNACE_9, HoneymodItems.HEATING_COIL, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 5, 5, 3, HoneymodItems.ENHANCED_FURNACE_11,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.ENHANCED_FURNACE_10, HoneymodItems.HEATING_COIL, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 10, 10, 3, HoneymodItems.REINFORCED_FURNACE,
        new ItemStack[] {HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.HEATING_COIL, HoneymodItems.ENHANCED_FURNACE_11, HoneymodItems.HEATING_COIL, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.REINFORCED_ALLOY_INGOT})
        .register(plugin);

        new EnhancedFurnace(categories.basicMachines, 20, 10, 3, HoneymodItems.CARBONADO_EDGED_FURNACE,
        new ItemStack[] {HoneymodItems.CARBONADO, HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.CARBONADO, HoneymodItems.HEATING_COIL, HoneymodItems.REINFORCED_FURNACE, HoneymodItems.HEATING_COIL, HoneymodItems.CARBONADO, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.CARBONADO})
        .register(plugin);

        new UnplaceableBlock(categories.technicalComponents, HoneymodItems.ELECTRO_MAGNET, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.NICKEL_INGOT, HoneymodItems.MAGNET, HoneymodItems.COBALT_INGOT, null, HoneymodItems.BATTERY, null, null, null, null})
        .register(plugin);

        new UnplaceableBlock(categories.technicalComponents, HoneymodItems.ELECTRIC_MOTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.COPPER_WIRE, HoneymodItems.COPPER_WIRE, HoneymodItems.COPPER_WIRE, null, HoneymodItems.ELECTRO_MAGNET, null, HoneymodItems.COPPER_WIRE, HoneymodItems.COPPER_WIRE, HoneymodItems.COPPER_WIRE})
        .register(plugin);

        new UnplaceableBlock(categories.technicalComponents, HoneymodItems.HEATING_COIL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.COPPER_WIRE, HoneymodItems.COPPER_WIRE, HoneymodItems.COPPER_WIRE, HoneymodItems.COPPER_WIRE, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.COPPER_WIRE, HoneymodItems.COPPER_WIRE, HoneymodItems.COPPER_WIRE, HoneymodItems.COPPER_WIRE})
        .register(plugin);

        new SlimefunItem(categories.technicalComponents, HoneymodItems.COPPER_WIRE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, null, HoneymodItems.COPPER_INGOT, HoneymodItems.COPPER_INGOT, HoneymodItems.COPPER_INGOT, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.COPPER_WIRE, 8))
        .register(plugin);
        
        new BlockPlacer(categories.basicMachines, HoneymodItems.BLOCK_PLACER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.GOLD_4K, new ItemStack(Material.PISTON), HoneymodItems.GOLD_4K, new ItemStack(Material.IRON_INGOT), HoneymodItems.ELECTRIC_MOTOR, new ItemStack(Material.IRON_INGOT), HoneymodItems.GOLD_4K, new ItemStack(Material.PISTON), HoneymodItems.GOLD_4K})
        .register(plugin);

        new TelepositionScroll(categories.magicalGadgets, HoneymodItems.SCROLL_OF_DIMENSIONAL_TELEPOSITION, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, HoneymodItems.ENDER_LUMP_3, HoneymodItems.MAGIC_EYE_OF_ENDER, HoneymodItems.ENDER_LUMP_3, HoneymodItems.MAGICAL_BOOK_COVER, HoneymodItems.ENDER_LUMP_3, HoneymodItems.MAGIC_EYE_OF_ENDER, HoneymodItems.ENDER_LUMP_3, null})
        .register(plugin);

        new ExplosiveBow(categories.weapons, HoneymodItems.EXPLOSIVE_BOW,
        new ItemStack[] {null, new ItemStack(Material.STICK), new ItemStack(Material.GUNPOWDER), HoneymodItems.STAFF_FIRE, null, HoneymodItems.SULFATE, null, new ItemStack(Material.STICK), new ItemStack(Material.GUNPOWDER)})
        .register(plugin);

        new IcyBow(categories.weapons, HoneymodItems.ICY_BOW,
        new ItemStack[] {null, new ItemStack(Material.STICK), new ItemStack(Material.ICE), HoneymodItems.STAFF_WATER, null, new ItemStack(Material.PACKED_ICE), null, new ItemStack(Material.STICK), new ItemStack(Material.ICE)})
        .register(plugin);

        new KnowledgeTome(categories.magicalGadgets, HoneymodItems.TOME_OF_KNOWLEDGE_SHARING, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, new ItemStack(Material.FEATHER), null, new ItemStack(Material.INK_SAC), HoneymodItems.MAGICAL_BOOK_COVER, new ItemStack(Material.GLASS_BOTTLE), null, new ItemStack(Material.WRITABLE_BOOK), null})
        .register(plugin);

        new KnowledgeFlask(categories.magicalGadgets, HoneymodItems.FLASK_OF_KNOWLEDGE, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, null, null, HoneymodItems.MAGIC_LUMP_2, new ItemStack(Material.GLASS_PANE), HoneymodItems.MAGIC_LUMP_2, null, HoneymodItems.MAGIC_LUMP_2, null}, 
        new SlimefunItemStack(HoneymodItems.FLASK_OF_KNOWLEDGE, 8))
        .register(plugin);

        new BirthdayCake(categories.birthday, new SlimefunItemStack("BIRTHDAY_CAKE", Material.CAKE, "&bBirthday Cake"), RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.TORCH), null, new ItemStack(Material.SUGAR), new ItemStack(Material.CAKE), new ItemStack(Material.SUGAR), null, null, null})
        .register(plugin);

        new Juice(categories.christmas, HoneymodItems.CHRISTMAS_MILK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.MILK_BUCKET), new ItemStack(Material.GLASS_BOTTLE), null, null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_MILK, 4))
        .register(plugin);

        new Juice(categories.christmas, HoneymodItems.CHRISTMAS_CHOCOLATE_MILK, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CHRISTMAS_MILK, new ItemStack(Material.COCOA_BEANS), null, null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_CHOCOLATE_MILK, 2))
        .register(plugin);

        new Juice(categories.christmas, HoneymodItems.CHRISTMAS_EGG_NOG, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CHRISTMAS_MILK, new ItemStack(Material.EGG), null, null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_EGG_NOG, 2))
        .register(plugin);

        new Juice(categories.christmas, HoneymodItems.CHRISTMAS_APPLE_CIDER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.APPLE_JUICE, new ItemStack(Material.SUGAR), null, null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_APPLE_CIDER, 2))
        .register(plugin);

        new SlimefunItem(categories.christmas, HoneymodItems.CHRISTMAS_COOKIE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.COOKIE), new ItemStack(Material.SUGAR), new ItemStack(Material.LIME_DYE), null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_COOKIE, 16))
        .register(plugin);

        new SlimefunItem(categories.christmas, HoneymodItems.CHRISTMAS_FRUIT_CAKE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.EGG), new ItemStack(Material.APPLE), new ItemStack(Material.MELON), new ItemStack(Material.SUGAR), null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_FRUIT_CAKE, 4))
        .register(plugin);

        new SlimefunItem(categories.christmas, HoneymodItems.CHRISTMAS_APPLE_PIE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.SUGAR), new ItemStack(Material.APPLE), new ItemStack(Material.EGG), null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_APPLE_PIE, 2))
        .register(plugin);

        new Juice(categories.christmas, HoneymodItems.CHRISTMAS_HOT_CHOCOLATE, RecipeType.SMELTERY,
        new ItemStack[] {HoneymodItems.CHRISTMAS_CHOCOLATE_MILK, null, null, null, null, null, null, null, null}, HoneymodItems.CHRISTMAS_HOT_CHOCOLATE)
        .register(plugin);

        new SlimefunItem(categories.christmas, HoneymodItems.CHRISTMAS_CAKE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.EGG), new ItemStack(Material.SUGAR), HoneymodItems.WHEAT_FLOUR, new ItemStack(Material.MILK_BUCKET), null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_CAKE, 4))
        .register(plugin);

        new SlimefunItem(categories.christmas, HoneymodItems.CHRISTMAS_CARAMEL, RecipeType.SMELTERY,
        new ItemStack[] {new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), null, null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_CARAMEL, 4))
        .register(plugin);

        new SlimefunItem(categories.christmas, HoneymodItems.CHRISTMAS_CARAMEL_APPLE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.CHRISTMAS_CARAMEL, null, null, new ItemStack(Material.APPLE), null, null, new ItemStack(Material.STICK), null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_CARAMEL_APPLE, 2))
        .register(plugin);

        new SlimefunItem(categories.christmas, HoneymodItems.CHRISTMAS_CHOCOLATE_APPLE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.COCOA_BEANS), null, null, new ItemStack(Material.APPLE), null, null, new ItemStack(Material.STICK), null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_CHOCOLATE_APPLE, 2))
        .register(plugin);

        new ChristmasPresent(categories.christmas, HoneymodItems.CHRISTMAS_PRESENT, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, new ItemStack(Material.NAME_TAG), null, new ItemStack(Material.RED_WOOL), new ItemStack(Material.GREEN_WOOL), new ItemStack(Material.RED_WOOL), new ItemStack(Material.RED_WOOL), new ItemStack(Material.GREEN_WOOL), new ItemStack(Material.RED_WOOL)},
            new SlimefunItemStack(HoneymodItems.CHRISTMAS_HOT_CHOCOLATE, 1),
            new SlimefunItemStack(HoneymodItems.CHRISTMAS_CHOCOLATE_APPLE, 4),
            new SlimefunItemStack(HoneymodItems.CHRISTMAS_CARAMEL_APPLE, 4),
            new SlimefunItemStack(HoneymodItems.CHRISTMAS_CAKE, 4),
            new SlimefunItemStack(HoneymodItems.CHRISTMAS_COOKIE, 8),
            new SlimefunItemStack(HoneymodItems.CHRISTMAS_PRESENT, 1),
            new SlimefunItemStack(HoneymodItems.CHRISTMAS_EGG_NOG, 1),
            new SlimefunItemStack(HoneymodItems.CHRISTMAS_MILK, 1),
            new SlimefunItemStack(HoneymodItems.CHRISTMAS_APPLE_CIDER, 1),
            new SlimefunItemStack(HoneymodItems.CHRISTMAS_FRUIT_CAKE, 4),
            new SlimefunItemStack(HoneymodItems.CHRISTMAS_APPLE_PIE, 4),
            new ItemStack(Material.EMERALD)
        ).register(plugin);

        new SlimefunItem(categories.easter, HoneymodItems.EASTER_CARROT_PIE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.SUGAR), new ItemStack(Material.CARROT), new ItemStack(Material.EGG), null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.EASTER_CARROT_PIE, 2))
        .register(plugin);

        new SlimefunItem(categories.easter, HoneymodItems.EASTER_APPLE_PIE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.SUGAR), new ItemStack(Material.APPLE), new ItemStack(Material.EGG), null, null, null, null, null, null}, 
        new SlimefunItemStack(HoneymodItems.CHRISTMAS_APPLE_PIE, 2))
        .register(plugin);

        new EasterEgg(categories.easter, HoneymodItems.EASTER_EGG, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, null, new ItemStack(Material.LIME_DYE), new ItemStack(Material.EGG), new ItemStack(Material.PURPLE_DYE), null, null, null}, 
        new SlimefunItemStack(HoneymodItems.EASTER_EGG, 2),
        // Gifts:
            new SlimefunItemStack(HoneymodItems.EASTER_CARROT_PIE, 4),
            new SlimefunItemStack(HoneymodItems.CARROT_JUICE, 1),
            new ItemStack(Material.EMERALD),
            new ItemStack(Material.CAKE),
            new ItemStack(Material.RABBIT_FOOT),
            new ItemStack(Material.GOLDEN_CARROT, 4)
        ).register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.REINFORCED_PLATE, RecipeType.COMPRESSOR,
        new ItemStack[] {new SlimefunItemStack(HoneymodItems.REINFORCED_ALLOY_INGOT, 8), null, null, null, null, null, null, null, null})
        .register(plugin);

        new HardenedGlass(categories.technicalComponents, HoneymodItems.HARDENED_GLASS, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), HoneymodItems.REINFORCED_PLATE, new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS), new ItemStack(Material.GLASS)},
        new SlimefunItemStack(HoneymodItems.HARDENED_GLASS, 16))
        .register(plugin);

        new UnplaceableBlock(categories.technicalComponents, HoneymodItems.COOLING_UNIT, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.ICE), new ItemStack(Material.ICE), new ItemStack(Material.ICE), HoneymodItems.ALUMINUM_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.ALUMINUM_INGOT, new ItemStack(Material.ICE), new ItemStack(Material.ICE), new ItemStack(Material.ICE)})
        .register(plugin);

        new Cooler(27, categories.usefulItems, HoneymodItems.COOLER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CLOTH, HoneymodItems.CLOTH, HoneymodItems.CLOTH, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.COOLING_UNIT, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.ALUMINUM_INGOT})
        .register(plugin);

        new WitherProofBlock(categories.technicalComponents, HoneymodItems.WITHER_PROOF_OBSIDIAN, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.LEAD_INGOT, new ItemStack(Material.OBSIDIAN), HoneymodItems.LEAD_INGOT, new ItemStack(Material.OBSIDIAN), HoneymodItems.HARDENED_GLASS, new ItemStack(Material.OBSIDIAN), HoneymodItems.LEAD_INGOT, new ItemStack(Material.OBSIDIAN), HoneymodItems.LEAD_INGOT},
        new SlimefunItemStack(HoneymodItems.WITHER_PROOF_OBSIDIAN, 4))
        .register(plugin);

        new AncientPedestal(categories.magicalResources, HoneymodItems.ANCIENT_PEDESTAL, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {new ItemStack(Material.OBSIDIAN), HoneymodItems.GOLD_8K, new ItemStack(Material.OBSIDIAN), null, new ItemStack(Material.STONE), null, new ItemStack(Material.OBSIDIAN), HoneymodItems.GOLD_8K, new ItemStack(Material.OBSIDIAN)}, 
        new SlimefunItemStack(HoneymodItems.ANCIENT_PEDESTAL, 4))
        .register(plugin);

        new AncientAltar(categories.magicalGadgets, 8, HoneymodItems.ANCIENT_ALTAR, RecipeType.MAGIC_WORKBENCH,
        new ItemStack[] {null, new ItemStack(Material.ENCHANTING_TABLE), null, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.GOLD_8K, HoneymodItems.MAGIC_LUMP_3, new ItemStack(Material.OBSIDIAN), HoneymodItems.GOLD_8K, new ItemStack(Material.OBSIDIAN)})
        .register(plugin);

        new EnergyRegulator(categories.electricity, HoneymodItems.ENERGY_REGULATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SILVER_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.SILVER_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.SILVER_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.SILVER_INGOT})
        .register(plugin);

        new EnergyConnector(categories.electricity, HoneymodItems.ENERGY_CONNECTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CARBON, HoneymodItems.COPPER_WIRE, HoneymodItems.CARBON, HoneymodItems.COPPER_WIRE, new ItemStack(Material.REDSTONE_BLOCK), HoneymodItems.COPPER_WIRE, HoneymodItems.CARBON, HoneymodItems.COPPER_WIRE, HoneymodItems.CARBON},
        new SlimefunItemStack(HoneymodItems.ENERGY_CONNECTOR, 8))
        .register(plugin);

        new SlimefunItem(categories.misc, HoneymodItems.DUCT_TAPE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.ALUMINUM_DUST, HoneymodItems.ALUMINUM_DUST, HoneymodItems.ALUMINUM_DUST, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.PAPER), new ItemStack(Material.PAPER), new ItemStack(Material.PAPER)}, 
        new SlimefunItemStack(HoneymodItems.DUCT_TAPE, 2))
        .register(plugin);

        new Capacitor(categories.electricity, 128, HoneymodItems.SMALL_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.DURALUMIN_INGOT, HoneymodItems.SULFATE, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.ENERGY_CONNECTOR, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.DURALUMIN_INGOT, new ItemStack(Material.REDSTONE), HoneymodItems.DURALUMIN_INGOT})
        .register(plugin);

        new Capacitor(categories.electricity, 512, HoneymodItems.MEDIUM_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.BILLON_INGOT, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.BILLON_INGOT, new ItemStack(Material.REDSTONE), HoneymodItems.SMALL_CAPACITOR, new ItemStack(Material.REDSTONE), HoneymodItems.BILLON_INGOT, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.BILLON_INGOT})
        .register(plugin);

        new Capacitor(categories.electricity, 1024, HoneymodItems.BIG_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.STEEL_INGOT, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.STEEL_INGOT, new ItemStack(Material.REDSTONE), HoneymodItems.MEDIUM_CAPACITOR, new ItemStack(Material.REDSTONE), HoneymodItems.STEEL_INGOT, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.STEEL_INGOT})
        .register(plugin);

        new Capacitor(categories.electricity, 8192, HoneymodItems.LARGE_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.REDSTONE), HoneymodItems.BIG_CAPACITOR, new ItemStack(Material.REDSTONE), HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.REINFORCED_ALLOY_INGOT})
        .register(plugin);

        new Capacitor(categories.electricity, 65536, HoneymodItems.CARBONADO_EDGED_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CARBONADO, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.CARBONADO, new ItemStack(Material.REDSTONE), HoneymodItems.LARGE_CAPACITOR, new ItemStack(Material.REDSTONE), HoneymodItems.CARBONADO, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.CARBONADO})
        .register(plugin);

        new Capacitor(categories.electricity, 524288, HoneymodItems.ENERGIZED_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CARBONADO, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.CARBONADO, new ItemStack(Material.NETHER_STAR), HoneymodItems.CARBONADO_EDGED_CAPACITOR, new ItemStack(Material.NETHER_STAR), HoneymodItems.CARBONADO, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.CARBONADO})
        .register(plugin);

        new SolarGenerator(categories.electricity, 2, 0, HoneymodItems.SOLAR_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SOLAR_PANEL, HoneymodItems.SOLAR_PANEL, HoneymodItems.SOLAR_PANEL, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.ALUMINUM_INGOT, null, HoneymodItems.ALUMINUM_INGOT, null})
        .register(plugin);

        new SolarGenerator(categories.electricity, 8, 0, HoneymodItems.SOLAR_GENERATOR_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SOLAR_GENERATOR, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.SOLAR_GENERATOR, HoneymodItems.ALUMINUM_INGOT, new ItemStack(Material.REDSTONE), HoneymodItems.ALUMINUM_INGOT, HoneymodItems.SOLAR_GENERATOR, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.SOLAR_GENERATOR})
        .register(plugin);

        new SolarGenerator(categories.electricity, 32, 0, HoneymodItems.SOLAR_GENERATOR_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SOLAR_GENERATOR_2, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.SOLAR_GENERATOR_2, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.CARBONADO, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.SOLAR_GENERATOR_2, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.SOLAR_GENERATOR_2})
        .register(plugin);

        new SolarGenerator(categories.electricity, 128, 64, HoneymodItems.SOLAR_GENERATOR_4, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SOLAR_GENERATOR_3, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.SOLAR_GENERATOR_3, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.SOLAR_GENERATOR_3, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.SOLAR_GENERATOR_3})
        .register(plugin);
        
        new ChargingBench(categories.electricity, HoneymodItems.CHARGING_BENCH, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.ELECTRO_MAGNET, null, HoneymodItems.BATTERY, new ItemStack(Material.CRAFTING_TABLE), HoneymodItems.BATTERY, null, HoneymodItems.SMALL_CAPACITOR, null})
        .setCapacity(128)
        .setEnergyConsumption(10)
        .setProcessingSpeed(1)
        .register(plugin);

        new ElectricFurnace(categories.electricity, HoneymodItems.ELECTRIC_FURNACE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.FURNACE), null, HoneymodItems.GILDED_IRON, HoneymodItems.HEATING_COIL, HoneymodItems.GILDED_IRON, HoneymodItems.GILDED_IRON, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.GILDED_IRON})
        .setCapacity(64)
        .setEnergyConsumption(2)
        .setProcessingSpeed(1)
        .register(plugin);

        new ElectricFurnace(categories.electricity, HoneymodItems.ELECTRIC_FURNACE_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.ELECTRIC_MOTOR, null, HoneymodItems.GILDED_IRON, HoneymodItems.ELECTRIC_FURNACE, HoneymodItems.GILDED_IRON, HoneymodItems.GILDED_IRON, HoneymodItems.HEATING_COIL, HoneymodItems.GILDED_IRON})
        .setCapacity(128)
        .setEnergyConsumption(3)
        .setProcessingSpeed(2)
        .register(plugin);

        new ElectricFurnace(categories.electricity, HoneymodItems.ELECTRIC_FURNACE_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.ELECTRIC_MOTOR, null, HoneymodItems.STEEL_INGOT, HoneymodItems.ELECTRIC_FURNACE_2, HoneymodItems.STEEL_INGOT, HoneymodItems.GILDED_IRON, HoneymodItems.HEATING_COIL, HoneymodItems.GILDED_IRON})
        .setCapacity(128)
        .setEnergyConsumption(5)
        .setProcessingSpeed(4)
        .register(plugin);

        new ElectricGoldPan(categories.electricity, HoneymodItems.ELECTRIC_GOLD_PAN, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.GOLD_PAN, null, new ItemStack(Material.FLINT), HoneymodItems.ELECTRIC_MOTOR, new ItemStack(Material.FLINT), HoneymodItems.ALUMINUM_INGOT, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.ALUMINUM_INGOT})
        .setCapacity(128)
        .setEnergyConsumption(1)
        .setProcessingSpeed(1)
        .register(plugin);

        new ElectricGoldPan(categories.electricity, HoneymodItems.ELECTRIC_GOLD_PAN_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.GOLD_PAN, null, new ItemStack(Material.IRON_INGOT), HoneymodItems.ELECTRIC_GOLD_PAN, new ItemStack(Material.IRON_INGOT), HoneymodItems.DURALUMIN_INGOT, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.DURALUMIN_INGOT})
        .setCapacity(128)
        .setEnergyConsumption(2)
        .setProcessingSpeed(3)
        .register(plugin);

        new ElectricGoldPan(categories.electricity, HoneymodItems.ELECTRIC_GOLD_PAN_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.GOLD_PAN, null, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.ELECTRIC_GOLD_PAN_2, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.COBALT_INGOT, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.COBALT_INGOT})
        .setCapacity(512)
        .setEnergyConsumption(7)
        .setProcessingSpeed(10)
        .register(plugin);

        new ElectricDustWasher(categories.electricity, HoneymodItems.ELECTRIC_DUST_WASHER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.WATER_BUCKET), null, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.ELECTRIC_GOLD_PAN, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.COPPER_INGOT, HoneymodItems.COPPER_INGOT, HoneymodItems.COPPER_INGOT})
        .setCapacity(128)
        .setEnergyConsumption(3)
        .setProcessingSpeed(1)
        .register(plugin);

        new ElectricDustWasher(categories.electricity, HoneymodItems.ELECTRIC_DUST_WASHER_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.WATER_BUCKET), null, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.ELECTRIC_DUST_WASHER, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT})
        .setCapacity(128)
        .setEnergyConsumption(5)
        .setProcessingSpeed(2)
        .register(plugin);

        new ElectricDustWasher(categories.electricity, HoneymodItems.ELECTRIC_DUST_WASHER_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.WATER_BUCKET), null, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.ELECTRIC_DUST_WASHER_2, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.CORINTHIAN_BRONZE_INGOT})
        .setCapacity(512)
        .setEnergyConsumption(15)
        .setProcessingSpeed(10)
        .register(plugin);

        new ElectricIngotFactory(categories.electricity, HoneymodItems.ELECTRIC_INGOT_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.FLINT_AND_STEEL), null, HoneymodItems.HEATING_COIL, HoneymodItems.ELECTRIC_DUST_WASHER, HoneymodItems.HEATING_COIL, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.DAMASCUS_STEEL_INGOT})
        .setCapacity(256)
        .setEnergyConsumption(4)
        .setProcessingSpeed(1)
        .register(plugin);

        new ElectricIngotFactory(categories.electricity, HoneymodItems.ELECTRIC_INGOT_FACTORY_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.GILDED_IRON, new ItemStack(Material.FLINT_AND_STEEL), HoneymodItems.GILDED_IRON, HoneymodItems.HEATING_COIL, HoneymodItems.ELECTRIC_INGOT_FACTORY, HoneymodItems.HEATING_COIL, HoneymodItems.BRASS_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.BRASS_INGOT})
        .setCapacity(256)
        .setEnergyConsumption(7)
        .setProcessingSpeed(2)
        .register(plugin);

        new ElectricIngotFactory(categories.electricity, HoneymodItems.ELECTRIC_INGOT_FACTORY_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.GILDED_IRON, new ItemStack(Material.FLINT_AND_STEEL), HoneymodItems.GILDED_IRON, HoneymodItems.HEATING_COIL, HoneymodItems.ELECTRIC_INGOT_FACTORY_2, HoneymodItems.HEATING_COIL, HoneymodItems.BRASS_INGOT, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.BRASS_INGOT})
        .setCapacity(512)
        .setEnergyConsumption(20)
        .setProcessingSpeed(8)
        .register(plugin);

        new ElectrifiedCrucible(categories.electricity, HoneymodItems.ELECTRIFIED_CRUCIBLE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.LEAD_INGOT, HoneymodItems.CRUCIBLE, HoneymodItems.LEAD_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.HEATING_COIL, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.LEAD_INGOT, HoneymodItems.LARGE_CAPACITOR, HoneymodItems.LEAD_INGOT})
        .setCapacity(1024)
        .setEnergyConsumption(24)
        .setProcessingSpeed(1)
        .register(plugin);

        new ElectrifiedCrucible(categories.electricity, HoneymodItems.ELECTRIFIED_CRUCIBLE_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.ELECTRIFIED_CRUCIBLE, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.LEAD_INGOT, HoneymodItems.HEATING_COIL, HoneymodItems.LEAD_INGOT})
        .setCapacity(1024)
        .setEnergyConsumption(40)
        .setProcessingSpeed(2)
        .register(plugin);

        new ElectrifiedCrucible(categories.electricity, HoneymodItems.ELECTRIFIED_CRUCIBLE_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.ELECTRIFIED_CRUCIBLE_2, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.STEEL_PLATE, HoneymodItems.POWER_CRYSTAL, HoneymodItems.STEEL_PLATE, HoneymodItems.LEAD_INGOT, HoneymodItems.LEAD_INGOT, HoneymodItems.LEAD_INGOT})
        .setCapacity(1024)
        .setEnergyConsumption(60)
        .setProcessingSpeed(4)
        .register(plugin);

        new ElectricOreGrinder(categories.electricity, HoneymodItems.ELECTRIC_ORE_GRINDER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.DIAMOND_PICKAXE), null, HoneymodItems.GILDED_IRON, HoneymodItems.HEATING_COIL, HoneymodItems.GILDED_IRON, HoneymodItems.GILDED_IRON, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.GILDED_IRON})
        .setCapacity(128)
        .setEnergyConsumption(6)
        .setProcessingSpeed(1)
        .register(plugin);

        new ElectricOreGrinder(categories.electricity, HoneymodItems.ELECTRIC_ORE_GRINDER_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.DIAMOND_PICKAXE), null, HoneymodItems.HEATING_COIL, HoneymodItems.ELECTRIC_ORE_GRINDER, HoneymodItems.HEATING_COIL, HoneymodItems.GILDED_IRON, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.GILDED_IRON})
        .setCapacity(512)
        .setEnergyConsumption(15)
        .setProcessingSpeed(4)
        .register(plugin);

        new ElectricOreGrinder(categories.electricity, HoneymodItems.ELECTRIC_ORE_GRINDER_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.REINFORCED_PLATE, HoneymodItems.HEATING_COIL, HoneymodItems.REINFORCED_PLATE, null, HoneymodItems.ELECTRIC_ORE_GRINDER_2, null, HoneymodItems.REINFORCED_PLATE, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.REINFORCED_PLATE})
        .setCapacity(1024)
        .setEnergyConsumption(45)
        .setProcessingSpeed(10)
        .register(plugin);

        new HeatedPressureChamber(categories.electricity, HoneymodItems.HEATED_PRESSURE_CHAMBER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.LEAD_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.LEAD_INGOT, HoneymodItems.LEAD_INGOT, new ItemStack(Material.GLASS), HoneymodItems.LEAD_INGOT, HoneymodItems.LEAD_INGOT, HoneymodItems.HEATING_COIL, HoneymodItems.LEAD_INGOT})
        .setCapacity(128)
        .setEnergyConsumption(5)
        .setProcessingSpeed(1)
        .register(plugin);

        new HeatedPressureChamber(categories.electricity, HoneymodItems.HEATED_PRESSURE_CHAMBER_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.LEAD_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.LEAD_INGOT, HoneymodItems.LEAD_INGOT, HoneymodItems.HEATED_PRESSURE_CHAMBER, HoneymodItems.LEAD_INGOT, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.HEATING_COIL, HoneymodItems.REINFORCED_ALLOY_INGOT})
        .setCapacity(256)
        .setEnergyConsumption(22)
        .setProcessingSpeed(5)
        .register(plugin);

        new ElectricIngotPulverizer(categories.electricity, HoneymodItems.ELECTRIC_INGOT_PULVERIZER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.ELECTRIC_ORE_GRINDER, null, HoneymodItems.LEAD_INGOT, HoneymodItems.MEDIUM_CAPACITOR, HoneymodItems.LEAD_INGOT, HoneymodItems.LEAD_INGOT, HoneymodItems.HEATING_COIL, HoneymodItems.LEAD_INGOT})
        .setCapacity(512)
        .setEnergyConsumption(7)
        .setProcessingSpeed(1)
        .register(plugin);

        new CoalGenerator(categories.electricity, HoneymodItems.COAL_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.HEATING_COIL, new ItemStack(Material.FURNACE), HoneymodItems.HEATING_COIL, HoneymodItems.NICKEL_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.NICKEL_INGOT, null, HoneymodItems.NICKEL_INGOT, null})
        .setCapacity(64)
        .setEnergyProduction(8)
        .register(plugin);

        new CoalGenerator(categories.electricity, HoneymodItems.COAL_GENERATOR_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.MAGMA_BLOCK), HoneymodItems.HEATING_COIL, new ItemStack(Material.MAGMA_BLOCK), HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.COAL_GENERATOR, HoneymodItems.HARDENED_METAL_INGOT, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .setCapacity(256)
        .setEnergyProduction(15)
        .register(plugin);

        new BioGenerator(categories.electricity, HoneymodItems.BIO_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.HEATING_COIL, HoneymodItems.COMPOSTER, HoneymodItems.HEATING_COIL, HoneymodItems.ALUMINUM_BRASS_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.ALUMINUM_BRASS_INGOT, null, HoneymodItems.ALUMINUM_BRASS_INGOT, null})
        .setCapacity(128)
        .setEnergyProduction(4)
        .register(plugin);

        new AutoDrier(categories.electricity, HoneymodItems.AUTO_DRIER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[]{null, null, null, HoneymodItems.HEATING_COIL, new ItemStack(Material.SMOKER), HoneymodItems.HEATING_COIL, null, new ItemStack(Material.CAMPFIRE), null})
        .setCapacity(128)
        .setEnergyConsumption(5)
        .setProcessingSpeed(1)
        .register(plugin);

        new AutoBrewer(categories.electricity, HoneymodItems.AUTO_BREWER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.HEATING_COIL, null, HoneymodItems.REINFORCED_PLATE, new ItemStack(Material.BREWING_STAND), HoneymodItems.REINFORCED_PLATE, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .setCapacity(128)
        .setEnergyConsumption(6)
        .setProcessingSpeed(1)
        .register(plugin);

        new ElectricPress(categories.electricity, HoneymodItems.ELECTRIC_PRESS, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.PISTON), HoneymodItems.ELECTRIC_MOTOR, new ItemStack(Material.PISTON), null, HoneymodItems.MEDIUM_CAPACITOR, null, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT})
        .setCapacity(256)
        .setEnergyConsumption(8)
        .setProcessingSpeed(1)
        .register(plugin);

        new ElectricPress(categories.electricity, HoneymodItems.ELECTRIC_PRESS_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.STICKY_PISTON), HoneymodItems.ELECTRIC_PRESS, new ItemStack(Material.STICKY_PISTON), HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.BIG_CAPACITOR, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.DAMASCUS_STEEL_INGOT})
        .setCapacity(1024)
        .setEnergyConsumption(20)
        .setProcessingSpeed(3)
        .register(plugin);
        
        new SlimefunItem(categories.resources, HoneymodItems.MAGNESIUM_SALT, RecipeType.HEATED_PRESSURE_CHAMBER,
        new ItemStack[] {HoneymodItems.MAGNESIUM_DUST, HoneymodItems.SALT, null, null, null, null, null, null, null})
        .register(plugin);
        
        new MagnesiumGenerator(categories.electricity, HoneymodItems.MAGNESIUM_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.ELECTRIC_MOTOR, null, HoneymodItems.COMPRESSED_CARBON, new ItemStack(Material.WATER_BUCKET), HoneymodItems.COMPRESSED_CARBON, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.DURALUMIN_INGOT, HoneymodItems.DURALUMIN_INGOT})
        .setCapacity(128)
        .setEnergyProduction(18)
        .register(plugin);

        new AutoEnchanter(categories.electricity, HoneymodItems.AUTO_ENCHANTER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.ENCHANTING_TABLE), null, HoneymodItems.CARBONADO, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.CARBONADO, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.WITHER_PROOF_OBSIDIAN})
        .setCapacity(128)
        .setEnergyConsumption(9)
        .setProcessingSpeed(1)
        .register(plugin);

        new AutoDisenchanter(categories.electricity, HoneymodItems.AUTO_DISENCHANTER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.REDSTONE), new ItemStack(Material.ANVIL), new ItemStack(Material.REDSTONE), HoneymodItems.CARBONADO, HoneymodItems.AUTO_ENCHANTER, HoneymodItems.CARBONADO, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.WITHER_PROOF_OBSIDIAN})
        .setCapacity(128)
        .setEnergyConsumption(9)
        .setProcessingSpeed(1)
        .register(plugin);

        new AutoAnvil(categories.electricity, 10, HoneymodItems.AUTO_ANVIL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.ANVIL), null, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK)})
        .setCapacity(128)
        .setEnergyConsumption(12)
        .setProcessingSpeed(1)
        .register(plugin);

        new AutoAnvil(categories.electricity, 25, HoneymodItems.AUTO_ANVIL_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.AUTO_ANVIL, null, HoneymodItems.STEEL_PLATE, HoneymodItems.HEATING_COIL, HoneymodItems.STEEL_PLATE, new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.IRON_BLOCK)})
        .setCapacity(256)
        .setEnergyConsumption(16)
        .setProcessingSpeed(1)
        .register(plugin);

        new BookBinder(categories.electricity, HoneymodItems.BOOK_BINDER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.ENCHANTING_TABLE), null, new ItemStack(Material.BOOKSHELF), HoneymodItems.HARDENED_METAL_INGOT, new ItemStack(Material.BOOKSHELF), HoneymodItems.SYNTHETIC_SAPPHIRE, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.SYNTHETIC_SAPPHIRE})
        .setCapacity(256)
        .setEnergyConsumption(16)
        .setProcessingSpeed(1)
        .register(plugin);

        new Multimeter(categories.technicalGadgets, HoneymodItems.MULTIMETER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.COPPER_INGOT, null, HoneymodItems.COPPER_INGOT, null, HoneymodItems.REDSTONE_ALLOY, null, null, HoneymodItems.GOLD_6K, null})
        .register(plugin);

        new SlimefunItem(categories.technicalComponents, HoneymodItems.PLASTIC_SHEET, RecipeType.HEATED_PRESSURE_CHAMBER,
        new ItemStack[] {null, null, null, null, HoneymodItems.OIL_BUCKET, null, null, null, null},
        new SlimefunItemStack(HoneymodItems.PLASTIC_SHEET, 8))
        .register(plugin);

        new UnplaceableBlock(categories.technicalComponents, HoneymodItems.ANDROID_MEMORY_CORE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.BRASS_INGOT, new ItemStack(Material.ORANGE_STAINED_GLASS), HoneymodItems.BRASS_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.TIN_DUST, HoneymodItems.POWER_CRYSTAL, HoneymodItems.BRASS_INGOT, new ItemStack(Material.ORANGE_STAINED_GLASS), HoneymodItems.BRASS_INGOT})
        .register(plugin);

        new GPSTransmitter(categories.gps, 1, HoneymodItems.GPS_TRANSMITTER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.STEEL_INGOT, HoneymodItems.ADVANCED_CIRCUIT_BOARD, HoneymodItems.STEEL_INGOT, HoneymodItems.STEEL_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.STEEL_INGOT}) {
            
            @Override
            public int getMultiplier(int y) {
                return y;
            }

            @Override
            public int getEnergyConsumption() {
                return 1;
            }

        }.register(plugin);

        new GPSTransmitter(categories.gps, 2, HoneymodItems.GPS_TRANSMITTER_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.GPS_TRANSMITTER, HoneymodItems.BRONZE_INGOT, HoneymodItems.GPS_TRANSMITTER, HoneymodItems.BRONZE_INGOT, HoneymodItems.CARBON, HoneymodItems.BRONZE_INGOT, HoneymodItems.GPS_TRANSMITTER, HoneymodItems.BRONZE_INGOT, HoneymodItems.GPS_TRANSMITTER}) {
            
            @Override
            public int getMultiplier(int y) {
                return y * 4 + 100;
            }

            @Override
            public int getEnergyConsumption() {
                return 3;
            }

        }.register(plugin);

        new GPSTransmitter(categories.gps, 3, HoneymodItems.GPS_TRANSMITTER_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.GPS_TRANSMITTER_2, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.GPS_TRANSMITTER_2, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.CARBONADO, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.GPS_TRANSMITTER_2, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.GPS_TRANSMITTER_2}) {
            
            @Override
            public int getMultiplier(int y) {
                return y * 16 + 500;
            }

            @Override
            public int getEnergyConsumption() {
                return 11;
            }

        }.register(plugin);

        new GPSTransmitter(categories.gps, 4, HoneymodItems.GPS_TRANSMITTER_4, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.GPS_TRANSMITTER_3, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.GPS_TRANSMITTER_3, HoneymodItems.NICKEL_INGOT, HoneymodItems.CARBONADO, HoneymodItems.NICKEL_INGOT, HoneymodItems.GPS_TRANSMITTER_3, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.GPS_TRANSMITTER_3}) {
            
            @Override
            public int getMultiplier(int y) {
                return y * 64 + 600;
            }

            @Override
            public int getEnergyConsumption() {
                return 46;
            }
            
        }.register(plugin);

        new GPSControlPanel(categories.gps, HoneymodItems.GPS_CONTROL_PANEL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.COBALT_INGOT, HoneymodItems.ADVANCED_CIRCUIT_BOARD, HoneymodItems.COBALT_INGOT, HoneymodItems.ALUMINUM_BRASS_INGOT, HoneymodItems.ALUMINUM_BRASS_INGOT, HoneymodItems.ALUMINUM_BRASS_INGOT})
        .register(plugin);

        new GPSMarkerTool(categories.gps, HoneymodItems.GPS_MARKER_TOOL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.ELECTRO_MAGNET, null, new ItemStack(Material.LAPIS_LAZULI), HoneymodItems.BASIC_CIRCUIT_BOARD, new ItemStack(Material.LAPIS_LAZULI), new ItemStack(Material.REDSTONE), HoneymodItems.REDSTONE_ALLOY, new ItemStack(Material.REDSTONE)})
        .register(plugin);

        new SlimefunItem(categories.gps, HoneymodItems.GPS_EMERGENCY_TRANSMITTER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.ELECTRO_MAGNET, null, null, HoneymodItems.GPS_TRANSMITTER, null, null, HoneymodItems.ESSENCE_OF_AFTERLIFE, null})
        .register(plugin);

        new AndroidInterface(categories.androids, HoneymodItems.ANDROID_INTERFACE_ITEMS, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.PLASTIC_SHEET, HoneymodItems.STEEL_INGOT, HoneymodItems.PLASTIC_SHEET, HoneymodItems.STEEL_INGOT, HoneymodItems.BASIC_CIRCUIT_BOARD, new ItemStack(Material.BLUE_STAINED_GLASS), HoneymodItems.PLASTIC_SHEET, HoneymodItems.STEEL_INGOT, HoneymodItems.PLASTIC_SHEET})
        .register(plugin);

        new AndroidInterface(categories.androids, HoneymodItems.ANDROID_INTERFACE_FUEL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.PLASTIC_SHEET, HoneymodItems.STEEL_INGOT, HoneymodItems.PLASTIC_SHEET, new ItemStack(Material.RED_STAINED_GLASS), HoneymodItems.BASIC_CIRCUIT_BOARD, HoneymodItems.STEEL_INGOT, HoneymodItems.PLASTIC_SHEET, HoneymodItems.STEEL_INGOT, HoneymodItems.PLASTIC_SHEET})
        .register(plugin);

        new ProgrammableAndroid(categories.androids, 1, HoneymodItems.PROGRAMMABLE_ANDROID, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.PLASTIC_SHEET, HoneymodItems.ANDROID_MEMORY_CORE, HoneymodItems.PLASTIC_SHEET, HoneymodItems.COAL_GENERATOR, HoneymodItems.ELECTRIC_MOTOR, new ItemStack(Material.CHEST), HoneymodItems.PLASTIC_SHEET, HoneymodItems.PLASTIC_SHEET, HoneymodItems.PLASTIC_SHEET})
        .register(plugin);

        new MinerAndroid(categories.androids, 1, HoneymodItems.PROGRAMMABLE_ANDROID_MINER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, null, new ItemStack(Material.DIAMOND_PICKAXE), HoneymodItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.DIAMOND_PICKAXE), null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new FarmerAndroid(categories.androids, 1, HoneymodItems.PROGRAMMABLE_ANDROID_FARMER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, null, new ItemStack(Material.DIAMOND_HOE), HoneymodItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.DIAMOND_HOE), null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new WoodcutterAndroid(categories.androids, 1, HoneymodItems.PROGRAMMABLE_ANDROID_WOODCUTTER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, null, new ItemStack(Material.DIAMOND_AXE), HoneymodItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.DIAMOND_AXE), null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new FisherAndroid(categories.androids, 1, HoneymodItems.PROGRAMMABLE_ANDROID_FISHERMAN, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, null, new ItemStack(Material.FISHING_ROD), HoneymodItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.FISHING_ROD), null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new ButcherAndroid(categories.androids, 1, HoneymodItems.PROGRAMMABLE_ANDROID_BUTCHER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.GPS_TRANSMITTER, null, new ItemStack(Material.DIAMOND_SWORD), HoneymodItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.DIAMOND_SWORD), null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new ProgrammableAndroid(categories.androids, 2, HoneymodItems.PROGRAMMABLE_ANDROID_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.PLASTIC_SHEET, HoneymodItems.ANDROID_MEMORY_CORE, HoneymodItems.PLASTIC_SHEET, HoneymodItems.COMBUSTION_REACTOR, HoneymodItems.PROGRAMMABLE_ANDROID, new ItemStack(Material.CHEST), HoneymodItems.PLASTIC_SHEET, HoneymodItems.POWER_CRYSTAL, HoneymodItems.PLASTIC_SHEET})
        .register(plugin);

        new FisherAndroid(categories.androids, 2, HoneymodItems.PROGRAMMABLE_ANDROID_2_FISHERMAN, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, null, new ItemStack(Material.FISHING_ROD), HoneymodItems.PROGRAMMABLE_ANDROID_2, new ItemStack(Material.FISHING_ROD), null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new ButcherAndroid(categories.androids, 2, HoneymodItems.PROGRAMMABLE_ANDROID_2_BUTCHER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.GPS_TRANSMITTER, null, new ItemStack(Material.DIAMOND_SWORD), HoneymodItems.PROGRAMMABLE_ANDROID_2, new ItemStack(Material.DIAMOND_SWORD), null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new FarmerAndroid(categories.androids, 2, HoneymodItems.PROGRAMMABLE_ANDROID_2_FARMER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.GPS_TRANSMITTER, null, new ItemStack(Material.DIAMOND_HOE), HoneymodItems.PROGRAMMABLE_ANDROID_2, new ItemStack(Material.DIAMOND_HOE), null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new ProgrammableAndroid(categories.androids, 3, HoneymodItems.PROGRAMMABLE_ANDROID_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.PLASTIC_SHEET, HoneymodItems.ANDROID_MEMORY_CORE, HoneymodItems.PLASTIC_SHEET, HoneymodItems.NUCLEAR_REACTOR, HoneymodItems.PROGRAMMABLE_ANDROID_2, new ItemStack(Material.CHEST), HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.POWER_CRYSTAL, HoneymodItems.BLISTERING_INGOT_3})
        .register(plugin);

        new FisherAndroid(categories.androids, 3, HoneymodItems.PROGRAMMABLE_ANDROID_3_FISHERMAN, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, null, new ItemStack(Material.FISHING_ROD), HoneymodItems.PROGRAMMABLE_ANDROID_3, new ItemStack(Material.FISHING_ROD), null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new ButcherAndroid(categories.androids, 3, HoneymodItems.PROGRAMMABLE_ANDROID_3_BUTCHER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.GPS_TRANSMITTER_3, null, new ItemStack(Material.DIAMOND_SWORD), HoneymodItems.PROGRAMMABLE_ANDROID_3, new ItemStack(Material.DIAMOND_SWORD), null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.BLANK_RUNE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.STONE), HoneymodItems.MAGIC_LUMP_1, new ItemStack(Material.STONE), HoneymodItems.MAGIC_LUMP_1, new ItemStack(Material.OBSIDIAN), HoneymodItems.MAGIC_LUMP_1, new ItemStack(Material.STONE), HoneymodItems.MAGIC_LUMP_1, new ItemStack(Material.STONE)})
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.AIR_RUNE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.FEATHER), HoneymodItems.MAGIC_LUMP_1, new ItemStack(Material.FEATHER), new ItemStack(Material.GHAST_TEAR), HoneymodItems.BLANK_RUNE, new ItemStack(Material.GHAST_TEAR), new ItemStack(Material.FEATHER), HoneymodItems.MAGIC_LUMP_1, new ItemStack(Material.FEATHER)}, 
        new SlimefunItemStack(HoneymodItems.AIR_RUNE, 4))
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.EARTH_RUNE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.DIRT), HoneymodItems.MAGIC_LUMP_1, new ItemStack(Material.STONE), new ItemStack(Material.OBSIDIAN), HoneymodItems.BLANK_RUNE, new ItemStack(Material.OBSIDIAN), new ItemStack(Material.STONE), HoneymodItems.MAGIC_LUMP_1, new ItemStack(Material.DIRT)}, 
        new SlimefunItemStack(HoneymodItems.EARTH_RUNE, 4))
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.FIRE_RUNE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.FIRE_CHARGE), HoneymodItems.MAGIC_LUMP_2, new ItemStack(Material.FIRE_CHARGE), new ItemStack(Material.BLAZE_POWDER), HoneymodItems.EARTH_RUNE, new ItemStack(Material.FLINT_AND_STEEL), new ItemStack(Material.FIRE_CHARGE), HoneymodItems.MAGIC_LUMP_2, new ItemStack(Material.FIRE_CHARGE)}, 
        new SlimefunItemStack(HoneymodItems.FIRE_RUNE, 4))
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.WATER_RUNE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.SALMON), HoneymodItems.MAGIC_LUMP_2, new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.SAND), HoneymodItems.BLANK_RUNE, new ItemStack(Material.SAND), new ItemStack(Material.WATER_BUCKET), HoneymodItems.MAGIC_LUMP_2, new ItemStack(Material.COD)}, 
        new SlimefunItemStack(HoneymodItems.WATER_RUNE, 4))
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.ENDER_RUNE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.ENDER_PEARL), HoneymodItems.ENDER_LUMP_3, new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.ENDER_EYE), HoneymodItems.BLANK_RUNE, new ItemStack(Material.ENDER_EYE), new ItemStack(Material.ENDER_PEARL), HoneymodItems.ENDER_LUMP_3, new ItemStack(Material.ENDER_PEARL)}, 
        new SlimefunItemStack(HoneymodItems.ENDER_RUNE, 6))
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.LIGHTNING_RUNE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.IRON_INGOT), HoneymodItems.MAGIC_LUMP_3, new ItemStack(Material.IRON_INGOT), HoneymodItems.AIR_RUNE, new ItemStack(Material.PHANTOM_MEMBRANE), HoneymodItems.WATER_RUNE, new ItemStack(Material.IRON_INGOT), HoneymodItems.MAGIC_LUMP_3, new ItemStack(Material.IRON_INGOT)}, 
        new SlimefunItemStack(HoneymodItems.LIGHTNING_RUNE, 4))
        .register(plugin);

        new SlimefunItem(categories.magicalResources, HoneymodItems.RAINBOW_RUNE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), HoneymodItems.MAGIC_LUMP_3, new ItemStack(Material.CYAN_DYE), new ItemStack(Material.WHITE_WOOL), HoneymodItems.ENDER_RUNE, new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.YELLOW_DYE), HoneymodItems.ENDER_LUMP_3, new ItemStack(Material.MAGENTA_DYE)})
        .register(plugin);

        new SoulboundRune(categories.magicalResources, HoneymodItems.SOULBOUND_RUNE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, HoneymodItems.ESSENCE_OF_AFTERLIFE, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.ENDER_LUMP_3, HoneymodItems.ENDER_RUNE, HoneymodItems.ENDER_LUMP_3, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.ESSENCE_OF_AFTERLIFE, HoneymodItems.MAGIC_LUMP_3})
        .register(plugin);

        new EnchantmentRune(categories.magicalResources, HoneymodItems.ENCHANTMENT_RUNE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, HoneymodItems.MAGICAL_GLASS, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.MAGICAL_GLASS, HoneymodItems.LIGHTNING_RUNE, HoneymodItems.MAGICAL_GLASS, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.MAGICAL_GLASS, HoneymodItems.MAGIC_LUMP_3})
        .register(plugin);

        new InfernalBonemeal(categories.magicalGadgets, HoneymodItems.INFERNAL_BONEMEAL, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.NETHER_WART), HoneymodItems.EARTH_RUNE, new ItemStack(Material.NETHER_WART), HoneymodItems.MAGIC_LUMP_2, new ItemStack(Material.BONE_MEAL), HoneymodItems.MAGIC_LUMP_2, new ItemStack(Material.NETHER_WART), new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.NETHER_WART)}, 
        new SlimefunItemStack(HoneymodItems.INFERNAL_BONEMEAL, 8))
        .register(plugin);

        new SlimefunItem(categories.magicalGadgets, HoneymodItems.ELYTRA_SCALE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {HoneymodItems.ENDER_LUMP_3, HoneymodItems.AIR_RUNE, HoneymodItems.ENDER_LUMP_3, new ItemStack(Material.PHANTOM_MEMBRANE), new ItemStack(Material.FEATHER), new ItemStack(Material.PHANTOM_MEMBRANE), HoneymodItems.ENDER_LUMP_3, HoneymodItems.AIR_RUNE, HoneymodItems.ENDER_LUMP_3})
        .register(plugin);

        new VanillaItem(categories.magicalGadgets, new ItemStack(Material.ELYTRA), "ELYTRA", RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {HoneymodItems.ELYTRA_SCALE, HoneymodItems.AIR_RUNE, HoneymodItems.ELYTRA_SCALE, HoneymodItems.AIR_RUNE, new ItemStack(Material.LEATHER_CHESTPLATE), HoneymodItems.AIR_RUNE, HoneymodItems.ELYTRA_SCALE, HoneymodItems.AIR_RUNE, HoneymodItems.ELYTRA_SCALE})
        .register(plugin);

        new SlimefunItem(categories.magicalGadgets, HoneymodItems.INFUSED_ELYTRA, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {HoneymodItems.FLASK_OF_KNOWLEDGE, HoneymodItems.ELYTRA_SCALE, HoneymodItems.FLASK_OF_KNOWLEDGE, HoneymodItems.FLASK_OF_KNOWLEDGE, new ItemStack(Material.ELYTRA), HoneymodItems.FLASK_OF_KNOWLEDGE, HoneymodItems.FLASK_OF_KNOWLEDGE, HoneymodItems.ELYTRA_SCALE, HoneymodItems.FLASK_OF_KNOWLEDGE})
        .register(plugin);

        new SoulboundItem(categories.magicalGadgets, HoneymodItems.SOULBOUND_ELYTRA, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {HoneymodItems.FLASK_OF_KNOWLEDGE, HoneymodItems.ESSENCE_OF_AFTERLIFE, HoneymodItems.FLASK_OF_KNOWLEDGE, HoneymodItems.ELYTRA_SCALE, new ItemStack(Material.ELYTRA), HoneymodItems.ELYTRA_SCALE, HoneymodItems.FLASK_OF_KNOWLEDGE, HoneymodItems.ESSENCE_OF_AFTERLIFE, HoneymodItems.FLASK_OF_KNOWLEDGE})
        .register(plugin);

        new VanillaItem(categories.magicalGadgets, new ItemStack(Material.TOTEM_OF_UNDYING), "TOTEM_OF_UNDYING", RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {HoneymodItems.ESSENCE_OF_AFTERLIFE, new ItemStack(Material.EMERALD_BLOCK), HoneymodItems.ESSENCE_OF_AFTERLIFE, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.COMMON_TALISMAN, HoneymodItems.MAGIC_LUMP_3, HoneymodItems.ESSENCE_OF_AFTERLIFE, new ItemStack(Material.EMERALD_BLOCK), HoneymodItems.ESSENCE_OF_AFTERLIFE})
        .register(plugin);

        new RainbowBlock(categories.magicalGadgets, HoneymodItems.RAINBOW_WOOL, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_WOOL, 8), new RainbowTickHandler(ColoredMaterial.WOOL))
        .register(plugin);

        new RainbowBlock(categories.magicalGadgets, HoneymodItems.RAINBOW_GLASS, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.WHITE_STAINED_GLASS), new ItemStack(Material.WHITE_STAINED_GLASS), new ItemStack(Material.WHITE_STAINED_GLASS), new ItemStack(Material.WHITE_STAINED_GLASS), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_STAINED_GLASS), new ItemStack(Material.WHITE_STAINED_GLASS), new ItemStack(Material.WHITE_STAINED_GLASS), new ItemStack(Material.WHITE_STAINED_GLASS)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLASS, 8), new RainbowTickHandler(ColoredMaterial.STAINED_GLASS))
        .register(plugin);

        new RainbowBlock(categories.magicalGadgets, HoneymodItems.RAINBOW_GLASS_PANE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.WHITE_STAINED_GLASS_PANE), new ItemStack(Material.WHITE_STAINED_GLASS_PANE), new ItemStack(Material.WHITE_STAINED_GLASS_PANE), new ItemStack(Material.WHITE_STAINED_GLASS_PANE), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_STAINED_GLASS_PANE), new ItemStack(Material.WHITE_STAINED_GLASS_PANE), new ItemStack(Material.WHITE_STAINED_GLASS_PANE), new ItemStack(Material.WHITE_STAINED_GLASS_PANE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLASS_PANE, 8), new RainbowTickHandler(ColoredMaterial.STAINED_GLASS_PANE))
        .register(plugin);

        new RainbowBlock(categories.magicalGadgets, HoneymodItems.RAINBOW_CLAY, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.WHITE_TERRACOTTA), new ItemStack(Material.WHITE_TERRACOTTA), new ItemStack(Material.WHITE_TERRACOTTA), new ItemStack(Material.WHITE_TERRACOTTA), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_TERRACOTTA), new ItemStack(Material.WHITE_TERRACOTTA), new ItemStack(Material.WHITE_TERRACOTTA), new ItemStack(Material.WHITE_TERRACOTTA)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_CLAY, 8), new RainbowTickHandler(ColoredMaterial.TERRACOTTA))
        .register(plugin);

        new RainbowBlock(categories.magicalGadgets, HoneymodItems.RAINBOW_CONCRETE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.WHITE_CONCRETE), new ItemStack(Material.WHITE_CONCRETE), new ItemStack(Material.WHITE_CONCRETE), new ItemStack(Material.WHITE_CONCRETE), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_CONCRETE), new ItemStack(Material.WHITE_CONCRETE), new ItemStack(Material.WHITE_CONCRETE), new ItemStack(Material.WHITE_CONCRETE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_CONCRETE, 8), new RainbowTickHandler(ColoredMaterial.CONCRETE))
        .register(plugin);
        
        new RainbowBlock(categories.magicalGadgets, HoneymodItems.RAINBOW_GLAZED_TERRACOTTA, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), new ItemStack(Material.WHITE_GLAZED_TERRACOTTA)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLAZED_TERRACOTTA, 8), new RainbowTickHandler(ColoredMaterial.GLAZED_TERRACOTTA))
        .register(plugin);

        // Christmas

        new RainbowBlock(categories.christmas, HoneymodItems.RAINBOW_WOOL_XMAS, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.GREEN_DYE), new ItemStack(Material.WHITE_WOOL), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.GREEN_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_WOOL_XMAS, 2), new RainbowTickHandler(Material.RED_WOOL, Material.GREEN_WOOL))
        .register(plugin);

        new RainbowBlock(categories.christmas, HoneymodItems.RAINBOW_GLASS_XMAS, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.GREEN_DYE), new ItemStack(Material.WHITE_STAINED_GLASS), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_STAINED_GLASS), new ItemStack(Material.GREEN_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLASS_XMAS, 2), new RainbowTickHandler(Material.RED_STAINED_GLASS, Material.GREEN_STAINED_GLASS))
        .register(plugin);

        new RainbowBlock(categories.christmas, HoneymodItems.RAINBOW_GLASS_PANE_XMAS, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.GREEN_DYE), new ItemStack(Material.WHITE_STAINED_GLASS_PANE), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_STAINED_GLASS_PANE), new ItemStack(Material.GREEN_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLASS_PANE_XMAS, 2), new RainbowTickHandler(Material.RED_STAINED_GLASS_PANE, Material.GREEN_STAINED_GLASS_PANE))
        .register(plugin);

        new RainbowBlock(categories.christmas, HoneymodItems.RAINBOW_CLAY_XMAS, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.GREEN_DYE), new ItemStack(Material.WHITE_TERRACOTTA), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_TERRACOTTA), new ItemStack(Material.GREEN_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_CLAY_XMAS, 2), new RainbowTickHandler(Material.RED_TERRACOTTA, Material.GREEN_TERRACOTTA))
        .register(plugin);

        new RainbowBlock(categories.christmas, HoneymodItems.RAINBOW_CONCRETE_XMAS, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.GREEN_DYE), new ItemStack(Material.WHITE_CONCRETE), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_CONCRETE), new ItemStack(Material.GREEN_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_CONCRETE_XMAS, 2), new RainbowTickHandler(Material.RED_CONCRETE, Material.GREEN_CONCRETE))
        .register(plugin);
        
        new RainbowBlock(categories.christmas, HoneymodItems.RAINBOW_GLAZED_TERRACOTTA_XMAS, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.GREEN_DYE), new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), new ItemStack(Material.GREEN_DYE), HoneymodItems.CHRISTMAS_COOKIE, new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLAZED_TERRACOTTA_XMAS, 2), new RainbowTickHandler(Material.RED_GLAZED_TERRACOTTA, Material.GREEN_GLAZED_TERRACOTTA))
        .register(plugin);
        
        // Valentines Day

        new RainbowBlock(categories.valentinesDay, HoneymodItems.RAINBOW_WOOL_VALENTINE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.PINK_DYE), new ItemStack(Material.WHITE_WOOL), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.PINK_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_WOOL_VALENTINE, 2), new RainbowTickHandler(Material.MAGENTA_WOOL, Material.PINK_WOOL))
        .register(plugin);

        new RainbowBlock(categories.valentinesDay, HoneymodItems.RAINBOW_GLASS_VALENTINE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.PINK_DYE), new ItemStack(Material.WHITE_STAINED_GLASS), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_STAINED_GLASS), new ItemStack(Material.PINK_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLASS_VALENTINE, 2), new RainbowTickHandler(Material.MAGENTA_STAINED_GLASS, Material.PINK_STAINED_GLASS))
        .register(plugin);

        new RainbowBlock(categories.valentinesDay, HoneymodItems.RAINBOW_GLASS_PANE_VALENTINE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.PINK_DYE), new ItemStack(Material.WHITE_STAINED_GLASS_PANE), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_STAINED_GLASS_PANE), new ItemStack(Material.PINK_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLASS_PANE_VALENTINE, 2), new RainbowTickHandler(Material.MAGENTA_STAINED_GLASS_PANE, Material.PINK_STAINED_GLASS_PANE))
        .register(plugin);

        new RainbowBlock(categories.valentinesDay, HoneymodItems.RAINBOW_CLAY_VALENTINE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.PINK_DYE), new ItemStack(Material.WHITE_TERRACOTTA), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_TERRACOTTA), new ItemStack(Material.PINK_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_CLAY_VALENTINE, 2), new RainbowTickHandler(Material.MAGENTA_TERRACOTTA, Material.PINK_TERRACOTTA))
        .register(plugin);
        
        new RainbowBlock(categories.valentinesDay, HoneymodItems.RAINBOW_CONCRETE_VALENTINE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.PINK_DYE), new ItemStack(Material.WHITE_CONCRETE), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_CONCRETE), new ItemStack(Material.PINK_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_CONCRETE_VALENTINE, 2), new RainbowTickHandler(Material.MAGENTA_CONCRETE, Material.PINK_CONCRETE))
        .register(plugin);
        
        new RainbowBlock(categories.valentinesDay, HoneymodItems.RAINBOW_GLAZED_TERRACOTTA_VALENTINE, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.RED_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.PINK_DYE), new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), new ItemStack(Material.PINK_DYE), new ItemStack(Material.POPPY), new ItemStack(Material.RED_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLAZED_TERRACOTTA_VALENTINE, 2), new RainbowTickHandler(Material.MAGENTA_GLAZED_TERRACOTTA, Material.PINK_GLAZED_TERRACOTTA))
        .register(plugin);

        // Halloween
        
        new RainbowBlock(categories.halloween, HoneymodItems.RAINBOW_WOOL_HALLOWEEN, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.ORANGE_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.WHITE_WOOL), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.ORANGE_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_WOOL_HALLOWEEN, 2), new RainbowTickHandler(Material.ORANGE_WOOL, Material.BLACK_WOOL))
        .register(plugin);

        new RainbowBlock(categories.halloween, HoneymodItems.RAINBOW_GLASS_HALLOWEEN, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.ORANGE_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.WHITE_STAINED_GLASS), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_STAINED_GLASS), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.ORANGE_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLASS_HALLOWEEN, 2), new RainbowTickHandler(Material.ORANGE_STAINED_GLASS, Material.BLACK_STAINED_GLASS))
        .register(plugin);

        new RainbowBlock(categories.halloween, HoneymodItems.RAINBOW_GLASS_PANE_HALLOWEEN, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.ORANGE_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.WHITE_STAINED_GLASS_PANE), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_STAINED_GLASS_PANE), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.ORANGE_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLASS_PANE_HALLOWEEN, 2), new RainbowTickHandler(Material.ORANGE_STAINED_GLASS_PANE, Material.BLACK_STAINED_GLASS_PANE))
        .register(plugin);

        new RainbowBlock(categories.halloween, HoneymodItems.RAINBOW_CLAY_HALLOWEEN, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.ORANGE_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.WHITE_TERRACOTTA), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_TERRACOTTA), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.ORANGE_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_CLAY_HALLOWEEN, 2), new RainbowTickHandler(Material.ORANGE_TERRACOTTA, Material.BLACK_TERRACOTTA))
        .register(plugin);
        
        new RainbowBlock(categories.halloween, HoneymodItems.RAINBOW_CONCRETE_HALLOWEEN, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.ORANGE_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.WHITE_CONCRETE), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_CONCRETE), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.ORANGE_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_CONCRETE_HALLOWEEN, 2), new RainbowTickHandler(Material.ORANGE_CONCRETE, Material.BLACK_CONCRETE))
        .register(plugin);
        
        new RainbowBlock(categories.halloween, HoneymodItems.RAINBOW_GLAZED_TERRACOTTA_HALLOWEEN, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.ORANGE_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), HoneymodItems.RAINBOW_RUNE, new ItemStack(Material.WHITE_GLAZED_TERRACOTTA), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.PUMPKIN), new ItemStack(Material.ORANGE_DYE)}, 
        new SlimefunItemStack(HoneymodItems.RAINBOW_GLAZED_TERRACOTTA_HALLOWEEN, 2), new RainbowTickHandler(Material.ORANGE_GLAZED_TERRACOTTA, Material.BLACK_GLAZED_TERRACOTTA))
        .register(plugin);

        new WitherProofBlock(categories.technicalComponents, HoneymodItems.WITHER_PROOF_GLASS, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.LEAD_INGOT, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.LEAD_INGOT, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.HARDENED_GLASS, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.LEAD_INGOT, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.LEAD_INGOT}, 
        new SlimefunItemStack(HoneymodItems.WITHER_PROOF_GLASS, 4))
        .register(plugin);

        new GEOScanner(categories.gps, HoneymodItems.GPS_GEO_SCANNER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, null, HoneymodItems.ELECTRO_MAGNET, null, HoneymodItems.STEEL_INGOT, HoneymodItems.STEEL_INGOT, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.ELECTRO_MAGNET})
        .register(plugin);

        new PortableGEOScanner(categories.gps, HoneymodItems.PORTABLE_GEO_SCANNER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.ELECTRO_MAGNET, new ItemStack(Material.COMPASS), HoneymodItems.ELECTRO_MAGNET, HoneymodItems.STEEL_INGOT, HoneymodItems.GPS_MARKER_TOOL, HoneymodItems.STEEL_INGOT, HoneymodItems.SOLDER_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.SOLDER_INGOT})
        .register(plugin);

        new OilPump(categories.gps, HoneymodItems.OIL_PUMP, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.STEEL_INGOT, HoneymodItems.MEDIUM_CAPACITOR, HoneymodItems.STEEL_INGOT, HoneymodItems.STEEL_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.STEEL_INGOT, null, new ItemStack(Material.BUCKET), null})
        .setCapacity(256)
        .setEnergyConsumption(14)
        .setProcessingSpeed(1)
        .register(plugin);

        new GEOMiner(categories.gps, HoneymodItems.GEO_MINER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.DIAMOND_PICKAXE), HoneymodItems.MEDIUM_CAPACITOR, new ItemStack(Material.DIAMOND_PICKAXE), HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.OIL_PUMP, HoneymodItems.REINFORCED_ALLOY_INGOT, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .setCapacity(512)
        .setEnergyConsumption(24)
        .setProcessingSpeed(1)
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.OIL_BUCKET, new RecipeType(new NamespacedKey(plugin, "oil_pump"), HoneymodItems.OIL_PUMP),
        new ItemStack[] {null, null, null, null, new ItemStack(Material.BUCKET), null, null, null, null})
        .register(plugin);

        new SlimefunItem(categories.resources, HoneymodItems.FUEL_BUCKET, RecipeType.REFINERY,
        new ItemStack[] {null, null, null, null, HoneymodItems.OIL_BUCKET, null, null, null, null})
        .register(plugin);

        new RadioactiveItem(categories.resources, Radioactivity.MODERATE, HoneymodItems.NETHER_ICE, RecipeType.GEO_MINER,
        new ItemStack[] {null, null, null, null, null, null, null, null, null})
        .register(plugin);

        new Refinery(categories.electricity, HoneymodItems.REFINERY, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.HARDENED_GLASS, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.HARDENED_GLASS, HoneymodItems.HARDENED_GLASS, HoneymodItems.REDSTONE_ALLOY, HoneymodItems.HARDENED_GLASS, new ItemStack(Material.PISTON), HoneymodItems.ELECTRIC_MOTOR, new ItemStack(Material.PISTON)})
        .setCapacity(256)
        .setEnergyConsumption(16)
        .setProcessingSpeed(1)
        .register(plugin);

        new LavaGenerator(categories.electricity, HoneymodItems.LAVA_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.GOLD_16K, null, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.HEATING_COIL, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.HEATING_COIL})
        .setCapacity(512)
        .setEnergyProduction(10)
        .register(plugin);

        new LavaGenerator(categories.electricity, HoneymodItems.LAVA_GENERATOR_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.COMPRESSED_CARBON, HoneymodItems.LAVA_GENERATOR, HoneymodItems.COMPRESSED_CARBON, HoneymodItems.HEATING_COIL, HoneymodItems.COMPRESSED_CARBON, HoneymodItems.HEATING_COIL})
        .setCapacity(1024)
        .setEnergyProduction(20)
        .register(plugin);

        new CombustionGenerator(categories.electricity, HoneymodItems.COMBUSTION_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.STEEL_INGOT, null, HoneymodItems.STEEL_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.STEEL_INGOT, HoneymodItems.HEATING_COIL, HoneymodItems.STEEL_INGOT, HoneymodItems.HEATING_COIL})
        .setCapacity(256)
        .setEnergyProduction(12)
        .register(plugin);

        new TeleporterPylon(categories.gps, HoneymodItems.GPS_TELEPORTER_PYLON, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.ZINC_INGOT, new ItemStack(Material.GLASS), HoneymodItems.ZINC_INGOT, new ItemStack(Material.GLASS), HoneymodItems.HEATING_COIL, new ItemStack(Material.GLASS), HoneymodItems.ZINC_INGOT, new ItemStack(Material.GLASS), HoneymodItems.ZINC_INGOT}, 
        new SlimefunItemStack(HoneymodItems.GPS_TELEPORTER_PYLON, 8))
        .register(plugin);

        new Teleporter(categories.gps, HoneymodItems.GPS_TELEPORTATION_MATRIX, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.GPS_TELEPORTER_PYLON, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.GPS_TELEPORTER_PYLON, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.GPS_CONTROL_PANEL, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.GPS_TELEPORTER_PYLON, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.GPS_TELEPORTER_PYLON})
        .register(plugin);

        new SlimefunItem(categories.gps, HoneymodItems.GPS_ACTIVATION_DEVICE_SHARED, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.STONE_PRESSURE_PLATE), null, new ItemStack(Material.REDSTONE), HoneymodItems.GPS_TRANSMITTER, new ItemStack(Material.REDSTONE), HoneymodItems.BILLON_INGOT, HoneymodItems.BILLON_INGOT, HoneymodItems.BILLON_INGOT})
        .register(plugin);

        new PersonalActivationPlate(categories.gps, HoneymodItems.GPS_ACTIVATION_DEVICE_PERSONAL, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.LEAD_INGOT, null, HoneymodItems.COBALT_INGOT, HoneymodItems.GPS_ACTIVATION_DEVICE_SHARED, HoneymodItems.COBALT_INGOT, null, HoneymodItems.LEAD_INGOT, null})
        .register(plugin);

        new InfusedHopper(categories.magicalGadgets, HoneymodItems.INFUSED_HOPPER, RecipeType.ANCIENT_ALTAR,
        new ItemStack[] {new ItemStack(Material.OBSIDIAN), HoneymodItems.EARTH_RUNE, new ItemStack(Material.HOPPER), HoneymodItems.ENDER_RUNE, HoneymodItems.INFUSED_MAGNET, HoneymodItems.ENDER_RUNE, new ItemStack(Material.HOPPER), HoneymodItems.EARTH_RUNE, new ItemStack(Material.OBSIDIAN)})
        .register(plugin);

        new RadioactiveItem(categories.resources, Radioactivity.HIGH, HoneymodItems.BLISTERING_INGOT, RecipeType.HEATED_PRESSURE_CHAMBER,
        new ItemStack[] {HoneymodItems.GOLD_24K, HoneymodItems.URANIUM, null, null, null, null, null, null, null})
        .register(plugin);

        new RadioactiveItem(categories.resources, Radioactivity.VERY_HIGH, HoneymodItems.BLISTERING_INGOT_2, RecipeType.HEATED_PRESSURE_CHAMBER,
        new ItemStack[] {HoneymodItems.BLISTERING_INGOT, HoneymodItems.CARBONADO, null, null, null, null, null, null, null})
        .register(plugin);

        new RadioactiveItem(categories.resources, Radioactivity.VERY_HIGH, HoneymodItems.BLISTERING_INGOT_3, RecipeType.HEATED_PRESSURE_CHAMBER,
        new ItemStack[] {HoneymodItems.BLISTERING_INGOT_2, new ItemStack(Material.NETHER_STAR), null, null, null, null, null, null, null})
        .register(plugin);

        new RadioactiveItem(categories.resources, Radioactivity.VERY_HIGH, HoneymodItems.ENRICHED_NETHER_ICE, RecipeType.HEATED_PRESSURE_CHAMBER,
        new ItemStack[] {HoneymodItems.NETHER_ICE, HoneymodItems.PLUTONIUM, null, null, null, null, null, null, null},
        new SlimefunItemStack(HoneymodItems.ENRICHED_NETHER_ICE, 4))
        .register(plugin);

        new ElevatorPlate(categories.gps, HoneymodItems.ELEVATOR_PLATE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.STONE_PRESSURE_PLATE), null, new ItemStack(Material.PISTON), HoneymodItems.ELECTRIC_MOTOR, new ItemStack(Material.PISTON), HoneymodItems.ALUMINUM_BRONZE_INGOT, HoneymodItems.ALUMINUM_BRONZE_INGOT, HoneymodItems.ALUMINUM_BRONZE_INGOT},
        new SlimefunItemStack(HoneymodItems.ELEVATOR_PLATE, 2))
        .register(plugin);

        new FoodFabricator(categories.electricity, HoneymodItems.FOOD_FABRICATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.BILLON_INGOT, HoneymodItems.SILVER_INGOT, HoneymodItems.BILLON_INGOT, HoneymodItems.TIN_CAN, HoneymodItems.SMALL_CAPACITOR, HoneymodItems.TIN_CAN, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .setCapacity(256)
        .setEnergyConsumption(7)
        .setProcessingSpeed(1)
        .register(plugin);

        new FoodFabricator(categories.electricity, HoneymodItems.FOOD_FABRICATOR_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.FOOD_FABRICATOR, HoneymodItems.ELECTRIC_MOTOR, null, HoneymodItems.ELECTRO_MAGNET, null})
        .setCapacity(512)
        .setEnergyConsumption(24)
        .setProcessingSpeed(6)
        .register(plugin);

        new OrganicFood(categories.misc, HoneymodItems.WHEAT_ORGANIC_FOOD, Material.WHEAT)
        .register(plugin);

        new OrganicFood(categories.misc, HoneymodItems.CARROT_ORGANIC_FOOD, Material.CARROT)
        .register(plugin);

        new OrganicFood(categories.misc, HoneymodItems.POTATO_ORGANIC_FOOD, Material.POTATO)
        .register(plugin);

        new OrganicFood(categories.misc, HoneymodItems.SEEDS_ORGANIC_FOOD, Material.WHEAT_SEEDS)
        .register(plugin);

        new OrganicFood(categories.misc, HoneymodItems.BEETROOT_ORGANIC_FOOD, Material.BEETROOT)
        .register(plugin);

        new OrganicFood(categories.misc, HoneymodItems.MELON_ORGANIC_FOOD, Material.MELON_SLICE)
        .register(plugin);

        new OrganicFood(categories.misc, HoneymodItems.APPLE_ORGANIC_FOOD, Material.APPLE)
        .register(plugin);

        new OrganicFood(categories.misc, HoneymodItems.SWEET_BERRIES_ORGANIC_FOOD, Material.SWEET_BERRIES)
        .register(plugin);

        new OrganicFood(categories.misc, HoneymodItems.KELP_ORGANIC_FOOD, Material.DRIED_KELP)
        .register(plugin);

        new OrganicFood(categories.misc, HoneymodItems.COCOA_ORGANIC_FOOD, Material.COCOA_BEANS)
        .register(plugin);

        new AutoBreeder(categories.electricity, HoneymodItems.AUTO_BREEDER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.GOLD_18K, HoneymodItems.TIN_CAN, HoneymodItems.GOLD_18K, HoneymodItems.ELECTRIC_MOTOR, new ItemStack(Material.HAY_BLOCK), HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.LEAD_INGOT, HoneymodItems.FOOD_FABRICATOR, HoneymodItems.LEAD_INGOT})
        .register(plugin);

        new AnimalGrowthAccelerator(categories.electricity, HoneymodItems.ANIMAL_GROWTH_ACCELERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.BLISTERING_INGOT_3, null, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.WHEAT_ORGANIC_FOOD, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.AUTO_BREEDER, HoneymodItems.REINFORCED_ALLOY_INGOT})
        .register(plugin);

        new TreeGrowthAccelerator(categories.electricity, HoneymodItems.TREE_GROWTH_ACCELERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.CARBONADO, null, HoneymodItems.ELECTRIC_MOTOR, new ItemStack(Material.DIAMOND_AXE), HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.MAGNESIUM_SALT, HoneymodItems.BIG_CAPACITOR, HoneymodItems.MAGNESIUM_SALT})
        .register(plugin);
        
        new XPCollector(categories.electricity, HoneymodItems.EXP_COLLECTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.BLISTERING_INGOT_3, null, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.AUTO_ENCHANTER, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.ALUMINUM_BRONZE_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.ALUMINUM_BRONZE_INGOT})
        .register(plugin);

        new FoodComposter(categories.electricity, HoneymodItems.FOOD_COMPOSTER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.FOOD_FABRICATOR, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.TIN_CAN, HoneymodItems.MEDIUM_CAPACITOR, HoneymodItems.TIN_CAN, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .setCapacity(256)
        .setEnergyConsumption(8)
        .setProcessingSpeed(1)
        .register(plugin);

        new FoodComposter(categories.electricity, HoneymodItems.FOOD_COMPOSTER_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.HARDENED_METAL_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.FOOD_COMPOSTER, HoneymodItems.ELECTRIC_MOTOR, null, HoneymodItems.ELECTRO_MAGNET, null})
        .setCapacity(512)
        .setEnergyConsumption(26)
        .setProcessingSpeed(10)
        .register(plugin);

        new OrganicFertilizer(categories.misc, HoneymodItems.WHEAT_FERTILIZER, HoneymodItems.WHEAT_ORGANIC_FOOD)
        .register(plugin);

        new OrganicFertilizer(categories.misc, HoneymodItems.CARROT_FERTILIZER, HoneymodItems.CARROT_ORGANIC_FOOD)
        .register(plugin);

        new OrganicFertilizer(categories.misc, HoneymodItems.POTATO_FERTILIZER, HoneymodItems.POTATO_ORGANIC_FOOD)
        .register(plugin);

        new OrganicFertilizer(categories.misc, HoneymodItems.SEEDS_FERTILIZER, HoneymodItems.SEEDS_ORGANIC_FOOD)
        .register(plugin);

        new OrganicFertilizer(categories.misc, HoneymodItems.BEETROOT_FERTILIZER, HoneymodItems.BEETROOT_ORGANIC_FOOD)
        .register(plugin);

        new OrganicFertilizer(categories.misc, HoneymodItems.MELON_FERTILIZER, HoneymodItems.MELON_ORGANIC_FOOD)
        .register(plugin);

        new OrganicFertilizer(categories.misc, HoneymodItems.APPLE_FERTILIZER, HoneymodItems.APPLE_ORGANIC_FOOD)
        .register(plugin);

        new OrganicFertilizer(categories.misc, HoneymodItems.SWEET_BERRIES_FERTILIZER, HoneymodItems.SWEET_BERRIES_ORGANIC_FOOD)
        .register(plugin);

        new OrganicFertilizer(categories.misc, HoneymodItems.KELP_FERTILIZER, HoneymodItems.KELP_ORGANIC_FOOD)
        .register(plugin);

        new OrganicFertilizer(categories.misc, HoneymodItems.COCOA_FERTILIZER, HoneymodItems.COCOA_ORGANIC_FOOD)
        .register(plugin);

        new CropGrowthAccelerator(categories.electricity, HoneymodItems.CROP_GROWTH_ACCELERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.BLISTERING_INGOT_3, null, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.PROGRAMMABLE_ANDROID_FARMER, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.ANIMAL_GROWTH_ACCELERATOR, HoneymodItems.ELECTRO_MAGNET}) {

            @Override
            public int getEnergyConsumption() {
                return 25;
            }

            @Override
            public int getRadius() {
                return 3;
            }

            @Override
            public int getSpeed() {
                return 3;
            }

        }.register(plugin);

        new CropGrowthAccelerator(categories.electricity, HoneymodItems.CROP_GROWTH_ACCELERATOR_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.BLISTERING_INGOT_3, null, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.CROP_GROWTH_ACCELERATOR, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.ADVANCED_CIRCUIT_BOARD, HoneymodItems.ELECTRO_MAGNET}) {

            @Override
            public int getEnergyConsumption() {
                return 30;
            }

            @Override
            public int getRadius() {
                return 4;
            }

            @Override
            public int getSpeed() {
                return 4;
            }

        }.register(plugin);

        new Freezer(categories.electricity, HoneymodItems.FREEZER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.SILVER_INGOT, null, HoneymodItems.ELECTRIC_MOTOR, new ItemStack(Material.PACKED_ICE), HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.COOLING_UNIT, HoneymodItems.MEDIUM_CAPACITOR, HoneymodItems.COOLING_UNIT})
        .setCapacity(256)
        .setEnergyConsumption(9)
        .setProcessingSpeed(1)
        .register(plugin);

        new Freezer(categories.electricity, HoneymodItems.FREEZER_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.SILVER_INGOT, null, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.FREEZER, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.COOLING_UNIT, HoneymodItems.ALUMINUM_BRASS_INGOT, HoneymodItems.COOLING_UNIT})
        .setCapacity(256)
        .setEnergyConsumption(15)
        .setProcessingSpeed(2)
        .register(plugin);

        new CoolantCell(categories.technicalComponents, HoneymodItems.REACTOR_COOLANT_CELL, RecipeType.FREEZER,
        new ItemStack[] {new ItemStack(Material.BLUE_ICE), null, null, null, null, null, null, null, null})
        .register(plugin);

        new CoolantCell(categories.technicalComponents, HoneymodItems.NETHER_ICE_COOLANT_CELL, RecipeType.HEATED_PRESSURE_CHAMBER,
        new ItemStack[] {HoneymodItems.ENRICHED_NETHER_ICE, null, null, null, null, null, null, null, null},
        new SlimefunItemStack(HoneymodItems.NETHER_ICE_COOLANT_CELL, 8))
        .register(plugin);

        new RadioactiveItem(categories.resources, Radioactivity.HIGH, HoneymodItems.NEPTUNIUM, RecipeType.NUCLEAR_REACTOR,
        new ItemStack[] {HoneymodItems.URANIUM, null, null, null, null, null, null, null, null})
        .register(plugin);

        new RadioactiveItem(categories.resources, Radioactivity.VERY_HIGH, HoneymodItems.PLUTONIUM, RecipeType.NUCLEAR_REACTOR,
        new ItemStack[] {HoneymodItems.NEPTUNIUM, null, null, null, null, null, null, null, null})
        .register(plugin);

        new RadioactiveItem(categories.resources, Radioactivity.VERY_HIGH, HoneymodItems.BOOSTED_URANIUM, RecipeType.HEATED_PRESSURE_CHAMBER,
        new ItemStack[] {HoneymodItems.PLUTONIUM, HoneymodItems.URANIUM, null, null, null, null, null, null, null})
        .register(plugin);

        new NuclearReactor(categories.electricity, HoneymodItems.NUCLEAR_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.CARBONADO_EDGED_CAPACITOR, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.REINFORCED_PLATE, HoneymodItems.COOLING_UNIT, HoneymodItems.REINFORCED_PLATE, HoneymodItems.LEAD_INGOT, HoneymodItems.REINFORCED_PLATE, HoneymodItems.LEAD_INGOT}){
            
            @Override
            public int getEnergyProduction() {
                return 250;
            }

            @Override
            public int getCapacity() {
                return 16384;
            }
            
        }.register(plugin);

        new NetherStarReactor(categories.electricity, HoneymodItems.NETHER_STAR_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.BOOSTED_URANIUM, HoneymodItems.CARBONADO_EDGED_CAPACITOR, HoneymodItems.BOOSTED_URANIUM, HoneymodItems.REINFORCED_PLATE, new ItemStack(Material.NETHER_STAR), HoneymodItems.REINFORCED_PLATE, HoneymodItems.CORINTHIAN_BRONZE_INGOT, HoneymodItems.REINFORCED_PLATE, HoneymodItems.CORINTHIAN_BRONZE_INGOT}){

            @Override
            public int getEnergyProduction() {
                return 512;
            }

            @Override
            public int getCapacity() {
                return 32768;
            }

        }.register(plugin);

        new UnplaceableBlock(categories.cargo, HoneymodItems.CARGO_MOTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.HARDENED_GLASS, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.HARDENED_GLASS, HoneymodItems.SILVER_INGOT, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.SILVER_INGOT, HoneymodItems.HARDENED_GLASS, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.HARDENED_GLASS}, 
        new SlimefunItemStack(HoneymodItems.CARGO_MOTOR, 4))
        .register(plugin);

        new CargoManager(categories.cargo, HoneymodItems.CARGO_MANAGER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.HOLOGRAM_PROJECTOR, null, HoneymodItems.REINFORCED_PLATE, HoneymodItems.CARGO_MOTOR, HoneymodItems.REINFORCED_PLATE, HoneymodItems.ALUMINUM_BRONZE_INGOT, HoneymodItems.ANDROID_MEMORY_CORE, HoneymodItems.ALUMINUM_BRONZE_INGOT})
        .register(plugin);

        new CargoConnectorNode(categories.cargo, HoneymodItems.CARGO_CONNECTOR_NODE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.BRONZE_INGOT, HoneymodItems.SILVER_INGOT, HoneymodItems.BRONZE_INGOT, HoneymodItems.SILVER_INGOT, HoneymodItems.CARGO_MOTOR, HoneymodItems.SILVER_INGOT, HoneymodItems.BRONZE_INGOT, HoneymodItems.SILVER_INGOT, HoneymodItems.BRONZE_INGOT}, 
        new SlimefunItemStack(HoneymodItems.CARGO_CONNECTOR_NODE, 4))
        .register(plugin);

        new CargoInputNode(categories.cargo, HoneymodItems.CARGO_INPUT_NODE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.HOPPER), null, HoneymodItems.BILLON_INGOT, HoneymodItems.CARGO_CONNECTOR_NODE, HoneymodItems.BILLON_INGOT, null, new ItemStack(Material.HOPPER), null}, 
        new SlimefunItemStack(HoneymodItems.CARGO_INPUT_NODE, 2))
        .register(plugin);

        new CargoOutputNode(categories.cargo, HoneymodItems.CARGO_OUTPUT_NODE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.HOPPER), null, HoneymodItems.BRASS_INGOT, HoneymodItems.CARGO_CONNECTOR_NODE, HoneymodItems.BRASS_INGOT, null, new ItemStack(Material.HOPPER), null}, 
        new SlimefunItemStack(HoneymodItems.CARGO_OUTPUT_NODE, 2))
        .register(plugin);

        new AdvancedCargoOutputNode(categories.cargo, HoneymodItems.CARGO_OUTPUT_NODE_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.CARGO_MOTOR, null, HoneymodItems.COBALT_INGOT, HoneymodItems.CARGO_OUTPUT_NODE, HoneymodItems.COBALT_INGOT, null, HoneymodItems.CARGO_MOTOR, null})
        .register(plugin);

        new AutomatedCraftingChamber(categories.electricity, HoneymodItems.AUTOMATED_CRAFTING_CHAMBER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, new ItemStack(Material.CRAFTING_TABLE), null, HoneymodItems.CARGO_MOTOR, HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.CARGO_MOTOR, null, HoneymodItems.ELECTRIC_MOTOR, null}) {

            @Override
            public int getEnergyConsumption() {
                return 10;
            }

            @Override
            public int getCapacity() {
                return 256;
            }

        }.register(plugin);

        new ReactorAccessPort(categories.cargo, HoneymodItems.REACTOR_ACCESS_PORT, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.BLISTERING_INGOT_3, null, HoneymodItems.LEAD_INGOT, HoneymodItems.CARGO_MOTOR, HoneymodItems.LEAD_INGOT, null, HoneymodItems.ELECTRIC_MOTOR, null})
        .register(plugin);

        new FluidPump(categories.electricity, HoneymodItems.FLUID_PUMP, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.MEDIUM_CAPACITOR, null, new ItemStack(Material.BUCKET), HoneymodItems.CARGO_MOTOR, new ItemStack(Material.BUCKET), null, HoneymodItems.OIL_PUMP, null})
        .register(plugin);

        new TrashCan(categories.cargo, HoneymodItems.TRASH_CAN, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {null, HoneymodItems.PORTABLE_DUSTBIN, null, HoneymodItems.LEAD_INGOT, HoneymodItems.CARGO_MOTOR, HoneymodItems.LEAD_INGOT, HoneymodItems.ALUMINUM_INGOT, HoneymodItems.LEAD_INGOT, HoneymodItems.ALUMINUM_INGOT})
        .register(plugin);

        new CarbonPress(categories.electricity, HoneymodItems.CARBON_PRESS, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CARBON, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.CARBON, HoneymodItems.CARBON, HoneymodItems.HEATED_PRESSURE_CHAMBER, HoneymodItems.CARBON, HoneymodItems.HEATING_COIL, HoneymodItems.CARBONADO, HoneymodItems.HEATING_COIL})
        .setCapacity(256)
        .setEnergyConsumption(10)
        .setProcessingSpeed(1)
        .register(plugin);

        new CarbonPress(categories.electricity, HoneymodItems.CARBON_PRESS_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CARBONADO, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.CARBONADO, HoneymodItems.CARBON, HoneymodItems.CARBON_PRESS, HoneymodItems.CARBON, HoneymodItems.HEATING_COIL, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.HEATING_COIL})
        .setCapacity(512)
        .setEnergyConsumption(25)
        .setProcessingSpeed(3)
        .register(plugin);

        new CarbonPress(categories.electricity, HoneymodItems.CARBON_PRESS_3, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.CARBONADO, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.CARBONADO, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.CARBON_PRESS_2, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.HEATING_COIL, HoneymodItems.ELECTRO_MAGNET, HoneymodItems.HEATING_COIL})
        .setCapacity(512)
        .setEnergyConsumption(90)
        .setProcessingSpeed(15)
        .register(plugin);

        new ElectricSmeltery(categories.electricity, HoneymodItems.ELECTRIC_SMELTERY, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {new ItemStack(Material.NETHER_BRICKS), HoneymodItems.ELECTRIC_MOTOR, new ItemStack(Material.NETHER_BRICKS), HoneymodItems.HEATING_COIL, HoneymodItems.ELECTRIC_INGOT_FACTORY, HoneymodItems.HEATING_COIL, HoneymodItems.GILDED_IRON, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.GILDED_IRON})
        .setCapacity(512)
        .setEnergyConsumption(10)
        .setProcessingSpeed(1)
        .register(plugin);

        new ElectricSmeltery(categories.electricity, HoneymodItems.ELECTRIC_SMELTERY_2, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.POWER_CRYSTAL, HoneymodItems.DAMASCUS_STEEL_INGOT, HoneymodItems.HEATING_COIL, HoneymodItems.ELECTRIC_SMELTERY, HoneymodItems.HEATING_COIL, HoneymodItems.GILDED_IRON, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.GILDED_IRON})
        .setCapacity(1024)
        .setEnergyConsumption(20)
        .setProcessingSpeed(3)
        .register(plugin);

        new IronGolemAssembler(categories.electricity, HoneymodItems.IRON_GOLEM_ASSEMBLER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.ADVANCED_CIRCUIT_BOARD, HoneymodItems.BLISTERING_INGOT_3, new ItemStack(Material.IRON_BLOCK), HoneymodItems.ANDROID_MEMORY_CORE, new ItemStack(Material.IRON_BLOCK), HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.CARBONADO_EDGED_CAPACITOR})
        .register(plugin);

        new WitherAssembler(categories.electricity, HoneymodItems.WITHER_ASSEMBLER, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.BLISTERING_INGOT_3, new ItemStack(Material.NETHER_STAR), HoneymodItems.BLISTERING_INGOT_3, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.ANDROID_MEMORY_CORE, HoneymodItems.WITHER_PROOF_OBSIDIAN, HoneymodItems.ELECTRIC_MOTOR, HoneymodItems.REINFORCED_ALLOY_INGOT, HoneymodItems.CARBONADO_EDGED_CAPACITOR})
        .register(plugin);
        
        new TapeMeasure(categories.usefulItems, HoneymodItems.TAPE_MEASURE, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] {HoneymodItems.SILICON, new ItemStack(Material.YELLOW_DYE), HoneymodItems.SILICON, new ItemStack(Material.YELLOW_DYE), new ItemStack(Material.STRING), new ItemStack(Material.YELLOW_DYE), HoneymodItems.GILDED_IRON, new ItemStack(Material.YELLOW_DYE), HoneymodItems.SILICON})
        .register(plugin);
        
        MinecraftVersion minecraftVersion = HoneymodPlugin.getMinecraftVersion();

        if (minecraftVersion.isAtLeast(MinecraftVersion.MINECRAFT_1_15)) {
            new SlimefunItem(categories.magicalArmor, HoneymodItems.BEE_HELMET, RecipeType.ARMOR_FORGE,
            new ItemStack[] {HoneymodItems.GOLD_8K, new ItemStack(Material.HONEY_BLOCK), HoneymodItems.GOLD_8K, new ItemStack(Material.HONEYCOMB_BLOCK), null, new ItemStack(Material.HONEYCOMB_BLOCK), null, null, null})
            .register(plugin);

            new BeeWings(categories.magicalArmor, HoneymodItems.BEE_WINGS, RecipeType.ARMOR_FORGE,
            new ItemStack[] {HoneymodItems.GOLD_8K, null, HoneymodItems.GOLD_8K, new ItemStack(Material.HONEYCOMB_BLOCK), new ItemStack(Material.ELYTRA), new ItemStack(Material.HONEYCOMB_BLOCK), new ItemStack(Material.HONEY_BLOCK), HoneymodItems.GOLD_8K, new ItemStack(Material.HONEY_BLOCK)})
            .register(plugin);

            new SlimefunItem(categories.magicalArmor, HoneymodItems.BEE_LEGGINGS, RecipeType.ARMOR_FORGE,
            new ItemStack[] {HoneymodItems.GOLD_8K, new ItemStack(Material.HONEY_BLOCK), HoneymodItems.GOLD_8K, new ItemStack(Material.HONEYCOMB_BLOCK), null, new ItemStack(Material.HONEYCOMB_BLOCK), new ItemStack(Material.HONEYCOMB_BLOCK), null, new ItemStack(Material.HONEYCOMB_BLOCK)})
            .register(plugin);

            new LongFallBoots(categories.magicalArmor, HoneymodItems.BEE_BOOTS, RecipeType.ARMOR_FORGE,
            new ItemStack[] {null, null, null, HoneymodItems.GOLD_8K, null, HoneymodItems.GOLD_8K, new ItemStack(Material.HONEY_BLOCK), null, new ItemStack(Material.HONEY_BLOCK)},
            new PotionEffect[] {new PotionEffect(PotionEffectType.JUMP, 300, 2)})
            .register(plugin);
        }

        if (minecraftVersion.isAtLeast(MinecraftVersion.MINECRAFT_1_16)) {
            new StrangeNetherGoo(categories.magicalResources, HoneymodItems.STRANGE_NETHER_GOO, RecipeType.BARTER_DROP,
            new ItemStack[] {null, null, null, null, new CustomItem(HeadTexture.PIGLIN_HEAD.getAsItemStack(), "&fPiglin"), null, null, null, null})
            .register(plugin);

            new VillagerRune(categories.magicalResources, HoneymodItems.VILLAGER_RUNE, RecipeType.ANCIENT_ALTAR, 
            new ItemStack[] {HoneymodItems.MAGIC_LUMP_3, HoneymodItems.MAGICAL_GLASS, new ItemStack(Material.CRYING_OBSIDIAN), HoneymodItems.STRANGE_NETHER_GOO, HoneymodItems.FIRE_RUNE, HoneymodItems.STRANGE_NETHER_GOO, new ItemStack(Material.CRYING_OBSIDIAN), HoneymodItems.MAGICAL_GLASS, HoneymodItems.MAGIC_LUMP_3},
            new SlimefunItemStack(HoneymodItems.VILLAGER_RUNE, 3))
            .register(plugin);
        }

        new ElytraCap(categories.magicalArmor, HoneymodItems.ELYTRA_CAP, RecipeType.ARMOR_FORGE,
        new ItemStack[]{new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.SLIME_BALL), HoneymodItems.ELYTRA_SCALE, HoneymodItems.ELYTRA_SCALE, HoneymodItems.ELYTRA_SCALE, new ItemStack(Material.SLIME_BALL), new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.SLIME_BALL)})
        .register(plugin);

        // @formatter:on
    }

    private static void registerArmorSet(Category category, ItemStack baseComponent, ItemStack[] items, String idSyntax, boolean vanilla, PotionEffect[][] effects, HoneymodAddon addon) {
        String[] components = new String[] { "_HELMET", "_CHESTPLATE", "_LEGGINGS", "_BOOTS" };
        List<ItemStack[]> recipes = new ArrayList<>();

        recipes.add(new ItemStack[] { baseComponent, baseComponent, baseComponent, baseComponent, null, baseComponent, null, null, null });
        recipes.add(new ItemStack[] { baseComponent, null, baseComponent, baseComponent, baseComponent, baseComponent, baseComponent, baseComponent, baseComponent });
        recipes.add(new ItemStack[] { baseComponent, baseComponent, baseComponent, baseComponent, null, baseComponent, baseComponent, null, baseComponent });
        recipes.add(new ItemStack[] { null, null, null, baseComponent, null, baseComponent, baseComponent, null, baseComponent });

        for (int i = 0; i < 4; i++) {
            if (vanilla) {
                new VanillaItem(category, items[i], idSyntax + components[i], RecipeType.ARMOR_FORGE, recipes.get(i)).register(addon);
            } else if (i < effects.length && effects[i].length > 0) {
                new HoneymodArmorPiece(category, new SlimefunItemStack(idSyntax + components[i], items[i]), RecipeType.ARMOR_FORGE, recipes.get(i), effects[i]).register(addon);
            } else {
                new SlimefunItem(category, new SlimefunItemStack(idSyntax + components[i], items[i]), RecipeType.ARMOR_FORGE, recipes.get(i)).register(addon);
            }
        }
    }

}