package edu.gordon.event.driversatm;

import com.google.common.eventbus.Subscribe;

public class CardInsertedHandler {
	@Subscribe
	public void handle(CardInsertedEvent e){
		//Appeler la fonction de l'atm

	}
}
