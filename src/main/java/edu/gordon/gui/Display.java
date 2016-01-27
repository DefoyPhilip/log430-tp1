/*
 * ATM Example system - file SimDisplay.java
 *
 * copyright (c) 2001 - Russell C. Bjork
 *
 */
 
package edu.gordon.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;

import edu.gordon.atm.ATM;

/** Simulate the display portion of the customer console
 */
public class Display extends Panel implements Observer
{
    /** Constructor
     */
	public Display(ATM atm, int displayableLines, String blankDisplayLine)
    {
		this.atm = atm;
		this.blankDisplayLine = blankDisplayLine;
		setLayout(new GridLayout(displayableLines, 1));
        setBackground(new Color(0, 96, 0));  // Dark green
        setForeground(Color.white);
        
        Font lineFont = new Font("Monospaced", Font.PLAIN, 14);
        displayLine = new Label[displayableLines];
        for (int i = 0; i < displayableLines; i ++)
        {
            displayLine[i] = new Label(blankDisplayLine);
            displayLine[i].setFont(lineFont);
            add(displayLine[i]);
        }
        currentDisplayLine = 0;
        
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
    
    /** Override the getInsets() method to provide additional spacing all
     *  around
     */
    public Insets getInsets()
    {
        Insets insets = super.getInsets();
        insets.top += 5;
        insets.bottom += 5;
        insets.left += 10;
        insets.right += 10;
        return insets;
    }

    /** Individual lines comprising the display
     */
    private Label [] displayLine;
    
    /** Number of the current line to write to
     */
    private int currentDisplayLine;
    
    private String blankDisplayLine;
    
    private ATM atm;

	public void update(Observable o, Object arg) {
		if ((String)arg == "DISPLAY_TEXT") {
			display(atm.getDisplayText());
		}
		else if ((String)arg == "ECHO_TEXT") {
			setEcho(atm.getEchoText());
		}
		else if ((String)arg == "CLEAR_TEXT") {
			clearDisplay();
		}
		else {
			System.out.println("Bad arg");
		}
	}
}                               
    
    
