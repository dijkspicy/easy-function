package com.dijkspicy.easyfunction;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/11
 */
public interface Fn {
    Object calculate(Object param, FnContext context) throws FnException;
}
