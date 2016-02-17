package edu.gordon.simulation.event.guidrivers;

import com.google.common.eventbus.Subscribe;

public class SimEnvelopeInsertedHandler {
	@Subscribe
	public void handle(SimEnvelopeInsertedEvent e){
		//Faire un autre event et post un event dans le bus driversAtm
		
	}

}
