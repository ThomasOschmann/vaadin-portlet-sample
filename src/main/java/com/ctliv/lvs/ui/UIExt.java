package com.ctliv.lvs.ui;

import com.ctliv.lvs.bus.UIBus;
import com.ctliv.lvs.bus.event.UIEvent;
import com.ctliv.lvs.bus.event.UIEvent.UIEventMode;
import com.ctliv.lvs.spring.util.BeanUtil;
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
		log.debug("Created UI (uiid=" + uiid + "): " + this);
		try {
			uiBus = BeanUtil.getBean(UIBus.class);
			uiBus.register(this);
			log.debug("Created UI bus: " + uiBus.objToString());
		} catch(Exception e) { }
	}
	
//	@PreDestroy
//	protected void preDestroy() {
//		if (uiBus != null) uiBus.unregister(this);
//		log.debug("Destroyed UI(" + this.getUIId() + ")");
//	}

	@Override
	protected void refresh(VaadinRequest request) {
		super.refresh(request);
		if (uiBus != null) uiBus.post(new UIEvent(this, UIEventMode.REFRESH));
		log.debug("Refreshed UI: " + this);
	}

	@Override
	public void attach() {
		super.attach();
		if (uiBus != null) uiBus.post(new UIEvent(this, UIEventMode.ATTACH));
		log.debug("Attached UI: " + this);
	}

	@Override
	public void detach() {
		super.detach();
		if (uiBus != null) uiBus.post(new UIEvent(this, UIEventMode.DETACH));
		log.debug("Detached UI: " + this);
	}

}
