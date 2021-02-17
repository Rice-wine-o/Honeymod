package me.spacetoastdev.honeymod.implementation.items.electric.machines;

import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.spacetoastdev.honeymod.implementation.HoneymodItems;
import me.spacetoastdev.honeymod.utils.HoneymodUtils;
import me.spacetoastdev.honeymod.utils.itemstack.ItemStackWrapper;

public class AnimalGrowthAccelerator extends AbstractGrowthAccelerator {

    private static final int ENERGY_CONSUMPTION = 14;
    private static final double RADIUS = 3.0;

    // We wanna strip the Slimefun Item id here
    private static final ItemStack organicFood = new ItemStackWrapper(HoneymodItems.ORGANIC_FOOD);

    public AnimalGrowthAccelerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public int getCapacity() {
        return 1024;
    }

    @Override
    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);

        for (Entity n : b.getWorld().getNearbyEntities(b.getLocation(), RADIUS, RADIUS, RADIUS, this::isReadyToGrow)) {
            for (int slot : getInputSlots()) {
                if (HoneymodUtils.isItemSimilar(inv.getItemInSlot(slot), organicFood, false, false)) {
                    if (getCharge(b.getLocation()) < ENERGY_CONSUMPTION) {
                        return;
                    }

                    Ageable ageable = (Ageable) n;
                    removeCharge(b.getLocation(), ENERGY_CONSUMPTION);
                    inv.consumeItem(slot);
                    ageable.setAge(ageable.getAge() + 2000);

                    if (ageable.getAge() > 0) {
                        ageable.setAge(0);
                    }

                    n.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, ((LivingEntity) n).getEyeLocation(), 8, 0.2F, 0.2F, 0.2F);
                    return;
                }
            }
        }
    }

    private boolean isReadyToGrow(Entity n) {
        return n instanceof Ageable && n.isValid() && !((Ageable) n).isAdult();
    }

}
