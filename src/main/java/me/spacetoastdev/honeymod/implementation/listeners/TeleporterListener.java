package me.spacetoastdev.honeymod.implementation.listeners;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.elevator.ElevatorPlate;
import me.spacetoastdev.honeymod.implementation.items.gps.Teleporter;

public class TeleporterListener implements Listener {

    private final BlockFace[] faces = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };

    public TeleporterListener(@Nonnull HoneymodPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPressurePlateEnter(PlayerInteractEvent e) {
        if (e.getAction() != Action.PHYSICAL || e.getClickedBlock() == null) {
            return;
        }

        String id = BlockStorage.checkID(e.getClickedBlock());

        if (id == null) {
            return;
        }

        if (isTeleporterPad(id, e.getClickedBlock(), e.getPlayer().getUniqueId())) {
            SlimefunItem teleporter = BlockStorage.check(e.getClickedBlock().getRelative(BlockFace.DOWN));

            if (teleporter instanceof Teleporter && checkForPylons(e.getClickedBlock().getRelative(BlockFace.DOWN))) {
                Block block = e.getClickedBlock().getRelative(BlockFace.DOWN);
                UUID owner = UUID.fromString(BlockStorage.getLocationInfo(block.getLocation(), "owner"));
                HoneymodPlugin.getGPSNetwork().getTeleportationManager().openTeleporterGUI(e.getPlayer(), owner, block, HoneymodPlugin.getGPSNetwork().getNetworkComplexity(owner));
            }
        } else if (id.equals(HoneymodItems.ELEVATOR_PLATE.getItemId())) {
            ElevatorPlate elevator = ((ElevatorPlate) HoneymodItems.ELEVATOR_PLATE.getItem());
            elevator.openInterface(e.getPlayer(), e.getClickedBlock());
        }
    }

    @ParametersAreNonnullByDefault
    private boolean isTeleporterPad(String id, Block b, UUID uuid) {
        if (id.equals(HoneymodItems.GPS_ACTIVATION_DEVICE_SHARED.getItemId())) {
            return true;
        } else if (id.equals(HoneymodItems.GPS_ACTIVATION_DEVICE_PERSONAL.getItemId())) {
            return BlockStorage.getLocationInfo(b.getLocation(), "owner").equals(uuid.toString());
        } else {
            return false;
        }
    }

    private boolean checkForPylons(@Nonnull Block teleporter) {
        for (BlockFace face : faces) {
            if (!BlockStorage.check(teleporter.getRelative(face), HoneymodItems.GPS_TELEPORTER_PYLON.getItemId())) {
                return false;
            }
        }

        return true;
    }

}
