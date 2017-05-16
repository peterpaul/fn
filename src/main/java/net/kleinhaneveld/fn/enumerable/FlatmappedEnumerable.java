package net.kleinhaneveld.fn.enumerable;

import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Function;
import net.kleinhaneveld.fn.enumerations.FlatmappedEnumeration;

import javax.annotation.Nonnull;

public class FlatmappedEnumerable<T, R> implements Enumerable<R> {
    private final Enumerable<T> in;
    private final Function<? super T, ? extends Enumerable<R>> mapper;

    public FlatmappedEnumerable(@Nonnull Enumerable<T> in, @Nonnull Function<? super T, ? extends Enumerable<R>> mapper) {
        this.in = in;
        this.mapper = mapper;
    }

    @Nonnull
    @Override
    public Enumeration<R> enumerate() {
        return new FlatmappedEnumeration<>(in.enumerate(), mapper);
    }
}
