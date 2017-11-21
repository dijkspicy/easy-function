package com.dijkspicy.easyfunction.constraint;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/20
 */
public abstract class BaseConstraint {
    protected Object expectedValue;

    protected abstract String getConsName();

    public Object getExpectedValue() {
        return expectedValue;
    }

    public BaseConstraint setExpectedValue(Object expectedValue) {
        this.expectedValue = expectedValue;
        return this;
    }

    public abstract boolean check(Object presentValue);
}
