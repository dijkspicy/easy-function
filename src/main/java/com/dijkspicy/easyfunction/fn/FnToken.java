package com.dijkspicy.easyfunction.fn;

import com.dijkspicy.easyfunction.Fn;
import com.dijkspicy.easyfunction.error.FunctionException;
import com.dijkspicy.easyfunction.util.OptionalCollection;
import com.dijkspicy.easyfunction.util.OptionalString;
import com.dijkspicy.easyfunction.util.PatternEscaper;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
public class FnToken implements Fn {
    @Override
    public Object calculate(Object param, FunctionContext context) {
        Parameters parameters = this.convert(param);
        String tokenSource = parameters.tokenSource();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(TOKEN_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new FunctionException("no such algorithm for token: " + TOKEN_ALGORITHM);
        }
        byte[] digest = md.digest(tokenSource.getBytes(StandardCharsets.UTF_8));
        return new BASE64Encoder().encode(digest);
    }

    private Parameters convert(Object param) {
        Collection<?> collection = OptionalCollection.ofSizable(param, 3).orElseThrow(() -> new FunctionException("invalid " + this.getFnName() + " param: " + param));
        Object[] array = collection.toArray();
        String var0 = OptionalString.ofNullable(array[0]).orElseThrow(() -> new FunctionException(this.getFnName() + "'s 1st arg can't be null"));
        String var1 = OptionalString.ofNullable(array[1]).orElseThrow(() -> new FunctionException(this.getFnName() + "'s 2nd arg can't be null"));
        Object var2 = array[2];
        if (var2 instanceof Number) {
            return new Parameters(var0, var1, ((Number) var2).intValue());
        } else {
            try {
                int index = Integer.parseUnsignedInt(String.valueOf(var2));
                return new Parameters(var0, var1, index);
            } catch (NumberFormatException e) {
                throw new FunctionException(this.getFnName() + "'s 3rd arg must be a number");
            }
        }
    }

    private String getFnName() {
        return TOKEN;
    }

    private class Parameters {
        final String stringWithTokens;
        final String stringOfTokenChars;
        final int substringIndex;

        private Parameters(String stringWithTokens, String stringOfTokenChars, int substringIndex) {
            this.stringWithTokens = stringWithTokens;
            this.stringOfTokenChars = stringOfTokenChars;
            this.substringIndex = substringIndex;
        }

        String tokenSource() {
            String[] splits = this.stringWithTokens.split(PatternEscaper.escape(this.stringOfTokenChars));
            if (this.substringIndex >= splits.length) {
                return this.stringWithTokens;
            }

            String start = splits[this.substringIndex];
            return this.stringWithTokens.substring(start.length() + this.stringOfTokenChars.length());
        }
    }
}
