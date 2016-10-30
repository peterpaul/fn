package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Reciter;

import java.util.Iterator;

public class IteratorReciter<T> extends Reciter<T> {
    private final Iterator<T> iterator;

    public IteratorReciter(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public Option<T> get() {
        return iterator.hasNext()
                ? Option.some(iterator.next())
                : Option.<T>none();
    }
}