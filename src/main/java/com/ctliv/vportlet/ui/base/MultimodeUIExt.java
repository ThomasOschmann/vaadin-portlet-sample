package com.ctliv.vportlet.ui.base;

import java.util.HashMap;

import javax.portlet.PortletMode;

import com.vaadin.server.VaadinPortletRequest;
import com.vaadin.server.VaadinPortletService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.AbstractComponent;

public abstract class MultimodeUIExt extends UIExt {

	private static final long serialVersionUID = 1L;
	
	private static final PortletMode DEFAULT_MODE = PortletMode.VIEW;
	
	private HashMap<PortletMode,AbstractComponent> map = new HashMap<>();

//	@Override
//	protected void init(VaadinRequest request) {
//		selectMode(JpaExWebUtil.getPortletMode());
//	}

	@Override
	protected void refresh(VaadinRequest request) {
		if (request instanceof VaadinPortletRequest) {
			selectMode(((VaadinPortletRequest) request).getPortletMode());	
		}
		super.refresh(request);
	}	
	
	private void selectMode(PortletMode mode) {
		AbstractComponent component = map.get(mode);
		if (component == null) component = map.get(DEFAULT_MODE);
		this.setContent(component);
	}
	
	public AbstractComponent getComponent(PortletMode mode) {
		return map.get(mode);
	}
	
	public void setComponent(PortletMode mode, AbstractComponent component) {
		map.put(mode, component);
		try {
			//Select component based on current mode
			selectMode(VaadinPortletService.getCurrentPortletRequest().getPortletMode());
		} catch (Exception e) {	}
	}

}
