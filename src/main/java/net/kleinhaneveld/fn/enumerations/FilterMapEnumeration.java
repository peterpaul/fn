package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Function;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;

public class FilterMapEnumeration<T, R> extends Enumeration<R> {
    private final Enumeration<T> in;
    private final Function<? super T, Option<R>> filteredMapper;

    public FilterMapEnumeration(Enumeration<T> in, Function<? super T, Option<R>> filteredMapper) {
        this.in = in;
        this.filteredMapper = filteredMapper;
    }

    @Nonnull
    @Override
    public Option<R> next() {
        Option<T> item;
        Option<R> out = Option.none();
        do {
            item = in.next();
            if (item.isPresent()) {
                out = filteredMapper.apply(item.get());
            }
        } while (hasValueAndFilterForbids(item, out));
        return out;
    }

    private boolean hasValueAndFilterForbids(Option<T> item, Option<R> out) {
        return item.isPresent() && !out.isPresent();
    }
}
