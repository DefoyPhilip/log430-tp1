package edu.gordon.drivers;

public interface ICashDispenser {
	public void setInitialCash(long initialCash);
	public boolean checkCashOnHand(long amount);
	public void dispenseCash(long amount);
}
