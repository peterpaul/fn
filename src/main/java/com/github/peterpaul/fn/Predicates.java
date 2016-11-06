package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public abstract class Predicates {
    public static <T> Predicate<T> equalTo(@Nonnull final T target) {
        return new Predicate<T>() {
            @Nonnull
            @Override
            public Boolean apply(@Nonnull T input) {
                return input.equals(target);
            }
        };
    }

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
