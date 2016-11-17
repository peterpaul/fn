package net.kleinhaneveld.fn;

import javax.annotation.Nonnull;

public class Suppliers {
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
}
