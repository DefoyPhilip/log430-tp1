/* * ATM Example system - file SimCashDispenser.java * * copyright (c) 2001 - Russell C. Bjork * */ package edu.gordon.simulation.gui;import java.awt.Color;import java.awt.Font;import java.awt.GridLayout;import java.awt.Label;import java.awt.Panel;import edu.gordon.banking.Money;/** Simulate the cash dispenser */public class SimCashDispenser extends Panel{    /** Constructor     */    public SimCashDispenser()    {        setLayout(new GridLayout(1,1));        label = new Label("$XXX.XX", Label.CENTER);        label.setFont(new Font("SansSerif", Font.PLAIN, 24));        label.setForeground(new Color(0, 64, 0));        add(label);        label.setVisible(false);    }        /** Animate dispensing cash to a customer     *     *  @param amount the amount of cash to dispense     *     *  Precondition: amount is <= cash on hand     */    public void animateDispensingCash(long amount)    {    	Money money = new Money(0,(int) amount);        label.setText(money.toString());        for (int size = 3; size <= 24; size += 1)        {             label.setFont(new Font("SansSerif", Font.PLAIN, size));            label.setVisible(true);            try            {                 Thread.sleep(250);            }            catch (InterruptedException e)            { }            label.setVisible(false);        }    }        /** Label that shows the amount of money being dispensed - starts out     *  small and grows to simulate cash coming out of the machine     */    private Label label;}                                       