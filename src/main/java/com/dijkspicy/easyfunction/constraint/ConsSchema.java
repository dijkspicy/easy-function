package com.dijkspicy.easyfunction.constraint;

import java.util.Collection;
import java.util.Map;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsSchema implements Constraint {
    @Override
    public boolean check(Object expectedValue, Object presentValue) {
        return (expectedValue instanceof Map && this.checkMap((Map<?, ?>) expectedValue, presentValue))
                || (expectedValue instanceof Collection && this.checkCollection((Collection<?>) expectedValue, presentValue));
    }

    private boolean checkCollection(Collection<?> expectedValue, Object presentValue) {
        if (!(presentValue instanceof Collection)) {
            return false;
        }

        if (expectedValue.isEmpty()) {
            return true;
        }

        final Object schemaItem = expectedValue.toArray()[0];
        return ((Collection<?>) presentValue).stream()
                .allMatch(any -> this.check(schemaItem, any));
    }

    private boolean checkMap(Map<?, ?> expectedMap, Object presentValue) {
        if (!(presentValue instanceof Map)) {
            return false;
        }

        if (expectedMap.isEmpty()) {
            return true;
        }

        Map presentMap = (Map) presentValue;
        for (Object schemaKey : expectedMap.keySet()) {
            if (!presentMap.containsKey(schemaKey)) {
                return false;
            }

            Object schemaValue = expectedMap.get(schemaKey);
            if (this.isValid(schemaValue) && !this.check(schemaValue, presentMap.get(schemaKey))) {
                return false;
            }
        }
        return false;
    }

    private boolean isValid(Object value) {
        return value instanceof Map || value instanceof Collection;
    }
}
