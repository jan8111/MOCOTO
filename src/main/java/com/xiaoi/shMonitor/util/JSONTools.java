package com.xiaoi.shMonitor.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;

public class JSONTools {

    private static final Logger LOG = LoggerFactory.getLogger(JSONTools.class);
    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.WRITE_NULL_MAP_VALUES);
        mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public static void setDateFormat(DateFormat dateFormat) {
        mapper.setDateFormat(dateFormat);
    }

    /**
     * 把Java对象转为JSON字符串
     *
     * @param obj the object need to transfer into json string.
     * @return json string.
     */
    public static String toJson(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }

    /**
     * 把JSON转换为Java对象
     *
     * @param json  the json string
     * @param clazz will convert into class
     * @param <T>   return type
     * @return java object.
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws Exception {
        return mapper.readValue(json, clazz);
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param jsonStr         json字符串
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类型
     */
    public static <T> T fromJson(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = mapper.getTypeFactory().constructParametrizedType(collectionClass, collectionClass, elementClasses);
        try {
            return mapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new RuntimeException("convert json error:" + e.getLocalizedMessage());
        }
    }

    /**
     * 把Object对象转换为Class类型的对象
     *
     * @param o
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T convert(Object o, Class<T> tClass) {
        return mapper.convertValue(o, tClass);
    }

}
