package com.github.peterpaul.fn;

public interface Registerable {
    void register(Runner runner);

    void unregister(Runner runner);
}
