package net.kleinhaneveld.fn.iterators;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;

public class ReciterIterator<T> extends BaseIterator<T> {
    private final Enumeration<T> tail;
    private Option<T> head;

    public ReciterIterator(@Nonnull Enumeration<T> enumeration) {
        this.tail = enumeration;
        this.head = tail.next();
    }

    @Override
    public boolean hasNext() {
        return head.isPresent();
    }

    @Nonnull
    @Override
    public T getNext() {
        T result = head.get();
        head = tail.next();
        return result;
    }
}
