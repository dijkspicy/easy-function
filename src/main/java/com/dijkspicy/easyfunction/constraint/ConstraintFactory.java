package com.dijkspicy.easyfunction.constraint;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/20
 */
public enum ConstraintFactory {
    singleton;

    private Injector injector = Guice.createInjector(binder -> {
        binder.bind(BaseConstraint.class).annotatedWith(Names.named("equal")).to(ConsEqual.class);
    });

    public BaseConstraint create(String operator, Object expectedValue) {
        return null;
    }
}
