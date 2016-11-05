package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Function;
import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Reciter;

import javax.annotation.Nonnull;

public class FilterMapReciter<T, R> extends Reciter<R> {
    private final Reciter<T> in;
    private final Function<? super T, Option<R>> filteredMapper;

    public FilterMapReciter(Reciter<T> in, Function<? super T, Option<R>> filteredMapper) {
        this.in = in;
        this.filteredMapper = filteredMapper;
    }

    @Nonnull
    @Override
    public Option<R> get() {
        Option<T> item;
        Option<R> out = Option.none();
        do {
            item = in.get();
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
