package net.kleinhaneveld.fn.recitables;

import net.kleinhaneveld.fn.Function;
import net.kleinhaneveld.fn.Recitable;
import net.kleinhaneveld.fn.Reciter;
import net.kleinhaneveld.fn.reciters.MappedReciter;

import javax.annotation.Nonnull;

public class MappedRecitable<T, R> implements Recitable<R> {
    private final Recitable<T> in;
    private final Function<? super T, ? extends R> mapper;

    public MappedRecitable(@Nonnull Recitable<T> in, @Nonnull Function<? super T, ? extends R> mapper) {
        this.in = in;
        this.mapper = mapper;
    }

    @Nonnull
    @Override
    public Reciter<R> reciter() {
        return new MappedReciter<>(in.reciter(), mapper);
    }
}
