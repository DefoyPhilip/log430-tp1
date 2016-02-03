package edu.gordon.drivers;

import edu.gordon.banking.Card;

public interface ICardReader {
	public Card readCard();
	public void ejectCard();
	public void retainCard();
}
