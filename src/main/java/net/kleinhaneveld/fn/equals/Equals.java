package net.kleinhaneveld.fn.equals;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

public abstract class Equals {
    @SuppressWarnings("unchecked")
    public static <T> boolean equals(@Nonnull T self, @CheckForNull Object o, @Nonnull EqualsChecker<T> equalsChecker) {
        return self == o
                || o != null
                && self.getClass().isAssignableFrom(o.getClass())
                && equalsChecker.isEqualTo((T) o);
    }

    public interface EqualsChecker<T> {
        boolean isEqualTo(@Nonnull T other);
    }
}
