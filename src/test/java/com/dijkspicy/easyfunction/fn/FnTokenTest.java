package com.dijkspicy.easyfunction.fn;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/14
 */
class FnTokenTest {
    @Test
    void test1() {
        Object out = new FnToken().calculate(Arrays.asList("1..2..3..4", "..", 2), null);
        System.out.println(out);
    }
}