package com.ctliv.vportlet.ui;

import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import com.ctliv.vportlet.ui.base.MultimodeUIExt;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedPortletSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Widgetset(AppWidgetSet.NAME)
@Theme(AppTheme.NAME)
@Component(immediate = false, service = UI.class, property = {
        "com.liferay.portlet.display-category=category.sample",
        "javax.portlet.name=VPortlet",
        "javax.portlet.portlet-mode=text/html;view;edit",
        "javax.portlet.display-name=My Vaadin portlet",
        "javax.portlet.security-role-ref=power-user,user",
        "com.vaadin.osgi.liferay.portlet-ui=true"},
		scope = ServiceScope.PROTOTYPE)
public class VPortletUI extends MultimodeUIExt {

    private Log log = LogFactoryUtil.getLog(this.getClass());

    @Override
    protected void init(VaadinRequest request) {
//    	final EventBus bus = new EventBus();
    	
        final String portletContextName = getPortletContextName(request);
        final Integer numOfRegisteredUsers = getPortalCountOfRegisteredUsers();
        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
//        setContent(layout);
        this.setComponent(PortletMode.VIEW, layout);

        final Button button = new Button("Click Me");
        button.addClickListener(event -> {
                layout.addComponents(new Label(
                        "Hello, World!<br>This is portlet "
                                + portletContextName
                                + ".<br>This portal has "
                                + numOfRegisteredUsers
                                + " registered users (according to the data returned by Liferay API call).",
                        ContentMode.HTML),
                        new Label("My Bus is: " + (uiBus == null ? "null" : uiBus.getNum())));
            }
        );
        layout.addComponent(button);
    }

    private String getPortletContextName(VaadinRequest request) {
        WrappedPortletSession wrappedPortletSession = (WrappedPortletSession) request
                .getWrappedSession();
        PortletSession portletSession = wrappedPortletSession
                .getPortletSession();

        final PortletContext context = portletSession.getPortletContext();
        final String portletContextName = context.getPortletContextName();
        return portletContextName;
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
