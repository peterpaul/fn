package com.github.peterpaul.fn;

import com.github.peterpaul.fn.annotations.Eager;
import com.github.peterpaul.fn.annotations.Lazy;
import com.github.peterpaul.fn.iterators.IterIterator;

import javax.annotation.Nonnull;
import java.util.Iterator;

public abstract class Reciter<T> implements Iterable<T> {
    @Eager
    @Nonnull
    public abstract Option<T> get();

    @Lazy
    @Nonnull
    @Override
    public Iterator<T> iterator() {
        return new IterIterator<>(this);
    }
}
