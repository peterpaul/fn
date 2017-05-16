package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Function;
import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Pair;

import javax.annotation.Nonnull;

public class ZipEnumeration<L, R> extends Enumeration<Pair<L, R>> {
    private final Enumeration<L> inLeft;
    private final Enumeration<R> inRight;
    private final Function<L, Option<Pair<L, R>>> zip = new Function<L, Option<Pair<L, R>>>() {
        @Nonnull
        @Override
        public Option<Pair<L, R>> apply(@Nonnull final L left) {
            return inRight.next().flatMap(new Function<R, Option<Pair<L, R>>>() {
                @Nonnull
                @Override
                public Option<Pair<L, R>> apply(@Nonnull R right) {
                    return Option.some(Pair.pair(left, right));
                }
            });
        }
    };

    public ZipEnumeration(@Nonnull Enumeration<L> inLeft, @Nonnull Enumeration<R> inRight) {
        this.inLeft = inLeft;
        this.inRight = inRight;
    }

    @Nonnull
    @Override
    public Option<Pair<L, R>> next() {
        return inLeft.next().flatMap(zip);
    }
}
