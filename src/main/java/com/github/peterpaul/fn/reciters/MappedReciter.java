package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Function;
import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Reciter;

public class MappedReciter<T, R> extends Reciter<R> {
    private final Reciter<T> in;
    private final Function<T, R> mapper;

    public MappedReciter(Reciter<T> in, Function<T, R> mapper) {
        this.in = in;
        this.mapper = mapper;
    }


    @Override
    public Option<R> get() {
        return in.get().map(mapper);
    }
}
