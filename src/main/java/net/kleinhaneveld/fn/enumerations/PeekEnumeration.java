package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Consumer;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;

public class PeekEnumeration<T> extends Enumeration<T> {
    private final Enumeration<T> in;
    private final Consumer<? super T> consumer;

    public PeekEnumeration(@Nonnull Enumeration<T> in, @Nonnull Consumer<? super T> consumer) {
        this.in = in;
        this.consumer = consumer;
    }

    @Nonnull
    @Override
    public Option<T> next() {
        return in.next().peek(consumer);
    }
}
