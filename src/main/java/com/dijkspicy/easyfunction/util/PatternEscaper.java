package com.dijkspicy.easyfunction.util;

import java.util.HashMap;
import java.util.Map;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
public final class PatternEscaper {
    private static final Map<Character, String> MAPPINGS = new HashMap<>();

    static {
        MAPPINGS.put('$', "\\$");
        MAPPINGS.put('.', "\\.");
        MAPPINGS.put('{', "\\{");
        MAPPINGS.put('[', "\\[");
        MAPPINGS.put('?', "\\?");
        MAPPINGS.put('=', "\\=");
        MAPPINGS.put('<', "\\<");
        MAPPINGS.put('^', "\\^");
        MAPPINGS.put('|', "\\|");
    }

    private PatternEscaper() {
    }

    /**
     * replace
     * .->\\.
     * \.->\\.
     */
    public static String escape(String from) {
        int start = 0;
        int end;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < from.length(); i++) {
            char ch = from.charAt(i);
            if (MAPPINGS.containsKey(ch)) {
                String value = MAPPINGS.get(ch);
                int t = 0;
                end = i;
                while (i > t && from.charAt(i - t - 1) == '\\') {
                    end = i - t - 1;
                    t++;
                }
                sb.append(from.substring(start, end)).append(value);

                if (i < from.length()) {
                    start = i + 1;
                } else {
                    start = i;
                }
            }
        }
        end = from.length();
        sb.append(from.substring(start, end));
        return sb.toString();
    }
}
