package net.kleinhaneveld.fn.recitables;

import net.kleinhaneveld.fn.Function;
import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Recitable;
import net.kleinhaneveld.fn.Reciter;
import net.kleinhaneveld.fn.reciters.FilterMapReciter;

import javax.annotation.Nonnull;

public class FilterMapRecitable<T, R> implements Recitable<R> {
    private final Recitable<T> in;
    private final Function<? super T, Option<R>> filteredMapper;

    public FilterMapRecitable(Recitable<T> in, Function<? super T, Option<R>> filteredMapper) {
        this.in = in;
        this.filteredMapper = filteredMapper;
    }

    @Nonnull
    @Override
    public Reciter<R> reciter() {
        return new FilterMapReciter<>(in.reciter(), filteredMapper);
    }
}
