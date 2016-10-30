package com.github.peterpaul.fn;

public abstract class Supplier<T> {
    public abstract T get();

    public static <T> Supplier<T> of(final T item) {
        return new Supplier<T>() {
            @Override
            public T get() {
                return item;
            }
        };
    }

    public CacheSupplier<T> cache() {
        return new CacheSupplier<>(this);
    }

    public <R> Supplier<R> map(final Function<T, R> mapper) {
        return new Supplier<R>() {
            @Override
            public R get() {
                return mapper.apply(Supplier.this.get());
            }
        };
    }
}
