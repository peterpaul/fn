package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.annotations.Eager;
import net.kleinhaneveld.fn.annotations.Lazy;
import net.kleinhaneveld.fn.enumerable.*;
import net.kleinhaneveld.fn.status.UniquenessErrorStatus;

import javax.annotation.Nonnull;
import java.util.*;

import static net.kleinhaneveld.fn.status.UniquenessErrorStatus.tooManyElements;

public class Stream<T> implements Enumerable<T>, Iterable<T> {
    private final Enumerable<T> stream;

    private Stream(@Nonnull Enumerable<T> stream) {
        this.stream = stream;
    }

    @Nonnull
    public static <T> Stream<T> stream(@Nonnull Iterable<T> in) {
        return stream(new IterableEnumerable<>(in));
    }

    @Nonnull
    @SafeVarargs
    public static <T> Stream<T> stream(@Nonnull T... items) {
        return stream(new ArrayEnumerable<>(items));
    }

    @Nonnull
    public static <T> Stream<T> stream(@Nonnull Enumerable<T> in) {
        return new Stream<>(in);
    }

    @Nonnull
    @Override
    public Enumeration<T> enumerate() {
        return stream.enumerate();
    }

    @Nonnull
    @Override
    public Iterator<T> iterator() {
        return enumerate().iterator();
    }

    @Lazy
    @Nonnull
    public <R> Stream<R> map(@Nonnull Function<? super T, ? extends R> mapper) {
        return stream(new MappedEnumerable<>(stream, mapper));
    }

    @Lazy
    @Nonnull
    public <R> Stream<R> flatMap(@Nonnull Function<? super T, ? extends Enumerable<R>> mapper) {
        return stream(new FlatmappedEnumerable<>(stream, mapper));
    }

    @Lazy
    @Nonnull
    public Stream<T> filter(@Nonnull Predicate<? super T> filter) {
        return stream(new FilteredEnumerable<>(stream, filter));
    }

    @Lazy
    @Nonnull
    public <R>  Stream<R> filterMap(@Nonnull Function<? super T, Option<R>> filteredMapper){
        return stream(new FilterMapEnumerable<>(stream, filteredMapper));
    }

    @Lazy
    @Nonnull
    public Stream<T> peek(@Nonnull Consumer<? super T> consumer) {
        return stream(new PeekEnumerable<>(stream, consumer));
    }

    @Lazy
    @Nonnull
    public Stream<T> drop(int n) {
        return stream(new DropEnumerable<>(stream, n));
    }

    @Lazy
    @Nonnull
    public Stream<T> take(int n) {
        return stream(new TakeEnumerable<>(stream, n));
    }

    @Lazy
    @Nonnull
    public Stream<T> uniqueStream() {
        return stream(new UniqueEnumerable<>(stream));
    }

    @Lazy
    @Nonnull
    public <S> Stream<Pair<T, S>> zip(Enumerable<S> others) {
        return stream(new ZipEnumerable<>(stream, others));
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
    boolean isEmpty() {
        return !enumerate().iterator().hasNext();
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
        return enumerate().next();
    }
}
