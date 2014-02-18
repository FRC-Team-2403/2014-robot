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
    
    boolean shoot = false;//allows for only one execution of shoot
    int shootingMode = 0;//0=high, 1=truss, 2=pass
    int step = 0;//step in the state machine
    long timeToWait; //how long to sleep the thread in truss and pass shots
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
        if (controls.getShootButton().isPressed()) {
            shoot = true;
            System.out.println("shoot");
        }
        
        if(controls.getToggleShootButton().isPressed()){
            toggleShootMode();
        }
            
        if(controls.getGamepad().getXButton().isPressed()){
            retract();
        }
        
        if(shoot){
            shoot(shootingMode);
        }
       
        
        
        
    }
    
    /**
     * Shoots the ball for different modes
     * @param mode to shoot the ball (0 = goal shot, 1 = truss shot, 2 = pass)
     * @return true when the shot is complete, false if no ball or shot not complete
     */
    public boolean shoot(int mode){
        shootingMode = mode;
        


        switch(step){
            case 0:
                if(!loadedSwitch.get()){
                    leftSolenoid.set(DoubleSolenoid.Value.kForward);
                    rightSolenoid.set(DoubleSolenoid.Value.kForward);
                    step++;    
                } else{
                    shoot = false;
                    return false;
                }

                break;

            case 1:
                if(shootingMode != 0){
                    try{
                        Thread.sleep(timeToWait);
                        System.out.println("Sleeping");
                    }catch(Exception e){

                    }
                    step++;
                }


                if(!pistonHalf.get())
                    step++;


                break;

            case 2:
                leftSolenoid.set(DoubleSolenoid.Value.kReverse);
                rightSolenoid.set(DoubleSolenoid.Value.kReverse);
                step++;
                break;

            default:

                step = 0;
                shoot = false;
                return true;


        }

       
        return false;
        
    }
     
    private void retract(){
        leftSolenoid.set(DoubleSolenoid.Value.kReverse);
        rightSolenoid.set(DoubleSolenoid.Value.kReverse);
        step = 0;
        
    }

    private void toggleShootMode(){
        System.out.println("Changing shooting mode");
        if(shootingMode == 2)
            shootingMode = -1;
        
        shootingMode += 1;
        
        if(shootingMode == 1)
            timeToWait = Constants.TRUSS_SHOT_WAIT_TIME;
        
        if(shootingMode == 2)
            timeToWait = Constants.PASS_WAIT_TIME;
                
        SmartDashboard.putNumber("shooting mode", shootingMode);
    }
   
    
    
}
