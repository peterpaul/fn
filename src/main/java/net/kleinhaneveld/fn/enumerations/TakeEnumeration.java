package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;

public class TakeEnumeration<T> extends Enumeration<T> {
    private final Enumeration<T> in;
    private int n;

    public TakeEnumeration(@Nonnull Enumeration<T> in, int n) {
        this.in = in;
        this.n = n;
    }

    @Nonnull
    @Override
    public Option<T> next() {
        if (n > 0) {
            n--;
            return in.next();
        } else {
            return Option.none();
        }
    }
}
