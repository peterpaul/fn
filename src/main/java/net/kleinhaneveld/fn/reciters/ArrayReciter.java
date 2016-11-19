package net.kleinhaneveld.fn.reciters;

import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Reciter;

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
