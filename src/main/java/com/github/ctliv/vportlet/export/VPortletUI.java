package com.github.ctliv.vportlet.export;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;

@Widgetset("com.ctliv.AppWidgetSet")
@Theme("vportlet-theme")
@SuppressWarnings("serial")
public class VPortletUI extends UI {

	public static final String PORTLET_CATEGORY = "Vaadin samples"; //Use "category.sample" for predefined Liferay samples category
	public static final String PORTLET_NAME = "com.github.ctliv.vportlet.VPortlet";
	public static final String PORTLET_DESC = "Sample Vaadin Portlet";
	public static final String PORTLET_MODES = "view";
	
    @Override
    protected void init(VaadinRequest request) {
    	
		ComboBox<String> combo = new ComboBox<>("Test combo:");
		combo.setItems("One","Two","Three");
		setContent(combo);
		
    }
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = VPortletUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
