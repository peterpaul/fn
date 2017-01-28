package net.kleinhaneveld.fn.validations;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

public abstract class Validations {
    public static <T> T notNull(@CheckForNull T item) {
        if (item == null) {
            throw new NullPointerException();
        }
        return item;
    }

    public static <T> T notNull(@CheckForNull T item, @Nonnull String message, @Nonnull String... args) {
        if (item == null) {
            throw new NullPointerException(String.format(message, args));
        }
        return item;
    }
}
