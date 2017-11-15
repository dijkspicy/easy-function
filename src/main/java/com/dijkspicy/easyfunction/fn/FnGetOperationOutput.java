package com.dijkspicy.easyfunction.fn;

import com.dijkspicy.easyfunction.Fn;
import com.dijkspicy.easyfunction.FunctionException;
import com.dijkspicy.easyfunction.util.JsonPathReader;
import com.dijkspicy.easyfunction.util.OptionalCollection;
import com.dijkspicy.easyfunction.util.OptionalString;

import java.util.Map;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
class FnGetOperationOutput implements Fn {
    static String getOperationKey(String entityName, String interfaceName, String operationName) {
        return entityName + "." + interfaceName + "." + operationName;
    }

    @Override
    public Object calculate(Object param, FunctionContext context) throws FunctionException {
        Parameters parameters = this.convert(param);
        String key = getOperationKey(parameters.entityName, parameters.interfaceName, parameters.operationName);
        Map<String, Object> output = context.getFnGetOperationOutput().get(key);
        if (output == null) {
            throw new FunctionException("no operation output named: " + key);
        }
        return parameters.outputVariableName == null
                ? output
                : JsonPathReader.read(output, parameters.outputVariableName);
    }

    private Parameters convert(Object param) {
        Object[] array = OptionalCollection.ofSizable(param, 3)
                .orElseThrow(() -> new FunctionException("invalid param for " + this.getFnName() + ": " + param))
                .toArray();
        String entityName = OptionalString.ofNullable(array[0]).orElseThrow(() -> new FunctionException(this.getFnName() + "'s 1st arg can't be null: " + param));
        String interfaceName = OptionalString.ofNullable(array[1]).orElseThrow(() -> new FunctionException(this.getFnName() + "'s 2nd arg can't be null: " + param));
        String operationName = OptionalString.ofNullable(array[2]).orElseThrow(() -> new FunctionException(this.getFnName() + "'s 3rd arg can't be null: " + param));
        if (array.length > 3) {
            String outputVariableName = OptionalString.ofNullable(array[3]).orElseThrow(() -> new FunctionException(this.getFnName() + "'s 4th arg can't be null: " + param));
            return new Parameters(entityName, interfaceName, operationName, outputVariableName);
        }
        return new Parameters(entityName, interfaceName, operationName);
    }

    private String getFnName() {
        return GET_OPERATION_OUTPUT;
    }

    private class Parameters {
        final String entityName;
        final String interfaceName;
        final String operationName;
        String outputVariableName;

        private Parameters(String entityName, String interfaceName, String operationName) {
            this.entityName = entityName;
            this.interfaceName = interfaceName;
            this.operationName = operationName;
        }

        private Parameters(String entityName, String interfaceName, String operationName, String outputVariableName) {
            this(entityName, interfaceName, operationName);
            this.outputVariableName = outputVariableName;
        }
    }
}
