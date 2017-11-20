package com.dijkspicy.easyfunction.fn;

import com.dijkspicy.easyfunction.Fn;
import com.dijkspicy.easyfunction.FunctionException;
import com.dijkspicy.easyfunction.constraint.ConstraintFactory;
import com.dijkspicy.easyfunction.util.OptionalCollection;
import com.dijkspicy.easyfunction.util.OptionalString;
import org.apache.commons.lang3.Validate;

import java.util.Map;

/**
 * easy-function
 * equal: [1, 2]
 *
 * @Author dijkspicy
 * @Date 2017/11/20
 */
public class FnConstraint implements Fn {
    @Override
    public Object calculate(Object param, FunctionContext context) throws FunctionException {
        Parameters parameters = this.convert(param);
        return ConstraintFactory.singleton.create(parameters.operator, parameters.expectedValue).check(parameters.presentValue);
    }

    private Parameters convert(Object param) {
        if (param instanceof Map) {
            Map map = (Map) param;
            Validate.notEmpty(map);

            String operator = OptionalString.ofNullable(map.keySet().iterator().next()).orElseThrow(() -> new FunctionException("operator can't be blank"));
            Object[] values = OptionalCollection.ofSizable(map.values().iterator().next(), 2).orElseThrow(() -> new FunctionException("values must be a collection with 2 values"))
                    .toArray();
            Object expectedValue = values[0];
            Object presentValue = values[1];
            return new Parameters(operator, expectedValue, presentValue);
        }
        throw new FunctionException(this.getFnName() + "'s param must be a map: " + param);
    }

    public String getFnName() {
        return CONSTRAINT;
    }

    private class Parameters {
        private final String operator;
        private final Object expectedValue;
        private final Object presentValue;

        private Parameters(String operator, Object expectedValue, Object presentValue) {
            this.operator = operator;
            this.expectedValue = expectedValue;
            this.presentValue = presentValue;
        }
    }
}
