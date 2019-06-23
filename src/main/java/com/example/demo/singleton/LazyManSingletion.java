package com.example.demo.singleton;

public class LazyManSingletion {
    private LazyManSingletion() {

    }

    private static volatile LazyManSingletion instance;

    public static LazyManSingletion getInstance() {
        if (instance == null) {// 双重检测机制+volatile 实现线程安全懒汉式 单例
            synchronized (LazyManSingletion.class) {
                if (instance == null) {
                    instance = new LazyManSingletion();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        LazyManSingletion s1 = LazyManSingletion.getInstance();
        LazyManSingletion s2 = LazyManSingletion.getInstance();
        System.out.println(s1 == s2);

    }

}
