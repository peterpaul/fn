package net.kleinhaneveld.fn;

import net.kleinhaneveld.fn.suppliers.ItemSupplier;

import javax.annotation.Nonnull;

public class Suppliers {
    @Nonnull
    public static <T> Supplier<T> of(@Nonnull final T item) {
        return new ItemSupplier<>(item);
    }
}
