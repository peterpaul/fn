package com.github.peterpaul.fn;

import javax.annotation.Nonnull;
import java.util.function.*;

public class Container<T> extends Supplier<Option<T>> {
    private T value;

    public void set(@Nonnull T value) {
        this.value = value;
    }

    public void reset() {
        this.value = null;
    }

    @Nonnull
    @Override
    public Option<T> get() {
        return Option.of(value);
    }

    @Override
    public String toString() {
        return "container(" + value + ')';
    }

    /*
     * Container value is mutable, so use instance hashCode.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /*
     * Container value is mutable, so use instance equality.
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
