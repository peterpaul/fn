package com.github.peterpaul.fn;

import javax.annotation.Nonnull;
import java.util.Map;

public abstract class Function<T, R> {
    @Nonnull
    public abstract R apply(@Nonnull T input);

    @Nonnull
    public static <T, R> Function<T, Option<R>> mapper(@Nonnull final Map<T, R> map) {
        return new Function<T, Option<R>>() {
            @Nonnull
            @Override
            public Option<R> apply(@Nonnull T input) {
                return Option.of(map.get(input));
            }
        };
    }

    public static <T> Function<T, T> identity() {
        return new Function<T, T>() {
            @Nonnull
            @Override
            public T apply(@Nonnull T input) {
                return input;
            }
        };
    }

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
