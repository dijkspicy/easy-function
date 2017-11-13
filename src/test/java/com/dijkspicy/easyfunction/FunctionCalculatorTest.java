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
class FunctionCalculatorTest {
    @Test
    void getFields() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<FunctionCalculator> javaObject = FunctionCalculator.class;
        Method method = javaObject.getDeclaredMethod("getCalculatableFields", Object.class, Class.class);
        method.setAccessible(true);

        Constructor constructor = javaObject.getConstructor(FunctionContext.class);
        Object obj = constructor.newInstance(new FunctionContext());
        List<Field> out = (List<Field>) method.invoke(obj, new HashMap(), FunctionProperty.class);
        out.forEach(it -> System.out.println(it.getName()));
    }
}