package me.spacetoastdev.honeymod.testing.annotations;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import me.spacetoastdev.honeymod.implementation.HoneymodItems;

public class SlimefunItemsProvider implements ArgumentsProvider, AnnotationConsumer<SlimefunItemsSource> {

    private String[] items;

    @Override
    public void accept(SlimefunItemsSource source) {
        items = source.items();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Arrays.stream(items).map(this::getAsItemStack).map(Arguments::of);
    }

    private ItemStack getAsItemStack(String fieldName) {
        Class<HoneymodItems> clazz = HoneymodItems.class;
        Field field;
        try {
            field = clazz.getField(fieldName);
            return (ItemStack) field.get(null);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new IllegalArgumentException("Could not find field HoneymodItems." + fieldName);
        }
    }

}