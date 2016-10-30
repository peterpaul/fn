package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.Function;
import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Pair;
import com.github.peterpaul.fn.Reciter;

import javax.annotation.Nonnull;

public class ZipReciter<L, R> extends Reciter<Pair<L, R>> {
    private final Reciter<L> inLeft;
    private final Reciter<R> inRight;

    public ZipReciter(@Nonnull Reciter<L> inLeft, @Nonnull Reciter<R> inRight) {
        this.inLeft = inLeft;
        this.inRight = inRight;
    }

    @Nonnull
    @Override
    public Option<Pair<L, R>> get() {
        return inRight.get()
                .flatMap(new Function<R, Option<Pair<L, R>>>() {
                    @Nonnull
                    @Override
                    public Option<Pair<L, R>> apply(@Nonnull final R right) {
                        return inLeft.get()
                                .flatMap(new Function<L, Option<Pair<L, R>>>() {
                                    @Nonnull
                                    @Override
                                    public Option<Pair<L, R>> apply(@Nonnull L left) {
                                        return Option.some(Pair.pair(left, right));
                                    }
                                });
                    }
                });
    }
}