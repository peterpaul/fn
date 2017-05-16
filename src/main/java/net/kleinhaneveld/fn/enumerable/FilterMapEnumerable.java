package net.kleinhaneveld.fn.enumerable;

import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Function;
import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.enumerations.FilterMapEnumeration;

import javax.annotation.Nonnull;

public class FilterMapEnumerable<T, R> implements Enumerable<R> {
    private final Enumerable<T> in;
    private final Function<? super T, Option<R>> filteredMapper;

    public FilterMapEnumerable(Enumerable<T> in, Function<? super T, Option<R>> filteredMapper) {
        this.in = in;
        this.filteredMapper = filteredMapper;
    }

    @Nonnull
    @Override
    public Enumeration<R> enumerate() {
        return new FilterMapEnumeration<>(in.enumerate(), filteredMapper);
    }
}
