package com.dijkspicy.easyfunction.constraint;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/26
 */
class ConsContainTest {
    @Test
    void test() {
        Constraint constraint = new ConsContain();
        System.out.println(constraint.check(1, Arrays.asList(1, 2, 3)));
        System.out.println(constraint.check(Arrays.asList(1, 2), Arrays.asList(1, 2, 3)));
        System.out.println(constraint.check(4, Arrays.asList(1, 2, 3)));
        System.out.println(constraint.check(Arrays.asList(1, 4), Arrays.asList(1, 2, 3)));
    }
}