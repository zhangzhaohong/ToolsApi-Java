package com.koala.service.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 17:30
 * @description
 */
public class GsonUtil {
    /**
     * 不用创建对象,直接使用Gson.就可以调用方法
     */
    private static final Gson GSON;

    //判断gson对象是否存在了,不存在则创建对象
    static {
        //当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,显示形式是"key":null，而直接new出来的就没有"key":null的
        GSON = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    private GsonUtil() {
    }

    /**
     * 将对象转成json格式
     *
     * @return String
     */
    public static String toString(Object object) {
        String gsonString = null;
        if (GSON != null) {
            gsonString = GSON.toJson(object);
        }
        return gsonString;
    }

    /**
     * 将json转成特定的cls的对象
     */
    public static <T> T toBean(String gsonString, Class<T> cls) {
        T t = null;
        if (GSON != null) {
            //传入json对象和对象类型,将json转成对象
            t = GSON.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * json字符串转成list
     */
    public static <T> List<T> toList(String gsonString) {
        Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<List<T>>() {
        }.getType(), new MapTypeAdapter()).create();
        return gson.fromJson(gsonString, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * json字符串转成list中有map的
     */
    public static <T> List<Map<String, T>> toListMaps(String gsonString) {
        Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<List<Map<String, T>>>() {
        }.getType(), new MapTypeAdapter()).create();
        return gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
        }.getType());
    }

    /**
     * json字符串转成map的
     */
    public static <T> Map<String, T> toMaps(String gsonString) {
        Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<Map<String, T>>() {
        }.getType(), new MapTypeAdapter()).create();
        return gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
        }.getType());
    }

    public static class MapTypeAdapter extends TypeAdapter<Object> {

        @Override
        public Object read(JsonReader in) throws IOException {
            JsonToken token = in.peek();
            switch (token) {
                case BEGIN_ARRAY:
                    List<Object> list = new ArrayList<>();
                    in.beginArray();
                    while (in.hasNext()) {
                        list.add(read(in));
                    }
                    in.endArray();
                    return list;
                case BEGIN_OBJECT:
                    Map<String, Object> map = new LinkedTreeMap<>();
                    in.beginObject();
                    while (in.hasNext()) {
                        map.put(in.nextName(), read(in));
                    }
                    in.endObject();
                    return map;
                case STRING:
                    return in.nextString();
                case NUMBER:
                    /*
                      改写数字的处理逻辑，将数字值分为整型与浮点型。
                     */
                    double dbNum = in.nextDouble();
                    // 数字超过long的最大值，返回浮点类型
                    if (dbNum > Long.MAX_VALUE) {
                        return dbNum;
                    }
                    // 判断数字是否为整数值
                    long lngNum = (long) dbNum;
                    return dbNum;
                case BOOLEAN:
                    return in.nextBoolean();
                case NULL:
                    in.nextNull();
                    return null;
                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public void write(JsonWriter out, Object value) throws IOException {
            // 序列化无需实现
        }

    }
}
