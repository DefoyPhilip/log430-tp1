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
import com.google.common.eventbus.EventBus;
import edu.gordon.atm.ATM;
import edu.gordon.gui.handler.DisplayHandler;

/** Simulate the display portion of the customer console
 */
public class Display extends Panel
{
    /** Constructor
     */
	public Display(ATM atm, int displayableLines, String blankDisplayLine, EventBus atmGuiBus)
    {
		setLayout(new GridLayout(displayableLines, 1));
        setBackground(new Color(0, 96, 0));  // Dark green
        setForeground(Color.white);
        
        Font lineFont = new Font("Monospaced", Font.PLAIN, 14);
        Label [] displayLine = new Label[displayableLines];
        for (int i = 0; i < displayableLines; i ++)
        {
            displayLine[i] = new Label(blankDisplayLine);
            displayLine[i].setFont(lineFont);
            add(displayLine[i]);
        }
        atmGuiBus.register(new DisplayHandler(atm, displayLine, blankDisplayLine));
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
}                               
    
    
