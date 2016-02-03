package edu.gordon.drivers;

public interface ILog {
	public void logSend(String message);
	public void logResponse(String response);
	public void logCashDispensed(String amount);
	public void logEnvelopeAccepted();
}
