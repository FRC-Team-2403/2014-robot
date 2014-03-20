/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.controls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.plasmarobotics.jim.Channels;

/**
 * Class to hold all controls of the robot
 * @author Jim
 */
public final class ControlPack {
    public static final boolean USE_JOYSTICK = false;
    private static ControlPack controlPack = null;
    
    //controllers
    private PlasmaJoystick rightJoystick = new PlasmaJoystick(Channels.LEFT_STICK_PORT);
    private PlasmaJoystick leftJoystick = new PlasmaJoystick(Channels.RIGHT_STICK_PORT);
    private PlasmaGamepad gamepad = new PlasmaGamepad(Channels.GAMEPAD_PORT);
    
    /*
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *ROBOT CONTROLS
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Forwards/Backwards -- left stick
    * Turning -- right stick
    * Aimbot -- LB
    * Shoot -- RB
    * Pickup intake -- RT
    * Pickup outtake -- LT
    * Toggle shooting mode -- Y
    * Raise pickup -- B
    * Lower pickup -- A
    * Open catch -- X
    */
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
    
    /**
     * @return Button to activate aimbot
     */
    public ToggleableButton getAimbotButton(){
        if(USE_JOYSTICK)
            return getLeftJoystick().getTriggerButton();
        else
            return getGamepad().getLeftBumper();
    }
    
    /**
     * 
     * @return Button to activate the shooter
     */
    public ToggleableButton getShootButton(){
        if(USE_JOYSTICK)
            return getRightJoystick().getTriggerButton();
        else
            return getGamepad().getRightBumper();
    }
    
    /**
     * 
     * @return button to toggle raised state of the pickup
     */
    public ToggleableButton getRaiseLowerShooterButton(){ //R5 is labeled raise for pickup on board :(
        if(USE_JOYSTICK)
            return getRightJoystick().getFive();
        else
            return getGamepad().getRightBumper();
        
    }

    /**
     * 
     * @return button to toggle shooting mode of the robot
     */
    public ToggleableButton getToggleShootButton () { //toggles shoot (?)
        if (USE_JOYSTICK)
            return getLeftJoystick().getThree();
        else 
            return getGamepad().getYButton(); //(?)
    }
    
    public ToggleableButton getFowardPickUpButton () {
        if (USE_JOYSTICK)
            return getRightJoystick().getThree();
        else
            return getGamepad().getXButton();
    }
    
    public ToggleableButton getBackwardPickUpButton () {
        if (USE_JOYSTICK)
            return getRightJoystick().getTwo();
        else 
            return getGamepad().getBButton();
    }
    
    public ToggleableButton getCatchButton(){
        return gamepad.getXButton();
    }
    public static ControlPack getInstance(){
        if(controlPack == null){
            controlPack = new ControlPack();
        }
        return controlPack;
    }
       
}