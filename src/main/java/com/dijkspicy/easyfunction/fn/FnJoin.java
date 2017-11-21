package com.dijkspicy.easyfunction.fn;

import com.dijkspicy.easyfunction.Fn;
import com.dijkspicy.easyfunction.error.FunctionException;
import com.dijkspicy.easyfunction.util.OptionalCollection;
import com.dijkspicy.easyfunction.util.OptionalString;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/12
 */
public class FnJoin implements Fn {
    private static final String JOIN_PREFIX = "";

    @Override
    public Object calculate(Object param, FunctionContext context) throws FunctionException {
        Parameters parameters = this.convertFromParam(param);
        return OptionalString.of(parameters.delimiter).isPresent()
                ? parameters.list.stream().reduce(JOIN_PREFIX, (foo, bar) -> JOIN_PREFIX.equals(foo) ? bar : foo.concat(parameters.delimiter).concat(bar))
                : parameters.list.stream().reduce(JOIN_PREFIX, String::concat);
    }

    private Parameters convertFromParam(Object param) {
        Object[] array = OptionalCollection.ofSizable(param, 1)
                .orElseThrow(() -> new FunctionException("invalid param for " + this.getFnName()))
                .toArray();
        Collection<?> list = OptionalCollection.ofSizable(array[0], 0)
                .orElseThrow(() -> new FunctionException("invalid param for " + this.getFnName()));
        return array.length > 1 && OptionalString.of(array[1]).isPresent()
                ? new Parameters(String.valueOf(array[1]), list)
                : new Parameters(list);
    }

    private String getFnName() {
        return JOIN;
    }

    private class Parameters {
        final List<String> list;
        String delimiter;

        private Parameters(String delimiter, Collection<?> list) {
            this(list);
            this.delimiter = delimiter;
        }

        private Parameters(Collection<?> list) {
            this.list = list.stream().map(String::valueOf).collect(Collectors.toList());
        }
    }
}
