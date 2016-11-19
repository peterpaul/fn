package net.kleinhaneveld.fn.status;

import net.kleinhaneveld.fn.equals.Equals;

import javax.annotation.Nonnull;
import java.util.Objects;

public final class NoElements {
    @Nonnull
    @Override
    public String toString() {
        return "NoElements()";
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o) {
        return Equals.equals(this, o, new Equals.EqualsChecker<NoElements>() {
            @Override
            public boolean isEqualTo(@Nonnull NoElements other) {
                return true;
            }
        });
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
