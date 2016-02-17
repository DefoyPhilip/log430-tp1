package edu.gordon.simulation.event.guidrivers;

import com.google.common.eventbus.Subscribe;

public class SimCardInsertedHandler {
	@Subscribe
	public void handle(SimCardInsertedEvent e){
		//Faire un autre event et post un event dans le bus driversAtm
	}
}
