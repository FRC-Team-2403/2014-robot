/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.plasmarobotics.jim.Channels;
import org.plasmarobotics.jim.Logger;
import org.plasmarobotics.jim.controls.ControlPack;
/**
 *Class to manage shooting functionality of the robot
 * @author Jim
 */
public class Shoot implements Mechanism{
    //shooter wait times
    private static final long TRUSS_SHOT_WAIT_TIME = 100;
    private static final long GOAL_SHOT_WAIT_TIME = 1000;
    private static final long PASS_WAIT_TIME = 60;
    
    private DoubleSolenoid leftSolenoid,
            rightSolenoid;

    private boolean goalShoot = true; //if shooting for goal
    
    private boolean shoot = false;//allows for only one execution of shoot
    private int shootingMode = 0;//0=high, 1=truss, 2=pass
    private int step = 0;//step in the state machine
    private long timeToWait; //how long to sleep the thread in truss and pass shots
    private DigitalInput loadedSwitch = new DigitalInput(Channels.SHOOT_LOADED_CHANNEL);
    private DigitalInput pistonHalf = new DigitalInput(6);
    
    /**
     * Creates a shoot object to control shooting aspects of the robot
     */
    public Shoot() {
        leftSolenoid = new DoubleSolenoid(Channels.LEFT_SOLENOID_FORWARD_CHANNEL, Channels.LEFT_SOLENOID_REVERSE_CHANNEL);
        rightSolenoid = new DoubleSolenoid(Channels.RIGHT_SOLENOID_FORWARD_CHANNEL, Channels.RIGHT_SOLENOID_REVERSE_CHANNEL);
        
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
            shoot = true;
            System.out.println("shoot");
        }
        
        if(ControlPack.getInstance().getToggleShootButton().isPressed()){
            toggleShootMode();
        }
            
        if(ControlPack.getInstance().getGamepad().getXButton().isPressed()){
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
            timeToWait = TRUSS_SHOT_WAIT_TIME;
        
        if(shootingMode == 2)
            timeToWait = PASS_WAIT_TIME;
                
        SmartDashboard.putNumber("shooting mode", shootingMode);
    }
    
}
