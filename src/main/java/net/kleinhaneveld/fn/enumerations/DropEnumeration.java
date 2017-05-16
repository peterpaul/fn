package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;

public class DropEnumeration<T> extends Enumeration<T> {
    private final Enumeration<T> in;
    private int n;

    public DropEnumeration(@Nonnull Enumeration<T> in, int n) {
        this.in = in;
        this.n = n;
    }

    @Nonnull
    @Override
    public Option<T> next() {
        while (n > 0) {
            n--;
            in.next();
        }
        return in.next();
    }
}
