/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.plasmarobotics.jim.Constants;

/**
 * Class to hold all controls of the robot
 * @author Jim
 */
public final class ControlPack {
    private static ControlPack controlPack = null;
    
    //controllers
    private PlasmaJoystick rightJoystick = new PlasmaJoystick(Constants.LEFT_STICK_PORT);
    private PlasmaJoystick leftJoystick = new PlasmaJoystick(Constants.RIGHT_STICK_PORT);
    private PlasmaGamepad gamepad = new PlasmaGamepad(Constants.GAMEPAD_PORT);
    
    /**
     * prevents instantiation
     */
    private ControlPack(){
        
    }
    
    //create switches for the Driver Station

    /**
     * @return the rightJoystick
     */
    public PlasmaJoystick getRightJoystick() {
        return rightJoystick;
    }

    /**
     * @return the leftJoystick
     */
    public PlasmaJoystick getLeftJoystick() {
        return leftJoystick;
    }

    /**
     * @return the gamepad
     */
    public PlasmaGamepad getGamepad() {
        return gamepad;
    }
    
    public ToggleableButton getAimbotButton(){
        if(Constants.USE_JOYSTICK)
            return getLeftJoystick().getTriggerButton();
        else
            return getGamepad().getLeftBumper();
    }
    
    public ToggleableButton getShootButton(){
        if(Constants.USE_JOYSTICK)
            return getRightJoystick().getTriggerButton();
        else
            return getGamepad().getRightBumper();
    }

    public static ControlPack getInstance(){
        if(controlPack == null){
            controlPack = new ControlPack();
        }
        return controlPack;
    }
}