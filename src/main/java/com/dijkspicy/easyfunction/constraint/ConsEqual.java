package com.dijkspicy.easyfunction.constraint;

import com.alibaba.fastjson.JSON;

import java.util.Collection;
import java.util.Objects;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/21
 */
public class ConsEqual implements Constraint {

    @Override
    public boolean check(Object expectedValue, Object presentValue) {
        return Objects.equals(expectedValue, presentValue)
                || this.compareList(expectedValue, presentValue)
                || this.compareNumber(expectedValue, presentValue)
                || this.compareDifferentType(expectedValue, presentValue);
    }

    private boolean compare(Object o1, Object o2) {
        // 支持简单类型和map，对于list类型需要特殊处理
        return Objects.equals(o1, o2)
                || this.compareList(o1, o2)
                || this.compareNumber(o1, o2)
                || this.compareDifferentType(o1, o2);
    }

    private boolean compareDifferentType(Object o1, Object o2) {
        return Objects.equals(format(o1), format(o2));
    }

    private boolean compareNumber(Object o1, Object o2) {
        if (o1 instanceof Number && o2 instanceof Number) {
            Number n1 = (Number) o1;
            Number n2 = (Number) o2;
            return n1.byteValue() == n2.byteValue()
                    && n1.doubleValue() == n2.doubleValue()
                    && n1.floatValue() == n2.floatValue()
                    && n1.intValue() == n2.intValue()
                    && n1.longValue() == n2.longValue()
                    && n1.shortValue() == n2.shortValue();

            // TODO : big number
        }
        return false;
    }

    private String getVal(Object o2) {
        String test = String.valueOf(o2);
        String[] splits = test.split("\\.");
        if (splits.length == 1) {
            String tmp = splits[0];
        }
        return test;
    }

    @SuppressWarnings("unchecked")
    private boolean compareList(Object o1, Object o2) {
        if (o1 instanceof Collection && o2 instanceof Collection) {
            Collection o1List = (Collection) o1;
            Collection o2List = (Collection) o2;
            return o1List.size() == o2List.size()
                    && o1List.parallelStream().allMatch(it1 -> o2List.parallelStream().anyMatch(it2 -> this.compare(it2, it1)))
                    && o2List.parallelStream().allMatch(it1 -> o1List.parallelStream().anyMatch(it2 -> this.compare(it2, it1)));
        }
        return false;
    }

    private Object format(Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof String) {
            String temp = (String) o;
            return temp.trim().isEmpty() ? "" : temp;
        }

        return JSON.toJSONString(o);
    }
}
