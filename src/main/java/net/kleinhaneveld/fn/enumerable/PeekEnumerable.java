package net.kleinhaneveld.fn.enumerable;

import net.kleinhaneveld.fn.Consumer;
import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.enumerations.PeekEnumeration;

import javax.annotation.Nonnull;

public class PeekEnumerable<T> implements Enumerable<T> {
    private final Enumerable<T> in;
    private final Consumer<? super T> consumer;

    public PeekEnumerable(@Nonnull Enumerable<T> in, @Nonnull Consumer<? super T> consumer) {
        this.in = in;
        this.consumer = consumer;
    }

    @Nonnull
    @Override
    public Enumeration<T> enumerate() {
        return new PeekEnumeration<>(in.enumerate(), consumer);
    }
}
