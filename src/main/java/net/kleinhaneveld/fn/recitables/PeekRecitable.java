package net.kleinhaneveld.fn.recitables;

import net.kleinhaneveld.fn.Consumer;
import net.kleinhaneveld.fn.Recitable;
import net.kleinhaneveld.fn.Reciter;
import net.kleinhaneveld.fn.reciters.PeekReciter;

import javax.annotation.Nonnull;

public class PeekRecitable<T> implements Recitable<T> {
    private final Recitable<T> in;
    private final Consumer<? super T> consumer;

    public PeekRecitable(@Nonnull Recitable<T> in, @Nonnull Consumer<? super T> consumer) {
        this.in = in;
        this.consumer = consumer;
    }

    @Nonnull
    @Override
    public Reciter<T> reciter() {
        return new PeekReciter<>(in.reciter(), consumer);
    }
}
