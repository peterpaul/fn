package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.IteratorReciter;

import javax.annotation.Nonnull;

public class IterableRecitable<T> implements Recitable<T> {
    private final Iterable<T> iterable;

    public IterableRecitable(@Nonnull Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Nonnull
    @Override
    public Reciter<T> recite() {
        return new IteratorReciter<>(iterable.iterator());
    }
}
