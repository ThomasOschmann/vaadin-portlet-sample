package com.ctliv.vportlet.ui.base;

public class UICounter {
	
	private static int count = 0;
	
	public static synchronized int next() {
		return count++;
	}

}
