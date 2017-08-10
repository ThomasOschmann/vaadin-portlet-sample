package com.ctliv.vportlet.ui.base;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.ctliv.vportlet.bus.UIBusBean;
import com.ctliv.vportlet.bus.event.UIEvent;
import com.ctliv.vportlet.bus.event.UIEvent.UIEventMode;
import com.ctliv.vportlet.config.BeanUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public abstract class BaseUI extends UI {
	
	private static final long serialVersionUID = 1L;

    private Log log = LogFactoryUtil.getLog(this.getClass());
	
	protected UIBusBean uiBus;

	public BaseUI() {
		super();
		try {
			uiBus = BeanUtil.getBean(UIBusBean.class);
		} catch(Exception e) { }
		if (uiBus != null) uiBus.register(this); 
		log.info("Initialized UI(" + this.getUIId() + ") with uiBus: " + uiBus);
//		UIID uiid = new UIID(UICounter.next());
//		CurrentInstance.set(UIID.class, uiid);
//		log.info("Created UI (uiid=" + uiid + "): " + this);
		log.info("Created UI(" + this.getUIId() + ")");
	}
	
	@PreDestroy
	protected void preDestroy() {
		if (uiBus != null) uiBus.unregister(this);
		log.info("Destroyed UI(" + this.getUIId() + ")");
	}

	@Override
	protected void refresh(VaadinRequest request) {
		super.refresh(request);
		if (uiBus != null) uiBus.post(new UIEvent(this, UIEventMode.REFRESH));
		log.info("Refreshed UI(" + this.getUIId() + ")");
	}

	@Override
	public void attach() {
		super.attach();
		if (uiBus != null) uiBus.post(new UIEvent(this, UIEventMode.ATTACH));
		log.info("Attached UI(" + this.getUIId() + ")");
	}

	@Override
	public void detach() {
		super.detach();
		if (uiBus != null) uiBus.post(new UIEvent(this, UIEventMode.DETACH));
		log.info("Detached UI(" + this.getUIId() + ")");
	}

}
