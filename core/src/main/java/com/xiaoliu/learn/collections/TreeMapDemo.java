package com.xiaoliu.learn.collections;

import java.util.Map;
import java.util.TreeMap;

/**
 * @description: {@link TreeMap}测试
 * @author: liufb
 * @create: 2020/9/23 11:33
 **/
public class TreeMapDemo {
    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>();
        map.put("1", "1");
        System.out.println(map.get("1"));
    }
}
