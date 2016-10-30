package com.github.peterpaul.fn;

import com.github.peterpaul.fn.annotations.Eager;
import com.github.peterpaul.fn.annotations.Lazy;
import com.github.peterpaul.fn.recitables.*;

import java.util.Collection;
import java.util.Iterator;

public class Stream<T> implements Recitable<T>, Iterable<T> {
    private final Recitable<T> stream;

    private Stream(Recitable<T> stream) {
        this.stream = stream;
    }

    public static <T> Stream<T> stream(Iterable<T> in) {
        return new Stream<>(new IterableRecitable<>(in));
    }

    @SafeVarargs
    public static <T> Stream<T> stream(T... items) {
        return new Stream<>(new ArrayRecitable<>(items));
    }

    public static <T> Stream<T> stream(Recitable<T> in) {
        return new Stream<>(in);
    }

    @Override
    public Reciter<T> recite() {
        return stream.recite();
    }

    @Override
    public Iterator<T> iterator() {
        return recite().iterator();
    }

    @Lazy
    public <R> Stream<R> map(Function<T, R> mapper) {
        return new Stream(new MappedRecitable(stream, mapper));
    }

    @Lazy
    public <R> Stream<R> flatMap(Function<T, Recitable<R>> mapper) {
        return new Stream(new FlatmappedRecitable(stream, mapper));
    }

    @Lazy
    public Stream<T> filter(Predicate<T> filter) {
        return new Stream<>(new FilteredRecitable<>(stream, filter));
    }

    @Eager
    public <C extends Collection<T>> C to(C target) {
        for (T item : this) {
            target.add(item);
        }
        return target;
    }

    @Eager
    public Option<T> reduce(final Reduction<T> reduction) {
        boolean hasValue = false;
        T out = null;
        for (T item : this) {
            if (hasValue) {
                out = reduction.apply(out, item);
            } else {
                out = item;
                hasValue = true;
            }
        }
        return Option.of(out);
    }

    @Eager
    public T reduce(T initial, Reduction<T> reduction) {
        T out = initial;
        for (T item : this) {
            out = reduction.apply(out, item);
        }
        return out;
    }
}
