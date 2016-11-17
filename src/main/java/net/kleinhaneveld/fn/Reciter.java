package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.annotations.Eager;
import net.kleinhaneveld.fn.annotations.Lazy;
import net.kleinhaneveld.fn.iterators.IterIterator;

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
