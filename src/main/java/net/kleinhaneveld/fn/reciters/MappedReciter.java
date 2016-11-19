package net.kleinhaneveld.fn.reciters;

import net.kleinhaneveld.fn.Function;
import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Reciter;

import javax.annotation.Nonnull;

public class MappedReciter<T, R> extends Reciter<R> {
    private final Reciter<T> in;
    private final Function<? super T, ? extends R> mapper;

    public MappedReciter(@Nonnull Reciter<T> in, @Nonnull Function<? super T, ? extends R> mapper) {
        this.in = in;
        this.mapper = mapper;
    }

    @Nonnull
    @Override
    public Option<R> get() {
        return in.get().map(mapper);
    }
}
