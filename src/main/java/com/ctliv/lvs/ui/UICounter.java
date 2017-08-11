package com.ctliv.lvs.ui;

public class UICounter {
	
	private static int count = 0;
	
	public static synchronized int next() {
		return count++;
	}

}
