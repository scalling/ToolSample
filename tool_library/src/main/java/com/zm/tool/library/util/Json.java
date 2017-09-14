package com.zm.tool.library.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json {
    private static ObjectMapper mapper = new ObjectMapper();
    public static boolean configure = false;//是否检测


    public static JsonNode toJson(final Object data) {
        configure();
        return mapper.valueToTree(data);
    }

    public static JsonNode parse(String src) {
        configure();
        try {
            return mapper.readValue(src, JsonNode.class);
        } catch (IOException t) {
            throw new RuntimeException(t);
        }
    }

    public static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        configure();
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static <T> T fromJson(String jsonString, JavaType javaType) {
        configure();
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String jsonString, TypeReference<T> tTypeReference) {
        configure();
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T) mapper.readValue(jsonString, tTypeReference);
        } catch (IOException e) {
            Logger.e(e);
            throw new RuntimeException(e);
        }
    }

    public static <A> A fromJson(String jsonString, Class<A> aClazz) {
        configure();
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonString, aClazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <A> A fromJson(String jsonString, Class<A> aClazz, Class tclazz) {
        configure();
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {

            return mapper.readValue(jsonString, aClazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void configure(){
        if(!configure){
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

    }


}
