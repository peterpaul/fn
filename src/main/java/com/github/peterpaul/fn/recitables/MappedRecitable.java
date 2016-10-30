package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Function;
import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.MappedReciter;

public class MappedRecitable<T, R> implements Recitable<R> {
    private final Recitable<T> in;
    private final Function<T, R> mapper;

    public MappedRecitable(Recitable<T> in, Function<T, R> mapper) {
        this.in = in;
        this.mapper = mapper;
    }

    @Override
    public Reciter<R> recite() {
        return new MappedReciter<>(in.recite(), mapper);
    }
}