package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.IteratorReciter;

public class IterableRecitable<T> implements Recitable<T> {
    private final Iterable<T> iterable;

    public IterableRecitable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Reciter<T> recite() {
        return new IteratorReciter<>(iterable.iterator());
    }
}
