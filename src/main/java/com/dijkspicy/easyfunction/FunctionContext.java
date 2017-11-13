package com.dijkspicy.easyfunction;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/11
 */
public class FunctionContext {
    private FnNotation fnNotation;

    public FnNotation getFnNotation() {
        return fnNotation;
    }

    public FunctionContext setFnNotation(FnNotation fnNotation) {
        this.fnNotation = fnNotation;
        return this;
    }
}
