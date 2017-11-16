package com.dijkspicy.easyfunction;

import com.google.inject.Module;
import com.google.inject.name.Names;

import java.util.ArrayList;
import java.util.List;

/**
 * easy-function
 * 使用工厂模式对每一个传入的fnName实例化一个相应的fn
 *
 * @Author dijkspicy
 * @Date 2017/11/11
 */
public final class FunctionFactory implements FunctionKeywords {
    private static final List<Module> MODULES = new ArrayList<>();

    public static Fn create(String fnName) {
        return null;
    }

    public static <T extends Fn> void registerFunction(String name, Class<T> fnClass) {
        Module module = binder -> {
            binder.bind(Fn.class).annotatedWith(Names.named(name)).to(fnClass);
        };
        MODULES.add(module);
    }
}
