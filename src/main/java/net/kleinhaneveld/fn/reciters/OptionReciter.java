package net.kleinhaneveld.fn.reciters;

import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Reciter;

import javax.annotation.Nonnull;

public class OptionReciter<T> extends Reciter<T> {
    private Option<T> item;

    public OptionReciter(@Nonnull Option<T> item) {
        this.item = item;
    }

    @Nonnull
    @Override
    public Option<T> get() {
        if (item.isPresent()) {
            Option<T> out = item;
            item = Option.none();
            return out;
        }
        return item;
    }
}
