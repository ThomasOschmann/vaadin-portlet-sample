package com.github.ctliv.vportlet.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public abstract class UIExt extends UI {
	
    private Log log = LogFactoryUtil.getLog(this.getClass());
	
	public UIExt() {
		super();
		log.debug(this + " created");
	}
	
	@Override
	protected void refresh(VaadinRequest request) {
		super.refresh(request);
		log.debug(this + " refreshed");
	}

	@Override
	public void attach() {
		super.attach();
		log.debug(this + " attached");
	}

	@Override
	public void detach() {
		super.detach();
		log.debug(this + " detached");
	}

}
