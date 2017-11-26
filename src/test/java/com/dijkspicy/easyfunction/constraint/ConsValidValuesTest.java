package com.dijkspicy.easyfunction.constraint;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/26
 */
class ConsValidValuesTest {
    @Test
    void test() {
        Constraint constraint = new ConsValidValues();
        System.out.println(constraint.check(Arrays.asList(1, 2, 3), 1));
        System.out.println(constraint.check(Arrays.asList(1, 2, 3), Arrays.asList(1, 2)));
        System.out.println(constraint.check(Arrays.asList(1, 2, 3), 4));
        System.out.println(constraint.check(Arrays.asList(1, 2, 3), Arrays.asList(1, 4)));
    }
}