package com.dijkspicy.easyfunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/12
 */
class FnJoin implements Fn {
    @Override
    public Object calculate(Object param, FnContext context) throws FnException {
        Join join = this.convertFromParam(param);
        return join.list.stream().reduce("", (foo, bar) -> foo.concat(join.delimiter).concat(bar));
    }

    private Join convertFromParam(Object param) {
        if (param instanceof Collection) {
            Collection<?> collection = (Collection<?>) param;
            if (collection.size() < 2) {
                throw new FnException("FnJoin needs at least two args");
            }

            List<?> list = new ArrayList<>(collection);
            Object var1 = list.get(1);
            if (var1 instanceof Collection) {
                return new Join(String.valueOf(list.get(0)), (Collection<?>) var1);
            }
        }

        throw new FnException("invalid param for FnParam");
    }

    class Join {
        final String delimiter;
        final List<String> list;

        Join(String delimiter, Collection<?> list) {
            this.delimiter = delimiter;
            this.list = list.stream().map(String::valueOf).collect(Collectors.toList());
        }
    }
}
