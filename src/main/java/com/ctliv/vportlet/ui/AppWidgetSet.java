package com.ctliv.vportlet.ui;

import org.osgi.service.component.annotations.Component;

import com.vaadin.osgi.resources.OsgiVaadinWidgetset;

@Component(immediate = true, service = OsgiVaadinWidgetset.class)
public class AppWidgetSet implements OsgiVaadinWidgetset {

	public static final String NAME = "com.ctliv.vportlet.ui.AppWidgetSet";

	@Override
    public String getName() {
        return NAME;
    }

}
