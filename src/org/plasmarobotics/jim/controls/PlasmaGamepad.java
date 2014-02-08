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
    /**
     * Get left joystick button (pressed in or not).
     **/
    public ToggleableButton getLeftJoystickButton() {
        return this.leftJoystickButton;
    }
    /**
     * Get right joystick button (pressed in or not).
     **/
    public ToggleableButton getRightJoystickButton() {
        return this.rightJoystickButton;
    }
    
    /**
     * Get left joystick x-axis (left-right).
     * Full left = -1, full right = +1.
     **/
    public double getLeftJoystickXAxis() {
        return super.getRawAxis(1);
    }
    /**
     * Get left joystick y-axis (up-down).
     * Full up = -1, full down = +1.
     **/
    public double getLeftJoystickYAxis() {
        return super.getRawAxis(2);
    }
    /**
     * Get right joystick x-axis (left-right).
     * Full left = -1, full right = +1.
     **/
    public double getRightJoystickXAxis() {
        return super.getRawAxis(4);
    }
    /**
     * Get right joystick y-axis (up-down).
     * Full up = -1, full down = +1.
     **/
    public double getRightJoystickYAxis() {
        return super.getRawAxis(5);
    }
    /**
     * Get trigger axis.
     * Right depressed -> -1
     * Left depressed -> +1
     * Both fully depressed = 0
     **/
    public double getTriggerAxis() {
        return super.getRawAxis(3);
    }
    
    
}
