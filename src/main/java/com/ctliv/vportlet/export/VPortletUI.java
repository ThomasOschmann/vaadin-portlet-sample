package com.ctliv.vportlet.export;

import javax.portlet.PortletMode;

import org.osgi.service.component.annotations.Component;

import com.ctliv.vportlet.component.DebugLayout;
import com.ctliv.vportlet.util.MultimodeUIExt;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Widgetset(AppWidgetSet.NAME)
@Theme(AppTheme.NAME)
@Component(immediate = false, service = UI.class, property = {
        "com.liferay.portlet.display-category=category.sample",
        "javax.portlet.name=com.ctliv.vportlet.VPortlet",
        "javax.portlet.portlet-mode=text/html;view,edit",
        "javax.portlet.display-name=Sample Vaadin Portlet",
        "javax.portlet.security-role-ref=power-user,user",
        "com.vaadin.osgi.liferay.portlet-ui=true"})
@SuppressWarnings("serial")
public class VPortletUI extends MultimodeUIExt {

    private Log log = LogFactoryUtil.getLog(this.getClass());

    @Override
    protected void init(VaadinRequest request) { 
    	
    	this.setComponent(PortletMode.VIEW, new DebugLayout());
    	this.setComponent(PortletMode.EDIT, new DebugLayout());
        log.debug(this + " initialized");
    }

}
