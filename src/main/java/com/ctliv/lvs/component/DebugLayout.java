package com.ctliv.lvs.component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.ctliv.lvs.bus.UIBus;
import com.ctliv.lvs.spring.util.BeanUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.vaadin.server.VaadinPortletService;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DebugLayout extends VerticalLayout {

	private Log log = LogFactoryUtil.getLog(this.getClass());

//	@Autowired 
	private UIBus uiBus;
	
	public DebugLayout() {
		log.debug("Creating...");
		this.addComponent(new Label("Portlet: " + getPortletContextName()));
		this.addComponent(new Label("Mode: " + getPortletMode()));
		this.addComponent(new Label("Users registered: " + 
				getPortalCountOfRegisteredUsers().toString()));
		uiBus = BeanUtil.getBean(UIBus.class);
		this.addComponent(new Label("UIBus: " + uiBus.objToString()));
		uiBus.register(this);
		log.debug("Created...");
	}

	@PostConstruct
	private void postCostruct() {
		log.debug("Initialized");
	}
	
	@PreDestroy
	private void preDestroy() {
		uiBus.unregister(this);
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
