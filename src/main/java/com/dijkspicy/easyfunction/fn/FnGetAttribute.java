package com.dijkspicy.easyfunction.fn;

import java.util.Map;
import java.util.function.Supplier;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
class FnGetAttribute extends FnGetParameter {
    @Override
    Map<String, Supplier<Map<String, Object>>> getFn(FunctionContext context) {
        return context.getFnAttributes();
    }

    @Override
    String getFnName() {
        return GET_ATTRIBUTE;
    }
}
