package com.ctliv.vportlet.ui.base;

public class UICounter {
	
	private static int count = 1;
	
	public static synchronized int next() {
		return count++;
	}

}
