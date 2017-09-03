package com.ctliv.vportlet.util;

import java.util.HashMap;

import javax.portlet.PortletMode;

import com.vaadin.server.VaadinPortletRequest;
import com.vaadin.server.VaadinPortletService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class MultimodeUIExt extends UIExt {

	private static final PortletMode DEFAULT_MODE = PortletMode.VIEW;
	
	private HashMap<PortletMode,AbstractComponent> map = new HashMap<>();
	
	private VerticalLayout layout = new VerticalLayout();
	private AbstractComponent header = null;
	private AbstractComponent footer = null;
	
//	@Override
//	protected void init(VaadinRequest request) {
//		selectMode(WebUtil.getPortletMode());
//	}
	
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
	
	public AbstractComponent getHeader() {
		return header;
	}
	
	public void setHeader(AbstractComponent header) {
		this.header = header;
	}
	
	public AbstractComponent getFooter() {
		return footer;
	}
	
	public void setFooter(AbstractComponent footer) {
		this.footer = footer;
	}
	
	private void selectMode(PortletMode mode) {
		AbstractComponent component = map.get(mode);
		if (component == null) component = map.get(DEFAULT_MODE);
		layout.removeAllComponents();
		if (header != null) layout.addComponent(header);
		if (component != null) layout.addComponent(component);
		if (footer != null) layout.addComponent(footer);
		this.setContent(layout);
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
