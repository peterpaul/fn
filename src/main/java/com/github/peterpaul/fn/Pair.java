package com.github.peterpaul.fn;

import com.github.peterpaul.fn.equals.Equals;

import javax.annotation.Nonnull;

import java.util.Objects;

import static com.github.peterpaul.fn.validations.Validations.notNull;

public final class Pair<L, R> {
    public static <L, R> Pair<L, R> pair(@Nonnull L left, @Nonnull R right) {
        return new Pair<>(left, right);
    }

    private final L left;
    private final R right;

    private Pair(@Nonnull L left, @Nonnull R right) {
        this.left = notNull(left);
        this.right = notNull(right);
    }

    @Nonnull
    public L getLeft() {
        return left;
    }

    @Nonnull
    public R getRight() {
        return right;
    }

	@Nonnull
	public <T> T map(@Nonnull BiFunction<L, R, T> fun) {
		return fun.apply(left, right);
	}
	
    @Override
    public String toString() {
        return "pair(" + left + ", " + right + ')';
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        return Equals.equals(this, o, new Equals.EqualsChecker<Pair<L, R>>() {
            @Override
            public boolean isEqualTo(@Nonnull Pair<L, R> other) {
                return Objects.equals(left, other.left) &&
                        Objects.equals(right, other.right);
            }
        });
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
