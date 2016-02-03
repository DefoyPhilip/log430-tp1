package edu.gordon.drivers;

import edu.gordon.simulation.drivers.CustomerConsole.Cancelled;

public interface ICustomerConsole {
	public void display(String message);
	public int readPIN(String prompt) throws Cancelled;
	public int readMenuChoice(String prompt, String[] menu) throws Cancelled;
	public long readAmount(String prompt) throws Cancelled;
}
