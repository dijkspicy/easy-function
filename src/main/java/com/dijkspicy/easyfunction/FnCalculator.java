package com.dijkspicy.easyfunction;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/12
 */
public class FnCalculator {
    private final FnContext context;

    public FnCalculator(FnContext context) {
        this.context = context;
    }

    public Object calculate(Object javaObject) {
        return this.calculate(javaObject, FnProperty.All.class);
    }

    public Object calculate(final Object javaObject, Class<? extends Annotation> anno) {
        if (null == javaObject) {
            return null;
        } else if (this.isBasicType(javaObject)) {
            return javaObject;
        } else if (this.isFnNotation(javaObject)) {
            return this.calculateNotation((String) javaObject);
        } else if (javaObject instanceof Collection) {
            return this.calculateCollection((Collection) javaObject, anno);
        } else if (javaObject instanceof Map) {
            return this.calculateMap((Map) javaObject, anno);
        } else {
            this.getCalculatableFields(javaObject, anno).forEach(it -> {
                this.setValue(javaObject, it, anno);
            });
            return javaObject;
        }
    }

    public Object calculateNotation(String notation) {
        return FnFactory.create(FnKeywords.GET_NOTATION).calculate(notation, this.context);
    }

    @SuppressWarnings("unchecked")
    public Object calculateMap(Map map, Class<? extends Annotation> anno) {
        List<Object> calculatedKeys = new ArrayList<>();
        map.forEach((k, v) -> {
            Object nk = this.calculate(k, anno);
            Object nv = this.calculate(v, anno);
            if (nk != k) {
                calculatedKeys.add(k);
            }
            map.put(nk, nv);
        });
        calculatedKeys.forEach(map::remove);
        return this.fnCalculate(map);
    }

    @SuppressWarnings("unchecked")
    public Object calculateCollection(Collection collection, Class<? extends Annotation> anno) {
        List list = new ArrayList(collection);
        collection.clear();
        list.forEach(it -> collection.add(this.calculate(it, anno)));
        return collection;
    }

    private Object fnCalculate(Map map) {
        if (map.size() != 1) {
            return map;
        }

        Object key = map.keySet().iterator().next();
        if (!(key instanceof String)) {
            return map;
        }
        return FnFactory.create((String) key).calculate(map.values().iterator().next(), this.context);
    }

    private void setValue(Object javaObject, Field field, Class<? extends Annotation> anno) {
        Object curValue = null;
        try {
            curValue = field.get(javaObject);
        } catch (IllegalAccessException ignored) {
        }
        Object calculatedValue = this.calculate(curValue, anno);
        try {
            field.set(javaObject, calculatedValue);
        } catch (IllegalAccessException ignored) {
        }
    }

    private List<Field> getCalculatableFields(Object javaObject, Class<? extends Annotation> anno) {
        Class<?> javaClass = javaObject.getClass();
        return this.getCalculatableFields(javaObject, javaClass, anno);
    }

    private List<Field> getCalculatableFields(Object javaObject, Class<?> javaClass, Class<? extends Annotation> anno) {
        if (null == javaClass) {
            return Collections.emptyList();
        }

        List<Field> out = Stream.of(javaClass.getDeclaredFields())
                .filter(it -> !Modifier.toString(it.getModifiers()).contains("final"))
                .filter(it -> {
                    it.setAccessible(true);
                    try {
                        return this.isBasicType(it.get(javaObject));
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                })
                .filter(it -> anno.equals(FnProperty.All.class) || it.getAnnotationsByType(anno) != null)
                .collect(Collectors.toList());
        out.addAll(this.getCalculatableFields(javaObject, javaClass.getSuperclass(), anno));
        return out;
    }

    // TODO fnNotation
    private boolean isFnNotation(Object javaObject) {
        FnNotation fnNotation = this.context.getFnNotation();
        return javaObject instanceof String && fnNotation != null && fnNotation.getPredicate().test((String) javaObject);
    }

    /**
     * string is for notation
     */
    private boolean isBasicType(Object javaObject) {
        return javaObject == null
                || javaObject instanceof Number
                || javaObject instanceof Boolean
                || javaObject instanceof Character
                || javaObject instanceof Enum;
    }
}
