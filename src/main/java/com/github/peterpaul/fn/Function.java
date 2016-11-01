package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public abstract class Function<T, R> {
    @Nonnull
    public abstract R apply(@Nonnull T input);

    @Nonnull
    public <S> Function<T, S> $(@Nonnull final Function<R, S> f) {
        return new Function<T, S>() {
            @Nonnull
            @Override
            public S apply(@Nonnull T input) {
                return f.apply(Function.this.apply(input));
            }
        };
    }

    @Nonnull
    public Supplier<R> $(@Nonnull final Supplier<T> input) {
        return new Supplier<R>() {
            @Nonnull
            @Override
            public R get() {
                return apply(input.get());
            }
        };
    }
}
