package com.dijkspicy.easyfunction.fn;

import com.dijkspicy.easyfunction.Fn;
import com.dijkspicy.easyfunction.FunctionException;
import com.dijkspicy.easyfunction.util.JsonPathReader;
import com.dijkspicy.easyfunction.util.OptionalCollection;
import com.dijkspicy.easyfunction.util.OptionalString;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
abstract class FnGetParameter implements Fn {
    @Override
    public Object calculate(Object param, FunctionContext context) throws FunctionException {
        Parameters parameters = this.convert(param);
        String jsonPath = Arrays.stream(parameters.nestedPropertyNameOrIndex)
                .reduce(parameters.propertyName, (foo, bar) -> String.valueOf(foo).concat(".").concat(String.valueOf(bar)))
                .toString();

        Map<String, Object> get = Optional.ofNullable(this.getFn(context).get(parameters.entityName))
                .orElseThrow(() -> new FunctionException("no such entity(" + parameters.entityName + ") in " + this.getFnName()))
                .get();
        return JsonPathReader.read(get, jsonPath);
    }

    abstract Map<String, Supplier<Map<String, Object>>> getFn(FunctionContext context);

    private Parameters convert(Object param) {
        Object[] array = OptionalCollection.ofSizable(param, 1)
                .orElseThrow(() -> new FunctionException(this.getFnName() + " must contain 1 arg at least: " + param))
                .toArray();

        String entityName = OptionalString.ofNullable(array[0]).orElseThrow(() -> new FunctionException(this.getFnName() + "'s 1st arg can't be null: " + param));
        if (array.length >= 2) {
            String propertyName = OptionalString.ofNullable(array[1]).orElseThrow(() -> new FunctionException(this.getFnName() + "'s 2nd arg can't be null: " + param));
            if (array.length > 2) {
                Object[] nestedPropertyNameOrIndex = new Object[array.length - 2];
                System.arraycopy(array, 2, nestedPropertyNameOrIndex, 0, nestedPropertyNameOrIndex.length);
                return new Parameters(entityName, propertyName, nestedPropertyNameOrIndex);
            }
            return new Parameters(entityName, propertyName);
        }
        return new Parameters(entityName);
    }

    abstract String getFnName();

    private class Parameters {
        final String entityName;
        String propertyName;
        Object[] nestedPropertyNameOrIndex = {};

        private Parameters(String entityName) {
            this.entityName = entityName;
        }

        private Parameters(String entityName, String propertyName) {
            this(entityName);
            this.propertyName = propertyName;
        }

        private Parameters(String entityName, String propertyName, Object[] nestedPropertyNameOrIndex) {
            this(entityName, propertyName);
            this.nestedPropertyNameOrIndex = nestedPropertyNameOrIndex;
        }
    }
}
