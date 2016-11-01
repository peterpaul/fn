package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public interface Recitable<T> {
    @Nonnull
    Reciter<T> reciter();
}
