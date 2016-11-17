package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.annotations.Eager;

import javax.annotation.Nonnull;

public interface Consumer<T> {
    @Eager
    void consume(@Nonnull T input);
}
