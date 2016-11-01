package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Function;
import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Reciter;

import javax.annotation.Nonnull;

public class MappedReciter<T, R> extends Reciter<R> {
    private final Reciter<T> in;
    private final Function<? super T, ? extends R> mapper;

    public MappedReciter(@Nonnull Reciter<T> in, @Nonnull Function<? super T, ? extends R> mapper) {
        this.in = in;
        this.mapper = mapper;
    }

    @Nonnull
    @Override
    public Option<R> get() {
        return in.get().map(mapper);
    }
}
