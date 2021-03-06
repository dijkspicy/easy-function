package com.dijkspicy.easyfunction;

import com.dijkspicy.easyfunction.fn.*;
import com.google.inject.*;
import com.google.inject.name.Names;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

/**
 * easy-function
 * 使用工厂模式对每一个传入的fnName实例化一个相应的fn
 *
 * @Author dijkspicy
 * @Date 2017/11/11
 */
public enum FunctionFactory implements FunctionKeywords {
    singleton;

    private final Map<String, FnRegister> FN_REGISTERED = new Hashtable<>();
    private Injector injector;

    FunctionFactory() {
        this.registerFunction(CONCAT, FnConcat.class, Scopes.SINGLETON);
        this.registerFunction(JOIN, FnJoin.class, Scopes.SINGLETON);
        this.registerFunction(TOKEN, FnToken.class, Scopes.SINGLETON);
        this.registerFunction(GET_INPUT, FnGetInput.class, Scopes.SINGLETON);
        this.registerFunction(GET_PROPERTY, FnGetProperty.class, Scopes.SINGLETON);
        this.registerFunction(GET_ATTRIBUTE, FnGetAttribute.class, Scopes.SINGLETON);
        this.registerFunction(GET_OPERATION_OUTPUT, FnGetOperationOutput.class, Scopes.SINGLETON);
        this.registerFunction(GET_NODES_OF_TYPE, FnGetNodesOfType.class, Scopes.SINGLETON);
        this.registerFunction(GET_ARTIFACT, FnGetArtifact.class, Scopes.SINGLETON);
    }

    public Fn create(String fnName) {
        Fn fn = (param, context) -> Collections.singletonMap(fnName, param);
        if (!FN_REGISTERED.containsKey(fnName)) {
            return fn;
        }

        try {
            return Optional.ofNullable(this.injector)
                    .orElseGet(() -> this.injector = Guice.createInjector(binder ->
                            FN_REGISTERED.forEach((k, v) -> binder.bind(Fn.class).annotatedWith(Names.named(k)).to(v.fnClass).in(v.scope))))
                    .getInstance(Key.get(Fn.class, Names.named(fnName)));
        } catch (ConfigurationException | ProvisionException e) {
            return fn;
        }
    }

    private <T extends Fn> void registerFunction(String name, Class<T> fnClass, Scope scope) {
        this.FN_REGISTERED.put(name, new FnRegister(name, fnClass, scope));
    }

    private class FnRegister {
        private final String name;
        private final Class<? extends Fn> fnClass;
        private final Scope scope;

        public <T extends Fn> FnRegister(String name, Class<T> fnClass, Scope scope) {
            this.name = name;
            this.fnClass = fnClass;
            this.scope = scope;
        }
    }
}
