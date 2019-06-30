package algorithms.swap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

    public static void main(String[] args) {
        // method1();
        method2();
        // method3();

    }

    private static void method3() {
        int a = 3, b = 2;
        log.info("a={},b={}", a, b);
        int temp = a;
        a = b;
        b = temp;
        log.info("a={},b={}", a, b);

    }

    private static void method2() {
        int a = 3, b = 2;
        log.info("a={},b={}", a, b);
        a = a + b;
        b = a - b;
        a = a - b;
        log.info("a={},b={}", a, b);

    }

    private static void method1() {
        int a = 3, b = 2;
        log.info("a={},b={}", a, b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        log.info("a={},b={}", a, b);

    }

}
