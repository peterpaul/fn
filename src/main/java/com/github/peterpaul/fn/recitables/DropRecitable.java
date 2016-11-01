package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.DropReciter;

import javax.annotation.Nonnull;

public class DropRecitable<T> implements Recitable<T> {
    private final Recitable<T> in;
    private final int n;

    public DropRecitable(@Nonnull Recitable<T> in, int n) {
        this.in = in;
        this.n = n;
    }

    @Nonnull
    @Override
    public Reciter<T> reciter() {
        return new DropReciter<>(in.reciter(), n);
    }
}
