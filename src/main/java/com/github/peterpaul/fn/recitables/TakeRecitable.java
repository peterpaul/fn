package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.TakeReciter;

public class TakeRecitable<T> implements Recitable<T> {
    private final Recitable<T> in;
    private final int n;

    public TakeRecitable(Recitable<T> in, int n) {
        this.in = in;
        this.n = n;
    }

    @Override
    public Reciter<T> recite() {
        return new TakeReciter<>(in.recite(), n);
    }
}
