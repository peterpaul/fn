package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Predicate;

import javax.annotation.Nonnull;

public class FilteredEnumeration<T> extends Enumeration<T> {
    private final Enumeration<T> in;
    private final Predicate<? super T> filter;

    public FilteredEnumeration(@Nonnull Enumeration<T> in, @Nonnull Predicate<? super T> filter) {
        this.in = in;
        this.filter = filter;
    }

    @Nonnull
    @Override
    public Option<T> next() {
        Option<T> item;
        do {
            item = in.next();
        } while (hasValueAndFilterForbids(item));
        return item;
    }

    private boolean hasValueAndFilterForbids(Option<T> item) {
        return item.isPresent() && !filter.apply(item.get());
    }
}
