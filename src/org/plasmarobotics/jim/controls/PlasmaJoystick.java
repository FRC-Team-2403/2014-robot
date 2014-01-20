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
    
    ToggleableButton triggerButton = new ToggleableButton(this, 1);
    ToggleableButton two = new ToggleableButton(this, 2);
    ToggleableButton three = new ToggleableButton(this, 3);
    ToggleableButton four = new ToggleableButton(this, 4);
    ToggleableButton five = new ToggleableButton(this, 5);
    ToggleableButton six = new ToggleableButton(this, 6);
    ToggleableButton seven = new ToggleableButton(this, 7);
    ToggleableButton eight = new ToggleableButton(this, 8);
    ToggleableButton nine = new ToggleableButton(this, 9);
    ToggleableButton ten = new ToggleableButton(this, 10);
    ToggleableButton eleven = new ToggleableButton(this, 11);
    
    public PlasmaJoystick(int port) {
        super(port);
    }

    
   
    
}
