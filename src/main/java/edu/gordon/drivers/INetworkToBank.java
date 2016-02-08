package edu.gordon.drivers;

import edu.gordon.banking.Balances;
import edu.gordon.banking.Message;
import edu.gordon.banking.Status;

public interface INetworkToBank {
	public void openConnection();
	public void closeConnection();
	public Status sendMessage(Message message, Balances balances);
}
