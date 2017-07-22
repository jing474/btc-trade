package com.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by caijing on 2017/7/20.
 */
public class SpringContexts implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext contex) throws BeansException {
        SpringContexts.context = contex;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 获取spring 管理的bean
     *
     * @param name
     * @param requiredType
     * @return
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return context.getBean(name, requiredType);
    }
    public static Object getBean( String beanName ) {
        return context.getBean(beanName);
    }

    public static <T> List<T> getBeans(Class<T> requireType) {
        if (context instanceof ListableBeanFactory) {
            ListableBeanFactory listFactory = (ListableBeanFactory) context;
            Map<String, T> beanMap = listFactory.getBeansOfType(requireType);
            Iterator<String> itor = beanMap.keySet().iterator();
            List<T> ret = new ArrayList<T>();
            while (itor.hasNext()) {
                ret.add(beanMap.get(itor.next()));
            }
            return ret;

        }
        return null;
    }

    public static <T> T getBeanByType(Class<T> clazz) throws BeansException {
        return context.getBean(clazz);
    }
}
