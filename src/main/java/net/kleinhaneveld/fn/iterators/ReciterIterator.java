package net.kleinhaneveld.fn.iterators;

import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Reciter;

import javax.annotation.Nonnull;

public class ReciterIterator<T> extends BaseIterator<T> {
    private final Reciter<T> tail;
    private Option<T> head;

    public ReciterIterator(@Nonnull Reciter<T> reciter) {
        this.tail = reciter;
        this.head = tail.get();
    }

    @Override
    public boolean hasNext() {
        return head.isPresent();
    }

    @Nonnull
    @Override
    public T getNext() {
        T result = head.get();
        head = tail.get();
        return result;
    }
}
