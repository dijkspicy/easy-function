package com.dijkspicy.easyfunction.fn;

import com.dijkspicy.easyfunction.Fn;
import org.apache.commons.lang3.Validate;

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
public class FnConcat implements Fn {
    private static final String CONCAT_PREFIX = "";

    @Override
    public Object calculate(Object param, FunctionContext context) {
        Validate.notNull(param, this.getFnName() + " needs a non-null value");

        List<String> list = this.convert(param);
        return list.stream().reduce(CONCAT_PREFIX, String::concat);
    }

    private List<String> convert(Object param) {
        if (param instanceof Collection) {
            return ((Collection<?>) param)
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
        }
        return Collections.singletonList((String) param);
    }

    public String getFnName() {
        return CONCAT;
    }
}
