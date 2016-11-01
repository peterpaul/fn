package com.github.peterpaul.fn;

import com.github.peterpaul.fn.equals.Equals;
import com.github.peterpaul.fn.reciters.OptionReciter;
import com.github.peterpaul.fn.validations.Validations;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.Objects;

public abstract class Option<T> implements Recitable<T> {
    private static final None NONE = new None();

    @Nonnull
    public static <T> Option<T> of(@CheckForNull T item) {
        if (item == null) {
            return none();
        } else {
            return new Some<>(item);
        }
    }

    @Nonnull
    public static <T> Option<T> some(@Nonnull T item) {
        return new Some<>(item);
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> Option<T> none() {
        return (Option<T>) NONE;
    }

    @Nonnull
    public T get() {
        throw new NullPointerException();
    }

    @Nonnull
    public T or(@Nonnull T defaultValue) {
        return defaultValue;
    }

    @Nonnull
    public T or(@Nonnull Supplier<T> defaultValue) {
        return defaultValue.get();
    }

    @Nonnull
    public T orThrow(@Nonnull RuntimeException e) {
        throw e;
    }

    @Nonnull
    public T orThrow(@Nonnull Supplier<RuntimeException> e) {
        throw e.get();
    }

    @Nonnull
    public <R> Option<R> map(@Nonnull Function<T, R> mapper) {
        return Option.none();
    }

    @Nonnull
    public <R> Option<R> flatMap(@Nonnull Function<T, Option<R>> mapper) {
        return Option.none();
    }

    @Nonnull
    public Option<T> filter(@Nonnull Predicate<T> predicate) {
        return Option.none();
    }

    @Nonnull
    public Option<T> peek(@Nonnull Consumer<T> consumer) {
        return this;
    }

    public boolean isPresent() {
        return false;
    }

    @Nonnull
    @Override
    public Reciter<T> reciter() {
        return new OptionReciter<>(this);
    }

    private static final class Some<T> extends Option<T> {
        private final T value;

        public Some(@Nonnull T value) {
            this.value = Validations.notNull(value);
        }

        @Nonnull
        @Override
        public T get() {
            return value;
        }

        @Nonnull
        @Override
        public T or(@Nonnull T defaultValue) {
            return value;
        }

        @Nonnull
        @Override
        public T or(@Nonnull Supplier<T> defaultValue) {
            return value;
        }

        @Nonnull
        @Override
        public T orThrow(@Nonnull RuntimeException e) {
            return value;
        }

        @Nonnull
        @Override
        public T orThrow(@Nonnull Supplier<RuntimeException> e) {
            return value;
        }

        @Nonnull
        @Override
        public <R> Option<R> map(@Nonnull Function<T, R> mapper) {
            return Option.of(mapper.apply(value));
        }

        @Nonnull
        @Override
        public <R> Option<R> flatMap(@Nonnull Function<T, Option<R>> mapper) {
            return mapper.apply(value);
        }

        @Nonnull
        @Override
        public Option<T> filter(@Nonnull Predicate<T> predicate) {
            return predicate.apply(value)
                    ? this
                    : Option.<T>none();
        }

        @Nonnull
        @Override
        public Option<T> peek(@Nonnull Consumer<T> consumer) {
            consumer.consume(value);
            return this;
        }

        @Override
        public boolean isPresent() {
            return true;
        }

        @Nonnull
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
                public boolean isEqualTo(@Nonnull Some<T> other) {
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
        @Nonnull
        @Override
        public String toString() {
            return "none";
        }
    }
}
