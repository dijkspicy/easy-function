package com.dijkspicy.easyfunction;

import com.alibaba.fastjson.JSONPath;

import java.util.Map;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
public final class JsonPathReader {
    private JsonPathReader() {

    }

    public static Object read(Map<String, Object> map, String jsonPath) {
        return JSONPath.eval(map, jsonPath);
    }
}
