/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.plasmarobotics.jim.Channels;
import org.plasmarobotics.jim.Logger;
import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.sensors.SensorPack;
/**
 *Class to manage shooting functionality of the robot
 * @author Jim
 */
public class Shoot implements Mechanism{
    //shooter wait times
    private static final long TRUSS_SHOT_WAIT_TIME = 100;
    private static final long GOAL_SHOT_WAIT_TIME = 1000;
    private static final long PASS_WAIT_TIME = 70;
    
//    private boolean switchOverride;
    private DoubleSolenoid leftSolenoid,
            rightSolenoid;

    private boolean goalShoot = true; //if shooting for goal
    
    private boolean shoot = false;//allows for only one execution of shoot
    private int shootingMode = 0;//0=high, 2=pass
    private int step = 0;//step in the state machine
    private long timeToWait; //how long to sleep the thread in truss and pass shots
//    private DigitalInput loadedSwitch = new DigitalInput(Channels.SHOOT_LOADED_CHANNEL);
//    private DigitalInput pistonHalf = new DigitalInput(6);
    
    /**
     * Creates a shoot object to control shooting aspects of the robot
     */
    public Shoot() {
        leftSolenoid = new DoubleSolenoid(Channels.LEFT_SOLENOID_FORWARD_CHANNEL, Channels.LEFT_SOLENOID_REVERSE_CHANNEL);
        rightSolenoid = new DoubleSolenoid(Channels.RIGHT_SOLENOID_FORWARD_CHANNEL, Channels.RIGHT_SOLENOID_REVERSE_CHANNEL);
        
        
    }
        
    /**
     * Only to be used when shit goes down
     */
    public void disable() {
        retract();
        Logger.log("disabled", this, 5);
    }

    public void setupAutonomous() {
        Logger.log("setup for autonomous", this, 5);

    }

    public void setupTeleop() {
        Logger.log("setup for teleop", this, 5);
    }

    public void updateTeleop() {
//        switchOverride = DriverStation.getInstance().getDigitalIn(5);
//        SmartDashboard.putBoolean("Ball Loaded:", !loadedSwitch.get());
//        SmartDashboard.putBoolean("Switch override",!switchOverride);
        if (ControlPack.getInstance().getShootButton().isPressed()) {
            shoot = true;
            System.out.println("shoot");
        }
        
        //should never run
        if(ControlPack.getInstance().getToggleShootButton().isPressed()){
            toggleShootMode();
        }
            
        if(ControlPack.getInstance().getGamepad().getXButton().isPressed()){
            retract();
        }
        
        if(shoot){
            if(ControlPack.getInstance().getShiftTrigger().get())
                shoot(1);
            else
               shoot(0);
            
        }
        
    }
    
    /**
     * Shoots the ball for different modes
     * @param mode to shoot the ball (0 = goal shot, 1 = truss shot, 2 = pass)
     * @return true when the shot is complete, false if no ball or shot not complete
     */
    //TODO: System.currentTimeMilis()
    public boolean shoot(int mode){
        
        if(mode == 0)
            timeToWait = GOAL_SHOT_WAIT_TIME;
        else
            timeToWait = PASS_WAIT_TIME;
       
        switch(step){
            //shoot ball out
            case 0:
//                if(!loadedSwitch.get() || !switchOverride){
                    leftSolenoid.set(DoubleSolenoid.Value.kForward);
                    rightSolenoid.set(DoubleSolenoid.Value.kForward);
                    step++;    
//                } else{
//                    shoot = false;
//                    return false;
//                }
//
                break;

                   //sleep thread
            case 1:
//                if(shootingMode != 0){
                
                
                    try{
                        Thread.sleep(timeToWait);
                        System.out.println("Sleeping in method shoot()");
                    }catch(InterruptedException e){
                        System.out.println("HOLY SHIT THREAD.SLEEP FAILED");
                    }
                    step++;
                


//                if(!pistonHalf.get())
//                    step++;
                break;
                //pull back
            case 2:
                retract();
                shoot = false;
                return true;
                

            default:
                System.err.println("shoot() switch statement derailed");
                step = 0;
                shoot = false;
                return true;

        }
       
        return false;
        
    }
     
    /**
     * Retracts the shooter and resets the state machine
     */
    public void retract(){
        leftSolenoid.set(DoubleSolenoid.Value.kReverse);
        rightSolenoid.set(DoubleSolenoid.Value.kReverse);
        step = 0;
        
    }

    /**
     * Toggles the robot between the three shooting modes
     * @deprecated 
     */
    private void toggleShootMode(){
        System.out.println("Changing shooting mode");
        if(shootingMode == 0)
            shootingMode = 1;
        else 
            shootingMode = 0;
                
        String[] shootingModes = {"Goal", "pass"};
        SmartDashboard.putString("Shooting Mode:", shootingModes[shootingMode] + " shot");
    }
    
}
