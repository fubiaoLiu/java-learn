package com.xiaoliu.learn.framework.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 使用迭代器模式
 * 优点：需要遍历的对象内部的数据结构变化不影响调用方，Java已经提供了迭代器，一般工作中这个不需要自己写。
 * @author: liufb
 * @create: 2020/5/16 11:40
 **/
public class IteratorPattern {
    public static void main(String[] args) {
        Classroom classroom = initClassroom();
        Iterator iterator = classroom.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
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

        public Iterator iterator() {
            return new ClassroomIterator(this);
        }
    }

    public interface Iterator {
        boolean hasNext();

        Student next();
    }

    public static class ClassroomIterator implements Iterator {
        private int index;
        private Classroom classroom;

        public ClassroomIterator(Classroom classroom) {
            this.classroom = classroom;
        }

        @Override
        public boolean hasNext() {
//            return index < classroom.students.length;
            return index < classroom.students.size();
        }

        @Override
        public Student next() {
//            return classroom.students[index++];
            return classroom.students.get(index++);
        }
    }
}
