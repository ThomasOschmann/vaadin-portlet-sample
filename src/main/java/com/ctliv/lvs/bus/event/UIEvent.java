package com.ctliv.lvs.bus.event;

import com.vaadin.ui.UI;

public class UIEvent extends BaseEvent {
	
	public enum UIEventMode {
		REFRESH,
		ATTACH,
		DETACH
	}
	
	private UIEventMode mode = null;

	public UIEvent(UI source, UIEventMode mode) {
		super(source);
		this.mode = mode;
	}

	public UIEventMode getMode() {
		return mode;
	}
	
}
