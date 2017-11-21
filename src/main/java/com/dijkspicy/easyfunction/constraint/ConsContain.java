package com.dijkspicy.easyfunction.constraint;

import java.util.Collection;
import java.util.Collections;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsContain extends BaseConstraint {
    @Override
    protected String getConsName() {
        return null;
    }

    @Override
    public boolean check(Object presentValue) {
        Collection<?> presentCol;
        if (presentValue instanceof Collection) {
            presentCol = (Collection<?>) presentValue;
        } else {
            presentCol = Collections.singletonList(presentValue);
        }

        final BaseConstraint equal = new ConsEqual().setExpectedValue(this.expectedValue);
        return presentCol.stream().anyMatch(equal::check);
    }
}
