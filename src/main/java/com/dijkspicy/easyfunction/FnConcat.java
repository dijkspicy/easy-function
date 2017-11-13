package com.dijkspicy.easyfunction;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/12
 */
class FnConcat implements Fn {
    @Override
    public Object calculate(Object param, FunctionContext context) throws FunctionException {
        List<String> list = this.convertFromParam(param);
        return list.stream().reduce("", String::concat);
    }

    private List<String> convertFromParam(Object param) {
        if (param instanceof Collection) {
            return ((Collection<?>) param)
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
        }
        return Collections.singletonList((String) param);
    }
}
