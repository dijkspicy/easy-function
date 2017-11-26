package com.dijkspicy.easyfunction.constraint;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
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
        binder.bind(Constraint.class).annotatedWith(Names.named("equal")).to(ConsEqual.class);
    });

    public Constraint create(String operator) {
        return this.injector.getInstance(Key.get(Constraint.class, Names.named(operator)));
    }
}
