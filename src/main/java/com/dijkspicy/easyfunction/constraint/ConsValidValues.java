package com.dijkspicy.easyfunction.constraint;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsValidValues implements Constraint {
    @Override
    public boolean check(Object expectedValue, Object presentValue) {
        return new ConsContain().check(presentValue, expectedValue);
    }
}
