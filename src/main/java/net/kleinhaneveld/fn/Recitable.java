package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.annotations.Lazy;

import javax.annotation.Nonnull;

public interface Recitable<T> {
    @Lazy
    @Nonnull
    Reciter<T> reciter();
}
