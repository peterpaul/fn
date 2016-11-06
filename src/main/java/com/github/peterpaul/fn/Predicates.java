package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public abstract class Predicates {
    public static <T> Predicate<T> isEqualTo(@Nonnull final T target) {
        return new Predicate<T>() {
            @Nonnull
            @Override
            public Boolean apply(@Nonnull T input) {
                return input.equals(target);
            }
        };
    }
}
