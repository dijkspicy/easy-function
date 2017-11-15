package com.dijkspicy.easyfunction.util;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/14
 */
public class OptionalCollection {
    private final Collection<?> value;

    private OptionalCollection(Collection<?> value) {
        this.value = value;
    }

    private OptionalCollection() {
        this(null);
    }

    public static OptionalCollection ofSizable(Object col, int size) {
        if (col instanceof Collection) {
            Collection<?> collection = (Collection<?>) col;
            if (collection.size() >= size) {
                return new OptionalCollection(collection);
            }
        }
        return new OptionalCollection();
    }

    public <X extends Throwable> Collection<?> orElseThrow(Supplier<X> exceptionSupplier) throws X {
        if (this.isPresent()) {
            return this.value;
        }
        throw exceptionSupplier.get();
    }

    public boolean isPresent() {
        return this.value != null;
    }
}
