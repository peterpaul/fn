package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.equals.Equals;

import javax.annotation.Nonnull;
import java.util.Objects;

public class IntegerRange implements Enumerable<Integer> {
    private final int from;
    private final int to;

    private IntegerRange(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException("Start of range cannot be greater than end.");
        }
        this.from = from;
        this.to = to;
    }

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
    public Enumeration<Integer> enumerate() {
        return new IntegerRangeEnumeration(from, to);
    }

    public static final class IntegerRangeEnumeration extends Enumeration<Integer> {
        private final int to;
        private int current;

        public IntegerRangeEnumeration(int from, int to) {
            this.current = from;
            this.to = to;
        }

        @Nonnull
        @Override
        public Option<Integer> next() {
            return current < to
                    ? Option.some(current++)
                    : Option.<Integer>none();
        }
    }
}
