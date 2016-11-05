package com.github.peterpaul.fn;

import com.github.peterpaul.fn.equals.Equals;

import javax.annotation.Nonnull;
import java.util.Objects;

public class IntegerRange implements Recitable<Integer> {
    private final int from;
    private final int to;

    public static IntegerRange range(int from, int to) {
        return new IntegerRange(from, to);
    }

    public static IntegerRange rangeOfSize(int size) {
        return range(0, size);
    }

    public static IntegerRange rangeStartingFrom(int from) {
        return range(from, Integer.MAX_VALUE);
    }

    public static IntegerRange maxRange() {
        return range(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private IntegerRange(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("Start of range cannot be greater than end.");
        }
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "range[" + from + ", " + to + ')';
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        return Equals.equals(this, o, new Equals.EqualsChecker<IntegerRange>() {
            @Override
            public boolean isEqualTo(@Nonnull IntegerRange other) {
                return from == other.from &&
                        to == other.to;
            }
        });
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Nonnull
    @Override
    public Reciter<Integer> reciter() {
        return new IntegerRangeReciter(from, to);
    }

    public static final class IntegerRangeReciter extends Reciter<Integer> {
        private int current;
        private final int to;

        public IntegerRangeReciter(int from, int to) {
            this.current = from;
            this.to = to;
        }

        @Nonnull
        @Override
        public Option<Integer> get() {
            return current < to
                    ? Option.some(current++)
                    : Option.<Integer>none();
        }
    }
}
