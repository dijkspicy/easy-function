package com.dijkspicy.easyfunction.fn;

import java.util.Map;
import java.util.function.Supplier;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/15
 */
class FnGetProperty extends FnGetParameter {
    @Override
    Map<String, Supplier<Map<String, Object>>> getFn(FunctionContext context) {
        return context.getFnProperties();
    }

    @Override
    String getFnName() {
        return GET_PROPERTY;
    }
}
