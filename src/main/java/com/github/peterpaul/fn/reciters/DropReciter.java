package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Reciter;

public class DropReciter<T> extends Reciter<T> {
    private final Reciter<T> in;
    private int n;

    public DropReciter(Reciter<T> in, int n) {
        this.in = in;
        this.n = n;
    }

    @Override
    public Option<T> get() {
        while (n > 0) {
            n--;
            in.get();
        }
        return in.get();
    }
}
