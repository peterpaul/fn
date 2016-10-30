package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Reciter;

import javax.annotation.Nonnull;

public class TakeReciter<T> extends Reciter<T> {
    private final Reciter<T> in;
    private int n;

    public TakeReciter(@Nonnull Reciter<T> in, int n) {
        this.in = in;
        this.n = n;
    }

    @Nonnull
    @Override
    public Option<T> get() {
        if (n > 0) {
            n--;
            return in.get();
        } else {
            return Option.none();
        }
    }
}
