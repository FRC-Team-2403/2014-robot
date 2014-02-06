/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import org.plasmarobotics.jim.Constants;
import org.plasmarobotics.jim.RichardSimmons;
import org.plasmarobotics.jim.controls.ControlPack;

import org.plasmarobotics.jim.controls.ToggleableButton;
/**
 *Class to manage shooting functionality of the robot
 * @author Jim
 */
public class Shoot implements Mechanism{
    
    private static Victor frontShootVictor,
            backShootVictor;
    
    private static DoubleSolenoid shootSolenoid;
    
    private boolean motorsSpinning;
    
    private Joystick rightJoystick;
    private ToggleableButton shootBtn,
            motorToggle;
    
    /**
     * Sets up a RobotNameHereShoot object
     * @param rightStick Needed to bind the JoystickButton to shoot
     * @param RobotNameHere an instance of the main RobotNameHere class
     */
    public Shoot(ControlPack controls){
        
        this.rightJoystick = controls.getRightJoystick();

        this.shootSolenoid = new DoubleSolenoid(Constants.SHOOT_KICKER_FORWARD_CHANNEL, Constants.SHOOT_KICKER_REVERSE_CHANNEL);
        
        this.frontShootVictor = new Victor(Constants.FRONT_SHOOT_CHANNEL);
        this.backShootVictor = new Victor(Constants.BACK_SHOOT_CHANNEL);
        
        motorsSpinning = false;
        
        System.out.println("Shooter online");
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

    public void disable() {
        
    }

    public void setupAutonomous() {
        System.out.println("Shoot prepared for autonomous");
    }

    public void updateAutonomous() {
    }

    
    public void setupTeleop() {
        System.out.println("Shoot prepared for teleop");
    }

    public void updateTeleop(boolean useJoystick) {
        
    }

    
    
    
        
}
