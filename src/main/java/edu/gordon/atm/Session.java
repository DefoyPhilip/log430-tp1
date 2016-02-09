
/* * ATM Example system - file Session.java * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.atm;import edu.gordon.atm.transaction.Transaction;import edu.gordon.banking.AccountInformation;
import edu.gordon.banking.Balances;
import edu.gordon.banking.Card;import edu.gordon.banking.Message;
import edu.gordon.banking.Money;
import edu.gordon.banking.Receipt;
import edu.gordon.banking.Status;
import edu.gordon.drivers.Cancelled;/** Representation for one ATM session serving a single customer. */public class Session{    /** Constructor     *     *  @param edu.gordon.atm the ATM on which the session is performed     */    public Session(ATM atm)    {        this.atm = atm;                state = READING_CARD_STATE;    }    /** Perform the Session Use Case     */    public void performSession()    {        Card card = null;        Transaction currentTransaction = null;                while (state != FINAL_STATE)        {            switch(state)            {                case READING_CARD_STATE:                    card = atm.getCardReader().readCard();                                        if (card.getNumber() != -1)                        state = READING_PIN_STATE;                    else                    {                        atm.getCustomerConsole().display("Unable to read card");                        state = EJECTING_CARD_STATE;                    }                    break;                                    case READING_PIN_STATE:                                    try                    {                        pin = atm.getCustomerConsole().readPIN(                            "Please enter your PIN\n" +                            "Then press ENTER");                        state = CHOOSING_TRANSACTION_STATE;                    }                    catch(Cancelled e)                    {                        state = EJECTING_CARD_STATE;                    }                    break;                                case CHOOSING_TRANSACTION_STATE:                                    try                    {                    	int choice = atm.getCustomerConsole().readMenuChoice(
                                "Please choose transaction type", TRANSACTION_TYPES_MENU);
                        currentTransaction = 
                            Transaction.makeTransaction(choice, card, pin);
                        state = PERFORMING_TRANSACTION_STATE;                    }                    catch(Cancelled e)                    {                        state = EJECTING_CARD_STATE;                    }                    break;                                    case PERFORMING_TRANSACTION_STATE:                                	try
                    {
                        boolean doAgain = performTransaction(currentTransaction);
                        
                        if (doAgain)
                            state = CHOOSING_TRANSACTION_STATE;
                        else
                            state = EJECTING_CARD_STATE;
                    }
                    catch(CardRetained e)
                    {
                        state = FINAL_STATE;
                    }                                    case EJECTING_CARD_STATE:                                    atm.getCardReader().ejectCard();                    state = FINAL_STATE;                    break;            }        }    }
    
    private boolean performTransaction(Transaction transaction) throws CardRetained
    {
        String doAnotherMessage = "";
        Status status = null;
        Receipt receipt = null;
        state = transaction.getState();
        
        while (true)    // Terminates by return in ASKING_DO_ANOTHER_STATE or exception
        {
            switch(state)
            {
                case GETTING_SPECIFICS_STATE:
                
                    try
                    {           
                        message = getSpecificsFromCustomer(transaction);
                        atm.getCustomerConsole().display("");
                        state = SENDING_TO_BANK_STATE;
                    }
                    catch(Cancelled e)
                    {
                        doAnotherMessage = "Last transaction was cancelled";
                        state = ASKING_DO_ANOTHER_STATE;
                    }
                    
                    break;
                    
                case SENDING_TO_BANK_STATE:
                                
                    status = atm.getNetworkToBank().sendMessage(message, transaction.getBalances());
                
                    if (status.isInvalidPIN())
                        state = INVALID_PIN_STATE;
                    else if (status.isSuccess())
                        state = COMPLETING_TRANSACTION_STATE;
                    else
                    {
                        doAnotherMessage = status.getMessage();
                        state = ASKING_DO_ANOTHER_STATE;
                    }
                    
                    break;
                
                case INVALID_PIN_STATE:
                
                    try
                    {
                        status = performInvalidPINExtension(transaction.getBalances());
                    
                        // If customer repeatedly enters invalid PIN's, a
                        // CardRetained exception is thrown, and this method
                        // terminates
                        
                        if (status.isSuccess())
                            state = COMPLETING_TRANSACTION_STATE;
                        else
                        {
                            doAnotherMessage = status.getMessage();
                            state = ASKING_DO_ANOTHER_STATE;
                        }
                    }
                    catch(Cancelled e)
                    {
                        doAnotherMessage = "Last transaction was cancelled";
                        state = ASKING_DO_ANOTHER_STATE;
                    }

                    break;
                        
                case COMPLETING_TRANSACTION_STATE:

                    try
                    {
                        receipt = completeTransaction(transaction);
                        state = PRINTING_RECEIPT_STATE;
                    }
                    catch(Cancelled e)
                    {
                        doAnotherMessage = "Last transaction was cancelled";
                        state = ASKING_DO_ANOTHER_STATE;
                    }
                    
                    break;
                    
                case PRINTING_RECEIPT_STATE:
                
                    atm.getReceiptPrinter().printReceipt(receipt);
                    state = ASKING_DO_ANOTHER_STATE;
                    
                    break;
                    
                case ASKING_DO_ANOTHER_STATE:
                
                    if (doAnotherMessage.length() > 0)
                        doAnotherMessage += "\n";
                        
                    try
                    {
                        String [] yesNoMenu = { "Yes", "No" };

                        boolean doAgain = atm.getCustomerConsole().readMenuChoice(
                            doAnotherMessage + 
                            "Would you like to do another transaction?",
                            yesNoMenu) == 0;
                        return doAgain;
                    }
                    catch(Cancelled e)
                    {
                        return false;
                    }
            }
        }
    }
    
    /** Perform a transaction.  This method asks the transaction what informations
     *  to ask the costumer for
     *
     *	@param the transaction to perform
     *  @return true if customer indicates a desire to do another transaction;
     *          false if customer does not desire to do another transaction
     *  @exception CardRetained if card was retained due to too many invalid PIN's
     */
    
    protected Message getSpecificsFromCustomer(Transaction transaction) throws Cancelled{
    	
    	String transactionVerb = transaction.getVerb();
    	Money amount = new Money(0);
    	if(transaction.getNeedsFrom()){
    		transaction.setFrom(atm.getCustomerConsole().readMenuChoice(
    	            "Account to "+transactionVerb+" from",
    	            AccountInformation.ACCOUNT_NAMES));
    	}
    	if(transaction.getNeedsTo()){
    		transaction.setTo(atm.getCustomerConsole().readMenuChoice(
    	            "Account to "+transactionVerb+" to",
    	            AccountInformation.ACCOUNT_NAMES));
    	}
    	if(transaction.getNeedsAmount()){
    		transaction.setAmount(atm.getCustomerConsole().readAmount("Amount to deposit"));
    	}
    	if(transaction.getNeedsRoundAmount()){
    		String [] amountOptions = { "$20", "$40", "$60", "$100", "$200" };
            Money [] amountValues = { 
                                      new Money(20), new Money(40), new Money(60),
                                      new Money(100), new Money(200)
                                    };
                                      
            String amountMessage = "";
            boolean validAmount = false;
            
            
            while (! validAmount)
            {
            	amount = amountValues [ atm.getCustomerConsole().readMenuChoice(
                        amountMessage + "Amount of cash to withdraw", amountOptions) ];
                                
                validAmount = atm.getCashDispenser().checkCashOnHand(amount);

                if (! validAmount)
                    amountMessage = "Insufficient cash available\n";
            }
            transaction.setAmount(amount);
    	}
    	return transaction.getSpecificsMessage();
    }    
    /** Complete an approved transaction  - each subclass must implement
     *  this appropriately.
     *
     *  @return receipt to be printed for this transaction
     *  @exception CustomerConsole.Cancelled if customer cancelled this transaction
     */
    public Receipt completeTransaction(final Transaction transaction) throws Cancelled{
    	if(transaction.getNeedsEnvelopeAcceptor()){
    		atm.getEnvelopeAcceptor().acceptEnvelope();
    	}
    	if(transaction.getNeedsToSendMessage()){
    		Status status = atm.getNetworkToBank().sendMessage(
    	            transaction.getMessageToSend(), 
    	            transaction.getBalances());
    	}
    	if(transaction.getNeedsCashDispenser()){
    		atm.getCashDispenser().dispenseCash(transaction.getAmount());
    	}
    	
    	return new Receipt(atm.getBankName(), atm.getID(), atm.getPlace(), transaction.getCard(), transaction, transaction.getBalances()) {
            {
                detailsPortion = transaction.getReceiptDetails();
            }
        };
    }
    
    /** Perform the Invalid PIN Extension - reset session pin to new value if successful
    *
    *  @return status code returned by bank from most recent re-submission
    *          of transaction
    *  @exception CustomerConsole.Cancelled if customer presses the CANCEL key
    *             instead of re-entering PIN
    *  @exception CardRetained if card was retained due to too many invalid PIN's
    */
   public Status performInvalidPINExtension(Balances balances) throws Cancelled, CardRetained
   {
       Status status = null;
       for (int i = 0; i < 3; i ++)
       {
           pin = atm.getCustomerConsole().readPIN(
               "PIN was incorrect\nPlease re-enter your PIN\n" +
               "Then press ENTER");
           atm.getCustomerConsole().display("");
           
           message.setPIN(pin);
           status = atm.getNetworkToBank().sendMessage(message, balances);
           if (! status.isInvalidPIN())
           {
               setPIN(pin);
               return status;
           }
       }
       
       atm.getCardReader().retainCard();
       atm.getCustomerConsole().display(
           "Your card has been retained\nPlease contact the bank.");
       try
       {
           Thread.sleep(5000);
       }
       catch(InterruptedException e)
       { }
       atm.getCustomerConsole().display("");
               
       throw new CardRetained();
   }
        /** Change the pin recorded for the customer (if invalid pin extension     *  was performed by a transaction     *     *  @param pin the newly entered pin     */    public void setPIN(int pin)    {        this.pin = pin;    }        // Instance variables    /** The ATM on which the session is performed     */    private ATM atm;        /** The PIN entered (or re-entered) by the customer     */    private int pin;    /** The current state of the session     */    private int state;
    
    /** Message to bank describing a transaction
     */
    protected Message message;    // Possible values for state
    
    /** Reading the customer's card
     */
    private static final int READING_CARD_STATE = 1;
    
    /** Asking the customer to enter a PIN
     */
    private static final int READING_PIN_STATE = 2;
    
    /** Asking the customer to choose a transaction type
     */
    private static final int CHOOSING_TRANSACTION_STATE = 3;
    
    /** Performing a transaction
     */
    private static final int PERFORMING_TRANSACTION_STATE = 4;
    
    /** Ejecting the customer's card
     */
    private static final int EJECTING_CARD_STATE = 5;
    
    /** Session finished
     */
    private static final int FINAL_STATE = 6;
    
    /** Getting specifics of the transaction from customer
     */
    private static final int GETTING_SPECIFICS_STATE = 1;
    
    /** Sending transaction to bank
     */
    private static final int SENDING_TO_BANK_STATE = 2;
    
    /** Performing invalid PIN extension
     */
    private static final int INVALID_PIN_STATE = 3;
    
    /** Completing transaction
     */
    private static final int COMPLETING_TRANSACTION_STATE = 4;
    
    /** Printing receipt
     */
    private static final int PRINTING_RECEIPT_STATE = 5;
    
    /** Asking if customer wants to do another transaction
     */
    private static final int ASKING_DO_ANOTHER_STATE = 6;
    
    /** List of available transaction types to display as a menu
     */
    private static final String [] TRANSACTION_TYPES_MENU = 
        { "Withdrawal", "Deposit", "Transfer", "Balance Inquiry" };
    
// Local class representing card retained exception
    
    /** Exception that is thrown when the customer's card is retained due to too
     *  many invalid PIN entries
     */
    public static class CardRetained extends Exception
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/** Constructor
         */
        public CardRetained()
        {
            super("Card retained due to too many invalid PINs");
        }
    }    }
