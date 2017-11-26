package com.dijkspicy.easyfunction.constraint;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsPattern implements Constraint {
    @Override
    public boolean check(Object expectedValue, Object presentValue) {
        return String.valueOf(presentValue).matches(String.valueOf(expectedValue));
    }
}
