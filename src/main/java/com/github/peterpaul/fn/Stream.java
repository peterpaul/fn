package com.github.peterpaul.fn;

import com.github.peterpaul.fn.annotations.Eager;
import com.github.peterpaul.fn.annotations.Lazy;
import com.github.peterpaul.fn.recitables.*;
import com.github.peterpaul.fn.status.UniquenessErrorStatus;

import javax.annotation.Nonnull;
import java.util.*;

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
    public <R> Stream<R> map(@Nonnull Function<? super T, ? extends R> mapper) {
        return new Stream<>(new MappedRecitable<>(stream, mapper));
    }

    @Lazy
    @Nonnull
    public <R> Stream<R> flatMap(@Nonnull Function<? super T, ? extends Recitable<R>> mapper) {
        return new Stream<>(new FlatmappedRecitable<>(stream, mapper));
    }

    @Lazy
    @Nonnull
    public Stream<T> filter(@Nonnull Predicate<? super T> filter) {
        return new Stream<>(new FilteredRecitable<>(stream, filter));
    }

    @Lazy
    @Nonnull
    public <R>  Stream<R> filterMap(@Nonnull Function<? super T, Option<R>> filteredMapper){
        return new Stream<>(new FilterMapRecitable<>(stream, filteredMapper));
    }

    @Lazy
    @Nonnull
    public Stream<T> peek(@Nonnull Consumer<? super T> consumer) {
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
    public void forEach(@Nonnull Consumer<? super T> consumer) {
        for (T item : this) {
            consumer.consume(item);
        }
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
    public <K, V> Map<K, V> toMap(@Nonnull Function<? super T, ? extends K> keyFunction,
                                  @Nonnull Function<? super T, ? extends V> valueFunction) {
        Map<K, V> out = new HashMap<>();
        for (T item : this) {
            out.put(keyFunction.apply(item), valueFunction.apply(item));
        }
        return out;
    }

    @Eager
    @Nonnull
    public <K, V> Map<K, V> toMap(@Nonnull Function<T, Pair<K, V>> entryFunction) {
        Map<K, V> out = new HashMap<>();
        for (T item : this) {
            Pair<K, V> entry = entryFunction.apply(item);
            out.put(entry.getLeft(), entry.getRight());
        }
        return out;
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

    @Eager
    @Nonnull
    public Option<T> first() {
        return reciter().get();
    }
}
