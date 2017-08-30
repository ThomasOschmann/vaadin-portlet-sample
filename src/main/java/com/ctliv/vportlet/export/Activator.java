package com.ctliv.vportlet.export;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class Activator implements BundleActivator {

	private Log log = LogFactoryUtil.getLog(this.getClass());
	
	@Override
	public void start(BundleContext context) throws Exception {
		log.info("Started: " + context);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		log.info("Stopped: " + context);
	}

}
