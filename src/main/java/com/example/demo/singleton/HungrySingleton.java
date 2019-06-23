package com.example.demo.singleton;

public class HungrySingleton {

    private HungrySingleton() {
    }

    private static HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        HungrySingleton hs1 = HungrySingleton.getInstance();
        HungrySingleton hs2 = HungrySingleton.getInstance();
        System.out.println(hs1 == hs2);
    }

}
