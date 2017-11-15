package com.dijkspicy.easyfunction.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/14
 */
public final class OptionalString {
    private final String value;

    private OptionalString(String value) {
        this.value = value;
    }

    private OptionalString() {
        this(null);
    }

    public static OptionalString ofNullable(Object o) {
        if (o == null) {
            return new OptionalString();
        }

        String cs = o.toString();
        return isBlank(cs)
                ? new OptionalString()
                : new OptionalString(cs);
    }

    public static OptionalString of(Object o) {
        return o instanceof String
                ? new OptionalString((String) o)
                : new OptionalString(o == null ? null : o.toString());
    }

    private static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public void ifPresent(Consumer<String> consumer) {
        if (this.isPresent()) {
            consumer.accept(this.value);
        }
    }

    public String orElse(String other) {
        return this.isPresent()
                ? this.value
                : other;
    }

    public String orElseGet(Supplier<String> stringSupplier) {
        return this.isPresent()
                ? this.value
                : stringSupplier.get();
    }

    public <X extends Throwable> String orElseThrow(Supplier<X> exceptionSupplier) throws X {
        if (this.isPresent()) {
            return this.value;
        }

        throw exceptionSupplier.get();
    }

    public boolean isPresent() {
        return !isBlank(this.value);
    }
}
