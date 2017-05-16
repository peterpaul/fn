package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.List;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;

public class ListEnumeration<T> extends Enumeration<T> {
    private List<T> tail;

    public ListEnumeration(@Nonnull List<T> in) {
        this.tail = in;
    }

    @Nonnull
    @Override
    public Option<T> next() {
        if (tail.isNil()) {
            return Option.none();
        } else {
            List.Cons<T> cons = tail.asCons();
            tail = cons.getTail();
            return Option.some(cons.getHead().get());
        }
    }
}
