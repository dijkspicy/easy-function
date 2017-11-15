package com.dijkspicy.easyfunction;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/14
 */
public interface Calculator extends FunctionKeywords {
    default Object calculate(Object javaObject) {
        return this.calculate(javaObject, null);
    }

    Object calculate(Object javaObject, Class<? extends Annotation> anno);

    Object calculateNotation(String notation);

    default Object calculateMap(Map map) {
        return this.calculateMap(map, null);
    }

    @SuppressWarnings("unchecked")
    Object calculateMap(Map map, Class<? extends Annotation> anno);

    default Object calculateCollection(Collection collection) {
        return this.calculateCollection(collection, null);
    }

    @SuppressWarnings("unchecked")
    default Object calculateCollection(Collection collection, Class<? extends Annotation> anno) {
        List list = new ArrayList(collection);
        collection.clear();
        list.forEach(it -> collection.add(this.calculate(it, anno)));
        return collection;
    }
}
