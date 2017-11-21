package com.dijkspicy.easyfunction;

import com.dijkspicy.easyfunction.error.FunctionException;
import com.dijkspicy.easyfunction.fn.FunctionContext;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/11
 */
@FunctionalInterface
public interface Fn extends FunctionKeywords {
    Object calculate(Object param, FunctionContext context) throws FunctionException;
}
