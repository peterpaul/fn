package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Consumer;
import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.PeekReciter;

import javax.annotation.Nonnull;

public class PeekRecitable<T> implements Recitable<T> {
    private final Recitable<T> in;
    private final Consumer<T> consumer;

    public PeekRecitable(@Nonnull Recitable<T> in, @Nonnull Consumer<T> consumer) {
        this.in = in;
        this.consumer = consumer;
    }

    @Nonnull
    @Override
    public Reciter<T> recite() {
        return new PeekReciter<>(in.recite(), consumer);
    }
}
