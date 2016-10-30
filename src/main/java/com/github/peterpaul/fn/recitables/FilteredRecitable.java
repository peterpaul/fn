package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Predicate;
import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.FilteredReciter;

public class FilteredRecitable<T> implements Recitable<T> {
    private final Recitable<T> in;
    private final Predicate<T> filter;

    public FilteredRecitable(Recitable<T> in, Predicate<T> filter) {
        this.in = in;
        this.filter = filter;
    }

    @Override
    public Reciter<T> recite() {
        return new FilteredReciter<>(in.recite(), filter);
    }
}
