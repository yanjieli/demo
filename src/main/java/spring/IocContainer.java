package spring;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IocContainer {
    private Map<String, Object> beans = new ConcurrentHashMap<>();

    public Object getBean(String beanId) {
        return beans.get(beanId);
    }

    /**
     * 委托ioc 容器创建一个bean
     * 
     * @param clazz
     * @param beanId
     * @param paramsBeanIds
     */
    public void setBeans(Class<?> clazz, String beanId, String... paramsBeanIds) {

        Object[] paramvalues = new Object[paramsBeanIds.length];
        for (int i = 0; i < paramvalues.length; i++) {
            paramvalues[i] = beans.get(paramsBeanIds[i]);
        }
        Object bean = null;
        for (Constructor<?> constructor : clazz.getConstructors()) {
            try {
                bean = constructor.newInstance(paramvalues);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
            }
        }
        if (bean == null)
            throw new RuntimeException("Cannot find the suitable constructor method to create bean.");
        beans.put(beanId, bean);
    }

}
