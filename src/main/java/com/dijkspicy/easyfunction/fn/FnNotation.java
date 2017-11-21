package com.dijkspicy.easyfunction.fn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.dijkspicy.easyfunction.Fn;
import com.dijkspicy.easyfunction.error.FunctionException;
import com.dijkspicy.easyfunction.util.OptionalString;
import com.dijkspicy.easyfunction.util.PatternEscaper;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * easy-function
 * ${entityName.jsonPathWithout$}
 *
 * @Author dijkspicy
 * @Date 2017/11/12
 */
public class FnNotation implements Fn {
    private static final Pattern PREDICATE_PATTERN = Pattern.compile(".*" + prefix() + ".+" + suffix() + ".*");
    private static final Pattern PATTERN = Pattern.compile(".*" + prefix() + ".+" + suffix() + ".*");
    private static final Pattern JSON_PATTERN = Pattern.compile("(\\{.*}|\\[.*])");

    public static Predicate<String> getPredicate() {
        return PREDICATE_PATTERN.asPredicate();
    }

    private static String suffix() {
        String value = OptionalString.ofNullable(NOTATION_SUFFIX)
                .orElse("}");
        return PatternEscaper.escape(value);
    }

    private static String prefix() {
        String value = OptionalString.ofNullable(NOTATION_PREFIX)
                .orElse("${");
        return PatternEscaper.escape(value);
    }

    @Override
    public Object calculate(Object param, FunctionContext context) throws FunctionException {
        String notation = OptionalString.ofNullable(param).orElseThrow(() -> new FunctionException(this.getFnName() + " needs a non-null value"));
        Matcher matcher = PATTERN.matcher(notation);
        while (matcher.find()) {
            String group = matcher.group();
            Object value = this.calculateGroup(group, context);
            notation = notation.replace(group, String.valueOf(value));
        }

        notation = notation.trim();
        if (JSON_PATTERN.asPredicate().test(notation)) {
            try {
                return JSON.parse(notation);
            } catch (JSONException ignored) {
            }
        }
        return notation;
    }

    private Object calculateGroup(String group, FunctionContext context) {
        String pureGroup = group.replaceFirst(prefix(), "").replaceFirst(suffix(), "");
        String[] splits = pureGroup.split("\\.");
        if (splits.length == 0) {
            return group;
        }
        if (context.getFnAttributes().containsKey(splits[0])) {
            return splits.length == 1
                    ? new FnGetAttribute().calculate(Collections.singletonList(splits[0]), context)
                    : new FnGetAttribute().calculate(Arrays.asList(splits[0], pureGroup.substring(splits[0].length() + 1)), context);
        }
        return group;
    }

    public String getFnName() {
        return this.getClass().getSimpleName();
    }
}
