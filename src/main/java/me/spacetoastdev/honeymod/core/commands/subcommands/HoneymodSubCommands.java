package me.spacetoastdev.honeymod.core.commands.subcommands;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import me.spacetoastdev.honeymod.core.commands.HoneymodCommand;
import me.spacetoastdev.honeymod.core.commands.SubCommand;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;

/**
 * This class holds the implementations of every {@link SubCommand}.
 * The implementations themselves are package-private, this class only provides
 * a static setup method
 * 
 * @author TheBusyBiscuit
 *
 */
public final class HoneymodSubCommands {

    private HoneymodSubCommands() {}

    public static Collection<SubCommand> getAllCommands(HoneymodCommand cmd) {
        HoneymodPlugin plugin = cmd.getPlugin();
        List<SubCommand> commands = new LinkedList<>();

        commands.add(new HelpCommand(plugin, cmd));
        commands.add(new VersionsCommand(plugin, cmd));
        commands.add(new CheatCommand(plugin, cmd));
        commands.add(new GuideCommand(plugin, cmd));
        commands.add(new GiveCommand(plugin, cmd));
        commands.add(new ResearchCommand(plugin, cmd));
        commands.add(new StatsCommand(plugin, cmd));
        commands.add(new TimingsCommand(plugin, cmd));
        commands.add(new TeleporterCommand(plugin, cmd));
        commands.add(new OpenGuideCommand(plugin, cmd));
        commands.add(new SearchCommand(plugin, cmd));
        commands.add(new DebugFishCommand(plugin, cmd));
        commands.add(new BackpackCommand(plugin, cmd));
        commands.add(new ChargeCommand(plugin, cmd));

        return commands;
    }
}
