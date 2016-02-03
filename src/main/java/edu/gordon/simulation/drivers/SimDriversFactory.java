package edu.gordon.simulation.drivers;

import edu.gordon.drivers.DriversFactory;
import edu.gordon.drivers.ICardReader;
import edu.gordon.drivers.ICashDispenser;
import edu.gordon.drivers.ILog;

public class SimDriversFactory implements DriversFactory {

	public SimDriversFactory() {
		// TODO Auto-generated constructor stub
	}

	public ICardReader createCardReader() {
		return new CardReader();
	}

	public ILog createLog() {
		return new Log();
	}

	public ICashDispenser createCashDispenser(ILog log) {
		return new CashDispenser(log);
	}

}
