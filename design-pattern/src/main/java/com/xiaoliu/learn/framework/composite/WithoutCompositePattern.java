package com.xiaoliu.learn.framework.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 不用组合模式
 * 场景：要递归删除某个部门下的所有部门
 * @author: liufb
 * @create: 2020/5/17 20:53
 **/
public class WithoutCompositePattern {
    public static void main(String[] args) {
        Department root = new Department("顶级部门");
        Department sub1 = new Department("子部门1");
        Department sub2 = new Department("子部门2");
        Department leaf1 = new Department("叶子部门1");
        Department leaf2 = new Department("叶子部门2");
        Department leaf3 = new Department("叶子部门3");

        sub1.getChildren().add(leaf1);
        sub1.getChildren().add(leaf2);
        root.getChildren().add(sub1);

        sub2.getChildren().add(leaf3);
        root.getChildren().add(sub2);

        for (Department sub : root.getChildren()) {
            if (!sub.getChildren().isEmpty()) {
                for (Department leaf : sub.getChildren()) {
                    leaf.remove();
                }
            }
            sub.remove();
        }
        root.remove();
    }

    public static class Department {
        private String name;
        private List<Department> children = new ArrayList<>();

        public Department(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Department> getChildren() {
            return children;
        }

        public void setChildren(List<Department> children) {
            this.children = children;
        }

        public void remove() {
            System.out.println("删除部门[" + name + "]");
        }
    }
}
