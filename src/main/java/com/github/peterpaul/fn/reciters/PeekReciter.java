package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Consumer;
import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Reciter;

public class PeekReciter<T> extends Reciter<T> {
    private final Reciter<T> in;
    private final Consumer<T> consumer;

    public PeekReciter(Reciter<T> in, Consumer<T> consumer) {
        this.in = in;
        this.consumer = consumer;
    }

    @Override
    public Option<T> get() {
        return in.get().peek(consumer);
    }
}
