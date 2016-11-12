package com.github.peterpaul.fn;

import com.github.peterpaul.fn.annotations.Lazy;

import javax.annotation.Nonnull;

public interface Recitable<T> {
    @Lazy
    @Nonnull
    Reciter<T> reciter();
}
