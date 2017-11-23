package com.dijkspicy.easyfunction.constraint;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsGreaterThan {
    @Override
    public boolean check(Object presentValue){
        return (this.expectedValue instanceof Comparable && presentValue instanceof Comparable)
                && ((Comparable) presentValue).compareTo(this.expectedValue) > 0;
    }
}
