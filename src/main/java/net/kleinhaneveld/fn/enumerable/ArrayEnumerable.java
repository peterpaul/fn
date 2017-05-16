package net.kleinhaneveld.fn.enumerable;

import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.enumerations.ArrayEnumeration;

import javax.annotation.Nonnull;

public class ArrayEnumerable<T> implements Enumerable<T> {
    private final T[] in;

    public ArrayEnumerable(@Nonnull T[] in) {
        this.in = in;
    }

    @Nonnull
    @Override
    public Enumeration<T> enumerate() {
        return new ArrayEnumeration<>(in);
    }
}
