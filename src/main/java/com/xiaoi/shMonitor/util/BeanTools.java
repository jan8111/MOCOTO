package com.xiaoi.shMonitor.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zy on 2017/6/20.
 */
@Configuration
public class BeanTools implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static Object getBean(Class classname) {
        return applicationContext.getBean(classname);
    }
}
