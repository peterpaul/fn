package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;
import java.util.Iterator;

public class IteratorEnumeration<T> extends Enumeration<T> {
    private final Iterator<T> iterator;

    public IteratorEnumeration(@Nonnull Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Nonnull
    @Override
    public Option<T> next() {
        return iterator.hasNext()
                ? Option.some(iterator.next())
                : Option.<T>none();
    }
}
