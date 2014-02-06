/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import edu.wpi.first.wpilibj.DoubleSolenoid;
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

    public void updateTeleop(boolean useJoystick) {
        goalShot();
    }
    

    /**
     * Shooting method intended for hitting the goal
     * called once every cycle
     */
    private void goalShot(){
        if(Constants.USE_JOYSTICK){ //joystick controls
            
            if(controls.getRightJoystick().getTriggerButton().get()){ //right trigger button pressed
                leftSolenoid.set(DoubleSolenoid.Value.kForward);
                rightSolenoid.set(DoubleSolenoid.Value.kForward);
            } else{ //not pressed
                leftSolenoid.set(DoubleSolenoid.Value.kReverse);
                rightSolenoid.set(DoubleSolenoid.Value.kReverse);
            }
        } else{//gamepad controls
            
            if(controls.getGamepad().getRightBumper().get()){//buttons pressed
                leftSolenoid.set(DoubleSolenoid.Value.kForward);
                rightSolenoid.set(DoubleSolenoid.Value.kForward);
                
            } else{//not pressed
                
                leftSolenoid.set(DoubleSolenoid.Value.kReverse);
                rightSolenoid.set(DoubleSolenoid.Value.kReverse);
            }
        }
    }
        
}
