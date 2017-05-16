package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Function;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;

public class MappedEnumeration<T, R> extends Enumeration<R> {
    private final Enumeration<T> in;
    private final Function<? super T, ? extends R> mapper;

    public MappedEnumeration(@Nonnull Enumeration<T> in, @Nonnull Function<? super T, ? extends R> mapper) {
        this.in = in;
        this.mapper = mapper;
    }

    @Nonnull
    @Override
    public Option<R> next() {
        return in.next().map(mapper);
    }
}
