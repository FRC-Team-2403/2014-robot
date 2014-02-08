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
        goalShot();
    }
    

    /**
     * Shooting method intended for hitting the goal
     * called once every cycle
     */
    private void goalShot(){
        SmartDashboard.putBoolean("Ball Loaded", !loadedSwitch.get());
        if (!loadedSwitch.get()){// if ball is there
            
 
            if(ControlPack.getInstance().getShootButton().isPressed()){
                leftSolenoid.set(DoubleSolenoid.Value.kForward);
                rightSolenoid.set(DoubleSolenoid.Value.kForward);
            } 

        }else{ //not pressed
                leftSolenoid.set(DoubleSolenoid.Value.kReverse);
                rightSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }
        
}
