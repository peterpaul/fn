package com.github.peterpaul.fn;

import com.github.peterpaul.fn.annotations.Eager;
import com.github.peterpaul.fn.annotations.Lazy;

import javax.annotation.Nonnull;

public abstract class Supplier<T> {
    @Eager
    @Nonnull
    public abstract T get();

    @Lazy
    @Nonnull
    public CacheSupplier<T> cache() {
        return new CacheSupplier<>(this);
    }

    @Lazy
    @Nonnull
    public <R> Supplier<R> map(@Nonnull final Function<T, R> mapper) {
        return new Supplier<R>() {
            @Nonnull
            @Override
            public R get() {
                return mapper.apply(Supplier.this.get());
            }
        };
    }

    @Lazy
    @Nonnull
    public Supplier<T> orWhenRuntimeException(final Supplier<T> defaultValue) {
        return new Supplier<T>() {
            @Nonnull
            @Override
            public T get() {
                try {
                    return Supplier.this.get();
                } catch (RuntimeException e) {
                    return defaultValue.get();
                }
            }
        };
    }
}
