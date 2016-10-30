package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public abstract class BiFunction<T, S, R> {
    @Nonnull
    public abstract R apply(@Nonnull T t, @Nonnull S s);

    @Nonnull
    public Function<S, R> $(@Nonnull final T t) {
        return new Function<S, R>() {
            @Nonnull
            @Override
            public R apply(@Nonnull S s) {
                return BiFunction.this.apply(t, s);
            }
        };
    }
}
