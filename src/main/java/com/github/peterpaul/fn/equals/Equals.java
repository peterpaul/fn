package com.github.peterpaul.fn.equals;

public abstract class Equals {
    public static <T> boolean equals(T self, Object o, EqualsChecker<T> equalsChecker) {
        if (self == o) {
            return true;
        }
        return self.getClass().isAssignableFrom(o.getClass())
                ? equalsChecker.isEqualTo((T) o)
                : false;
    }

    public interface EqualsChecker<T> {
        boolean isEqualTo(T other);
    }
}
