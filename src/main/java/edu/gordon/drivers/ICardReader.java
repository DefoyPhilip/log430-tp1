package edu.gordon.drivers;

public interface ICardReader {
	public int readCard();
	public void ejectCard();
	public void retainCard();
}
