package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.annotations.Eager;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;

public class MemoizedFunction<T, R> extends Function<T, R> implements Registerable, Flushable {
    private final Broadcaster broadcaster;
    private final Function<T, R> functionToMemoize;
    private final LinkedHashMap<T, R> memo;

    MemoizedFunction(@Nonnull Function<T, R> functionToMemoize) {
        this.functionToMemoize = functionToMemoize;
        this.broadcaster = new Broadcaster();
        this.memo = new LinkedHashMap<>();
    }

    @Eager
    @Nonnull
    @Override
    public R apply(@Nonnull T in) {
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
    public void register(@Nonnull Runner runner) {
        broadcaster.register(runner);
    }

    @Override
    public void unregister(@Nonnull Runner runner) {
        broadcaster.unregister(runner);
    }

    @Override
    public void flush() {
        memo.clear();
        broadcaster.run();
    }
}
