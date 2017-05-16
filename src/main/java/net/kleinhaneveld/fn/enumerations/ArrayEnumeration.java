package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;

public class ArrayEnumeration<T> extends Enumeration<T> {
    private final T[] array;
    private int offset;

    public ArrayEnumeration(@Nonnull T[] array) {
        this.array = array;
    }

    @Nonnull
    @Override
    public Option<T> next() {
        return offset < array.length
                ? Option.some(array[offset++])
                : Option.<T>none();
    }
}
