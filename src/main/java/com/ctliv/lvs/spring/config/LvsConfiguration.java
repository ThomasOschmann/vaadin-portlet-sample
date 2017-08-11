package com.ctliv.lvs.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.ctliv.lvs.bus.UIBus;
import com.ctliv.lvs.component.DebugLayout;
import com.ctliv.lvs.spring.util.BeanUtil;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.UIScope;

@Configuration
@EnableVaadin
public class LvsConfiguration {
	
	@Bean 
	public BeanUtil beanUtil() {
		return new BeanUtil();
	}
	
	@Bean @UIScope
	public UIBus uiBus() {
		return new UIBus();	
	}
	
	@Bean @Scope("prototype")
	public DebugLayout debugLayout() {
		return new DebugLayout();
	}

}
