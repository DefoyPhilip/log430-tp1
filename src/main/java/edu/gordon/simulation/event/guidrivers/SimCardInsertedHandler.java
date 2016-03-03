package edu.gordon.simulation.event.guidrivers;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import edu.gordon.event.driversatm.CardInsertedEvent;

public class SimCardInsertedHandler {
	private EventBus driversAtmBus;
	public SimCardInsertedHandler(EventBus driversAtmBus){
		this.driversAtmBus = driversAtmBus;
	}
	@Subscribe
	public void handle(SimCardInsertedEvent e){
		driversAtmBus.post(new CardInsertedEvent());
	}
}
