package com.github.peterpaul.fn;

public abstract class BiFunction<T, S, R> {
    public abstract R apply(T t, S s);

    public Function<S, R> $(final T t) {
        return new Function<S, R>() {
            @Override
            public R apply(S s) {
                return BiFunction.this.apply(t, s);
            }
        };
    }
}
