package com.hhr.lazyread.util;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: Harry
 * @Date: 2019-12-29 17:00
 * @Version 1.0
 */

@Component
public class JsonXMLUtils {

    public String obj2json(Object obj) throws Exception {
        return JSON.toJSONString(obj);
    }

    public <T> T json2obj(String jsonStr, Class<T> clazz) throws Exception {
        return JSON.parseObject(jsonStr, clazz);
    }

    public <T> Map<String, Object> json2map(String jsonStr) throws Exception {
        return JSON.parseObject(jsonStr, Map.class);
    }

    public <T> T map2obj(Map<?, ?> map, Class<T> clazz) throws Exception {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    public <T> T map2obj(Object o, Class<T> clazz) throws Exception {
        return map2obj((Map<String,Object>)o,clazz);
    }
}