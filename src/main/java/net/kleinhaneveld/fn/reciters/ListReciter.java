package net.kleinhaneveld.fn.reciters;

import net.kleinhaneveld.fn.List;
import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Reciter;

import javax.annotation.Nonnull;

public class ListReciter<T> extends Reciter<T> {
    private List<T> tail;

    public ListReciter(@Nonnull List<T> in) {
        this.tail = in;
    }

    @Nonnull
    @Override
    public Option<T> get() {
        if (tail.isNil()) {
            return Option.none();
        } else {
            List.Cons<T> cons = tail.asCons();
            tail = cons.getTail();
            return Option.some(cons.getHead().get());
        }
    }
}
