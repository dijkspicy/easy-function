package com.dijkspicy.easyfunction.util;

import com.alibaba.fastjson.JSONPath;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/15
 */
public final class JsonPathReader {
    private JsonPathReader() {

    }

    public static Object read(Object value, String jsonPath) {
        return JSONPath.eval(value, jsonPath);
    }
}
