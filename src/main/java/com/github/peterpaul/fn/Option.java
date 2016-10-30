package com.github.peterpaul.fn;

import com.github.peterpaul.fn.equals.Equals;
import com.github.peterpaul.fn.reciters.OptionReciter;

import java.util.Objects;

public abstract class Option<T> extends Supplier<T> implements Recitable<T> {
    private static final None NONE = new None();

    public static <T> Option<T> of(T item) {
        if (item == null) {
            return (Option<T>) NONE;
        } else {
            return new Some(item);
        }
    }

    public static <T> Option<T> some(T item) {
        return new Some(item);
    }

    public static <T> Option<T> none() {
        return (Option<T>) NONE;
    }

    public T or(T defaultValue) {
        return defaultValue;
    }

    public T or(Supplier<T> defaultValue) {
        return defaultValue.get();
    }

    public T orThrow(RuntimeException e) {
        throw e;
    }

    public T orThrow(Supplier<RuntimeException> e) {
        throw e.get();
    }

    @Override
    public <R> Option<R> map(Function<T, R> mapper) {
        return Option.none();
    }

    public <R> Option<R> flatMap(Function<T, Option<R>> mapper) {
        return Option.none();
    }

    public Option<T> filter(Predicate<T> predicate) {
        return Option.none();
    }

    public boolean isPresent() {
        return false;
    }

    @Override
    public Reciter<T> recite() {
        return new OptionReciter<>(this);
    }

    private static final class Some<T> extends Option<T> {
        private final T value;

        public Some(T value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.value = value;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public T or(T defaultValue) {
            return value;
        }

        @Override
        public T or(Supplier<T> defaultValue) {
            return value;
        }

        @Override
        public T orThrow(RuntimeException e) {
            return value;
        }

        @Override
        public T orThrow(Supplier<RuntimeException> e) {
            return value;
        }

        @Override
        public <R> Option<R> map(Function<T, R> mapper) {
            return Option.of(mapper.apply(value));
        }

        @Override
        public <R> Option<R> flatMap(Function<T, Option<R>> mapper) {
            return mapper.apply(value);
        }

        @Override
        public Option<T> filter(Predicate<T> predicate) {
            return predicate.apply(value)
                    ? this
                    : Option.<T>none();
        }

        @Override
        public boolean isPresent() {
            return true;
        }

        @Override
        public String toString() {
            return "some(" +
                    "value=" + value +
                    ')';
        }

        @Override
        @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
        public boolean equals(Object o) {
            return Equals.equals(this, o, new Equals.EqualsChecker<Some<T>>() {
                @Override
                public boolean isEqualTo(Some<T> other) {
                    return Objects.equals(value, other.value);
                }
            });
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private static final class None<T> extends Option<T> {
        @Override
        public T get() {
            throw new NullPointerException();
        }

        @Override
        public String toString() {
            return "none";
        }
    }
}
