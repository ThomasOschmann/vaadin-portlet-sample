package com.ctliv.vportlet.ui.base;

import com.ctliv.vportlet.bus.UIBus;
import com.ctliv.vportlet.bus.event.UIEvent;
import com.ctliv.vportlet.bus.event.UIEvent.UIEventMode;
import com.ctliv.vportlet.config.BeanUtil;
import com.jpaex.core.ui.UICounter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.internal.UIID;
import com.vaadin.ui.UI;
import com.vaadin.util.CurrentInstance;

public abstract class UIExt extends UI {
	
	private static final long serialVersionUID = 1L;

    private Log log = LogFactoryUtil.getLog(this.getClass());
	
	protected UIBus uiBus;

	public UIExt() {
		super();
		UIID uiid = new UIID(UICounter.next());
		CurrentInstance.set(UIID.class, uiid);
		log.info("Created UI (uiid=" + uiid + "): " + this);
		try {
			uiBus = BeanUtil.getBean(UIBus.class);
			uiBus.register(this);
		} catch(Exception e) { }
		log.info("Created bus: " + uiBus == null ? "null" : uiBus.objToString());
	}
	
//	@PreDestroy
//	protected void preDestroy() {
//		if (uiBus != null) uiBus.unregister(this);
//		log.info("Destroyed UI(" + this.getUIId() + ")");
//	}

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
