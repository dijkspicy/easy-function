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
    Properties PROPERTIES = new Properties() {
        {
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("function.properties");
            try {
                PROPERTIES.load(stream);
            } catch (IOException e) {
                LoggerFactory.getLogger(FunctionKeywords.class).error("failed to load function.properties, error: " + e.getMessage(), e);
            }
        }
    };

    String SOURCE = "SOURCE";
    String TARGET = "TARGET";
    String SELF = "SELF";
    String HOST = "HOST";

    String NOTATION_PREFIX = "${";
    String NOTATION_SUFFIX = "}";

    // default function names
    String CONCAT = PROPERTIES.getProperty("CONCAT", "concat");
    String JOIN = PROPERTIES.getProperty("JOIN", "join");
    String TOKEN = PROPERTIES.getProperty("TOKEN", "token");
    String GET_INPUT = PROPERTIES.getProperty("GET_INPUT", "get_input");
    String GET_PROPERTY = PROPERTIES.getProperty("GET_PROPERTY", "get_property");
    String GET_ATTRIBUTE = PROPERTIES.getProperty("GET_ATTRIBUTE", "get_attribute");
    String GET_OPERATION_OUT = PROPERTIES.getProperty("GET_OPERATION_OUT", "get_operation_out");
    String GET_NODES_OF_TYPE = PROPERTIES.getProperty("GET_NODES_OF_TYPE", "get_nodes_of_type");
    String GET_ARTIFACT = PROPERTIES.getProperty("GET_ARTIFACT", "get_artifact");
}
