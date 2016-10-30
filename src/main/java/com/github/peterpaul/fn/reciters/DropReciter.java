package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Reciter;

import javax.annotation.Nonnull;

public class DropReciter<T> extends Reciter<T> {
    private final Reciter<T> in;
    private int n;

    public DropReciter(@Nonnull Reciter<T> in, int n) {
        this.in = in;
        this.n = n;
    }

    @Nonnull
    @Override
    public Option<T> get() {
        while (n > 0) {
            n--;
            in.get();
        }
        return in.get();
    }
}
