package com.dijkspicy.easyfunction.constraint;

import java.util.Collection;
import java.util.Collections;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsContain extends BaseConstraint {
    @Override
    protected String getConsName() {
        return null;
    }

    @Override
    public boolean check(Object presentValue) {
        if (!(presentValue instanceof Collection)) {
            return false;
        }

        List<Object> expectedCollection = new ArrayList<>();
        if (this.expectedValue instanceof Collection) {
            expectedCollection.addAll((Collection<?>) this.expectedValue);
        } else {
            expectedCollection.add(this.expectedValue);
        }

        /*
        表示期望值的每一个值都需要在当前值的列表中
         */
        Collection<?> presentCollection = (Collection) presentValue;
        for (Object exp : expectedCollection) {
            // real list包含condition的每一个值
            if (presentCollection.stream().noneMatch(it -> new ConsEqual().compare(it, exp))) {
                return false;
            }
        }
        return true;
    }
}
