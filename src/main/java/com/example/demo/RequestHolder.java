package com.example.demo;

public class RequestHolder {
    private static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    private static void add(Long id) {
        requestHolder.set(id);
    }

    public static long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
