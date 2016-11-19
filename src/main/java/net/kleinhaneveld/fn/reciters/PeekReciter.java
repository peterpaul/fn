package net.kleinhaneveld.fn.reciters;

import net.kleinhaneveld.fn.Consumer;
import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Reciter;

import javax.annotation.Nonnull;

public class PeekReciter<T> extends Reciter<T> {
    private final Reciter<T> in;
    private final Consumer<? super T> consumer;

    public PeekReciter(@Nonnull Reciter<T> in, @Nonnull Consumer<? super T> consumer) {
        this.in = in;
        this.consumer = consumer;
    }

    @Nonnull
    @Override
    public Option<T> get() {
        return in.get().peek(consumer);
    }
}
