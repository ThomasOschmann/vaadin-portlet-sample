package com.github.ctliv.vportlet.util;

import java.util.HashMap;

import javax.portlet.PortletMode;

import com.vaadin.server.VaadinPortletRequest;
import com.vaadin.server.VaadinPortletService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class MultimodeUIExt extends UIExt {

	private HashMap<PortletMode,AbstractComponent> map = new HashMap<>();
	
	private VerticalLayout layout = new VerticalLayout();
	
	public MultimodeUIExt() {
		layout.setMargin(false);
	}

	@Override
	protected void refresh(VaadinRequest request) {
		if (request instanceof VaadinPortletRequest) {
			selectMode(((VaadinPortletRequest) request).getPortletMode());	
		}
		super.refresh(request);
	}	
	
	private void selectMode(PortletMode mode) {
		if (mode == null) throw new IllegalArgumentException("Mode cannot be null");
		AbstractComponent component = map.get(mode);
		layout.removeAllComponents();
		if (component != null) layout.addComponent(component);
		this.setContent(layout);
	}
	
	public AbstractComponent getComponent(PortletMode mode) {
		if (mode == null) throw new IllegalArgumentException("Mode cannot be null");
		return map.get(mode);
	}
	
	public void setComponent(PortletMode mode, AbstractComponent component) {
		if (mode == null) throw new IllegalArgumentException("Mode cannot be null");
		map.put(mode, component);
		try {
			//Immediately shows component if mode is current mode
			selectMode(VaadinPortletService.getCurrentPortletRequest().getPortletMode());
		} catch (Exception e) {	}
	}

}
