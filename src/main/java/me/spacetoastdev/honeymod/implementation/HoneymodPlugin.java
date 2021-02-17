package me.spacetoastdev.honeymod.implementation;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.scheduler.BukkitTask;

import io.github.thebusybiscuit.cscorelib2.config.Config;
import io.github.thebusybiscuit.cscorelib2.protection.ProtectionManager;
import io.papermc.lib.PaperLib;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.MenuListener;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.UniversalBlockMenu;
import me.spacetoastdev.honeymod.api.MinecraftVersion;
import me.spacetoastdev.honeymod.api.HoneymodAddon;
import me.spacetoastdev.honeymod.api.exceptions.TagMisconfigurationException;
import me.spacetoastdev.honeymod.api.gps.GPSNetwork;
import me.spacetoastdev.honeymod.api.player.PlayerProfile;
import me.spacetoastdev.honeymod.core.HoneymodRegistry;
import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.networks.NetworkManager;
import me.spacetoastdev.honeymod.core.services.AutoSavingService;
import me.spacetoastdev.honeymod.core.services.BackupService;
import me.spacetoastdev.honeymod.core.services.BlockDataService;
import me.spacetoastdev.honeymod.core.services.CustomItemDataService;
import me.spacetoastdev.honeymod.core.services.CustomTextureService;
import me.spacetoastdev.honeymod.core.services.LocalizationService;
import me.spacetoastdev.honeymod.core.services.MetricsService;
import me.spacetoastdev.honeymod.core.services.MinecraftRecipeService;
import me.spacetoastdev.honeymod.core.services.PerWorldSettingsService;
import me.spacetoastdev.honeymod.core.services.PermissionsService;
import me.spacetoastdev.honeymod.core.services.UpdaterService;
import me.spacetoastdev.honeymod.core.services.github.GitHubService;
import me.spacetoastdev.honeymod.core.services.holograms.HologramsService;
import me.spacetoastdev.honeymod.core.services.profiler.HoneymodProfiler;
import me.spacetoastdev.honeymod.implementation.items.altar.AncientAltar;
import me.spacetoastdev.honeymod.implementation.items.altar.AncientPedestal;
import me.spacetoastdev.honeymod.implementation.items.backpacks.Cooler;
import me.spacetoastdev.honeymod.implementation.items.electric.reactors.Reactor;
import me.spacetoastdev.honeymod.implementation.items.magical.BeeWings;
import me.spacetoastdev.honeymod.implementation.items.tools.GrapplingHook;
import me.spacetoastdev.honeymod.implementation.items.weapons.SeismicAxe;
import me.spacetoastdev.honeymod.implementation.items.weapons.VampireBlade;
import me.spacetoastdev.honeymod.implementation.listeners.AncientAltarListener;
import me.spacetoastdev.honeymod.implementation.listeners.BackpackListener;
import me.spacetoastdev.honeymod.implementation.listeners.BeeWingsListener;
import me.spacetoastdev.honeymod.implementation.listeners.BlockListener;
import me.spacetoastdev.honeymod.implementation.listeners.BlockPhysicsListener;
import me.spacetoastdev.honeymod.implementation.listeners.ButcherAndroidListener;
import me.spacetoastdev.honeymod.implementation.listeners.CargoNodeListener;
import me.spacetoastdev.honeymod.implementation.listeners.CoolerListener;
import me.spacetoastdev.honeymod.implementation.listeners.DeathpointListener;
import me.spacetoastdev.honeymod.implementation.listeners.DebugFishListener;
import me.spacetoastdev.honeymod.implementation.listeners.DispenserListener;
import me.spacetoastdev.honeymod.implementation.listeners.ElytraImpactListener;
import me.spacetoastdev.honeymod.implementation.listeners.EnhancedFurnaceListener;
import me.spacetoastdev.honeymod.implementation.listeners.ExplosionsListener;
import me.spacetoastdev.honeymod.implementation.listeners.GadgetsListener;
import me.spacetoastdev.honeymod.implementation.listeners.GrapplingHookListener;
import me.spacetoastdev.honeymod.implementation.listeners.HopperListener;
import me.spacetoastdev.honeymod.implementation.listeners.ItemDropListener;
import me.spacetoastdev.honeymod.implementation.listeners.ItemPickupListener;
import me.spacetoastdev.honeymod.implementation.listeners.MultiBlockListener;
import me.spacetoastdev.honeymod.implementation.listeners.NetworkListener;
import me.spacetoastdev.honeymod.implementation.listeners.PlayerProfileListener;
import me.spacetoastdev.honeymod.implementation.listeners.SeismicAxeListener;
import me.spacetoastdev.honeymod.implementation.listeners.HoneymodBootsListener;
import me.spacetoastdev.honeymod.implementation.listeners.HoneymodBowListener;
import me.spacetoastdev.honeymod.implementation.listeners.HoneymodGuideListener;
import me.spacetoastdev.honeymod.implementation.listeners.HoneymodItemConsumeListener;
import me.spacetoastdev.honeymod.implementation.listeners.HoneymodInteractListener;
import me.spacetoastdev.honeymod.implementation.listeners.SoulboundListener;
import me.spacetoastdev.honeymod.implementation.listeners.TalismanListener;
import me.spacetoastdev.honeymod.implementation.listeners.VampireBladeListener;
import me.spacetoastdev.honeymod.implementation.listeners.VillagerTradingListener;
import me.spacetoastdev.honeymod.implementation.listeners.WorldListener;
import me.spacetoastdev.honeymod.implementation.listeners.crafting.AnvilListener;
import me.spacetoastdev.honeymod.implementation.listeners.crafting.BrewingStandListener;
import me.spacetoastdev.honeymod.implementation.listeners.crafting.CartographyTableListener;
import me.spacetoastdev.honeymod.implementation.listeners.crafting.CauldronListener;
import me.spacetoastdev.honeymod.implementation.listeners.crafting.CraftingTableListener;
import me.spacetoastdev.honeymod.implementation.listeners.crafting.GrindstoneListener;
import me.spacetoastdev.honeymod.implementation.listeners.entity.BeeListener;
import me.spacetoastdev.honeymod.implementation.listeners.entity.EntityInteractionListener;
import me.spacetoastdev.honeymod.implementation.listeners.entity.FireworksListener;
import me.spacetoastdev.honeymod.implementation.listeners.entity.IronGolemListener;
import me.spacetoastdev.honeymod.implementation.listeners.entity.MobDropListener;
import me.spacetoastdev.honeymod.implementation.listeners.entity.PiglinListener;
import me.spacetoastdev.honeymod.implementation.listeners.entity.WitherListener;
import me.spacetoastdev.honeymod.implementation.resources.GEOResourcesSetup;
import me.spacetoastdev.honeymod.implementation.setup.PostSetup;
import me.spacetoastdev.honeymod.implementation.setup.ResearchSetup;
import me.spacetoastdev.honeymod.implementation.setup.HoneymodItemSetup;
import me.spacetoastdev.honeymod.implementation.tasks.ArmorTask;
import me.spacetoastdev.honeymod.implementation.tasks.HoneymodStartupTask;
import me.spacetoastdev.honeymod.implementation.tasks.TickerTask;
import me.spacetoastdev.honeymod.integrations.IntegrationsManager;
import me.spacetoastdev.honeymod.utils.NumberUtils;
import me.spacetoastdev.honeymod.utils.tags.HoneymodTag;

/**
 * This is the main class of Slimefun.
 * This is where all the magic starts, take a look around.
 *
 * @author TheBusyBiscuit
 */
public final class HoneymodPlugin extends JavaPlugin implements HoneymodAddon {

    /**
     * Our static instance of {@link HoneymodPlugin}.
     * Make sure to clean this up in {@link #onDisable()} !
     */
    private static HoneymodPlugin instance;

    /**
     * Keep track of which {@link MinecraftVersion} we are on.
     */
    private MinecraftVersion minecraftVersion = MinecraftVersion.UNKNOWN;

    /**
     * Keep track of whether this is a fresh install or a regular boot up.
     */
    private boolean isNewlyInstalled = false;

    // Various things we need
    private final HoneymodRegistry registry = new HoneymodRegistry();
    private final HoneymodCommand command = new HoneymodCommand(this);
    private final TickerTask ticker = new TickerTask();

    // Services - Systems that fulfill certain tasks, treat them as a black box
    private final CustomItemDataService itemDataService = new CustomItemDataService(this, "honeymod_item");
    private final BlockDataService blockDataService = new BlockDataService(this, "honeymod_block");
    private final CustomTextureService textureService = new CustomTextureService(new Config(this, "item-models.yml"));
    private final GitHubService gitHubService = new GitHubService("Slimefun/Slimefun4");
    private final UpdaterService updaterService = new UpdaterService(this, getDescription().getVersion(), getFile());
    private final MetricsService metricsService = new MetricsService(this);
    private final AutoSavingService autoSavingService = new AutoSavingService();
    private final BackupService backupService = new BackupService();
    private final PermissionsService permissionsService = new PermissionsService(this);
    private final PerWorldSettingsService worldSettingsService = new PerWorldSettingsService(this);
    private final MinecraftRecipeService recipeService = new MinecraftRecipeService(this);
    private final HologramsService hologramsService = new HologramsService(this);

    // Some other things we need
    private final IntegrationsManager integrations = new IntegrationsManager(this);
    private final HoneymodProfiler profiler = new HoneymodProfiler();
    private final GPSNetwork gpsNetwork = new GPSNetwork(this);

    // Even more things we need
    private NetworkManager networkManager;
    private LocalizationService local;

    // Important config files for Slimefun
    private final Config config = new Config(this);
    private final Config items = new Config(this, "Items.yml");
    private final Config researches = new Config(this, "Researches.yml");

    // Listeners that need to be accessed elsewhere
    private final GrapplingHookListener grapplingHookListener = new GrapplingHookListener();
    private final BackpackListener backpackListener = new BackpackListener();
    private final HoneymodBowListener bowListener = new HoneymodBowListener();

    /**
     * Our default constructor for {@link HoneymodPlugin}.
     */
    public HoneymodPlugin() {
        super();
    }

    /**
     * This constructor is invoked in Unit Test environments only.
     * 
     * @param loader
     *            Our {@link JavaPluginLoader}
     * @param description
     *            A {@link PluginDescriptionFile}
     * @param dataFolder
     *            The data folder
     * @param file
     *            A {@link File} for this {@link Plugin}
     */
    @ParametersAreNonnullByDefault
    public HoneymodPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);

        // This is only invoked during a Unit Test
        minecraftVersion = MinecraftVersion.UNIT_TEST;
    }

    /**
     * This is called when the {@link Plugin} has been loaded and enabled on a {@link Server}.
     */
    @Override
    public void onEnable() {
        setInstance(this);

        if (isUnitTest()) {
            // We handle Unit Tests seperately.
            onUnitTestStart();
        } else if (isVersionUnsupported()) {
            // We wanna ensure that the Server uses a compatible version of Minecraft.
            getServer().getPluginManager().disablePlugin(this);
        } else {
            // The Environment has been validated.
            onPluginStart();
        }
    }

    /**
     * This is our start method for a Unit Test environment.
     */
    private void onUnitTestStart() {
        local = new LocalizationService(this, "", null);
        networkManager = new NetworkManager(200);
        command.register();
        registry.load(this, config);
        loadTags();
    }

    /**
     * This is our start method for a correct Slimefun installation.
     */
    private void onPluginStart() {
        long timestamp = System.nanoTime();

        // Check if Paper (<3) is installed
        if (PaperLib.isPaper()) {
            getLogger().log(Level.INFO, "Paper was detected! Performance optimizations have been applied.");
        } else {
            PaperLib.suggestPaper(this);
        }

        // Disabling backwards-compatibility for fresh Slimefun installs
        if (!new File("data-storage/Honeymod").exists()) {
            config.setValue("options.backwards-compatibility", false);
            config.save();

            isNewlyInstalled = true;
        }

        // Creating all necessary Folders
        getLogger().log(Level.INFO, "Creating directories...");
        createDirectories();
        registry.load(this, config);

        // Set up localization
        getLogger().log(Level.INFO, "Loading language files...");
        local = new LocalizationService(this, config.getString("options.chat-prefix"), config.getString("options.language"));

        int networkSize = config.getInt("networks.max-size");

        // Make sure that the network size is a valid input
        if (networkSize < 1) {
            getLogger().log(Level.WARNING, "Your 'networks.max-size' setting is misconfigured! It must be at least 1, it was set to: {0}", networkSize);
            networkSize = 1;
        }

        networkManager = new NetworkManager(networkSize, config.getBoolean("networks.enable-visualizer"), config.getBoolean("networks.delete-excess-items"));

        // Setting up bStats
        new Thread(metricsService::start, "Honeymod Metrics").start();

        // Starting the Auto-Updater
        if (config.getBoolean("options.auto-update")) {
            getLogger().log(Level.INFO, "Starting Auto-Updater...");
            updaterService.start();
        } else {
            updaterService.disable();
        }

        // Registering all GEO Resources
        getLogger().log(Level.INFO, "Loading GEO-Resources...");
        GEOResourcesSetup.setup();

        getLogger().log(Level.INFO, "Loading Tags...");
        loadTags();

        getLogger().log(Level.INFO, "Loading items...");
        loadItems();

        getLogger().log(Level.INFO, "Loading researches...");
        loadResearches();

        registry.setResearchingEnabled(getResearchCfg().getBoolean("enable-researching"));
        PostSetup.setupWiki();

        getLogger().log(Level.INFO, "Registering listeners...");
        registerListeners();

        // Initiating various Stuff and all items with a slight delay (0ms after the Server finished loading)
        runSync(new HoneymodStartupTask(this, () -> {
            textureService.register(registry.getAllSlimefunItems(), true);
            permissionsService.register(registry.getAllSlimefunItems(), true);

            // This try/catch should prevent buggy Spigot builds from blocking item loading
            try {
                recipeService.refresh();
            } catch (Exception | LinkageError x) {
                getLogger().log(Level.SEVERE, x, () -> "An Exception occurred while iterating through the Recipe list on Minecraft Version " + minecraftVersion.getName() + " (Honeymod v" + getVersion() + ")");
            }

        }), 0);

        // Setting up our commands
        try {
            command.register();
        } catch (Exception | LinkageError x) {
            getLogger().log(Level.SEVERE, "An Exception occurred while registering the /honeymod command", x);
        }

        // Armor Update Task
        if (config.getBoolean("options.enable-armor-effects")) {
            boolean radioactiveFire = config.getBoolean("options.burn-players-when-radioactive");
            getServer().getScheduler().runTaskTimerAsynchronously(this, new ArmorTask(radioactiveFire), 0L, config.getInt("options.armor-update-interval") * 20L);
        }

        // Starting our tasks
        autoSavingService.start(this, config.getInt("options.auto-save-delay-in-minutes"));
        hologramsService.start();
        ticker.start(this);

        // Loading integrations
        getLogger().log(Level.INFO, "Loading Third-Party plugin integrations...");
        integrations.start();
        gitHubService.start(this);

        // Hooray!
        getLogger().log(Level.INFO, "Honeymod has finished loading in {0}", getStartupTime(timestamp));
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Slimefun/Slimefun4/issues";
    }

    /**
     * This method gets called when the {@link Plugin} gets disabled.
     * Most often it is called when the {@link Server} is shutting down or reloading.
     */
    @Override
    public void onDisable() {
        // Slimefun never loaded successfully, so we don't even bother doing stuff here
        if (instance() == null || minecraftVersion == MinecraftVersion.UNIT_TEST) {
            return;
        }

        // Cancel all tasks from this plugin immediately
        Bukkit.getScheduler().cancelTasks(this);

        // Finishes all started movements/removals of block data
        try {
            ticker.halt();
            ticker.run();
        } catch (Exception x) {
            getLogger().log(Level.SEVERE, x, () -> "Something went wrong while disabling the ticker task for Honeymod v" + getDescription().getVersion());
        }

        // Kill our Profiler Threads
        profiler.kill();

        // Save all Player Profiles that are still in memory
        PlayerProfile.iterator().forEachRemaining(profile -> {
            if (profile.isDirty()) {
                profile.save();
            }
        });

        // Save all registered Worlds
        for (Map.Entry<String, BlockStorage> entry : getRegistry().getWorlds().entrySet()) {
            try {
                entry.getValue().saveAndRemove();
            } catch (Exception x) {
                getLogger().log(Level.SEVERE, x, () -> "An Error occurred while saving Honeymod-Blocks in World '" + entry.getKey() + "' for Honeymod " + getVersion());
            }
        }

        // Save all "universal" inventories (ender chests for example)
        for (UniversalBlockMenu menu : registry.getUniversalInventories().values()) {
            menu.save();
        }

        // Create a new backup zip
        backupService.run();

        // Close and unload any resources from our Metrics Service
        metricsService.cleanUp();

        // Terminate our Plugin instance
        setInstance(null);

        // Clean up any static fields
        cleanUp();

        /**
         * Close all inventories on the server to prevent item dupes
         * (Incase some idiot uses /reload)
         */
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.closeInventory();
        }
    }

    /**
     * This is a private internal method to set the de-facto instance of {@link HoneymodPlugin}.
     * Having this as a seperate method ensures the seperation between static and non-static fields.
     * It also makes sonarcloud happy :)
     * Only ever use it during {@link #onEnable()} or {@link #onDisable()}.
     * 
     * @param pluginInstance
     *            Our instance of {@link HoneymodPlugin} or null
     */
    private static void setInstance(@Nullable HoneymodPlugin pluginInstance) {
        instance = pluginInstance;
    }

    /**
     * This returns the time it took to load Slimefun (given a starting point).
     * 
     * @param timestamp
     *            The time at which we started to load Slimefun.
     * 
     * @return The total time it took to load Slimefun (in ms or s)
     */
    @Nonnull
    private String getStartupTime(long timestamp) {
        long ms = (System.nanoTime() - timestamp) / 1000000;

        if (ms > 1000) {
            return NumberUtils.roundDecimalNumber(ms / 1000.0) + 's';
        } else {
            return NumberUtils.roundDecimalNumber(ms) + "ms";
        }
    }

    /**
     * Cleaning up our static fields prevents memory leaks from a reload.
     * 
     * @deprecated These static Maps should really be removed at some point...
     */
    @Deprecated
    private static void cleanUp() {
        AContainer.processing = null;
        AContainer.progress = null;

        AGenerator.processing = null;
        AGenerator.progress = null;

        Reactor.processing = null;
        Reactor.progress = null;
    }

    /**
     * This method checks if this is currently running in a unit test
     * environment.
     * 
     * @return Whether we are inside a unit test
     */
    public boolean isUnitTest() {
        return minecraftVersion == MinecraftVersion.UNIT_TEST;
    }

    /**
     * This method checks for the {@link MinecraftVersion} of the {@link Server}.
     * If the version is unsupported, a warning will be printed to the console.
     *
     * @return Whether the {@link MinecraftVersion} is unsupported
     */
    private boolean isVersionUnsupported() {
        try {
            // First check if they still use the unsupported CraftBukkit software.
            if (!PaperLib.isSpigot() && Bukkit.getName().equals("CraftBukkit")) {
                getLogger().log(Level.SEVERE, "###############################################");
                getLogger().log(Level.SEVERE, "### Honeymod was not installed correctly!");
                getLogger().log(Level.SEVERE, "### CraftBukkit is no longer supported!");
                getLogger().log(Level.SEVERE, "###");
                getLogger().log(Level.SEVERE, "### Honeymod requires you to use Spigot, Paper or");
                getLogger().log(Level.SEVERE, "### any supported fork of Spigot or Paper.");
                getLogger().log(Level.SEVERE, "### (We recommend Paper)");
                getLogger().log(Level.SEVERE, "###############################################");

                return true;
            }

            // Now check the actual Version of Minecraft
            int version = PaperLib.getMinecraftVersion();

            if (version > 0) {
                // Check all supported versions of Minecraft
                for (MinecraftVersion supportedVersion : MinecraftVersion.values()) {
                    if (supportedVersion.isMinecraftVersion(version)) {
                        minecraftVersion = supportedVersion;
                        return false;
                    }
                }

                // Looks like you are using an unsupported Minecraft Version
                getLogger().log(Level.SEVERE, "#############################################");
                getLogger().log(Level.SEVERE, "### Honeymod was not installed correctly!");
                getLogger().log(Level.SEVERE, "### You are using the wrong version of Minecraft!");
                getLogger().log(Level.SEVERE, "###");
                getLogger().log(Level.SEVERE, "### You are using Minecraft 1.{0}.x", version);
                getLogger().log(Level.SEVERE, "### but Honeymod {0} requires you to be using", getDescription().getVersion());
                getLogger().log(Level.SEVERE, "### Minecraft {0}", String.join(" / ", getSupportedVersions()));
                getLogger().log(Level.SEVERE, "#############################################");
                return true;
            } else {
                getLogger().log(Level.WARNING, "We could not determine the version of Minecraft you were using? ({0})", Bukkit.getVersion());

                /*
                 * If we are unsure about it, we will assume "supported".
                 * They could be using a non-Bukkit based Software which still
                 * might support Bukkit-based plugins.
                 * Use at your own risk in this case.
                 */
                return false;
            }
        } catch (Exception | LinkageError x) {
            getLogger().log(Level.SEVERE, x, () -> "Error: Could not determine Environment or version of Minecraft for Honeymod v" + getDescription().getVersion());

            // We assume "unsupported" if something went wrong.
            return true;
        }
    }

    /**
     * This private method gives us a {@link Collection} of every {@link MinecraftVersion}
     * that Slimefun is compatible with (as a {@link String} representation).
     * <p>
     * Example:
     * 
     * <pre>
     * { 1.14.x, 1.15.x, 1.16.x }
     * </pre>
     * 
     * @return A {@link Collection} of all compatible minecraft versions as strings
     */
    @Nonnull
    private Collection<String> getSupportedVersions() {
        List<String> list = new ArrayList<>();

        for (MinecraftVersion version : MinecraftVersion.values()) {
            if (!version.isVirtual()) {
                list.add(version.getName());
            }
        }

        return list;
    }

    /**
     * This method creates all necessary directories (and sub directories) for Slimefun.
     */
    private void createDirectories() {
        String[] storageFolders = { "Players", "blocks", "stored-blocks", "stored-inventories", "stored-chunks", "universal-inventories", "waypoints", "block-backups" };
        String[] pluginFolders = { "scripts", "error-reports", "cache/github", "world-settings" };

        for (String folder : storageFolders) {
            File file = new File("data-storage/Honeymod", folder);

            if (!file.exists()) {
                file.mkdirs();
            }
        }

        for (String folder : pluginFolders) {
            File file = new File("plugins/Honeymod", folder);

            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    /**
     * This method registers all of our {@link Listener Listeners}.
     */
    private void registerListeners() {
        // Old deprecated CS-CoreLib Listener
        new MenuListener(this);

        new HoneymodBootsListener(this);
        new HoneymodInteractListener(this);
        new HoneymodItemConsumeListener(this);
        new BlockPhysicsListener(this);
        new CargoNodeListener(this);
        new MultiBlockListener(this);
        new GadgetsListener(this);
        new DispenserListener(this);
        new BlockListener(this);
        new EnhancedFurnaceListener(this);
        new ItemPickupListener(this);
        new ItemDropListener(this);
        new DeathpointListener(this);
        new ExplosionsListener(this);
        new DebugFishListener(this);
        new FireworksListener(this);
        new WitherListener(this);
        new IronGolemListener(this);
        new EntityInteractionListener(this);
        new MobDropListener(this);
        new VillagerTradingListener(this);
        new ElytraImpactListener(this);
        new CraftingTableListener(this);
        new AnvilListener(this);
        new BrewingStandListener(this);
        new CauldronListener(this);
        new GrindstoneListener(this);
        new CartographyTableListener(this);
        new ButcherAndroidListener(this);
        new NetworkListener(this, networkManager);
        new HopperListener(this);
        new TalismanListener(this);
        new SoulboundListener(this);

        // Bees were added in 1.15
        if (minecraftVersion.isAtLeast(MinecraftVersion.MINECRAFT_1_15)) {
            new BeeListener(this);
            new BeeWingsListener(this, (BeeWings) HoneymodItems.BEE_WINGS.getItem());
        }

        // Piglins were added in 1.16
        if (minecraftVersion.isAtLeast(MinecraftVersion.MINECRAFT_1_16)) {
            new PiglinListener(this);
        }

        // Item-specific Listeners
        new VampireBladeListener(this, (VampireBlade) HoneymodItems.BLADE_OF_VAMPIRES.getItem());
        new CoolerListener(this, (Cooler) HoneymodItems.COOLER.getItem());
        new SeismicAxeListener(this, (SeismicAxe) HoneymodItems.SEISMIC_AXE.getItem());
        new AncientAltarListener(this, (AncientAltar) HoneymodItems.ANCIENT_ALTAR.getItem(), (AncientPedestal) HoneymodItems.ANCIENT_PEDESTAL.getItem());
        grapplingHookListener.register(this, (GrapplingHook) HoneymodItems.GRAPPLING_HOOK.getItem());
        bowListener.register(this);
        backpackListener.register(this);

        // Handle Slimefun Guide being given on Join
        new HoneymodGuideListener(this, config.getBoolean("guide.receive-on-first-join"));

        // Load/Unload Worlds in Slimefun
        new WorldListener(this);

        // Clear the Slimefun Guide History upon Player Leaving
        new PlayerProfileListener(this);
    }

    /**
     * This (re)loads every {@link HoneymodTag}.
     */
    private void loadTags() {
        for (HoneymodTag tag : HoneymodTag.values()) {
            try {
                // Only reload "empty" (or unloaded) Tags
                if (tag.isEmpty()) {
                    tag.reload();
                }
            } catch (TagMisconfigurationException e) {
                getLogger().log(Level.SEVERE, e, () -> "Failed to load Tag: " + tag.name());
            }
        }
    }

    /**
     * This loads all of our items.
     */
    private void loadItems() {
        try {
            HoneymodItemSetup.setup(this);
        } catch (Exception | LinkageError x) {
            getLogger().log(Level.SEVERE, x, () -> "An Error occurred while initializing HoneymodItems for Honeymod " + getVersion());
        }
    }

    /**
     * This loads our researches.
     */
    private void loadResearches() {
        try {
            ResearchSetup.setupResearches();
        } catch (Exception | LinkageError x) {
            getLogger().log(Level.SEVERE, x, () -> "An Error occurred while initializing Honeymod Researches for Honeymod " + getVersion());
        }
    }

    /**
     * This private static method allows us to throw a proper {@link Exception}
     * whenever someone tries to access a static method while the instance is null.
     * This happens when the method is invoked before {@link #onEnable()} or after {@link #onDisable()}.
     * <p>
     * Use it whenever a null check is needed to avoid a non-descriptive {@link NullPointerException}.
     */
    private static void validateInstance() {
        if (instance == null) {
            throw new IllegalStateException("Cannot invoke static method, Honeymod instance is null.");
        }
    }

    /**
     * This returns the global instance of {@link HoneymodPlugin}.
     * This may return null if the {@link Plugin} was disabled.
     *
     * @return The {@link HoneymodPlugin} instance
     */
    @Nullable
    public static HoneymodPlugin instance() {
        return instance;
    }

    /**
     * This returns the {@link Logger} instance that Slimefun uses.
     * <p>
     * <strong>Any {@link HoneymodAddon} should use their own {@link Logger} instance!</strong>
     * 
     * @return Our {@link Logger} instance
     */
    @Nonnull
    public static Logger logger() {
        validateInstance();
        return instance.getLogger();
    }

    /**
     * This returns the version of Slimefun that is currently installed.
     *
     * @return The currently installed version of Slimefun
     */
    @Nonnull
    public static String getVersion() {
        validateInstance();
        return instance.getDescription().getVersion();
    }

    @Nonnull
    public static Config getCfg() {
        validateInstance();
        return instance.config;
    }

    @Nonnull
    public static Config getResearchCfg() {
        validateInstance();
        return instance.researches;
    }

    @Nonnull
    public static Config getItemCfg() {
        validateInstance();
        return instance.items;
    }

    @Nonnull
    public static GPSNetwork getGPSNetwork() {
        validateInstance();
        return instance.gpsNetwork;
    }

    @Nonnull
    public static TickerTask getTickerTask() {
        validateInstance();
        return instance.ticker;
    }

    /**
     * This returns the {@link LocalizationService} of Slimefun.
     *
     * @return The {@link LocalizationService} of Slimefun
     */
    @Nonnull
    public static LocalizationService getLocalization() {
        validateInstance();
        return instance.local;
    }

    /**
     * This method returns out {@link MinecraftRecipeService} for Slimefun.
     * This service is responsible for finding/identifying {@link Recipe Recipes}
     * from vanilla Minecraft.
     * 
     * @return Slimefun's {@link MinecraftRecipeService} instance
     */
    @Nonnull
    public static MinecraftRecipeService getMinecraftRecipeService() {
        validateInstance();
        return instance.recipeService;
    }

    @Nonnull
    public static CustomItemDataService getItemDataService() {
        validateInstance();
        return instance.itemDataService;
    }

    @Nonnull
    public static CustomTextureService getItemTextureService() {
        validateInstance();
        return instance.textureService;
    }

    @Nonnull
    public static PermissionsService getPermissionsService() {
        validateInstance();
        return instance.permissionsService;
    }

    @Nonnull
    public static BlockDataService getBlockDataService() {
        validateInstance();
        return instance.blockDataService;
    }

    @Nonnull
    public static PerWorldSettingsService getWorldSettingsService() {
        validateInstance();
        return instance.worldSettingsService;
    }

    @Nonnull
    public static HologramsService getHologramsService() {
        validateInstance();
        return instance.hologramsService;
    }

    /**
     * This returns our instance of {@link IntegrationsManager}.
     * This is responsible for managing any integrations with third party {@link Plugin plugins}.
     * 
     * @return Our instance of {@link IntegrationsManager}
     */
    @Nonnull
    public static IntegrationsManager getIntegrations() {
        validateInstance();
        return instance.integrations;
    }

    /**
     * This returns out instance of the {@link ProtectionManager}.
     * This bridge is used to hook into any third-party protection {@link Plugin}.
     * 
     * @return Our instanceof of the {@link ProtectionManager}
     */
    @Nonnull
    public static ProtectionManager getProtectionManager() {
        return getIntegrations().getProtectionManager();
    }

    /**
     * This method returns the {@link UpdaterService} of Slimefun.
     * It is used to handle automatic updates.
     *
     * @return The {@link UpdaterService} for Slimefun
     */
    @Nonnull
    public static UpdaterService getUpdater() {
        validateInstance();
        return instance.updaterService;
    }

    /**
     * This method returns the {@link MetricsService} of Slimefun.
     * It is used to handle sending metric information to bStats.
     *
     * @return The {@link MetricsService} for Slimefun
     */
    @Nonnull
    public static MetricsService getMetricsService() {
        validateInstance();
        return instance.metricsService;
    }

    /**
     * This method returns the {@link GitHubService} of Slimefun.
     * It is used to retrieve data from GitHub repositories.
     *
     * @return The {@link GitHubService} for Slimefun
     */
    @Nonnull
    public static GitHubService getGitHubService() {
        validateInstance();
        return instance.gitHubService;
    }

    /**
     * This returns our {@link NetworkManager} which is responsible
     * for handling the Cargo and Energy networks.
     * 
     * @return Our {@link NetworkManager} instance
     */
    @Nonnull
    public static NetworkManager getNetworkManager() {
        validateInstance();
        return instance.networkManager;
    }

    @Nonnull
    public static HoneymodRegistry getRegistry() {
        validateInstance();
        return instance.registry;
    }

    @Nonnull
    public static GrapplingHookListener getGrapplingHookListener() {
        validateInstance();
        return instance.grapplingHookListener;
    }

    @Nonnull
    public static BackpackListener getBackpackListener() {
        validateInstance();
        return instance.backpackListener;
    }

    @Nonnull
    public static HoneymodBowListener getBowListener() {
        validateInstance();
        return instance.bowListener;
    }

    /**
     * The {@link Command} that was added by Slimefun.
     *
     * @return Slimefun's command
     */
    @Nonnull
    public static HoneymodCommand getCommand() {
        validateInstance();
        return instance.command;
    }

    /**
     * This returns our instance of the {@link HoneymodProfiler}, a tool that is used
     * to analyse performance and lag.
     *
     * @return The {@link HoneymodProfiler}
     */
    @Nonnull
    public static HoneymodProfiler getProfiler() {
        validateInstance();
        return instance.profiler;
    }

    /**
     * This returns the currently installed version of Minecraft.
     *
     * @return The current version of Minecraft
     */
    @Nonnull
    public static MinecraftVersion getMinecraftVersion() {
        validateInstance();
        return instance.minecraftVersion;
    }

    /**
     * This method returns whether this version of Slimefun was newly installed.
     * It will return true if this {@link Server} uses Slimefun for the very first time.
     *
     * @return Whether this is a new installation of Slimefun
     */
    public static boolean isNewlyInstalled() {
        validateInstance();
        return instance.isNewlyInstalled;
    }

    /**
     * This method returns a {@link Set} of every {@link Plugin} that lists Slimefun
     * as a required or optional dependency.
     * <p>
     * We will just assume this to be a list of our addons.
     *
     * @return A {@link Set} of every {@link Plugin} that is dependent on Slimefun
     */
    @Nonnull
    public static Set<Plugin> getInstalledAddons() {
        validateInstance();

        String pluginName = instance.getName();

        // @formatter:off
        return Arrays.stream(instance.getServer().getPluginManager().getPlugins())
                .filter(plugin -> {
                    PluginDescriptionFile description = plugin.getDescription();
                    return description.getDepend().contains(pluginName) || description.getSoftDepend().contains(pluginName);
                }).collect(Collectors.toSet());
        // @formatter:on
    }

    /**
     * This method schedules a delayed synchronous task for Slimefun.
     * <strong>For Slimefun only, not for addons.</strong>
     * 
     * This method should only be invoked by Slimefun itself.
     * Addons must schedule their own tasks using their own {@link Plugin} instance.
     * 
     * @param runnable
     *            The {@link Runnable} to run
     * @param delay
     *            The delay for this task
     * 
     * @return The resulting {@link BukkitTask} or null if Slimefun was disabled
     */
    @Nullable
    public static BukkitTask runSync(@Nonnull Runnable runnable, long delay) {
        Validate.notNull(runnable, "Cannot run null");
        Validate.isTrue(delay >= 0, "The delay cannot be negative");

        if (getMinecraftVersion() == MinecraftVersion.UNIT_TEST) {
            runnable.run();
            return null;
        }

        if (instance == null || !instance.isEnabled()) {
            return null;
        }

        return instance.getServer().getScheduler().runTaskLater(instance, runnable, delay);
    }

    /**
     * This method schedules a synchronous task for Slimefun.
     * <strong>For Slimefun only, not for addons.</strong>
     * 
     * This method should only be invoked by Slimefun itself.
     * Addons must schedule their own tasks using their own {@link Plugin} instance.
     * 
     * @param runnable
     *            The {@link Runnable} to run
     * 
     * @return The resulting {@link BukkitTask} or null if Slimefun was disabled
     */
    @Nullable
    public static BukkitTask runSync(@Nonnull Runnable runnable) {
        Validate.notNull(runnable, "Cannot run null");

        if (getMinecraftVersion() == MinecraftVersion.UNIT_TEST) {
            runnable.run();
            return null;
        }

        if (instance == null || !instance.isEnabled()) {
            return null;
        }

        return instance.getServer().getScheduler().runTask(instance, runnable);
    }

}