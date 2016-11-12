package com.github.peterpaul.fn;

import com.github.peterpaul.fn.annotations.Eager;

public interface Runner {
    @Eager
    void run();
}
