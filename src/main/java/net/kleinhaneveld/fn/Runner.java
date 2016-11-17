package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.annotations.Eager;

public interface Runner {
    @Eager
    void run();
}
