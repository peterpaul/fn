package net.kleinhaneveld.fn.reciters;

import net.kleinhaneveld.fn.Option;
import net.kleinhaneveld.fn.Reciter;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class UniqueReciter<T> extends Reciter<T> {
    private final Reciter<T> reciter;
    private final Set<T> previousItems;

    public UniqueReciter(Reciter<T> reciter) {
        super();
        this.reciter = reciter;
        this.previousItems = new HashSet<>();
    }

    @Nonnull
    @Override
    public Option<T> get() {
        Option<T> option = reciter.get();
        while (option.isPresent()) {
            T item = option.get();
            if (!previousItems.contains(item)) {
                previousItems.add(item);
                return option;
            } else {
                option = reciter.get();
            }
        }
        return option;
    }
}
