package com.dijkspicy.easyfunction.constraint;

import java.util.Collection;
import java.util.Map;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsLength implements Constraint {

    @Override
    public boolean check(Object expectedValue, Object presentValue) {
        long limit;
        if (expectedValue instanceof Number) {
            limit = ((Number) expectedValue).longValue();
        } else {
            limit = this.parseLong(expectedValue);
        }

        return presentValue instanceof String
                ? ((String) presentValue).length() == limit
                : (presentValue instanceof Map
                ? ((Map) presentValue).size() == limit
                : (presentValue instanceof Collection
                ? ((Collection) presentValue).size() == limit
                : String.valueOf(presentValue).length() == limit));
    }

    private long parseLong(Object expectedValue) {
        try {
            return Long.parseUnsignedLong(String.valueOf(expectedValue));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
