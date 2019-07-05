package spring;

import org.junit.Before;
import org.junit.Test;

public class SpringTest {
    private IocContainer ioccontainer = new IocContainer();

    @Before
    public void before() {
        ioccontainer.setBeans(AudiCar.class, "audi");
        ioccontainer.setBeans(BuilkCar.class, "buik");
        ioccontainer.setBeans(Zhangsan.class, "zhangsan", "audi");
        ioccontainer.setBeans(Lisi.class, "lisi", "buik");
    }

    @Test
    public void test() {
        Humen zhangsan = (Humen) ioccontainer.getBean("zhangsan");
        zhangsan.goHome();
        Humen lisi = (Humen) ioccontainer.getBean("lisi");
        lisi.goHome();

    }
}
