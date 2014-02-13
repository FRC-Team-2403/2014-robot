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
public class GhettoButton extends ToggleableButton {
    private boolean negativeEnd;
    
    /**
     * @param joystick The joystick
     * @param axisNumber Number corresponding to axis on joystick.
     * @param negativeEnd If axis value approaches -1 when target entity is active.
     **/
    public GhettoButton(GenericHID joystick, int axisNumber, boolean negativeEnd) {
        super(joystick, axisNumber);
        this.negativeEnd = negativeEnd;
    }
    public boolean get() {
        double axisValue = joystick.getRawAxis(axisNumber);
        return (negativeEnd) ? (axisValue < 0) : (axisValue > 0);
    }
}
