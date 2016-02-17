package edu.gordon.event.driversatm;

import com.google.common.eventbus.EventBus;

public class DriversAtmBus {
	private static EventBus bus = new EventBus();
	public static EventBus getInstance(){
		return bus;
	}
}
