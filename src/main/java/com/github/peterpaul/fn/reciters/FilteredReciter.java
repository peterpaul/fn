package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Predicate;
import com.github.peterpaul.fn.Reciter;

public class FilteredReciter<T> extends Reciter<T> {
    private final Reciter<T> in;
    private final Predicate<T> filter;

    public FilteredReciter(Reciter<T> in, Predicate<T> filter) {
        this.in = in;
        this.filter = filter;
    }

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
