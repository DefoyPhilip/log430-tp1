package edu.gordon.drivers;

import edu.gordon.simulation.drivers.CustomerConsole;

public interface IEnvelopeAcceptor {
	public void acceptEnvelope() throws CustomerConsole.Cancelled;
}
