package com.dijkspicy.easyfunction.constraint;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/20
 */
public interface Constraint {
    boolean check(Object expectedValue, Object presentValue);
}
