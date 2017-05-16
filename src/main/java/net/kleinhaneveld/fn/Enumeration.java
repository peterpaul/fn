package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.annotations.Eager;
import net.kleinhaneveld.fn.annotations.Lazy;
import net.kleinhaneveld.fn.iterators.ReciterIterator;

import javax.annotation.Nonnull;
import java.util.Iterator;

public abstract class Enumeration<T> implements Iterable<T> {
    @Eager
    @Nonnull
    public abstract Option<T> next();

    @Lazy
    @Nonnull
    @Override
    public Iterator<T> iterator() {
        return new ReciterIterator<>(this);
    }
}
