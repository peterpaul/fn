package net.kleinhaneveld.fn.recitables;

import net.kleinhaneveld.fn.Pair;
import net.kleinhaneveld.fn.Recitable;
import net.kleinhaneveld.fn.Reciter;
import net.kleinhaneveld.fn.reciters.ZipReciter;

import javax.annotation.Nonnull;

public class ZipRecitable<T, S>  implements Recitable<Pair<T, S>> {
    private final Recitable<T> stream;
    private final Recitable<S> others;

    public ZipRecitable(@Nonnull Recitable<T> stream, @Nonnull Recitable<S> others) {
        this.stream = stream;
        this.others = others;
    }

    @Nonnull
    @Override
    public Reciter<Pair<T, S>> reciter() {
        return new ZipReciter<>(stream.reciter(), others.reciter());
    }
}
