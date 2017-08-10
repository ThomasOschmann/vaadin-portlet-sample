package com.ctliv.vportlet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ctliv.vportlet.bus.UIBus;

@Configuration
//@ComponentScan
public class SpringConfiguration {

	@Bean
	public UIBus uiBusBean() {
		return new UIBus();
	}
	
	@Bean
	public BeanUtil beanUtil() {
		return new BeanUtil();
	}
	
}
