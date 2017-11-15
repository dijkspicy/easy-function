package com.dijkspicy.easyfunction.fn;

import com.dijkspicy.easyfunction.Fn;
import com.dijkspicy.easyfunction.FunctionException;
import org.apache.commons.lang3.Validate;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
class FnGetNodesOfType implements Fn {
    @Override
    public Object calculate(Object param, FunctionContext context) throws FunctionException {
        Validate.notNull(param);

        String nodeTypeName = param.toString();
        return null;
    }
}
