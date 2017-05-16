package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;

public class OptionEnumeration<T> extends Enumeration<T> {
    private Option<T> item;

    public OptionEnumeration(@Nonnull Option<T> item) {
        this.item = item;
    }

    @Nonnull
    @Override
    public Option<T> next() {
        if (item.isPresent()) {
            Option<T> out = item;
            item = Option.none();
            return out;
        }
        return item;
    }
}
