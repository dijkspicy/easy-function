package com.dijkspicy.easyfunction.constraint;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsGreaterThan implements Constraint {

    @Override
    public boolean check(Object expectedValue, Object presentValue) {
        return (expectedValue instanceof Comparable && presentValue instanceof Comparable)
                && ((Comparable) presentValue).compareTo(expectedValue) > 0;
    }
}
