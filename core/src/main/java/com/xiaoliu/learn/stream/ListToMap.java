package com.xiaoliu.learn.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @description: 
 * @author: FuBiaoLiu
 * @date: 2019/9/25
 */
public class ListToMap {
    public static void main(String[] args) {
        // 创建list
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.setId(i);
            p.setName("p" + i);
            p.setScore(i * 10);
            personList.add(p);
        }

        // 添加和id=8相同对象score值不同
        Person p = new Person();
        p.setId(8);
        p.setName("p" + 8);
        p.setScore(88);
        personList.add(p);

        System.out.println("list：========================");
        personList.forEach(System.out::println);

        // 转换为HashMap
        Map<Integer, Person> map = personList.stream().collect(Collectors.toMap(Person::getId, d -> d, (oldValue, newValue) -> newValue));
        System.out.println("hashMap：========================");
        map.entrySet().forEach(System.out::println);

        // 转换为TreeMap
        Map<Integer, Person> treeMap = personList.stream().collect(Collectors.toMap(Person::getId, d -> d, (oldValue, newValue) -> newValue, TreeMap::new));
        System.out.println("treeMap：========================");
        treeMap.entrySet().forEach(System.out::println);
    }
}
