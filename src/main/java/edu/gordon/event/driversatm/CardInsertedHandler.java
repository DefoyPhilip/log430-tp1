package edu.gordon.event.driversatm;

import com.google.common.eventbus.Subscribe;
import edu.gordon.atm.ATM;

public class CardInsertedHandler {
	private ATM atm;

	public CardInsertedHandler(ATM atm){
		this.atm = atm;
	}

	@Subscribe
	public void handle(CardInsertedEvent e){
		//Appeler la fonction de l'atm
		atm.cardInserted();
	}
}
