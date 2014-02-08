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
    
    DigitalInput loadedSwitch = new DigitalInput(Constants.SHOOT_LOADED_CHANNEL);
    
    public Shoot(ControlPack controls) {
        this.controls = controls;
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
        if (ControlPack.getInstance().getShootButton().isPressed()) {
            shoot(0);
        }
        
    }
    
    /**
     * Shoots the ball for different modes
     * @param mode to shoot the ball (0 = goal shot, 1 = truss shot, 2 = pass)
     * @return true when the shot is complete, false if no ball or shot not complete
     */
    public boolean shoot(int mode){
        long waitTime = 0;
        
        if (mode == 0) {
            waitTime = Constants.GOAL_SHOT_WAIT_TIME;
        } else if (mode == 1) {
            waitTime = Constants.TRUSS_SHOT_WAIT_TIME;
        } else if (mode == 2) {
            waitTime = Constants.PASS_WAIT_TIME;
        } else {
            return false;
        }
        
        if (loadedSwitch.get()) { //not loaded
            return false;
        } else {
            leftSolenoid.set(DoubleSolenoid.Value.kForward);
            rightSolenoid.set(DoubleSolenoid.Value.kForward);
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            leftSolenoid.set(DoubleSolenoid.Value.kReverse);
            rightSolenoid.set(DoubleSolenoid.Value.kReverse);
            return true;
        }
        
    }
        
}
