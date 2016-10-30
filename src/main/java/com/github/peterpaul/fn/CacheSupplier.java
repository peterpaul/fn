package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public class CacheSupplier<T> extends Supplier<T> implements Registerable, Flushable {
    private final Supplier<T> delegate;
    private final Broadcaster broadcaster;
    private volatile T value;

    public CacheSupplier(@Nonnull Supplier<T> delegate) {
        this.delegate = delegate;
        broadcaster = new Broadcaster();
    }

    @Nonnull
    @Override
    public T get() {
        T result = value;
        if (result == null) {
            synchronized (this) {
                result = value;
                if (result == null) {
                    value = result = delegate.get();
                }
            }
        }
        return result;
    }


    @Override
    public void register(@Nonnull Runner runner) {
        broadcaster.register(runner);
    }

    @Override
    public void unregister(@Nonnull Runner runner) {
        broadcaster.unregister(runner);
    }

    @Override
    public void flush() {
        value = null;
        broadcaster.run();
    }
}
