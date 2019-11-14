package com.sayee.sxsy.common.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sayee.sxsy.common.mapper.JsonMapper;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class JsonUtil {

    static JsonMapper defaultMapper = new JsonMapper();

    public static String toJson(Object obj)
    {
        return defaultMapper.toJson(obj);
    }

    public static String toJson(Object obj, boolean fillterSlash)
    {
        if (fillterSlash) {
            return fillterSlash(defaultMapper.toJson(obj));
        }
        return defaultMapper.toJson(obj);
    }




    private static String fillterSlash(String orgi) {
        if (StringUtils.isBlank(orgi)) {
            return orgi;
        }
        StringBuffer strb = new StringBuffer();
        for (int i = 0; i < orgi.length(); i++) {
            System.out.println(orgi.charAt(i));
            if (orgi.charAt(i) == '\\')
                strb.append(new char[] { '\\', '\\' });
            else {
                strb.append(orgi.charAt(i));
            }
        }
        return strb.toString();
    }

//    public static <T> T toBean(String json, Class<T> clz)
//    {
//        return defaultMapper.toBean(json, clz);
//    }


    public static <T> List<T> toList(String json, Class<T> clz)
    {
        @SuppressWarnings("unchecked")
        List<T> ts = (List<T>) JSONArray.parseArray(json, clz);
        return ts;
    }

    @Deprecated
    public static List jsonToStringList(String json)
    {
        return toList(json, String.class);
    }

    @Deprecated
    public static List jsonToMapList(String json)
    {
        return toList(json, HashMap.class);
    }


    public static String map2Json(Map map)
    {
        StringBuilder sb = new StringBuilder("{");
        Set keys = map.keySet();
        for (Iterator localIterator = keys.iterator(); localIterator.hasNext(); ) { Object k = localIterator.next();
            sb.append("\"");
            sb.append(k.toString());
            sb.append("\"");
            sb.append(":");

            Object v = map.get(k);
            if ((v instanceof Map)) {
                sb.append(map2Json((Map)v));
            } else if ((v instanceof Number)) {
                sb.append(v.toString());
            } else {
                sb.append("\"");
                sb.append(v.toString());
                sb.append("\"");
            }
            sb.append(",");
        }
        if (sb.length() > 1) {
            return sb.substring(0, sb.length() - 1) + "}";
        }
        return "}";
    }
}
