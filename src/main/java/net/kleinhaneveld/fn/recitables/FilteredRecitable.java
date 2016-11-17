package net.kleinhaneveld.fn.recitables;

import net.kleinhaneveld.fn.Predicate;
import net.kleinhaneveld.fn.Recitable;
import net.kleinhaneveld.fn.Reciter;
import net.kleinhaneveld.fn.reciters.FilteredReciter;

import javax.annotation.Nonnull;

public class FilteredRecitable<T> implements Recitable<T> {
    private final Recitable<T> in;
    private final Predicate<? super T> filter;

    public FilteredRecitable(@Nonnull Recitable<T> in, @Nonnull Predicate<? super T> filter) {
        this.in = in;
        this.filter = filter;
    }

    @Nonnull
    @Override
    public Reciter<T> reciter() {
        return new FilteredReciter<>(in.reciter(), filter);
    }
}
