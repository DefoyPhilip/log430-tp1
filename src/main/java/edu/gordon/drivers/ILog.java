package edu.gordon.drivers;

import edu.gordon.banking.Message;
import edu.gordon.banking.Money;
import edu.gordon.banking.Status;

public interface ILog {
	public void logSend(Message  message);
	public void logResponse(Status response);
	public void logCashDispensed(Money amount);
	public void logEnvelopeAccepted();
}
