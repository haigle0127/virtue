package cn.haigle.around.config.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 上下文对象实例
 * 如果未加入Spring的bean注解，如@Component，可用getBean在方法中直接获取
 * @author haigle
 * @date 2018/11/28 10:02
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContextHolder.applicationContext == null){
            SpringContextHolder.applicationContext  = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     * @author haigle
     * @date 2018/11/28 10:05
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取Bean
     * @author haigle
     * @date 2018/11/28 10:05
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     * @author haigle
     * @date 2018/11/28 10:06
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name，以及Clazz返回指定的Bean
     * @author haigle
     * @date 2018/11/28 10:06
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}
