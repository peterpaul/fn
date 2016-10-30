package com.github.peterpaul.fn;

import java.util.HashSet;
import java.util.Set;

public final class Broadcaster implements Registerable, Runner {
    private final Set<Runner> listeners;

    public Broadcaster() {
        listeners = new HashSet<>();
    }

    @Override
    public void run() {
        for (Runner listener : listeners) {
            listener.run();
        }
    }

    @Override
    public synchronized void register(Runner listener) {
        listeners.add(listener);
    }

    @Override
    public synchronized void unregister(Runner listener) {
        listeners.remove(listener);
    }
}
