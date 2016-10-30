package com.github.peterpaul.fn;

import com.github.peterpaul.fn.equals.Equals;
import com.github.peterpaul.fn.validations.Validations;

import javax.annotation.Nonnull;
import java.util.Objects;

public abstract class Either<L, R> {
    public static <L, R> Either<L, R> left(@Nonnull L left) {
        return new Left<>(left);
    }

    public static <L, R> Either<L, R> right(@Nonnull R right) {
        return new Right<>(right);
    }

    public boolean isLeft() {
        return false;
    }

    public boolean isRight() {
        return false;
    }

    @Nonnull
    public L fromLeft() {
        throw new IllegalStateException();
    }

    @Nonnull
    public R fromRight() {
        throw new IllegalStateException();
    }

    @Nonnull
    public Option<L> tryFromLeft() {
        return Option.none();
    }

    @Nonnull
    public Option<R> tryFromRight() {
        return Option.none();
    }

    @Nonnull
    public abstract <S> S map(@Nonnull Function<L, S> fromLeft, @Nonnull Function<R, S> fromRight);

    public abstract void consume(@Nonnull Consumer<L> left, @Nonnull Consumer<R> right);

    public static final class Left<L, R> extends Either<L, R> {
        private final L left;

        Left(@Nonnull L left) {
            this.left = Validations.notNull(left);
        }

        @Nonnull
        @Override
        public <S> S map(@Nonnull Function<L, S> fromLeft, @Nonnull Function<R, S> fromRight) {
            return fromLeft.apply(left);
        }

        @Override
        public void consume(@Nonnull Consumer<L> left, @Nonnull Consumer<R> right) {
            left.consume(this.left);
        }

        @Override
        public boolean isLeft() {
            return true;
        }

        @Nonnull
        @Override
        public L fromLeft() {
            return left;
        }

        @Nonnull
        @Override
        public Option<L> tryFromLeft() {
            return Option.some(left);
        }

        @Override
        public String toString() {
            return "left(" + left +
                    ')';
        }

        @Override
        @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
        public boolean equals(Object o) {
            return Equals.equals(this, o, new Equals.EqualsChecker<Left<L, R>>() {
                @Override
                public boolean isEqualTo(@Nonnull Left<L, R> other) {
                    return Objects.equals(left, other.left);
                }
            });
        }

        @Override
        public int hashCode() {
            return Objects.hash(left);
        }
    }

    public static final class Right<L, R> extends Either<L, R> {
        private final R right;

        Right(@Nonnull R right) {
            this.right = Validations.notNull(right);
        }

        @Nonnull
        @Override
        public <S> S map(@Nonnull Function<L, S> fromLeft, @Nonnull Function<R, S> fromRight) {
            return fromRight.apply(right);
        }

        @Override
        public void consume(@Nonnull Consumer<L> left, @Nonnull Consumer<R> right) {
            right.consume(this.right);
        }

        @Override
        public boolean isRight() {
            return true;
        }

        @Nonnull
        @Override
        public R fromRight() {
            return right;
        }

        @Nonnull
        @Override
        public Option<R> tryFromRight() {
            return Option.some(right);
        }

        @Override
        public String toString() {
            return "right(" + right +
                    ')';
        }

        @Override
        @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
        public boolean equals(Object o) {
            return Equals.equals(this, o, new Equals.EqualsChecker<Right<L, R>>() {
                @Override
                public boolean isEqualTo(@Nonnull Right<L, R> other) {
                    return Objects.equals(right, other.right);
                }
            });
        }

        @Override
        public int hashCode() {
            return Objects.hash(right);
        }
    }
}
