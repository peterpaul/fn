package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public class Reductions {
    public static Reduction<String> join(final String delimiter) {
        return new Reduction<String>() {
            @Nonnull
            @Override
            public String apply(@Nonnull String s, @Nonnull String t) {
                return s + delimiter + t;
            }
        };
    }
}
