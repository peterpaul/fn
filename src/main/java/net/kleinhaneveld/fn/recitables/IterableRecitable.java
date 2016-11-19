package net.kleinhaneveld.fn.recitables;

import net.kleinhaneveld.fn.Recitable;
import net.kleinhaneveld.fn.Reciter;
import net.kleinhaneveld.fn.reciters.IteratorReciter;

import javax.annotation.Nonnull;

public class IterableRecitable<T> implements Recitable<T> {
    private final Iterable<T> iterable;

    public IterableRecitable(@Nonnull Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Nonnull
    @Override
    public Reciter<T> reciter() {
        return new IteratorReciter<>(iterable.iterator());
    }
}
