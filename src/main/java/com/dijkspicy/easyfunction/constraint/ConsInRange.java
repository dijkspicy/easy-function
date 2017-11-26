package com.dijkspicy.easyfunction.constraint;

import java.util.Collection;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsInRange implements Constraint {

    @Override
    public boolean check(Object expectedValue, Object presentValue) {
        if (expectedValue instanceof Collection) {
            Collection<?> collection = (Collection<?>) expectedValue;
            boolean isContain = collection.stream()
                    .anyMatch(any -> new ConsEqual().check(presentValue, any));
            if (!isContain) {
                if (collection.size() == 2) {
                    Object[] array = collection.toArray();
                    Object from = array[0];
                    Object to = array[1];
                    return new ConsLessOrEqual().check(to, presentValue) && new ConsGreaterOrEqual().check(from, presentValue);
                }
            }
            return isContain;
        }

        return new ConsEqual().check(expectedValue, presentValue);
    }
}
