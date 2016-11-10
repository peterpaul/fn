package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public class Functions {
    public static final Function<Object, String> TO_STRING = new Function<Object, String>() {
        @Nonnull
        @Override
        public String apply(@Nonnull Object input) {
            return input.toString();
        }
    };
}
