package com.xiaoliu.learn.service;

import com.xiaoliu.learn.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Scope("prototype")
public class TestService {
    @Autowired
    private TestDao dao;

    public void test1() {
        System.out.println("TestService#test1");
        dao.test();
    }

    public void test2(String str) {
        System.out.println("TestService#test2(" + str + ")");
    }
}
