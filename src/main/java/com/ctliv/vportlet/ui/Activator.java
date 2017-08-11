package com.ctliv.vportlet.ui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ctliv.vportlet.config.VPortletConfiguration;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class Activator implements BundleActivator {

	private Log log = LogFactoryUtil.getLog(this.getClass());
	
	private ApplicationContext springContext = null;
	
	@Override
	public void start(BundleContext context) throws Exception {
		springContext = new AnnotationConfigApplicationContext(VPortletConfiguration.class);
		log.info("Found " + springContext.getBeanDefinitionCount() + " beans");
		for (String name : springContext.getBeanDefinitionNames()) {
			log.info("  Bean: " + name);	
		}
		log.info("Started: " + springContext);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		((ConfigurableApplicationContext)springContext).close();
		log.info("Stopped: " + springContext);
	}

}
