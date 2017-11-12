package com.dijkspicy.easyfunction;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/12
 */
class FnCalculatorTest {
    @Test
    void getFields() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<FnCalculator> javaObject = FnCalculator.class;
        Method method = javaObject.getDeclaredMethod("getCalculatableFields", Object.class, Class.class);
        method.setAccessible(true);

        Constructor constructor = javaObject.getConstructor(FnContext.class);
        Object obj = constructor.newInstance(new FnContext());
        List<Field> out = (List<Field>) method.invoke(obj, new HashMap(), FnProperty.class);
        out.forEach(it -> System.out.println(it.getName()));
    }
}