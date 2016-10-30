package com.github.peterpaul.fn;

import javax.annotation.Nonnull;

public interface Registerable {
    void register(@Nonnull Runner runner);

    void unregister(@Nonnull Runner runner);
}
