package com.dijkspicy.easyfunction.fn;

import java.util.*;
import java.util.function.Supplier;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/11
 */
public class FunctionContext {
    private final Map<String, Map<String, Object>> fnGetOperationOutput = new Hashtable<>();
    private final Map<String, Supplier<Object>> fnArtifacts = new Hashtable<>();
    private final Map<String, Set<Object>> fnNodesOfType = new Hashtable<>();
    private final Map<String, Supplier<Map<String, Object>>> fnProperties = new Hashtable<>();
    private final Map<String, Supplier<Map<String, Object>>> fnAttributes = new Hashtable<>();
    private Supplier<Map<String, Object>> fnInputs = Collections::emptyMap;

    public final FunctionContext addArtifact(String entityName, String interfaceName, String operationName, Map<String, Object> output) {
        String key = FnGetOperationOutput.getOperationKey(entityName, interfaceName, operationName);
        this.fnGetOperationOutput.put(key, output);
        return this;
    }

    public final FunctionContext addArtifact(String entityName, String artifactName, Supplier<Object> artifactSupplier) {
        String key = FnGetArtifact.getArtifactKey(entityName, artifactName);
        this.fnArtifacts.put(key, artifactSupplier);
        return this;
    }

    public final FunctionContext addNodes(String type, Collection<Object> nodes) {
        this.fnNodesOfType.computeIfAbsent(type, s -> new HashSet<>()).addAll(nodes);
        return this;
    }

    public final FunctionContext addNode(String type, Object node) {
        this.fnNodesOfType.computeIfAbsent(type, s -> new HashSet<>()).add(node);
        return this;
    }

    public final FunctionContext addAttribute(String entityName, Supplier<Map<String, Object>> supplier) {
        this.fnAttributes.put(entityName, supplier);
        return this;
    }

    public final FunctionContext addProperty(String entityName, Supplier<Map<String, Object>> supplier) {
        this.fnProperties.put(entityName, supplier);
        return this;
    }

    public final FunctionContext addInputs(Map<String, Object> inputs) {
        this.fnInputs = () -> inputs;
        return this;
    }

    public Map<String, Map<String, Object>> getFnGetOperationOutput() {
        return fnGetOperationOutput;
    }

    public Map<String, Supplier<Object>> getFnArtifacts() {
        return fnArtifacts;
    }

    public Map<String, Set<Object>> getFnNodesOfType() {
        return fnNodesOfType;
    }

    public Map<String, Supplier<Map<String, Object>>> getFnProperties() {
        return fnProperties;
    }

    public Map<String, Supplier<Map<String, Object>>> getFnAttributes() {
        return fnAttributes;
    }

    public Supplier<Map<String, Object>> getFnInputs() {
        return fnInputs;
    }

    public FunctionContext setFnInputs(Supplier<Map<String, Object>> fnInputs) {
        this.fnInputs = fnInputs;
        return this;
    }
}
