package com.github.peterpaul.fn;

import com.github.peterpaul.fn.reciters.ListReciter;

public abstract class List<T> implements Recitable<T> {
    private static final Nil NIL = new Nil();

    public static <T> Cons<T> cons(Supplier<T> head, List<T> tail) {
        return new Cons<>(head, tail);
    }

    public static <T> Cons<T> cons(T head, List<T> tail) {
        return new Cons<>(Supplier.of(head), tail);
    }

    public static <T> Nil<T> nil() {
        return NIL;
    }

    public boolean isNil() {
        return true;
    }

    public Cons<T> asCons() {
        throw new ClassCastException(toString() + " cannot be cast to Cons");
    }

    @Override
    public Reciter<T> recite() {
        return new ListReciter<>(this);
    }

    public Stream<T> stream() {
        return Stream.stream(this);
    }

    public static final class Cons<T> extends List<T> {
        private final Supplier<T> head;
        private final List<T> tail;

        public Cons(Supplier<T> head, List<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        public Supplier<T> getHead() {
            return head;
        }

        public List<T> getTail() {
            return tail;
        }

        @Override
        public boolean isNil() {
            return false;
        }

        @Override
        public Cons<T> asCons() {
            return this;
        }

        @Override
        public String toString() {
            return "cons(" +
                    head.get() +
                    ", " + tail +
                    ')';
        }
    }

    public static final class Nil<T> extends List<T> {
        @Override
        public String toString() {
            return "nil";
        }
    }
}
