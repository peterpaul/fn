package net.kleinhaneveld.fn.reciters;

import net.kleinhaneveld.fn.Function;
import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Recitable;
import net.kleinhaneveld.fn.Reciter;

import javax.annotation.Nonnull;

public class FlatmappedReciter<T, R> extends Reciter<R> {
    private final Reciter<T> in;
    private final Function<? super T, ? extends Recitable<R>> mapper;
    private final Function<Recitable<R>, Reciter<R>> recite = new Function<Recitable<R>, Reciter<R>>() {
        @Nonnull
        @Override
        public Reciter<R> apply(@Nonnull Recitable<R> recitable) {
            return recitable.reciter();
        }
    };
    private final Function<Reciter<R>, Option<R>> supplier = new Function<Reciter<R>, Option<R>>() {
        @Nonnull
        @Override
        public Option<R> apply(@Nonnull Reciter<R> reciter) {
            return reciter.get();
        }
    };
    private Option<T> inputItem;
    private Option<Reciter<R>> outputReciter;

    public FlatmappedReciter(@Nonnull Reciter<T> in, @Nonnull Function<? super T, ? extends Recitable<R>> mapper) {
        super();
        this.in = in;
        this.mapper = mapper;
        next();
    }

    private void next() {
        inputItem = in.get();
        outputReciter = inputItem.map(mapper).map(recite);
    }

    @Nonnull
    @Override
    public Option<R> get() {
        while (inputItem.isPresent()) {
            Option<R> out = outputReciter.flatMap(supplier);
            if (out.isPresent()) {
                return out;
            } else {
                next();
            }
        }
        return Option.none();
    }
}
