package com.github.peterpaul.fn;

import com.github.peterpaul.fn.annotations.Eager;

import javax.annotation.Nonnull;

public interface Consumer<T> {
    @Eager
    void consume(@Nonnull T input);
}
