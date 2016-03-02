package edu.gordon.simulation.drivers;

import com.google.common.eventbus.EventBus;
import edu.gordon.drivers.DriversFactory;
import edu.gordon.drivers.ICardReader;
import edu.gordon.drivers.ICashDispenser;
import edu.gordon.drivers.ICustomerConsole;
import edu.gordon.drivers.IEnvelopeAcceptor;
import edu.gordon.drivers.ILog;
import edu.gordon.drivers.INetworkToBank;
import edu.gordon.drivers.IOperatorPanel;
import edu.gordon.drivers.IReceiptPrinter;

public class SimDriversFactory implements DriversFactory {
	private EventBus driversAtmBus;

	public SimDriversFactory(EventBus driversAtmBus) {
		// TODO Auto-generated constructor stub
		this.driversAtmBus = driversAtmBus;
	}

	public ICardReader createCardReader() {
		return new CardReader(driversAtmBus);
	}

	public ILog createLog() {
		return new Log();
	}

	public ICashDispenser createCashDispenser(ILog log) {
		return new CashDispenser(log);
	}
	
	public ICustomerConsole createCustomerConsole() {
		return new CustomerConsole();
	}

	public IOperatorPanel createOperatorPanel() {
		return new OperatorPanel();
	}

	public IReceiptPrinter createReceiptPrinter() {
		return new ReceiptPrinter();
	}

	public INetworkToBank createINetworkToBank(ILog log) {
		return new NetworkToBank((Log) log);
	}

	public IEnvelopeAcceptor createEnvelopeAcceptor(ILog log) {
		return new EnvelopeAcceptor((Log) log);
	}

}
