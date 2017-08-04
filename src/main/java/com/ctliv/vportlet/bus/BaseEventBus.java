package com.ctliv.vportlet.bus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ctliv.vportlet.ui.VPortletUI;
import com.google.common.eventbus.EventBus;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class BaseEventBus extends EventBus implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Log log = LogFactoryUtil.getLog(VPortletUI.class);
	
	private List<Object> subscribers = new ArrayList<Object>();
	
	public synchronized int getSize() {
		return subscribers.size();
	}

	@Override
	public synchronized void register(Object object) {
		if (object == null) {
			log.info("Received null object reference!");
			throw new IllegalArgumentException("Invalid registration for object (null)");
		}
		//Stores object in subscribers' list to allow cleanup
		if (!subscribers.contains(object)) {
			subscribers.add(object);
			//Registers object once in wrapped EventBus
			super.register(object);
			log.info("Bus [" + this + "] registered: " + object + " (size is " + Integer.toString(subscribers.size()) + ")");
		}
	}

	@Override
	public synchronized void unregister(Object object) {
		if (object == null) {
			log.info("Received null object reference!");
			throw new IllegalArgumentException("Invalid deregistration for object (null)");
		}
		//Removes object in subscribers' list
		subscribers.remove(object);
		//Unregisters object from wrapped EventBus
		super.unregister(object);
		log.info("Bus [" + this + "] unregistered: " + object + " (size is " + Integer.toString(subscribers.size()) + ")");
	}

	public synchronized void cleanup() {
		//Remove all subscribers from underlying EventBus
		for (Object object : subscribers) {
			super.unregister(object);
		}
		//Clears local subscribers' list
		subscribers.clear();
//		log.info("Cleared subscribers");
	}

	@Override
	public void post(Object event) {
		log.info("Bus [" + this + "] posting: " + (event == null ? "(null)" : event.toString()));
		super.post(event);
	}
	
//	public synchronized void delegatePost(BaseEvent event) {
//		if (event == null) return;
//		
//		com.vaadin.ui.Component component = event.getComponent();
//		if (component != null) {	
//			//Checks and build hierarchy of event interceptors
//			Stack<EventInterceptor> interceptorsStack = new Stack<EventInterceptor>();
//			do {
//				com.vaadin.ui.Component parent = component.getParent();
//				if (parent == null) break;
//				//Avoids infinite loop if Vaadin returns same component
//				if (parent == component) break;
//				try {
//					EventInterceptor eventInterceptor = (EventInterceptor) parent;
//					interceptorsStack.push(eventInterceptor);
//				} catch(Exception e) { }
//				if (parent instanceof UI) break;
//				component = parent;
//			} while (true);
//			
//			//Calls event interceptors
//			while (!interceptorsStack.empty()) {
//				//Gets outer container that implements EventInterceptor
//				EventInterceptor eventInterceptor = interceptorsStack.pop();
//				//Updates source component
//				event.setSender((com.vaadin.ui.AbstractComponent) eventInterceptor);
//				//Calls interceptor
//				if (eventInterceptor.interceptEvent(event)) return;
//				//Event was not intercepted. Restores sender
//				event.setSender((AbstractComponent) event.getComponent());
//			}
//		}
//		//No container component (that implements EventInterceptor) has posted the event.
//		post(event);
//	}

}
