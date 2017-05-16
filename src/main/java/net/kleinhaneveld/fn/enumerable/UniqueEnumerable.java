package net.kleinhaneveld.fn.enumerable;

import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.enumerations.UniqueEnumeration;

import javax.annotation.Nonnull;

public class UniqueEnumerable<T> implements Enumerable<T> {
    private final Enumerable<T> stream;

    public UniqueEnumerable(Enumerable<T> stream) {
        this.stream = stream;
    }

    @Nonnull
    @Override
    public Enumeration<T> enumerate() {
        return new UniqueEnumeration<>(stream.enumerate());
    }
}
