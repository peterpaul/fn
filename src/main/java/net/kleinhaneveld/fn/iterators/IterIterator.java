package net.kleinhaneveld.fn.iterators;

import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Reciter;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterIterator<T> implements Iterator<T> {
    private final Reciter<T> tail;
    private Option<T> head;

    public IterIterator(@Nonnull Reciter<T> reciter) {
        this.tail = reciter;
        this.head = tail.get();
    }

    @Override
    public boolean hasNext() {
        return head.isPresent();
    }

    @Nonnull
    @Override
    public T next() {
        if (head.isPresent()) {
            T result = head.get();
            head = tail.get();
            return result;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
