package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Function;
import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.FlatmappedReciter;

import javax.annotation.Nonnull;

public class FlatmappedRecitable<T, R> implements Recitable<R> {
    private final Recitable<T> in;
    private final Function<? super T, ? extends Recitable<R>> mapper;

    public FlatmappedRecitable(@Nonnull Recitable<T> in, @Nonnull Function<? super T, ? extends Recitable<R>> mapper) {
        this.in = in;
        this.mapper = mapper;
    }

    @Nonnull
    @Override
    public Reciter<R> reciter() {
        return new FlatmappedReciter<>(in.reciter(), mapper);
    }
}
