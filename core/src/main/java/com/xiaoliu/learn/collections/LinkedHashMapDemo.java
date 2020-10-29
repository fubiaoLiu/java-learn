package com.xiaoliu.learn.collections;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: {@link LinkedHashMap}测试
 * @author: liufb
 * @create: 2020/9/23 11:33
 **/
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("1", "1");
        System.out.println(map.get("1"));
    }
}
