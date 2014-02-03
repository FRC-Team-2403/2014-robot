/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.controls;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Allek
 */
public class PlasmaGamepad extends Joystick {
    
    private ToggleableButton aButton = new ToggleableButton(this, 1),
            bButton = new ToggleableButton(this, 2),
            xButton = new ToggleableButton(this, 3),
            yButton = new ToggleableButton(this, 4),
            leftBumper = new ToggleableButton(this, 5),
            rightBumper = new ToggleableButton(this, 6),
            backButton = new ToggleableButton(this, 7),
            startButton = new ToggleableButton(this, 8),
            leftJoystickButton = new ToggleableButton(this, 9),
            rightJoystickButton = new ToggleableButton(this, 10);
            
    
    public PlasmaGamepad(int port) {
        super(port);
    }
    
    public ToggleableButton getAButton() {
        return this.aButton;
    }
    public ToggleableButton getBButton() {
        return this.bButton;
    }
    public ToggleableButton getXButton() {
        return this.xButton;
    }
    public ToggleableButton getYButton() {
        return this.yButton;
    }
    public ToggleableButton getLeftBumper() {
        return this.leftBumper;
    }
    public ToggleableButton getRightBumper() {
        return this.rightBumper;
    }
    public ToggleableButton getBackButton() {
        return this.backButton;
    }
    public ToggleableButton getStartButton() {
        return this.startButton;
    }
    public ToggleableButton getLeftJoystickButton() {
        return this.leftJoystickButton;
    }
    public ToggleableButton getRightJoystickButton() {
        return this.rightJoystickButton;
    }
}
