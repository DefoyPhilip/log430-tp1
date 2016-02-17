package edu.gordon.event.atmgui;

import java.util.EventObject;

public class DisplayEvent extends EventObject {
	
	public String type;
	public final static String DISPLAY_TEXT_EVENT = "DISPLAY_TEXT_EVENT";
	public final static String ECHO_TEXT_EVENT = "ECHO_TEXT_EVENT";
	public final static String CLEAR_TEXT_EVENT = "CLEAR_TEXT_EVENT";

	public DisplayEvent(Object source, String type) {
		super(source);
		if (type != DISPLAY_TEXT_EVENT || type != ECHO_TEXT_EVENT || type != CLEAR_TEXT_EVENT) {
			System.out.println("Bad event type");
		}
		this.type = type;
	}
}
