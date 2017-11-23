package com.github.ctliv.vportlet.export;

import javax.servlet.annotation.WebServlet;

import org.osgi.service.component.annotations.Component;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

@Component(service = VaadinServlet.class)
@WebServlet(urlPatterns = "/*", name = "VPortletUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = VPortletUI.class, productionMode = false)
@SuppressWarnings("serial")
public class VPortletServlet extends VaadinServlet {

}
