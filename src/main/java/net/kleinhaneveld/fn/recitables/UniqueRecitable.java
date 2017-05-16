package net.kleinhaneveld.fn.recitables;

import net.kleinhaneveld.fn.Recitable;
import net.kleinhaneveld.fn.Reciter;
import net.kleinhaneveld.fn.reciters.UniqueReciter;

import javax.annotation.Nonnull;

public class UniqueRecitable<T> implements Recitable<T> {
    private final Recitable<T> stream;

    public UniqueRecitable(Recitable<T> stream) {
        this.stream = stream;
    }

    @Nonnull
    @Override
    public Reciter<T> reciter() {
        return new UniqueReciter<>(stream.reciter());
    }
}
