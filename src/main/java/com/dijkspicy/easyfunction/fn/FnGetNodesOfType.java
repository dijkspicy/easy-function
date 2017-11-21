package com.dijkspicy.easyfunction.fn;

import com.dijkspicy.easyfunction.Fn;
import com.dijkspicy.easyfunction.error.FunctionException;
import org.apache.commons.lang3.Validate;

import java.util.Collections;
import java.util.Set;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
public class FnGetNodesOfType implements Fn {
    @Override
    public Object calculate(Object param, FunctionContext context) throws FunctionException {
        Validate.notNull(param, this.getFnName() + " needs a non-null value");

        String nodeTypeName = param.toString();
        Set<Object> nodes = context.getFnNodesOfType().get(nodeTypeName);
        if (nodes == null) {
            return Collections.emptyList();
        }
        return nodes;
    }

    public String getFnName() {
        return GET_NODES_OF_TYPE;
    }
}
