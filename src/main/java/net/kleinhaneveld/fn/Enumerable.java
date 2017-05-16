package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.annotations.Lazy;

import javax.annotation.Nonnull;

public interface Enumerable<T> {
    @Lazy
    @Nonnull
    Enumeration<T> enumerate();
}
