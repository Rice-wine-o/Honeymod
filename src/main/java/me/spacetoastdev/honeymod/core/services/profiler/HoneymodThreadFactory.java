package me.spacetoastdev.honeymod.core.services.profiler;

import java.util.concurrent.ThreadFactory;

import javax.annotation.Nonnull;

/**
 * This is our {@link ThreadFactory} for the {@link HoneymodProfiler}.
 * It holds the amount of {@link Thread Threads} we dedicate towards our {@link HoneymodProfiler}
 * and provides a naming convention for our {@link Thread Threads}.
 * 
 * @author TheBusyBiscuit
 * 
 * @see HoneymodProfiler
 *
 */
final class HoneymodThreadFactory implements ThreadFactory {

    private final int threadCount;

    /**
     * This constructs a new {@link HoneymodThreadFactory} with the given {@link Thread} count.
     * 
     * @param threadCount
     *            The amount of {@link Thread Threads} to provide to the {@link HoneymodProfiler}
     */
    HoneymodThreadFactory(int threadCount) {
        this.threadCount = threadCount;
    }

    /**
     * This returns the amount of {@link Thread Threads} we dedicate towards
     * the {@link HoneymodProfiler}.
     * 
     * @return The {@link Thread} count
     */
    int getThreadCount() {
        return threadCount;
    }

    /**
     * This creates a new {@link Thread} for the {@link HoneymodProfiler}.
     */
    @Override
    public Thread newThread(@Nonnull Runnable runnable) {
        return new Thread(runnable, "Honeymod Profiler");
    }

}
