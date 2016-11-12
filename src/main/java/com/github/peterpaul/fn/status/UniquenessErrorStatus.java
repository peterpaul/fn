package com.github.peterpaul.fn.status;

import com.github.peterpaul.fn.Either;
import com.github.peterpaul.fn.equals.Equals;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Set;

public class UniquenessErrorStatus<T> {
    private final Either<NoElements, TooManyElements<T>> status;

    @Nonnull
    public static <T> UniquenessErrorStatus<T> noElements() {
        return new UniquenessErrorStatus<>(new NoElements());
    }

    @Nonnull
    public static <T> UniquenessErrorStatus<T> tooManyElements(Set<T> items) {
        return new UniquenessErrorStatus<>(new TooManyElements<>(items));
    }

    private UniquenessErrorStatus(NoElements status) {
        this.status = Either.left(status);
    }

    private UniquenessErrorStatus(TooManyElements<T> status) {
        this.status = Either.right(status);
    }

    @Nonnull
    public Either<NoElements, TooManyElements<T>> getStatus() {
        return status;
    }

    @Nonnull
    @Override
    public String toString() {
        return "UniquenessErrorStatus(" + status + '}';
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o) {
        return Equals.equals(this, o, new Equals.EqualsChecker<UniquenessErrorStatus>() {
            @Override
            public boolean isEqualTo(@Nonnull UniquenessErrorStatus other) {
                return Objects.equals(status, other.status);
            }
        });
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}
