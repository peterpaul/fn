package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Reciter;

import javax.annotation.Nonnull;

public class ArrayReciter<T> extends Reciter<T> {
    private final T[] array;
    private int offset;

    public ArrayReciter(@Nonnull T[] array) {
        this.array = array;
    }

    @Nonnull
    @Override
    public Option<T> get() {
        return offset < array.length
                ? Option.some(array[offset++])
                : Option.<T>none();
    }
}
