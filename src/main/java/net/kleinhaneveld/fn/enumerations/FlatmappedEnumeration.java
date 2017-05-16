package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumerable;
import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Function;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;

public class FlatmappedEnumeration<T, R> extends Enumeration<R> {
    private final Enumeration<T> in;
    private final Function<? super T, ? extends Enumerable<R>> mapper;
    private final Function<Enumerable<R>, Enumeration<R>> recite = new Function<Enumerable<R>, Enumeration<R>>() {
        @Nonnull
        @Override
        public Enumeration<R> apply(@Nonnull Enumerable<R> enumerable) {
            return enumerable.enumerate();
        }
    };
    private final Function<Enumeration<R>, Option<R>> supplier = new Function<Enumeration<R>, Option<R>>() {
        @Nonnull
        @Override
        public Option<R> apply(@Nonnull Enumeration<R> enumeration) {
            return enumeration.next();
        }
    };
    private Option<T> inputItem;
    private Option<Enumeration<R>> outputReciter;

    public FlatmappedEnumeration(@Nonnull Enumeration<T> in, @Nonnull Function<? super T, ? extends Enumerable<R>> mapper) {
        super();
        this.in = in;
        this.mapper = mapper;
        getNext();
    }

    private void getNext() {
        inputItem = in.next();
        outputReciter = inputItem.map(mapper).map(recite);
    }

    @Nonnull
    @Override
    public Option<R> next() {
        while (inputItem.isPresent()) {
            Option<R> out = outputReciter.flatMap(supplier);
            if (out.isPresent()) {
                return out;
            } else {
                getNext();
            }
        }
        return Option.none();
    }
}
