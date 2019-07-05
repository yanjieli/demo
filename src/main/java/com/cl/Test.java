package com.cl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

    public static void main(String[] args) {
        ClassLoader loader = Test.class.getClassLoader();
        while (loader != null) {
            log.info("{}", loader.toString());
            loader = loader.getParent();
        }

    }

}
