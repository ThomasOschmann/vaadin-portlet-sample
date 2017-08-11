package com.ctliv.vportlet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ctliv.vportlet.bus.UIBus;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.UIScope;

@Configuration
@EnableVaadin
//@ComponentScan
public class SpringConfiguration {

	@Bean
	@UIScope
	public UIBus uiBus() {
		return new UIBus();	
	}
	
	@Bean 
	public BeanUtil beanUtil() {
		return new BeanUtil();
	}
	
//	@Bean
//	public VPortletUI vPortletUI() {
//		return new VPortletUI();
//	}
	
}
