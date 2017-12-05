package com.dijkspicy.easyfunction;

import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * easy-function
 *
 * @Author dijkspicy
 * @Date 2017/11/11
 */
public interface FunctionKeywords {
    Properties FN_KEYWORDS = new Properties() {
        {
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("function-keywords.properties");
            try {
                FN_KEYWORDS.load(stream);
            } catch (IOException e) {
                LoggerFactory.getLogger("function-keywords.properties").error("failed to load function-keywords.properties, error: " + e.getMessage(), e);
            }
        }
    };

    String SOURCE = "SOURCE";
    String TARGET = "TARGET";
    String SELF = "SELF";
    String HOST = "HOST";

    // config
    String TOKEN_ALGORITHM = FN_KEYWORDS.getProperty("TOKEN_ALGORITHM", "MD5");
    String NOTATION_PREFIX = FN_KEYWORDS.getProperty("NOTATION_PREFIX", "${");
    String NOTATION_SUFFIX = FN_KEYWORDS.getProperty("NOTATION_SUFFIX", "}");

    // default function names
    String CONCAT = FN_KEYWORDS.getProperty("CONCAT", "concat");
    String JOIN = FN_KEYWORDS.getProperty("JOIN", "join");
    String TOKEN = FN_KEYWORDS.getProperty("TOKEN", "token");
    String GET_INPUT = FN_KEYWORDS.getProperty("GET_INPUT", "get_input");
    String GET_PROPERTY = FN_KEYWORDS.getProperty("GET_PROPERTY", "get_property");
    String GET_ATTRIBUTE = FN_KEYWORDS.getProperty("GET_ATTRIBUTE", "get_attribute");
    String GET_OPERATION_OUTPUT = FN_KEYWORDS.getProperty("GET_OPERATION_OUTPUT", "get_operation_output");
    String GET_NODES_OF_TYPE = FN_KEYWORDS.getProperty("GET_NODES_OF_TYPE", "get_nodes_of_type");
    String GET_ARTIFACT = FN_KEYWORDS.getProperty("GET_ARTIFACT", "get_artifact");
    // extend
    String CONSTRAINT = FN_KEYWORDS.getProperty("CONSTRAINT", "constraint");
}
