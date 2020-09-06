package com.zlx.javase;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 用于测试 接口和 抽象类是否可以 new
 *
 * 测试证明：通过匿名内部类可以new 接口和抽象类
 */
public class NewClass {
    public static void main(String[] args) {
        //new 抽象类
        AbstractClass abstractClass = new AbstractClass() {
            @Override
            void sub() {
                return;
            }
        };

        //new 接口
        InterfaceClass interfaceClass = new InterfaceClass() {
            @Override
            public void muti() {
                return;
            }
        };

        //new 接口
        List list = new List<Integer>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Integer> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Integer integer) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Integer> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Integer> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public Integer get(int index) {
                return null;
            }

            @Override
            public Integer set(int index, Integer element) {
                return null;
            }

            @Override
            public void add(int index, Integer element) {

            }

            @Override
            public Integer remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Integer> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Integer> listIterator(int index) {
                return null;
            }

            @Override
            public List<Integer> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }
}

//创建抽象类
abstract class AbstractClass{
    int sum(){
        return 0;
    }
    abstract void sub();
}

interface InterfaceClass{
    void muti();
}