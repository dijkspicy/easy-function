package com.dijkspicy.easyfunction.constraint;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsEqual extends BaseConstraint {
    @Override
    public boolean check(Object presentValue) {
        return this.expectedValue == presentValue;
    }
}
