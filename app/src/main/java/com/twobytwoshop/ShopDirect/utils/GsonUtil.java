package com.twobytwoshop.ShopDirect.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GsonUtil {
    private static Gson gson = null;

    static {
        if (gson == null){
            gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
        }
    }

    private GsonUtil() {
    }

    public static String toString(Object object) {
        String gsonString = null;

        if (gson != null) {
            gsonString = gson.toJson(object);
        }

        return gsonString;
    }

    public static <T> T toBean(String json, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(json, cls);
        }
        return t;
    }

    public static <T> List<T> toList(String json, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(json, new TypeToken<List<T>>() {

            }.getType());
        }
        return list;
    }

    public static <T> List<T> toNoHeaderList(String json, Class<T> cls) {
        List<T> list = new ArrayList<>();

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();

        for (JsonElement element : jsonArray) {
            T bean = gson.fromJson(element, cls);
            list.add(bean);
        }
        return list;
    }
}
