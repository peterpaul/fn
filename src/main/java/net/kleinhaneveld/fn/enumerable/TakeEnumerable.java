package net.kleinhaneveld.fn.enumerable;

import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.enumerations.TakeEnumeration;

import javax.annotation.Nonnull;

public class TakeEnumerable<T> implements Enumerable<T> {
    private final Enumerable<T> in;
    private final int n;

    public TakeEnumerable(@Nonnull Enumerable<T> in, int n) {
        this.in = in;
        this.n = n;
    }

    @Nonnull
    @Override
    public Enumeration<T> enumerate() {
        return new TakeEnumeration<>(in.enumerate(), n);
    }
}
