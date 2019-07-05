package anno;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnnotationTest {

    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("anno.Son");
            Description d = (Description) c.getAnnotation(Description.class);
            Method[] ms = c.getMethods();
            for (Method m : ms) {
                Description md = m.getAnnotation(Description.class);
                log.info("{}", md.test());
            }
            log.info("{}", d.test());
        } catch (ClassNotFoundException e) {
            log.error("{}", e.getMessage());
        }
    }

}
