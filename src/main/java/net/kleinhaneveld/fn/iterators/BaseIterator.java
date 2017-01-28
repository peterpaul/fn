package net.kleinhaneveld.fn.iterators;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class BaseIterator<T> implements Iterator<T> {
    public abstract T getNext();

    @Nonnull
    @Override
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return getNext();
    }

    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
