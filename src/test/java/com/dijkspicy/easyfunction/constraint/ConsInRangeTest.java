package com.dijkspicy.easyfunction.constraint;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/26
 */
class ConsInRangeTest {
    @Test
    void test() {
        Constraint constraint = new ConsInRange();
        System.out.println(constraint.check(Arrays.asList(1, 3), 1));
        System.out.println(constraint.check(Arrays.asList(1, 3), 2));

        System.out.println(constraint.check(Arrays.asList(1, 3), 3));
        System.out.println(constraint.check(Arrays.asList(1, 3), 4));
        System.out.println(constraint.check(1, 1));
        System.out.println(constraint.check(1, 3));
    }
}