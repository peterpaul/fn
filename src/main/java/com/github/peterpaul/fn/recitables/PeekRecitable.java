package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Consumer;
import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.PeekReciter;

public class PeekRecitable<T> implements Recitable<T> {
    private final Recitable<T> in;
    private final Consumer<T> consumer;

    public PeekRecitable(Recitable<T> in, Consumer<T> consumer) {
        this.in = in;
        this.consumer = consumer;
    }

    @Override
    public Reciter<T> recite() {
        return new PeekReciter<>(in.recite(), consumer);
    }
}
