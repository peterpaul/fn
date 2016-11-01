package com.github.peterpaul.fn.status;

import com.github.peterpaul.fn.equals.Equals;

import javax.annotation.Nonnull;
import java.util.Objects;

public final class NoElements {
    @Override
    public String toString() {
        return "NoElements()";
    }

    @Override
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
