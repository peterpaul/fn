package net.kleinhaneveld.fn.enumerations;

import net.kleinhaneveld.fn.Enumeration;
import net.kleinhaneveld.fn.Option;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class UniqueEnumeration<T> extends Enumeration<T> {
    private final Enumeration<T> enumeration;
    private final Set<T> previousItems;

    public UniqueEnumeration(Enumeration<T> enumeration) {
        super();
        this.enumeration = enumeration;
        this.previousItems = new HashSet<>();
    }

    @Nonnull
    @Override
    public Option<T> next() {
        Option<T> option = enumeration.next();
        while (option.isPresent()) {
            T item = option.get();
            if (!previousItems.contains(item)) {
                previousItems.add(item);
                return option;
            } else {
                option = enumeration.next();
            }
        }
        return option;
    }
}
