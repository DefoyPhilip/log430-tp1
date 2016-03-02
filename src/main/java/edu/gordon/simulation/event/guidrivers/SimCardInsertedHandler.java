package edu.gordon.simulation.event.guidrivers;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import edu.gordon.event.driversatm.CardInsertedEvent;
import edu.gordon.event.driversatm.DriversAtmBus;

public class SimCardInsertedHandler {
	private EventBus driversAtmBus;
	public SimCardInsertedHandler(EventBus driversAtmBus){
		this.driversAtmBus = driversAtmBus;
	}
	@Subscribe
	public void handle(SimCardInsertedEvent e){
		//Faire un autre event et post un event dans le bus driversAtm
		DriversAtmBus.getInstance().post(new CardInsertedEvent());
	}
}
