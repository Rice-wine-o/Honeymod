package me.spacetoastdev.honeymod.core.commands.subcommands;

import java.util.Collection;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;

import io.papermc.lib.PaperLib;
import me.spacetoastdev.honeymod.api.HoneymodAddon;
import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;

/**
 * This is our class for the /hm versions subcommand.
 * 
 * @author TheBusyBiscuit
 * @author Walshy
 *
 */
class VersionsCommand extends SubCommand {

    VersionsCommand(HoneymodPlugin plugin, HoneymodCommand cmd) {
        super(plugin, cmd, "versions", false);
    }

    @Override
    public void onExecute(@Nonnull CommandSender sender, @Nonnull String[] args) {
        if (sender.hasPermission("slimefun.command.versions") || sender instanceof ConsoleCommandSender) {
            /*
             * After all these years... Spigot still displays as "CraftBukkit".
             * so we will just fix this inconsistency for them :)
             */
            String serverSoftware = PaperLib.isSpigot() && !PaperLib.isPaper() ? "Spigot" : Bukkit.getName();
            ComponentBuilder builder = new ComponentBuilder();

            // @formatter:off
            builder.append("This Server uses the following setup of Honeymod:\n")
                .color(ChatColor.GRAY)
                .append(serverSoftware)
                .color(ChatColor.YELLOW)
                .append(" " + Bukkit.getVersion() + '\n')
                .color(ChatColor.YELLOW)
                .append("Honeymod ")
                .color(ChatColor.GOLD)
                .append(HoneymodPlugin.getVersion() + '\n')
                .color(ChatColor.YELLOW);
            // @formatter:on

            if (HoneymodPlugin.getMetricsService().getVersion() != null) {
                // @formatter:off
                builder.append("Metrics-Module ")
                    .color(ChatColor.GOLD)
                    .append("#" + HoneymodPlugin.getMetricsService().getVersion() + '\n')
                    .color(ChatColor.YELLOW);
                // @formatter:on
            }

            addJavaVersion(builder);

            if (HoneymodPlugin.getRegistry().isBackwardsCompatible()) {
                // @formatter:off
                HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(
                    "Backwards compatibility has a negative impact on performance!\n" +
                    "We recommend you to disable this setting unless your server still " +
                    "has legacy Slimefun items (from before summer 2019) in circulation."
                ));
                // @formatter:on

                builder.append("\nBackwards compatibility enabled!\n").color(ChatColor.RED).event(hoverEvent);
            }

            builder.append("\n").event((HoverEvent) null);
            addPluginVersions(builder);

            sender.spigot().sendMessage(builder.create());
        } else {
            HoneymodPlugin.getLocalization().sendMessage(sender, "messages.no-permission", true);
        }
    }

    private void addJavaVersion(@Nonnull ComponentBuilder builder) {
        String javaVer = System.getProperty("java.version");

        if (javaVer.startsWith("1.")) {
            javaVer = javaVer.substring(2);
        }

        // If it's like 11.0.1.3 or 8.0_275
        if (javaVer.indexOf('.') != -1) {
            javaVer = javaVer.substring(0, javaVer.indexOf('.'));
        }

        int version = Integer.parseInt(javaVer);

        if (version < 11) {
            // @formatter:off
            builder.append("Java " + version).color(ChatColor.RED)
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(
                    "Your Java version is out of date!\n!" +
                    "You should use Java 11 or higher.\n" +
                    "Paper will be dropping support for older versions with the release of Minecraft 1.17."
                )))
                .append("\n")
                .event((HoverEvent) null);
            // @formatter:on
        } else {
            builder.append("Java ").color(ChatColor.GOLD).append(version + "\n").color(ChatColor.YELLOW);
        }
    }

    private void addPluginVersions(@Nonnull ComponentBuilder builder) {
        Collection<Plugin> addons = HoneymodPlugin.getInstalledAddons();

        if (addons.isEmpty()) {
            builder.append("No Addons installed").color(ChatColor.GRAY).italic(true);
            return;
        }

        builder.append("Installed Addons: ").color(ChatColor.GRAY).append("(" + addons.size() + ")").color(ChatColor.DARK_GRAY);

        for (Plugin plugin : addons) {
            String version = plugin.getDescription().getVersion();

            HoverEvent hoverEvent = null;
            ClickEvent clickEvent = null;
            ChatColor primaryColor;
            ChatColor secondaryColor;

            if (Bukkit.getPluginManager().isPluginEnabled(plugin)) {
                primaryColor = ChatColor.GOLD;
                secondaryColor = ChatColor.YELLOW;
                String authors = String.join(", ", plugin.getDescription().getAuthors());

                if (plugin instanceof HoneymodAddon && ((HoneymodAddon) plugin).getBugTrackerURL() != null) {
                    // @formatter:off
                    hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(new ComponentBuilder()
                        .append("Author(s): ")
                        .append(authors)
                        .color(ChatColor.YELLOW)
                        .append("\n> Click here to go to their issues tracker")
                        .color(ChatColor.GOLD)
                        .create()
                    ));
                    // @formatter:on

                    clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, ((HoneymodAddon) plugin).getBugTrackerURL());
                } else {
                    // @formatter:off
                    hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(new ComponentBuilder()
                        .append("Author(s): ")
                        .append(authors)
                        .color(ChatColor.YELLOW)
                        .create()
                    ));
                    // @formatter:on
                }
            } else {
                primaryColor = ChatColor.RED;
                secondaryColor = ChatColor.DARK_RED;

                if (plugin instanceof HoneymodAddon && ((HoneymodAddon) plugin).getBugTrackerURL() != null) {
                    // @formatter:off
                    hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(new ComponentBuilder()
                        .append("This plugin is disabled.\nCheck the console for an error message.")
                        .color(ChatColor.RED)
                        .append("\n> Click here to report on their issues tracker")
                        .color(ChatColor.DARK_RED)
                        .create()
                    ));
                    // @formatter:on

                    HoneymodAddon addon = (HoneymodAddon) plugin;

                    if (addon.getBugTrackerURL() != null) {
                        clickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, addon.getBugTrackerURL());
                    }
                } else {
                    hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Plugin is disabled. Check the console for an error and report on their issues tracker."));
                }
            }

            // @formatter:off
            // We need to reset the hover event or it's added to all components
            builder.append("\n  " + plugin.getName())
                .color(primaryColor)
                .event(hoverEvent)
                .event(clickEvent)
                .append(" v" + version)
                .color(secondaryColor)
                .append("")
                .event((ClickEvent) null)
                .event((HoverEvent) null);
            // @formatter:on
        }
    }
}