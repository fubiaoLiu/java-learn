package com.xiaoliu.learn.framework.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 使用访问者模式 - 一般跟组合模式搭配使用，动态给目标对象增加新功能
 * 将访问数据的逻辑封装在不用的访问者中，方便对数据的功能进行维护，特别是对层级结构的复杂数据。
 * @author: liufb
 * @create: 2020/5/18 9:53
 **/
public class VisitorPattern {
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

        Visitor removeVisitor = new RemoveVisitor();
        root.accept(removeVisitor);
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

        public void accept(Visitor visitor) {
            visitor.executor(this);
        }
    }

    public interface Visitor {
        void executor(Department department);
    }

    public static class RemoveVisitor implements Visitor {
        @Override
        public void executor(Department department) {
            if (!department.getChildren().isEmpty()) {
                for (Department child : department.getChildren()) {
                    child.accept(this);
                }
            }

            System.out.println("删除部门[" + department.getName() + "]");
        }
    }
}
