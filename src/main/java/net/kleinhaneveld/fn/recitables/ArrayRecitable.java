package net.kleinhaneveld.fn.recitables;

import net.kleinhaneveld.fn.Recitable;
import net.kleinhaneveld.fn.Reciter;
import net.kleinhaneveld.fn.reciters.ArrayReciter;

import javax.annotation.Nonnull;

public class ArrayRecitable<T> implements Recitable<T> {
    private final T[] in;

    public ArrayRecitable(@Nonnull T[] in) {
        this.in = in;
    }

    @Nonnull
    @Override
    public Reciter<T> reciter() {
        return new ArrayReciter<>(in);
    }
}
