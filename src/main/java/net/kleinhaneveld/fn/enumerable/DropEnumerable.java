package net.kleinhaneveld.fn.enumerable;

import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.enumerations.DropEnumeration;

import javax.annotation.Nonnull;

public class DropEnumerable<T> implements Enumerable<T> {
    private final Enumerable<T> in;
    private final int n;

    public DropEnumerable(@Nonnull Enumerable<T> in, int n) {
        this.in = in;
        this.n = n;
    }

    @Nonnull
    @Override
    public Enumeration<T> enumerate() {
        return new DropEnumeration<>(in.enumerate(), n);
    }
}
