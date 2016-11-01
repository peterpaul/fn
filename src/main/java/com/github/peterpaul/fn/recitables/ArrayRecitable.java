package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.ArrayReciter;

import javax.annotation.Nonnull;

public class ArrayRecitable<T> implements Recitable<T> {
    private final T[] in;

    public ArrayRecitable(@Nonnull T[] in) {
        this.in = in;
    }

    @Nonnull
    @Override
    public Reciter<T> reciter() {
        return new ArrayReciter<>(in);
    }
}
