package com.dijkspicy.easyfunction.constraint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsContain implements Constraint {

    @Override
    public boolean check(Object expectedValue, Object presentValue) {
        if (!(presentValue instanceof Collection)) {
            return false;
        }

        List<Object> expectedCollection = new ArrayList<>();
        if (expectedValue instanceof Collection) {
            expectedCollection.addAll((Collection<?>) expectedValue);
        } else {
            expectedCollection.add(expectedValue);
        }

        /*
        表示期望值的每一个值都需要在当前值的列表中
         */
        Collection<?> presentCollection = (Collection) presentValue;
        return expectedCollection.stream()
                .allMatch(exp -> presentCollection.stream().anyMatch(pre -> new ConsEqual().check(exp, pre)));
    }
}
