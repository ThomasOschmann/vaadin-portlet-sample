package com.ctliv.vportlet.bus.event;

public class BaseEvent {

	protected Object source = null;

	public BaseEvent(Object source) {
		this.source = source;
	}

	public Object getSource() {
		return source;
	}

}
