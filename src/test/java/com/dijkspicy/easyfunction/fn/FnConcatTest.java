package com.dijkspicy.easyfunction.fn;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/14
 */
class FnConcatTest {
    @Test
    void test1() {
        Object out = new FnConcat().calculate(Arrays.asList("http://", "127.0.0.1", ":", 31943), null);
        System.out.println(out);
    }
}