package net.kleinhaneveld.fn.recitables;

import net.kleinhaneveld.fn.Recitable;
import net.kleinhaneveld.fn.Reciter;
import net.kleinhaneveld.fn.reciters.TakeReciter;

import javax.annotation.Nonnull;

public class TakeRecitable<T> implements Recitable<T> {
    private final Recitable<T> in;
    private final int n;

    public TakeRecitable(@Nonnull Recitable<T> in, int n) {
        this.in = in;
        this.n = n;
    }

    @Nonnull
    @Override
    public Reciter<T> reciter() {
        return new TakeReciter<>(in.reciter(), n);
    }
}
