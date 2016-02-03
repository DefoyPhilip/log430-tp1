package edu.gordon.drivers;

public interface DriversFactory {
	public ICardReader createCardReader();
	public ILog createLog();
	public ICashDispenser createCashDispenser(ILog log);
}
