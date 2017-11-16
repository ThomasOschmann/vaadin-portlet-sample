package com.github.ctliv.vportlet.export;

import org.osgi.service.component.annotations.Component;

import com.vaadin.osgi.resources.OsgiVaadinWidgetset;

@Component(immediate = true, service = OsgiVaadinWidgetset.class)
public class AppWidgetSet implements OsgiVaadinWidgetset {

	//This maps to file "src/main/resources/com/github/ctliv/vportlet/AppWidgetSet.gwt.xml";
	public static final String NAME = "com.github.AppWidgetSet";

	@Override
    public String getName() {
        return NAME;
    }

}
