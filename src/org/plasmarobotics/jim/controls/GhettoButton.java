/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.plasmarobotics.jim.controls;

import edu.wpi.first.wpilibj.GenericHID;

/**
 * Attempted emulation of ToggleableButton, used for PlamsaGamepad
 * triggers and D-Pad. Instead of calling GenericHID.getRawButton to
 * provide output for get(), calls GenericHID.getRawAxis and calculates
 * boolean value based its return.
 * @author Allek
 */
public class GhettoButton {
    private final GenericHID joystick;
    private final int axisNumber;
    private boolean isHeld = false;
    private final boolean negativeEnd;
    
    /**
     * @param joystick The joystick
     * @param axisNumber Number corresponding to axis on joystick.
     * @param negativeEnd If axis value approaches -1 when target entity is active.
     **/
    public GhettoButton(GenericHID joystick, int axisNumber, boolean negativeEnd) {
        this.joystick = joystick;
        this.axisNumber = axisNumber;
        this.negativeEnd = negativeEnd;
    }
    
    /**
     * Check if button is pressed.
     * @return If button is pressed
     **/
    public boolean get() {
        double axisValue = joystick.getRawAxis(this.axisNumber);
        return (negativeEnd) ? (axisValue < 0) : (axisValue > 0);
    }
    
    /**
     * [GhettoButton implementation]
     * Prevents the button from creating infinite loops
     * @return True for one time that the button is pressed
     */
    public boolean isPressed() {
        if (this.get()) {
            if(!isHeld)
                isHeld = true;
        } else
            isHeld = false;
        
        return isHeld;
    }
    
    /**
     * [GhettoButton implementation]
     * Prevents the button from creating infinite loops
     * @return True only when the button is released
     */
    public boolean isReleased(){
        if (this.get()) {
            this.isHeld = true;
            return false;
        } else if (this.isHeld) {
            this.isHeld = false;
            return true;
        }
        
        System.out.println("isReleased in GhettoButton has returned false unexpectedly");
        return false;
    }
}
