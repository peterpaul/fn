package net.kleinhaneveld.fn.enumerable;

import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Pair;
import net.kleinhaneveld.fn.enumerations.ZipEnumeration;

import javax.annotation.Nonnull;

public class ZipEnumerable<T, S> implements Enumerable<Pair<T, S>> {
    private final Enumerable<T> stream;
    private final Enumerable<S> others;

    public ZipEnumerable(@Nonnull Enumerable<T> stream, @Nonnull Enumerable<S> others) {
        this.stream = stream;
        this.others = others;
    }

    @Nonnull
    @Override
    public Enumeration<Pair<T, S>> enumerate() {
        return new ZipEnumeration<>(stream.enumerate(), others.enumerate());
    }
}
