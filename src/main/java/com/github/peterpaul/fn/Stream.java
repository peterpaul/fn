package com.github.peterpaul.fn;

import com.github.peterpaul.fn.annotations.Eager;
import com.github.peterpaul.fn.annotations.Lazy;
import com.github.peterpaul.fn.recitables.*;
import com.github.peterpaul.fn.status.UniquenessErrorStatus;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import static com.github.peterpaul.fn.status.UniquenessErrorStatus.tooManyElements;

public class Stream<T> implements Recitable<T>, Iterable<T> {
    private final Recitable<T> stream;

    private Stream(@Nonnull Recitable<T> stream) {
        this.stream = stream;
    }

    @Nonnull
    public static <T> Stream<T> stream(@Nonnull Iterable<T> in) {
        return new Stream<>(new IterableRecitable<>(in));
    }

    @Nonnull
    @SafeVarargs
    public static <T> Stream<T> stream(@Nonnull T... items) {
        return new Stream<>(new ArrayRecitable<>(items));
    }

    @Nonnull
    public static <T> Stream<T> stream(@Nonnull Recitable<T> in) {
        return new Stream<>(in);
    }

    @Nonnull
    @Override
    public Reciter<T> reciter() {
        return stream.reciter();
    }

    @Nonnull
    @Override
    public Iterator<T> iterator() {
        return reciter().iterator();
    }

    @Lazy
    @Nonnull
    public <R> Stream<R> map(@Nonnull Function<T, R> mapper) {
        return new Stream<>(new MappedRecitable<>(stream, mapper));
    }

    @Lazy
    @Nonnull
    public <R> Stream<R> flatMap(@Nonnull Function<T, Recitable<R>> mapper) {
        return new Stream<>(new FlatmappedRecitable<>(stream, mapper));
    }

    @Lazy
    @Nonnull
    public Stream<T> filter(@Nonnull Predicate<T> filter) {
        return new Stream<>(new FilteredRecitable<>(stream, filter));
    }

    @Lazy
    @Nonnull
    public Stream<T> peek(@Nonnull Consumer<T> consumer) {
        return new Stream<>(new PeekRecitable<>(stream, consumer));
    }

    @Lazy
    @Nonnull
    public Stream<T> drop(int n) {
        return new Stream<>(new DropRecitable<>(stream, n));
    }

    @Lazy
    @Nonnull
    public Stream<T> take(int n) {
        return new Stream<>(new TakeRecitable<>(stream, n));
    }

    @Lazy
    @Nonnull
    public <S> Stream<Pair<T, S>> zip(Recitable<S> others) {
        return new Stream<>(new ZipRecitable<>(stream, others));
    }

    @Eager
    @Nonnull
    public <C extends Collection<T>> C to(@Nonnull C target) {
        for (T item : this) {
            target.add(item);
        }
        return target;
    }

    @Eager
    @Nonnull
    public Option<T> reduce(@Nonnull final Reduction<T> reduction) {
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
    @Nonnull
    public T reduce(@Nonnull T initial, @Nonnull Reduction<T> reduction) {
        T out = initial;
        for (T item : this) {
            out = reduction.apply(out, item);
        }
        return out;
    }

    @Eager
    @Nonnull
    public Either<T, UniquenessErrorStatus<T>> unique() {
        HashSet<T> resultSet = to(new HashSet<T>());
        if (resultSet.isEmpty()) {
            return Either.right(UniquenessErrorStatus.<T>noElements());
        } else if (resultSet.size() > 1) {
            return Either.right(tooManyElements(resultSet));
        } else {
            return Either.left(resultSet.iterator().next());
        }
    }
}
