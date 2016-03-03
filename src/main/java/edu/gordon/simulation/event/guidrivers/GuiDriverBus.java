package edu.gordon.simulation.event.guidrivers;

import com.google.common.eventbus.EventBus;

public class GuiDriverBus {
	private static EventBus bus = new EventBus();
	
	public static EventBus getInstance(){
		return bus;
	}
}
