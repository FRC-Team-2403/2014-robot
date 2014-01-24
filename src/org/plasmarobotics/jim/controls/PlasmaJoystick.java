/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.controls;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Modified Joystick class with buttons 
 * @author Jim
 */
public class PlasmaJoystick extends Joystick{
    
    private ToggleableButton triggerButton = new ToggleableButton(this, 1);
    private ToggleableButton two = new ToggleableButton(this, 2);
    private ToggleableButton three = new ToggleableButton(this, 3);
    private ToggleableButton four = new ToggleableButton(this, 4);
    private ToggleableButton five = new ToggleableButton(this, 5);
    private ToggleableButton six = new ToggleableButton(this, 6);
    private ToggleableButton seven = new ToggleableButton(this, 7);
    private ToggleableButton eight = new ToggleableButton(this, 8);
    private ToggleableButton nine = new ToggleableButton(this, 9);
    private ToggleableButton ten = new ToggleableButton(this, 10);
    private ToggleableButton eleven = new ToggleableButton(this, 11);
    
    public PlasmaJoystick(int port) {
        super(port);
    }

    /**
     * @return the triggerButton
     */
    public ToggleableButton getTriggerButton() {
        return triggerButton;
    }

    /**
     * @return the two
     */
    public ToggleableButton getTwo() {
        return two;
    }

    /**
     * @return the three
     */
    public ToggleableButton getThree() {
        return three;
    }

    /**
     * @return the four
     */
    public ToggleableButton getFour() {
        return four;
    }

    /**
     * @return the five
     */
    public ToggleableButton getFive() {
        return five;
    }

    /**
     * @return the six
     */
    public ToggleableButton getSix() {
        return six;
    }

    /**
     * @return the seven
     */
    public ToggleableButton getSeven() {
        return seven;
    }

    /**
     * @return the eight
     */
    public ToggleableButton getEight() {
        return eight;
    }

    /**
     * @return the nine
     */
    public ToggleableButton getNine() {
        return nine;
    }

    /**
     * @return the ten
     */
    public ToggleableButton getTen() {
        return ten;
    }

    /**
     * @return the eleven
     */
    public ToggleableButton getEleven() {
        return eleven;
    }

    
   
    
}
