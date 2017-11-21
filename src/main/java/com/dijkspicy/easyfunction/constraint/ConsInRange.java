package com.dijkspicy.easyfunction.constraint;

import com.dijkspicy.easyfunction.error.ConstraintException;
import com.dijkspicy.easyfunction.util.OptionalCollection;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsInRange extends BaseConstraint {
    @Override
    public boolean check(Object presentValue) {
        Object[] array = OptionalCollection.ofSizable(this.expectedValue, 2)
                .orElseThrow(() -> new ConstraintException(this.getConsName() + " must contain 2 values at least: " + presentValue))
                .toArray();

        return new ConsGreaterOrEqual().setExpectedValue(array[0]).check(presentValue)
                && new ConsLessOrEqual().setExpectedValue(array[1]).check(presentValue);
    }
}
