/* * ATM Example system - file SimOperatorPanel.java * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.simulation.gui;import java.awt.BorderLayout;import java.awt.Button;import java.awt.Color;import java.awt.Label;import java.awt.Panel;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import edu.gordon.simulation.Simulation;/** Simulate the operator panel */public class SimOperatorPanel extends Panel{    /** Constructor     *     *  @param edu.gordon.Simulation.getInstance() the overall edu.gordon.Simulation.getInstance() object     */    public SimOperatorPanel()    {        setLayout(new BorderLayout(10, 0));        setBackground(new Color(128,128,255));        add(new Label("     Operator Panel"), BorderLayout.WEST);        final Label message = new Label("Click button to turn ATM on", Label.CENTER);        add(message, BorderLayout.CENTER);        final Button button = new Button(" ON ");        button.addActionListener(new ActionListener() {            public void actionPerformed(ActionEvent e)            {                if (button.getLabel().equals("OFF"))    // ATM is currently on                {                    message.setText("Click button to turn ATM on  ");                    button.setLabel(" ON ");                                        Simulation.getInstance().switchChanged(false);                }                else                                    // ATM is currently off                {                    message.setText("Click button to turn ATM off");                    button.setLabel("OFF");                                        Simulation.getInstance().switchChanged(true);                }            }        });        Panel buttonPanel = new Panel();        buttonPanel.add(button);        add(buttonPanel, BorderLayout.EAST);                // Use a thread to blink the "Click button to turn ATM on" message when        // the ATM is off.  This will also make the message invisible when the        // button is not enabled.                new Thread() {            public void run()            {                while(true)                {                    try                    {                        sleep(1000);                    }                    catch(InterruptedException e)                    { }                                        if (message.isVisible() && ! button.getLabel().equals("OFF")                            || ! SimOperatorPanel.this.isEnabled() )                        message.setVisible(false);                    else                        message.setVisible(true);                }            }        }.start();    }}                                       