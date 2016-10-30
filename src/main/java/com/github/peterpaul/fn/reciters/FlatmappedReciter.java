package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.*;

import javax.annotation.Nonnull;

public class FlatmappedReciter<T, R> extends Reciter<R> {
    private final Reciter<T> in;
    private final Function<T, Recitable<R>> mapper;
    private Option<T> inputItem;
    private Option<Reciter<R>> outputReciter;

    public FlatmappedReciter(@Nonnull Reciter<T> in, @Nonnull Function<T, Recitable<R>> mapper) {
        super();
        this.in = in;
        this.mapper = mapper;
        next();
    }

    private void next() {
        inputItem = in.get();
        outputReciter = inputItem.map(mapper).map(new Function<Recitable<R>, Reciter<R>>() {
            @Nonnull
            @Override
            public Reciter<R> apply(@Nonnull Recitable<R> input) {
                return input.recite();
            }
        });
    }

    @Nonnull
    @Override
    public Option<R> get() {
        while (inputItem.isPresent()) {
            Option<R> out = outputReciter.flatMap(new Function<Reciter<R>, Option<R>>() {
                @Nonnull
                @Override
                public Option<R> apply(@Nonnull Reciter<R> input) {
                    return input.get();
                }
            });
            if (out.isPresent()) {
                return out;
            } else {
                next();
            }
        }
        return Option.none();
    }
}
