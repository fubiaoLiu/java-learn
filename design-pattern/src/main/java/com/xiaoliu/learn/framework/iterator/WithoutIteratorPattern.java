package com.xiaoliu.learn.framework.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 不用迭代器模式
 * @author: liufb
 * @create: 2020/5/16 11:24
 **/
public class WithoutIteratorPattern {
    public static void main(String[] args) {
        Classroom classroom = initClassroom();

        // 第一个版本，Classroom内部用数组存储学生信息
        /*Student[] students = classroom.getStudents();
        for (Student student : students) {
            System.out.println(student);
        }*/

        // 第二个版本，因为需求变更需要使用集合存储学生信息，调用方需要跟着变更，
        // 假设系统有100个地方有同样的调用操作，那么更改会很麻烦
        List<Student> students = classroom.getStudents();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static Classroom initClassroom() {
        /*Student[] students = new Student[2];
        Student student1 = new Student();
        student1.setName("小A");
        students[0] = student1;
        Student student2 = new Student();
        student2.setName("小B");
        students[1] = student2;*/

        List<Student> students = new ArrayList<>(2);
        Student student1 = new Student();
        student1.setName("小A");
        students.add(student1);
        Student student2 = new Student();
        student2.setName("小B");
        students.add(student2);

        Classroom classroom = new Classroom();
        classroom.setStudents(students);
        return classroom;
    }

    public static class Student {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class Classroom {
        // 第一个版本，使用数组存储学生信息
        /*private Student[] students;

        public Student[] getStudents() {
            return students;
        }

        public void setStudents(Student[] students) {
            this.students = students;
        }*/

        // 第二个版本，使用集合存储学生信息
        private List<Student> students;

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }
    }
}
