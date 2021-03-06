package me.spacetoastdev.honeymod.implementation.items.cargo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.cscorelib2.protection.ProtectableAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import me.spacetoastdev.honeymod.core.networks.cargo.CargoNet;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.implementation.HoneymodPlugin;
import me.spacetoastdev.honeymod.implementation.items.electric.reactors.Reactor;
import me.spacetoastdev.honeymod.implementation.items.misc.CoolantCell;
import me.spacetoastdev.honeymod.utils.ChestMenuUtils;

/**
 * The {@link ReactorAccessPort} is a block which acts as an interface
 * between a {@link Reactor} and a {@link CargoNet}.
 * Any item placed into the port will get transferred to the {@link Reactor}.
 * 
 * @author TheBusyBiscuit
 * @author AlexLander123
 *
 */
public class ReactorAccessPort extends SlimefunItem {

    private static final int INFO_SLOT = 49;

    private final int[] background = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 12, 13, 14, 21, 23 };
    private final int[] fuelBorder = { 9, 10, 11, 18, 20, 27, 29, 36, 38, 45, 46, 47 };
    private final int[] inputBorder = { 15, 16, 17, 24, 26, 33, 35, 42, 44, 51, 52, 53 };
    private final int[] outputBorder = { 30, 31, 32, 39, 41, 48, 50 };

    @ParametersAreNonnullByDefault
    public ReactorAccessPort(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        new BlockMenuPreset(getId(), "&2Reactor Access Port") {

            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || HoneymodPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.INTERACT_BLOCK);
            }

            @Override
            public void newInstance(BlockMenu menu, Block b) {
                BlockMenu reactor = getReactor(b.getLocation());

                if (reactor != null) {
                    menu.replaceExistingItem(INFO_SLOT, new CustomItem(Material.GREEN_WOOL, "&7Reactor", "", "&6Detected", "", "&7> Click to view Reactor"));
                    menu.addMenuClickHandler(INFO_SLOT, (p, slot, item, action) -> {
                        if (reactor != null) {
                            reactor.open(p);
                        }

                        newInstance(menu, b);

                        return false;
                    });
                } else {
                    menu.replaceExistingItem(INFO_SLOT, new CustomItem(Material.RED_WOOL, "&7Reactor", "", "&cNot detected", "", "&7Reactor must be", "&7placed 3 blocks below", "&7the access port!"));
                    menu.addMenuClickHandler(INFO_SLOT, (p, slot, item, action) -> {
                        newInstance(menu, b);
                        return false;
                    });
                }
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.INSERT) {
                    return getInputSlots();
                } else {
                    return getOutputSlots();
                }
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow == ItemTransportFlow.INSERT) {
                    if (SlimefunItem.getByItem(item) instanceof CoolantCell) {
                        return getCoolantSlots();
                    } else {
                        return getFuelSlots();
                    }
                } else {
                    return getOutputSlots();
                }
            }
        };

        registerBlockHandler(getId(), (p, b, tool, reason) -> {
            BlockMenu inv = BlockStorage.getInventory(b);

            if (inv != null) {
                inv.dropItems(b.getLocation(), getFuelSlots());
                inv.dropItems(b.getLocation(), getCoolantSlots());
                inv.dropItems(b.getLocation(), getOutputSlots());
            }

            return true;
        });
    }

    private void constructMenu(@Nonnull BlockMenuPreset preset) {
        preset.drawBackground(ChestMenuUtils.getBackground(), background);

        preset.drawBackground(new CustomItem(Material.LIME_STAINED_GLASS_PANE, " "), fuelBorder);
        preset.drawBackground(new CustomItem(Material.CYAN_STAINED_GLASS_PANE, " "), inputBorder);
        preset.drawBackground(new CustomItem(Material.GREEN_STAINED_GLASS_PANE, " "), outputBorder);

        preset.addItem(1, new CustomItem(HoneymodItems.URANIUM, "&7Fuel Slot", "", "&rThis Slot accepts radioactive Fuel such as:", "&2Uranium &ror &aNeptunium"), ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(22, new CustomItem(HoneymodItems.PLUTONIUM, "&7Byproduct Slot", "", "&rThis Slot contains the Reactor's Byproduct", "&rsuch as &aNeptunium &ror &7Plutonium"), ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(7, new CustomItem(HoneymodItems.REACTOR_COOLANT_CELL, "&bCoolant Slot", "", "&rThis Slot accepts Coolant Cells", "&4Without any Coolant Cells, your Reactor", "&4will explode"), ChestMenuUtils.getEmptyClickHandler());
    }

    @Nonnull
    public int[] getInputSlots() {
        return new int[] { 19, 28, 37, 25, 34, 43 };
    }

    @Nonnull
    public int[] getFuelSlots() {
        return new int[] { 19, 28, 37 };
    }

    @Nonnull
    public int[] getCoolantSlots() {
        return new int[] { 25, 34, 43 };
    }

    @Nonnull
    public static int[] getOutputSlots() {
        return new int[] { 40 };
    }

    @Nullable
    private BlockMenu getReactor(@Nonnull Location l) {
        Location location = new Location(l.getWorld(), l.getX(), l.getY() - 3, l.getZ());
        SlimefunItem item = BlockStorage.check(location.getBlock());

        if (item instanceof Reactor) {
            return BlockStorage.getInventory(location);
        }

        return null;
    }

}
