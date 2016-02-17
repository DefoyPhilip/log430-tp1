package edu.gordon.gui.handler;

import java.awt.Label;
import java.util.StringTokenizer;

import com.google.common.eventbus.Subscribe;

import edu.gordon.atm.ATM;
import edu.gordon.event.atmgui.DisplayEvent;

public class DisplayHandler {
	
	private Label [] displayLine;
	private ATM atm;
	private int currentDisplayLine;
	private String blankDisplayLine;

	public DisplayHandler(ATM atm, Label [] displayLine, String blankDisplayLine) {
		this.atm = atm;
		this.displayLine = displayLine;
		this.currentDisplayLine = 0;
		this.blankDisplayLine = blankDisplayLine;
	}
	
	/** Clear the display
     */
    private void clearDisplay()
    { 
        for (int i = 0; i < displayLine.length; i ++)
            displayLine[i].setText("");
        currentDisplayLine = 0;
    }
      
    /** Add text to the display - may contain one or more lines delimited by \n
     *
     *  @param text the text to display
     */
    private void display(String text)
    { 
        StringTokenizer tokenizer = new StringTokenizer(text, "\n", false);
        while (tokenizer.hasMoreTokens())
        { 
            try
            { 
                displayLine[currentDisplayLine ++].setText(tokenizer.nextToken()); 
            }
            catch (Exception e)
            { }
        }
    }
    
    /** Set echoed input on the display
     *
     *  @param echo the line to be echoed - always the entire line
     */
    private void setEcho(String echo)
    {
        displayLine[currentDisplayLine].setText(
            blankDisplayLine.substring(0, 
                blankDisplayLine.length() / 2 - echo.length()) + echo);
    }
	
	@Subscribe
	public void handleEvent(DisplayEvent event) {
		if (event.type == DisplayEvent.DISPLAY_TEXT_EVENT) {
			display(atm.getDisplayText());
		}
		else if (event.type == DisplayEvent.ECHO_TEXT_EVENT) {
			setEcho(atm.getEchoText());
		}
		else if (event.type == DisplayEvent.CLEAR_TEXT_EVENT) {
			clearDisplay();
		}
		else {
			System.out.println("Bad arg");
		}
	}
}
