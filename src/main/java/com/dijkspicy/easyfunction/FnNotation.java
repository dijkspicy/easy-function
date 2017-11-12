package com.dijkspicy.easyfunction;

import java.util.function.Predicate;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/12
 */
public interface FnNotation extends Fn {
    default String getPrefix() {
        return "\\$\\{";
    }

    default String getSuffix() {
        return "}";
    }

    default Predicate<String> getPredicate() {
        return test -> test != null && test.matches(this.getPrefix() + ".*" + this.getSuffix());
    }
}
