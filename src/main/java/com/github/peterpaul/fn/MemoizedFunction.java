package com.github.peterpaul.fn;

import java.util.LinkedHashMap;

public class MemoizedFunction<T, R> extends Function<T, R> implements Registerable, Flushable {
    public static <T, R> MemoizedFunction<T, R> memoize(Function<T, R> functionToMemoize) {
        return new MemoizedFunction<>(functionToMemoize);
    }

    private final Broadcaster broadcaster;
    private final Function<T, R> functionToMemoize;
    private final LinkedHashMap<T, R> memo;

    private MemoizedFunction(Function<T, R> functionToMemoize) {
        this.functionToMemoize = functionToMemoize;
        this.broadcaster = new Broadcaster();
        this.memo = new LinkedHashMap<>();
    }

    @Override
    public R apply(T in) {
        R out = memo.get(in);
        if (out == null) {
            synchronized (this) {
                out = memo.get(in);
                if (out == null) {
                    out = functionToMemoize.apply(in);
                    memo.put(in, out);
                }
            }
        }
        return out;
    }

    @Override
    public void register(Runner runner) {
        broadcaster.register(runner);
    }

    @Override
    public void unregister(Runner runner) {
        broadcaster.unregister(runner);
    }

    @Override
    public void flush() {
        memo.clear();
        broadcaster.run();
    }
}
