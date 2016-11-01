package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Predicate;
import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.FilteredReciter;

import javax.annotation.Nonnull;

public class FilteredRecitable<T> implements Recitable<T> {
    private final Recitable<T> in;
    private final Predicate<T> filter;

    public FilteredRecitable(@Nonnull Recitable<T> in, @Nonnull Predicate<T> filter) {
        this.in = in;
        this.filter = filter;
    }

    @Nonnull
    @Override
    public Reciter<T> reciter() {
        return new FilteredReciter<>(in.reciter(), filter);
    }
}
