package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.annotations.Lazy;

import javax.annotation.Nonnull;

public abstract class Predicates {
    @Lazy
    @Nonnull
    public static <T> Predicate<T> equalTo(@Nonnull final T target) {
        return new Predicate<T>() {
            @Nonnull
            @Override
            public Boolean apply(@Nonnull T input) {
                return input.equals(target);
            }
        };
    }

    @Lazy
    @Nonnull
    public static <T> Predicate<T> not(@Nonnull final Predicate<T> p) {
        return new Predicate<T>() {
            @Nonnull
            @Override
            public Boolean apply(@Nonnull T input) {
                return !p.apply(input);
            }
        };
    }
}
