package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Function;
import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.FlatmappedReciter;

public class FlatmappedRecitable<T, R> implements Recitable<T> {
    private final Recitable<T> in;
    private final Function<T, Recitable<R>> mapper;

    public FlatmappedRecitable(Recitable<T> in, Function<T, Recitable<R>> mapper) {
        this.in = in;
        this.mapper = mapper;
    }

    @Override
    public Reciter<T> recite() {
        return new FlatmappedReciter(in.recite(), mapper);
    }
}
