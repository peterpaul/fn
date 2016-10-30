package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Reciter;

public class TakeReciter<T> extends Reciter<T> {
    private final Reciter<T> in;
    private int n;

    public TakeReciter(Reciter<T> in, int n) {
        this.in = in;
        this.n = n;
    }

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
