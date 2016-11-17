package net.kleinhaneveld.fn.reciters;

import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Reciter;

import javax.annotation.Nonnull;
import java.util.Iterator;

public class IteratorReciter<T> extends Reciter<T> {
    private final Iterator<T> iterator;

    public IteratorReciter(@Nonnull Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Nonnull
    @Override
    public Option<T> get() {
        return iterator.hasNext()
                ? Option.some(iterator.next())
                : Option.<T>none();
    }
}
