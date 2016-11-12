package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public class Runners {
    @Nonnull
    public static <T> Runner of(@Nonnull final Supplier<T> supplier, @Nonnull final Consumer<T> consumer) {
        return new Runner() {
            @Override
            public void run() {
                consumer.consume(supplier.get());
            }
        };
    }
}
