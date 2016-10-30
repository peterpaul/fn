package com.github.peterpaul.fn;

import com.github.peterpaul.fn.reciters.ListReciter;

import javax.annotation.Nonnull;

public abstract class List<T> implements Recitable<T> {
    private static final Nil NIL = new Nil();

    @Nonnull
    public static <T> Cons<T> cons(@Nonnull Supplier<T> head, @Nonnull List<T> tail) {
        return new Cons<>(head, tail);
    }

    @Nonnull
    public static <T> Cons<T> cons(@Nonnull T head, @Nonnull List<T> tail) {
        return new Cons<>(Supplier.of(head), tail);
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> Nil<T> nil() {
        return NIL;
    }

    public boolean isNil() {
        return true;
    }

    @Nonnull
    public Cons<T> asCons() {
        throw new ClassCastException(toString() + " cannot be cast to Cons");
    }

    @Nonnull
    @Override
    public Reciter<T> recite() {
        return new ListReciter<>(this);
    }

    @Nonnull
    public Stream<T> stream() {
        return Stream.stream(this);
    }

    public static final class Cons<T> extends List<T> {
        private final Supplier<T> head;
        private final List<T> tail;

        public Cons(@Nonnull Supplier<T> head, @Nonnull List<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Nonnull
        public Supplier<T> getHead() {
            return head;
        }

        @Nonnull
        public List<T> getTail() {
            return tail;
        }

        @Override
        public boolean isNil() {
            return false;
        }

        @Nonnull
        @Override
        public Cons<T> asCons() {
            return this;
        }

        @Nonnull
        @Override
        public String toString() {
            return "cons(" +
                    head.get() +
                    ", " + tail +
                    ')';
        }
    }

    public static final class Nil<T> extends List<T> {
        @Nonnull
        @Override
        public String toString() {
            return "nil";
        }
    }
}
