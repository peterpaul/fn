package net.kleinhaneveld.fn.reciters;

import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Predicate;
import net.kleinhaneveld.fn.Reciter;

import javax.annotation.Nonnull;

public class FilteredReciter<T> extends Reciter<T> {
    private final Reciter<T> in;
    private final Predicate<? super T> filter;

    public FilteredReciter(@Nonnull Reciter<T> in, @Nonnull Predicate<? super T> filter) {
        this.in = in;
        this.filter = filter;
    }

    @Nonnull
    @Override
    public Option<T> get() {
        Option<T> item;
        do {
            item = in.get();
        } while (hasValueAndFilterForbids(item));
        return item;
    }

    private boolean hasValueAndFilterForbids(Option<T> item) {
        return item.isPresent() && !filter.apply(item.get());
    }
}