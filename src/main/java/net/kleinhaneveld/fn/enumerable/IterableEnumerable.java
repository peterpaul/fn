package net.kleinhaneveld.fn.enumerable;

import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.enumerations.IteratorEnumeration;

import javax.annotation.Nonnull;

public class IterableEnumerable<T> implements Enumerable<T> {
    private final Iterable<T> iterable;

    public IterableEnumerable(@Nonnull Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Nonnull
    @Override
    public Enumeration<T> enumerate() {
        return new IteratorEnumeration<>(iterable.iterator());
    }
}
