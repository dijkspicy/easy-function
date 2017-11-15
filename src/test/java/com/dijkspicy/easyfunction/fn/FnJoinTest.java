package com.dijkspicy.easyfunction.fn;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/14
 */
class FnJoinTest {
    @Test
    void test1() {
        Object out = new FnJoin().calculate(Arrays.asList(Arrays.asList("prefix", 1111, "suffix"), "_"), null);
        System.out.println(out);
    }

    @Test
    void test2() {
        Object out = new FnJoin().calculate(Arrays.asList(Arrays.asList("prefix", 1111, "suffix")), null);
        System.out.println(out);
    }
}