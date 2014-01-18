/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.manipulator;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import org.plasmarobotics.jim.Controls;
import org.plasmarobotics.jim.RobotNameHere;

import org.plasmarobotics.jim.sensors.ToggleableButton;
/**
 *Class to manage shooting functionality of the robot
 * @author Jim
 */
public class Shoot {
    
    private static Victor frontShootVictor,
            backShootVictor;
    
    private static DoubleSolenoid shootSolenoid;
    
    private boolean motorsSpinning;
    
    private Joystick rightJoystick;
    private ToggleableButton shootBtn,
            motorToggle;
    
    /**
     * Sets up a froboShoot object
     * @param rightStick Needed to bind the JoystickButton to shoot
     * @param frobo an instance of the main frobo class
     */
    public Shoot(Joystick rightStick, RobotNameHere frobo){
        
        this.rightJoystick = frobo.getRightJoystick();

        this.shootBtn = new ToggleableButton(rightStick, Constants.SHOOT_TRIGGER_BUTTON);

        
        this.shootBtn = new ToggleableButton(rightStick, Constants.JOYSTICK_TRIGGER_BUTTON);
        this.motorToggle = new ToggleableButton(rightStick, Constants.JOYSTICK_MIDDLE_THUMB_BUTTON);

        
        this.shootSolenoid = new DoubleSolenoid(Constants.SHOOT_KICKER_FORWARD_CHANNEL, Constants.SHOOT_KICKER_REVERSE_CHANNEL);
        
        this.frontShootVictor = new Victor(Constants.FRONT_SHOOT_CHANNEL);
        this.backShootVictor = new Victor(Constants.BACK_SHOOT_CHANNEL);
        
        motorsSpinning = false;
    }
    
    /**
     * Called by teleopPeriodic() in main class
     */
    public void update(){
       if(shootBtn.isPressed()){
           shootSolenoid.set(DoubleSolenoid.Value.kForward);
       } else{
           shootSolenoid.set(DoubleSolenoid.Value.kReverse);
       }
       
       refreshMotors(motorToggle);
       
    }
  
    /**
     * Toggles whether or not the shoot motors are turning
     */
    private void refreshMotors(ToggleableButton button){
        
        if(button.isPressed()){
           motorsSpinning = !motorsSpinning;
        }
        
        if(motorsSpinning){
            frontShootVictor.set(Constants.FRONT_SHOOT_MOTOR_SPEED);
            backShootVictor.set(Constants.BACK_SHOOT_MOTOR_SPEED);
        } else{
            frontShootVictor.set(0);
            backShootVictor.set(0);
        }
    }
    
        
}
