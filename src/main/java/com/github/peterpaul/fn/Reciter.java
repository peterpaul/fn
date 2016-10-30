package com.github.peterpaul.fn;

import com.github.peterpaul.fn.iterators.IterIterator;

import javax.annotation.Nonnull;
import java.util.Iterator;

public abstract class Reciter<T> implements Iterable<T> {
    @Nonnull
    public abstract Option<T> get();

    @Nonnull
    @Override
    public Iterator<T> iterator() {
        return new IterIterator<>(this);
    }
}
