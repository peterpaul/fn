package com.github.peterpaul.fn.recitables;

import com.github.peterpaul.fn.Pair;
import com.github.peterpaul.fn.Recitable;
import com.github.peterpaul.fn.Reciter;
import com.github.peterpaul.fn.reciters.ZipReciter;

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
