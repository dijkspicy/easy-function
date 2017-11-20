package com.dijkspicy.easyfunction.constraint;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/20
 */
public abstract class BaseConstraint {
    protected Object expectedValue;

    public abstract boolean check(Object presentValue);
}
