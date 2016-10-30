package com.github.peterpaul.fn;

public abstract class Function<T, R> {
    public abstract R apply(T input);

    public <S> Function<T, S> $(final Function<R, S> f) {
        return new Function<T, S>() {
            @Override
            public S apply(T input) {
                return f.apply(Function.this.apply(input));
            }
        };
    }
}
