package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public interface Consumer<T> {
    void consume(@Nonnull T input);
}
