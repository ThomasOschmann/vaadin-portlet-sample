package com.github.ctliv.vportlet.export;

import org.osgi.service.component.annotations.Component;

import com.vaadin.osgi.resources.OsgiVaadinWidgetset;

@Component(immediate = true, service = OsgiVaadinWidgetset.class)
public class AppWidgetSet implements OsgiVaadinWidgetset {

	public static final String NAME = "com.ctliv.test.AppWidgetSet";

	@Override
    public String getName() {
        return NAME;
    }

}
