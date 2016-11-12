package com.github.peterpaul.fn;

import com.github.peterpaul.fn.annotations.Eager;
import com.github.peterpaul.fn.annotations.Lazy;

import javax.annotation.Nonnull;

public abstract class BiFunction<T, S, R> {
    @Eager
    @Nonnull
    public abstract R apply(@Nonnull T t, @Nonnull S s);

    @Lazy
    @Nonnull
    public Function<S, R> applyT(@Nonnull final T t) {
        return new Function<S, R>() {
            @Nonnull
            @Override
            public R apply(@Nonnull S s) {
                return BiFunction.this.apply(t, s);
            }
        };
    }

    @Lazy
    @Nonnull
    public Function<T, R> applyS(@Nonnull final S s) {
        return new Function<T, R>() {
            @Nonnull
            @Override
            public R apply(@Nonnull T t) {
                return BiFunction.this.apply(t, s);
            }
        };
    }
}
