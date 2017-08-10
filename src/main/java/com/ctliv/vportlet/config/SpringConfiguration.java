package com.ctliv.vportlet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ctliv.vportlet.bus.UIBusBean;

@Configuration
//@ComponentScan
public class SpringConfiguration {
	
	@Autowired private static ApplicationContext context;

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    @Bean
	public UIBusBean uiBusBean() {
		return new UIBusBean();
	}
	
	@Bean
	public BeanUtil beanUtil() {
		return new BeanUtil();
	}
	
}
