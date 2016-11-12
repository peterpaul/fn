package com.github.peterpaul.fn;

import javax.annotation.Nonnull;
import java.util.Map;

public class Functions {
    public static final Function<Object, String> TO_STRING = new Function<Object, String>() {
        @Nonnull
        @Override
        public String apply(@Nonnull Object input) {
            return input.toString();
        }
    };

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

    @Nonnull
    public static <T> Function<T, T> identity() {
        return new Function<T, T>() {
            @Nonnull
            @Override
            public T apply(@Nonnull T input) {
                return input;
            }
        };
    }
}
