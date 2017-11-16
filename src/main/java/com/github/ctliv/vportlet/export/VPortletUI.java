package com.github.ctliv.vportlet.export;

import org.osgi.service.component.annotations.Component;

import com.github.ctliv.vportlet.component.DebugLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Widgetset(AppWidgetSet.NAME)
@Theme(AppTheme.NAME)
@Component(immediate = false, service = UI.class, property = {
        "com.liferay.portlet.display-category=" + VPortletUI.PORTLET_CATEGORY,
        "javax.portlet.name=" + VPortletUI.PORTLET_NAME,
        "javax.portlet.display-name=" + VPortletUI.PORTLET_DESC,
        "javax.portlet.portlet-mode=text/html;" + VPortletUI.PORTLET_MODES,
        "javax.portlet.security-role-ref=power-user,user",
        "com.vaadin.osgi.liferay.portlet-ui=true"})
@SuppressWarnings("serial")
public class VPortletUI extends UI {

	public static final String PORTLET_CATEGORY = "Vaadin samples"; //Use "category.sample" for predefined Liferay samples category
	public static final String PORTLET_NAME = "com.github.ctliv.vportlet.VPortlet";
	public static final String PORTLET_DESC = "Sample Vaadin Portlet";
	public static final String PORTLET_MODES = "view";
	
//    private Log log = LogFactoryUtil.getLog(this.getClass());

    @Override
    protected void init(VaadinRequest request) { 
    	
    	this.setContent(new DebugLayout());
    }

}
