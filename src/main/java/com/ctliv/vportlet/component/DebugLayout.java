package com.ctliv.vportlet.component;

import javax.annotation.PostConstruct;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinPortletService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DebugLayout extends VerticalLayout {

	private Log log = LogFactoryUtil.getLog(this.getClass());
	
	private Button btn = new Button();

	public DebugLayout() {
		log.debug("Creating...");
		btn.setIcon(VaadinIcons.SEARCH);
		this.addComponent(btn);
		this.addComponent(new Label("Portlet: " + getPortletContextName()));
		this.addComponent(new Label("Mode: " + getPortletMode()));
		this.addComponent(new Label("Users registered: " + 
				getPortalCountOfRegisteredUsers().toString()));
		log.debug("Created...");
	}

	@PostConstruct
	private void postCostruct() {
		log.debug("Initialized");
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
			log.error(e);
		}

		return result;
	}
}
