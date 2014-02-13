/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.plasmarobotics.jim.Constants;
import org.plasmarobotics.jim.Logger;
import org.plasmarobotics.jim.controls.ControlPack;
/**
 *Class to manage shooting functionality of the robot
 * @author Jim
 */
public class Shoot implements Mechanism{

    DoubleSolenoid leftSolenoid,
            rightSolenoid;

    ControlPack controls;
    boolean goalShoot = true; //if shooting for goal
    
    int step = 0;
    
    DigitalInput loadedSwitch = new DigitalInput(Constants.SHOOT_LOADED_CHANNEL);
    DigitalInput pistonHalf = new DigitalInput(6);
    
    public Shoot(ControlPack controls) {
        this.controls = controls;
        leftSolenoid = new DoubleSolenoid(Constants.LEFT_SOLENOID_FORWARD_CHANNEL, Constants.LEFT_SOLENOID_REVERSE_CHANNEL);
        rightSolenoid = new DoubleSolenoid(Constants.RIGHT_SOLENOID_FORWARD_CHANNEL, Constants.RIGHT_SOLENOID_REVERSE_CHANNEL);
    }
        
    public void disable() {
        Logger.log("disabled", this, 5);
    }

    public void setupAutonomous() {
        Logger.log("setup for autonomous", this, 5);

    }

    public void setupTeleop() {
        Logger.log("setup for teleop", this, 5);
    }

    public void updateTeleop() {
        SmartDashboard.putBoolean("Ball Loaded", !loadedSwitch.get());
        if (ControlPack.getInstance().getShootButton().get()) {
            shoot(0);
        }
        
        if(ControlPack.getInstance().getGamepad().getXButton().isPressed()){
            retract();
        }
        
        
    }
    
    /**
     * Shoots the ball for different modes
     * @param mode to shoot the ball (0 = goal shot, 1 = truss shot, 2 = pass)
     * @return true when the shot is complete, false if no ball or shot not complete
     */
    public boolean shoot(int mode){
        
        
        if (loadedSwitch.get()) { //not loaded
            return false;
        } else {
          switch(step){
              case 0:
                  leftSolenoid.set(DoubleSolenoid.Value.kForward);
                  rightSolenoid.set(DoubleSolenoid.Value.kForward);
                  step++;
                  break;
                  
              case 1:
                  if(!pistonHalf.get())
                      step++;
                  break;
                  
              case 2:
                  leftSolenoid.set(DoubleSolenoid.Value.kReverse);
                  rightSolenoid.set(DoubleSolenoid.Value.kReverse);
                  break;
                  
              default:
                  
                  return true;
          }
        }
        return false;
        
    }
     
    private void retract(){
        leftSolenoid.set(DoubleSolenoid.Value.kReverse);
        rightSolenoid.set(DoubleSolenoid.Value.kReverse);
        step = 0;
        
    }
}
