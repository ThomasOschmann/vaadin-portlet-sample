package com.github.ctliv.vportlet.export;

import org.osgi.service.component.annotations.Component;

import com.vaadin.osgi.resources.OsgiVaadinTheme;
import com.vaadin.ui.themes.ValoTheme;

@Component(immediate = true, service = OsgiVaadinTheme.class)
public class AppTheme extends ValoTheme implements OsgiVaadinTheme {
	
	//This maps to theme "styles.css" in folder "src/main/webapp/VAADIN/themes/vportlet-theme"
	//WARNING: This name cannot contain dots, otherwise error occurs.
	public static final String NAME = "vportlet-theme";
	
    @Override
    public String getName() {
        return NAME;
    }

}
