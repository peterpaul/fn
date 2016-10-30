package com.github.peterpaul.fn.equals;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

public abstract class Equals {
    @SuppressWarnings("unchecked")
    public static <T> boolean equals(@Nonnull T self, @CheckForNull Object o, @Nonnull EqualsChecker<T> equalsChecker) {
        if (self == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        return self.getClass().isAssignableFrom(o.getClass()) && equalsChecker.isEqualTo((T) o);
    }

    public interface EqualsChecker<T> {
        boolean isEqualTo(@Nonnull T other);
    }
}
