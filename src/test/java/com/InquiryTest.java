package com;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.gordon.atm.transaction.Transaction;
import edu.gordon.banking.Card;
import edu.gordon.banking.Money;
import edu.gordon.drivers.Cancelled;
import edu.gordon.simulation.SimulatedBank;

public class InquiryTest {

	private Money amount;
	private Card card;
	private int pin;
	private int account1 = 0;
	private SimulatedBank bank;
	
	@Test
	public void testInquiry(){
		try{
			card = new Card(2);
			pin = 1234;
			amount = new Money(20);
			bank = new SimulatedBank();
			Transaction transaction = Transaction.makeTransaction(3, card, pin);
			transaction.setAmount(amount);
			transaction.setFrom(account1);
			
			bank.handleMessage(transaction.getSpecificsMessage(), transaction.getBalances());
			
			Money expectedTotal = new Money(100);
			
			assertEquals(transaction.getBalances().getTotal().toString(),expectedTotal.toString());
		}catch(Cancelled e){
			//Since Cancelled can only be thrown by GUI, it will not happen here	
		}
	}

}
