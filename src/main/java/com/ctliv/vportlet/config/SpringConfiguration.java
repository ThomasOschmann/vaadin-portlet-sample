package com.ctliv.vportlet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ctliv.vportlet.bus.UIBusBean;

@Configuration
//@ComponentScan
public class SpringConfiguration {
	
	@Bean
	public UIBusBean uiBusBean() {
		return new UIBusBean();
	}
	
	
	

}
