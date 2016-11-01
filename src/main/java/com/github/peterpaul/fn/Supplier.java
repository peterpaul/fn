package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public abstract class Supplier<T> {
    @Nonnull
    public abstract T get();

    @Nonnull
    public static <T> Supplier<T> of(@Nonnull final T item) {
        return new Supplier<T>() {
            @Nonnull
            @Override
            public T get() {
                return item;
            }
        };
    }

    @Nonnull
    public CacheSupplier<T> cache() {
        return new CacheSupplier<>(this);
    }

    @Nonnull
    public <R> Supplier<R> map(@Nonnull final Function<T, R> mapper) {
        return mapper.$(this);
    }
}
