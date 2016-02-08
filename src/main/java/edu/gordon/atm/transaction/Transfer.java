/* * ATM Example system - file Transfer.java   * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.atm.transaction;import edu.gordon.atm.Session;import edu.gordon.banking.AccountInformation;import edu.gordon.banking.Card;import edu.gordon.banking.Message;import edu.gordon.banking.Money;/** Representation for a transfer transaction */public class Transfer extends Transaction{    /** Constructor     *     *  @param edu.gordon.atm the ATM used to communicate with customer     *  @param session the session in which the transaction is being performed     *  @param card the customer's card     *  @param pin the PIN entered by the customer     */    public Transfer(Session session, Card card, int pin)    {        super(session, card, pin);    }        public boolean getNeedsFrom(){    	return true;    }        public boolean getNeedsTo(){    	return true;    }        public boolean getNeedsAmount(){    	return true;    }        public boolean getNeedsRoundAmount(){    	return false;    }        public int getMessageCode(){    	return Message.TRANSFER;    }        public String getTransactionName(){    	return "TRANSFER";    }        public String getVerb(){    	return "transfer";    }        public void setFrom(int from){    	this.from = from;    }        public void setTo(int to){    	this.to = to;    }        public void setAmount(Money amount){    	this.amount = amount;    }        /** Accounts to transfer from     */    private int from;        /** Account to transfer to     */    private int to;        /** Amount of money to transfer     */    private Money amount;	public Message getSpecificsMessage() {		return new Message(Message.TRANSFER,                 card, pin, serialNumber, from, to, amount);	}	@Override	public boolean getNeedsEnvelopeAcceptor() {		return false;	}	@Override	public boolean getNeedsCashDispenser() {		return false;	}	@Override	public boolean getNeedsToSendMessage() {		return false;	}	@Override	public Message getMessageToSend() {		return null;	}	@Override	public String[] getReceiptDetails() {		String[] detailsPortion = new String[2];        detailsPortion[0] = "TRANSFER FROM: " +                             AccountInformation.ACCOUNT_ABBREVIATIONS[from] +                            " TO: " +                             AccountInformation.ACCOUNT_ABBREVIATIONS[to] ;        detailsPortion[1] = "AMOUNT: " + amount.toString();		return detailsPortion;	}           		public Money getAmount() {		return amount;	}}