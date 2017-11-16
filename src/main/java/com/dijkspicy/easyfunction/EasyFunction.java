package com.dijkspicy.easyfunction;

import com.dijkspicy.easyfunction.fn.FunctionContext;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/14
 */
public final class EasyFunction {
    private EasyFunction() {
    }

    public static FunctionContext createContext() {
        return new InnerFunctionContext();
    }

    public static FunctionCalculator createCalculator(FunctionContext context) {
        return new FunctionCalculator(context);
    }

    private static class InnerFunctionContext extends FunctionContext {
    }
}
