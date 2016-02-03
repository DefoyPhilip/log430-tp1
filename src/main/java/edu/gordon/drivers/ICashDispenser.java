package edu.gordon.drivers;

import edu.gordon.banking.Money;

public interface ICashDispenser {
	public void setInitialCash(Money initialCash);
	public boolean checkCashOnHand(Money amount);
	public void dispenseCash(Money amount);
}
