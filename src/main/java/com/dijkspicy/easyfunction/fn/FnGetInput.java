package com.dijkspicy.easyfunction.fn;

import com.dijkspicy.easyfunction.Fn;
import com.dijkspicy.easyfunction.error.FunctionException;
import org.apache.commons.lang3.Validate;

import java.util.Optional;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
public class FnGetInput implements Fn {
    @Override
    public Object calculate(Object param, FunctionContext context) throws FunctionException {
        Validate.notNull(param, this.getFnName() + " needs a non-null value");

        String inputPropertyName = param.toString();
        return Optional.ofNullable(context.getFnInputs())
                .orElseThrow(() -> new FunctionException("no " + this.getFnName() + " context defined"))
                .get()
                .get(inputPropertyName);
    }

    public String getFnName() {
        return GET_INPUT;
    }
}
