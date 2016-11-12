package com.github.peterpaul.fn;

import com.github.peterpaul.fn.annotations.Eager;
import com.github.peterpaul.fn.annotations.Lazy;

import javax.annotation.Nonnull;

public abstract class Function<T, R> {
    @Eager
    @Nonnull
    public abstract R apply(@Nonnull T input);

    @Lazy
    @Nonnull
    public <S> Function<T, S> andThen(@Nonnull final Function<R, S> f) {
        return new Function<T, S>() {
            @Nonnull
            @Override
            public S apply(@Nonnull T input) {
                return f.apply(Function.this.apply(input));
            }
        };
    }

    @Lazy
    @Nonnull
    public <S> Function<S, R> compose(@Nonnull final Function<S, T> f) {
        return new Function<S, R>() {
            @Nonnull
            @Override
            public R apply(@Nonnull S input) {
                return Function.this.apply(f.apply(input));
            }
        };
    }

    @Lazy
    @Nonnull
    public MemoizedFunction<T, R> memoize() {
        return new MemoizedFunction<>(this);
    }
}
