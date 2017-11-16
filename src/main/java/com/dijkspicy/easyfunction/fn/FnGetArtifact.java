package com.dijkspicy.easyfunction.fn;

import com.dijkspicy.easyfunction.Fn;
import com.dijkspicy.easyfunction.FunctionException;
import com.dijkspicy.easyfunction.util.OptionalCollection;
import com.dijkspicy.easyfunction.util.OptionalString;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/13
 */
public class FnGetArtifact implements Fn {
    private static final String LOCAL_FILE = "LOCAL_FILE";

    static String getArtifactKey(String entityName, String artifactName) {
        return entityName + "." + artifactName;
    }

    @Override
    public Object calculate(Object param, FunctionContext context) throws FunctionException {
        Parameters parameters = this.convert(param);
        String key = getArtifactKey(parameters.entityName, parameters.artifactName);
        if (LOCAL_FILE.equals(parameters.location) || parameters.remove) {
            return context.getFnArtifacts().remove(key);
        }
        return context.getFnArtifacts().get(key);
    }

    private Parameters convert(Object param) {
        Object[] array = OptionalCollection.ofSizable(param, 2)
                .orElseThrow(() -> new FunctionException("invalid param for " + this.getFnName()))
                .toArray();
        String entityName = OptionalString.ofNullable(array[0]).orElseThrow(() -> new FunctionException(this.getFnName() + "'s 1st arg can't be null: " + param));
        String artifactName = OptionalString.ofNullable(array[1]).orElseThrow(() -> new FunctionException(this.getFnName() + "'s 2nd arg can't be null: " + param));
        if (array.length > 2) {
            String location = OptionalString.ofNullable(array[2]).orElse(null);
            if (array.length > 3) {
                boolean remove = Boolean.parseBoolean(String.valueOf(array[3]));
                return new Parameters(entityName, artifactName, location, remove);
            }
            return new Parameters(entityName, artifactName, location);
        }
        return new Parameters(entityName, artifactName);
    }

    private String getFnName() {
        return GET_ARTIFACT;
    }

    private class Parameters {
        final String entityName;
        final String artifactName;
        String location;
        boolean remove;

        private Parameters(String entityName, String artifactName) {
            this.entityName = entityName;
            this.artifactName = artifactName;
        }

        private Parameters(String entityName, String artifactName, String location) {
            this(entityName, artifactName);
            this.location = location;
        }

        private Parameters(String entityName, String artifactName, String location, boolean remove) {
            this(entityName, artifactName, location);
            this.remove = remove;
        }
    }
}
