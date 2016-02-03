package edu.gordon.drivers;


public interface INetworkToBank {
	public void openConnection();
	public void closeConnection();
	public String sendMessage(String message, long balances);
}
