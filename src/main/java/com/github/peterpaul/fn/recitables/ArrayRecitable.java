package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.ArrayReciter;

public class ArrayRecitable<T> implements Recitable<T> {
    private final T[] in;

    public ArrayRecitable(T[] in) {
        this.in = in;
    }

    @Override
    public Reciter<T> recite() {
        return new ArrayReciter<>(in);
    }
}
