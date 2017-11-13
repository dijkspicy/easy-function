package com.dijkspicy.easyfunction;

import java.util.List;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
abstract class FnGetParameter implements Fn {
    @Override
    public Object calculate(Object param, FunctionContext context) throws FunctionException {
        Parameters parameters = this.getParameters(param);

        return param;
    }

    private Parameters getParameters(Object param) {

        return null;
    }

    private class Parameters {
        final String entityName;
        final List<String> propertyPath;

        private Parameters(String entityName, List<String> propertyPath) {
            this.entityName = entityName;
            this.propertyPath = propertyPath;
        }
    }
}
