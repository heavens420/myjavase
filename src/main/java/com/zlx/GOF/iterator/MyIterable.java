package com.zlx.GOF.iterator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 实现可迭代接口
 */
public class MyIterable implements Iterable<Student> {

    List<Student> students = new ArrayList<>();

    int currentIndex = 0;

    /**
     * 模拟迭代器中的数据
     */
    public MyIterable() {
        students.add(new Student("1", "zhangsan"));
        students.add(new Student("2", "lisi"));
        students.add(new Student("3", "wangwu"));
    }

    public boolean remove(Student student) {
        return students.remove(student);
    }

    public boolean add(Student student) {
        return students.add(student);
    }

    @NotNull
    @Override
    public Iterator<Student> iterator() {
        return new MyIterator();
    }

    /**
     * 实现迭代器接口
     */
    private class MyIterator implements Iterator<Student> {
        @Override
        public boolean hasNext() {
            return currentIndex < students.size();
        }

        @Override
        public Student next() {
            Student student = students.get(currentIndex);
            currentIndex++;
            return student;
        }
    }
}


