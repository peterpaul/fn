package net.kleinhaneveld.fn.enumerable;

import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Function;
import net.kleinhaneveld.fn.enumerations.MappedEnumeration;

import javax.annotation.Nonnull;

public class MappedEnumerable<T, R> implements Enumerable<R> {
    private final Enumerable<T> in;
    private final Function<? super T, ? extends R> mapper;

    public MappedEnumerable(@Nonnull Enumerable<T> in, @Nonnull Function<? super T, ? extends R> mapper) {
        this.in = in;
        this.mapper = mapper;
    }

    @Nonnull
    @Override
    public Enumeration<R> enumerate() {
        return new MappedEnumeration<>(in.enumerate(), mapper);
    }
}
