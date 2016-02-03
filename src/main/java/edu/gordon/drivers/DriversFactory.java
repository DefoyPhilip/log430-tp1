package edu.gordon.drivers;

import java.net.InetAddress;

public interface DriversFactory {
	public ICardReader createCardReader();
	public ILog createLog();
	public ICashDispenser createCashDispenser(ILog log);
	public ICustomerConsole createCustomerConsole();
	public IOperatorPanel createOperatorPanel();
	public IReceiptPrinter createReceiptPrinter();
	public IEnvelopeAcceptor createEnvelopeAcceptor(ILog log);
	public INetworkToBank createINetworkToBank(ILog log, InetAddress bankAddress);
}
