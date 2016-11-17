package net.kleinhaneveld.fn;

import javax.annotation.Nonnull;

public class Reductions {
    public static final Reduction<Boolean> ALL_TRUE = new Reduction<Boolean>() {
        @Nonnull
        @Override
        public Boolean apply(@Nonnull Boolean a, @Nonnull Boolean b) {
            return a && b;
        }
    };

    public static final Reduction<Boolean> ANY_TRUE = new Reduction<Boolean>() {
        @Nonnull
        @Override
        public Boolean apply(@Nonnull Boolean a, @Nonnull Boolean b) {
            return a || b;
        }
    };

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
