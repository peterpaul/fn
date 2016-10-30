package com.github.peterpaul.fn.reciters;

import com.github.peterpaul.fn.List;
import com.github.peterpaul.fn.Option;
import com.github.peterpaul.fn.Reciter;

public class ListReciter<T> extends Reciter<T> {
    private List<T> tail;

    public ListReciter(List<T> in) {
        this.tail = in;
    }

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
