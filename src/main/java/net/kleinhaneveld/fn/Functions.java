package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.annotations.Lazy;

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

    @Lazy
    @Nonnull
    public static <T, R> Function<T, Option<R>> getFrom(@Nonnull final Map<T, R> map) {
        return new Function<T, Option<R>>() {
            @Nonnull
            @Override
            public Option<R> apply(@Nonnull T input) {
                return Option.of(map.get(input));
            }
        };
    }

    @Lazy
    @Nonnull
    public static <T, R> Function<T, Option<R>> getAndRemoveFrom(@Nonnull final Map<T, R> map) {
        return new Function<T, Option<R>>() {
            @Nonnull
            @Override
            public Option<R> apply(@Nonnull T input) {
                return Option.of(map.remove(input));
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
