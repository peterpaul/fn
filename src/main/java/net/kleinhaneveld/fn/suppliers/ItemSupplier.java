package net.kleinhaneveld.fn.suppliers;

import net.kleinhaneveld.fn.Supplier;

public class ItemSupplier<T> extends Supplier<T> {
    private final T item;

    public ItemSupplier(T item) {
        this.item = item;
    }

    @Override
    public T get() {
        return item;
    }
}
