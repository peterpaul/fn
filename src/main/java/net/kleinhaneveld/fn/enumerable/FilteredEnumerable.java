package net.kleinhaneveld.fn.enumerable;

import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Predicate;
import net.kleinhaneveld.fn.enumerations.FilteredEnumeration;

import javax.annotation.Nonnull;

public class FilteredEnumerable<T> implements Enumerable<T> {
    private final Enumerable<T> in;
    private final Predicate<? super T> filter;

    public FilteredEnumerable(@Nonnull Enumerable<T> in, @Nonnull Predicate<? super T> filter) {
        this.in = in;
        this.filter = filter;
    }

    @Nonnull
    @Override
    public Enumeration<T> enumerate() {
        return new FilteredEnumeration<>(in.enumerate(), filter);
    }
}
