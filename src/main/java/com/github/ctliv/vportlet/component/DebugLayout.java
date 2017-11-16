package com.github.ctliv.vportlet.component;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinPortletService;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DebugLayout extends VerticalLayout {

	private Log log = LogFactoryUtil.getLog(this.getClass());

	public DebugLayout() {
		
		Button btn = new Button();
		btn.addClickListener(e -> Notification.show("Button clicked!", Notification.Type.TRAY_NOTIFICATION));
		btn.setIcon(VaadinIcons.SEARCH);
		this.addComponent(btn);
		
		ComboBox<String> combo = new ComboBox<>("Prova");
		combo.setItems("One","Two","Three");
		combo.addValueChangeListener(e -> Notification.show("Combo selection changed!", Notification.Type.TRAY_NOTIFICATION));
		this.addComponent(combo);
		
		this.addComponent(new Label("Portlet: " + getPortletContextName()));
		
		this.addComponent(new Label("Mode: " + getPortletMode()));
		
		this.addComponent(new Label("Users registered: " + 
				getPortalCountOfRegisteredUsers().toString()));
		
		log.debug("Created portlet...");
	}

	private String getPortletMode() {
		try {
			return VaadinPortletService
					.getCurrentPortletRequest()
					.getPortletMode()
					.toString();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	private String getPortletContextName() {
		try {
			return VaadinPortletService
					.getCurrentPortletRequest()
					.getPortletSession()
					.getPortletContext()
					.getPortletContextName();
		} catch(Exception e) {
			return e.getMessage();
		}
	}

	private Integer getPortalCountOfRegisteredUsers() {
		Integer result = null;

		try {
			result = UserLocalServiceUtil.getService().getUsersCount();
		} catch (SystemException e) {
			log.debug(e);
		}

		return result;
	}
}
