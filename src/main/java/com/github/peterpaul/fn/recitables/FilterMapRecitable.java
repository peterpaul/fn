package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Function;
import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.FilterMapReciter;

import javax.annotation.Nonnull;

public class FilterMapRecitable<T, R> implements Recitable<R> {
    private final Recitable<T> in;
    private final Function<? super T, Option<R>> filteredMapper;

    public FilterMapRecitable(Recitable<T> in, Function<? super T, Option<R>> filteredMapper) {
        this.in = in;
        this.filteredMapper = filteredMapper;
    }

    @Nonnull
    @Override
    public Reciter<R> reciter() {
        return new FilterMapReciter(in.reciter(), filteredMapper);
    }
}
