package me.spacetoastdev.honeymod.testing.mocks;

import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;

public class MockItemHandler implements ItemHandler {

    @Override
    public Class<? extends ItemHandler> getIdentifier() {
        return getClass();
    }

}
