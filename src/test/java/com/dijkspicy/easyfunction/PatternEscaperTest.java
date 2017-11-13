package com.dijkspicy.easyfunction;

import org.junit.jupiter.api.Test;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
class PatternEscaperTest {
    @Test
    void test1() {
        System.out.println(PatternEscaper.escape("test.adsf"));
        System.out.println(PatternEscaper.escape(".test.adsf"));
        System.out.println(PatternEscaper.escape("test.adsf."));
        System.out.println(PatternEscaper.escape("te!st\\.adsf"));
        System.out.println(PatternEscaper.escape("\\.test.adsf"));
        System.out.println(PatternEscaper.escape("test.adsf\\."));
        System.out.println(PatternEscaper.escape("test\\\\.adsf"));
        System.out.println(PatternEscaper.escape("\\\\.test.adsf"));
        System.out.println(PatternEscaper.escape("test.adsf\\\\."));
        System.out.println();
        System.out.println(PatternEscaper.escape("test\\\\\\.adsf"));
        System.out.println(PatternEscaper.escape("test\\\\\\\\.adsf"));
    }
}