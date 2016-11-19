package net.kleinhaneveld.fn.status;

import net.kleinhaneveld.fn.equals.Equals;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Set;

public final class TooManyElements<T> {
    private final Set<T> items;

    public TooManyElements(@Nonnull Set<T> items) {
        this.items = items;
    }

    @Nonnull
    public Set<T> getItems() {
        return items;
    }

    @Nonnull
    @Override
    public String toString() {
        return "TooManyElements(" + items + ')';
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o) {
        return Equals.equals(this, o, new Equals.EqualsChecker<TooManyElements<T>>() {
            @Override
            public boolean isEqualTo(@Nonnull TooManyElements<T> other) {
                return Objects.equals(items, other.items);
            }
        });
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
