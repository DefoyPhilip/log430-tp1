/* * ATM Example system - file Withdrawal.java     * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.atm.transaction;import edu.gordon.banking.AccountInformation;import edu.gordon.banking.Card;import edu.gordon.banking.Message;import edu.gordon.banking.Money;/** Representation for a cash withdrawal transaction */public class Withdrawal extends Transaction{    /** Constructor     *     *  @param edu.gordon.atm the ATM used to communicate with customer     *  @param session the session in which the transaction is being performed     *  @param card the customer's card     *  @param pin the PIN entered by the customer     */    public Withdrawal(Card card, int pin)    {        super(card, pin);    }        public boolean getNeedsFrom(){    	return true;    }        public boolean getNeedsTo(){    	return false;    }        public boolean getNeedsAmount(){    	return false;    }        public boolean getNeedsRoundAmount(){    	return true;    }        public int getMessageCode(){    	return Message.WITHDRAWAL;    }        public String getTransactionName(){    	return "WITHDRAWAL";    }        public String getVerb(){    	return "withdraw";    }        public void setFrom(int from){    	this.from = from;    }        public void setTo(int to){    	//Do nothing    }        public void setAmount(Money amount){    	this.amount = amount;    }        /** Account to withdraw from     */     private int from;        /** Amount of money to withdraw     */    private Money amount;	public Message getSpecificsMessage() {		return new Message(Message.WITHDRAWAL,                 card, pin, serialNumber, from, -1, amount);	}	@Override	public boolean getNeedsEnvelopeAcceptor() {		return false;	}	@Override	public boolean getNeedsCashDispenser() {		return true;	}	@Override	public boolean getNeedsToSendMessage() {		return false;	}	@Override	public Message getMessageToSend() {		return null;	}	@Override	public String[] getReceiptDetails() {		String[] detailsPortion = new String[2];        detailsPortion[0] = "WITHDRAWAL FROM: " +                             AccountInformation.ACCOUNT_ABBREVIATIONS[from];        detailsPortion[1] = "AMOUNT: " + amount.toString();		return detailsPortion;	}           		public Money getAmount() {		return amount;	}}