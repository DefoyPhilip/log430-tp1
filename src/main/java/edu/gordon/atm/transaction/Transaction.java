
/* * ATM Example system - file Transaction.java    * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.atm.transaction;import edu.gordon.atm.ATM;import edu.gordon.atm.Session;import edu.gordon.banking.Balances;import edu.gordon.banking.Card;import edu.gordon.banking.Message;import edu.gordon.banking.Money;import edu.gordon.drivers.Cancelled;/** Abstract base class for classes representing the various kinds of *  transaction the ATM can perform */public abstract class Transaction{    /** Constructor     *     *  @param card the customer's card     *  @param pin the PIN entered by the customer     */     	protected Transaction(Card card, int pin)
    {
        this.card = card;
        this.pin = pin;
        this.serialNumber = nextSerialNumber ++;
        this.balances = new Balances();
        
        state = GETTING_SPECIFICS_STATE;
    }             /** Create a transaction of an appropriate type by asking the customer     *  what type of transaction is desired and then returning a newly-created     *  member of the appropriate subclass     *     *  @param edu.gordon.atm the ATM used to communicate with customer     *  @param session the session in which this transaction is being performed     *  @param card the customer's card     *  @param pin the PIN entered by the customer     *  @return a newly created Transaction object of the appropriate type     *  @exception CustomerConsole.Cancelled if the customer presses cancel instead     *         of choosing a transaction type     */    public static Transaction makeTransaction(int choice, Card card, int pin) throws Cancelled                  {    	switch(choice)
        {
            case 0:
            
                return new Withdrawal(card, pin);
                
            case 1:
            
                return new Deposit(card, pin);
                
            case 2:
            	
            
                return new Transfer(card, pin);
                
            case 3:
            
                return new Inquiry(card, pin);
                
            default:
            
                return null;    // To keep compiler happy - should not happen!
        }    }        /** Get serial number of this transaction     *     *  @return serial number     */    public int getSerialNumber()    {        return serialNumber;    }
    
    public Card getCard(){
    	return card;
    }            // Local class representing card retained exception           /** Exception that is thrown when the customer's card is retained due to too     *  many invalid PIN entries     */    public static class CardRetained extends Exception    {        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/** Constructor         */        public CardRetained()        {            super("Card retained due to too many invalid PINs");        }    }            // Instance variables    /** ATM to use for communication with the customer     */    protected ATM atm;        /** Session in which this transaction is being performed     */    protected Session session;        /** Customer card for the session this transaction is part of     */    protected Card card;        /** PIN entered or re-entered by customer     */    protected int pin;        /** Serial number of this transaction     */    protected int serialNumber;        /** Message to bank describing this transaction     */    protected Message message;        /** Used to return account balances from the bank     */    protected Balances balances;            /** Next serial number - used to assign a unique serial number to     *  each transaction     */    private static int nextSerialNumber = 1;        /** The current state of the transaction     */    private int state;        // Possible values for state        /** Getting specifics of the transaction from customer     */    private static final int GETTING_SPECIFICS_STATE = 1;
    
    /**
     * Get if the transaction needs a "from" account - each subclass must implement
     *  this appropriately.
     * @return if the transaction needs a "from" account
     * */
    public abstract boolean getNeedsFrom();
    
    /**
     * Get if the transaction needs a "to" account- each subclass must implement
     *  this appropriately.
     * @return if the transaction needs a "to" account
     * */
    public abstract boolean getNeedsTo();
    
    /**
     * Get if the transaction needs an amount of money- each subclass must implement
     *  this appropriately.
     * @return if the transaction needs an amount of money
     * */
    public abstract boolean getNeedsAmount();
    
    /**
     * Get if the transaction needs a specific round amount of money (20, 40, 60...)- each subclass must implement
     *  this appropriately.
     * @return if the transaction needs a specific amount of money
     * */
    public abstract boolean getNeedsRoundAmount();
    
    /**
     * Get the message code for the transaction 
     * @return the message code for the transaction
     * */
    public abstract int getMessageCode();
    
    public abstract String getTransactionName();
    
    public abstract String getVerb();
    
    public abstract Message getSpecificsMessage();
    
    public abstract boolean getNeedsEnvelopeAcceptor();
    
    public abstract boolean getNeedsCashDispenser();
    
    public abstract boolean getNeedsToSendMessage();
    
    public abstract Message getMessageToSend();
    
    public abstract String[] getReceiptDetails();
    
    public abstract Money getAmount();
    
    public Balances getBalances(){
    	return balances;
    }
    
    public int getState(){
    	return state;
    }
    
    /**
     * Set the "from" account of the transaction (if needed) - each subclass must implement
     *  this appropriately.
     * */
    public abstract void setFrom(int from);
    
    /**
     * Set the "to" account of the transaction (if needed) - each subclass must implement
     *  this appropriately.
     * */
    public abstract void setTo(int to);
    
    /**
     * Set the amount of money of the transaction (if needed) - each subclass must implement
     *  this appropriately.
     * */
    public abstract void setAmount(Money amount);}
