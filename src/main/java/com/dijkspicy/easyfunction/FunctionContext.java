package com.dijkspicy.easyfunction;

import java.util.Hashtable;
import java.util.Map;
import java.util.function.Supplier;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/11
 */
public class FunctionContext {
    private final Map<String, Supplier<Map<String, Object>>> fnProperties = new Hashtable<>();
    private final Map<String, Supplier<Map<String, Object>>> fnAttributes = new Hashtable<>();
    private Supplier<Map<String, Object>> fnInputs;

    public Map<String, Supplier<Map<String, Object>>> getFnProperties() {
        return fnProperties;
    }

    public Map<String, Supplier<Map<String, Object>>> getFnAttributes() {
        return fnAttributes;
    }

    public Supplier<Map<String, Object>> getFnInputs() {
        return fnInputs;
    }

    public FunctionContext setFnInputs(Supplier<Map<String, Object>> fnInputs) {
        this.fnInputs = fnInputs;
        return this;
    }
}
