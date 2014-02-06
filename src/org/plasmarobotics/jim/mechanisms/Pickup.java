/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.sensors.SensorPack;

/**
 * This class handles pickup operations of the robot
 * @author bryce
 */
public class Pickup implements Mechanism{
    /**
     * all final ports need to be added to updated Constants class after testing
     * on new robot
     */
    
    //victor motor controlers, reset ports
    Victor roller1 = new Victor(1,3);
    Victor roller2 = new Victor(1,4);
    Victor liftesc = new Victor(1,5);
    //check port for limit switchs | digital IO
    DigitalInput liftlimit1 = new DigitalInput(5);
    DigitalInput liftlimit2 = new DigitalInput(6);
    //for possible encoders
    
    public Pickup(ControlPack controls, SensorPack sensors){
        
        System.out.println("Pickup online");
    }
    /**
     * raise the pickup mechanism
     * @return when the action is completed
     */
    public boolean raise(){
        //raises pickup untill trigering limit switch
        if (liftlimit1.get() == false){
            liftesc.set(1);
        }else{
            liftesc.set(0);
        }
        return true;
    }
    
    /**
     * lower the pickup mechanism
     * @return when the action is completed
     */
    public boolean lower(){
        if (liftlimit2.get() == false){
            liftesc.set(-1);
        }else{
            liftesc.set(0);
        }
        return true;
    }
    
    /**
     * pickup the ball
     * @return when the action is completed
     */
    public boolean forward(){
        roller1.set(1);
        roller2.set(1);
        return true;
    }
    
    /**
     * stop the pickup
     * @return when the action is completed
     */
    public boolean stop(){
        roller1.set(0);
        roller2.set(0);
        return true;
    }
    
    /**
     * reverse the pickup 
     * @return when the action is completed
     */
    public boolean reverse(){
        roller1.set(-1);
        roller2.set(-1);
        return true;
    }

    public void disable() {
        //stops pickup rollers for rest of match
        final int stop = 0;
        roller1.set(stop);
        roller2.set(stop);
    }

    public void setupAutonomous() {
        System.out.println("Pickup prepared for autonomous");
        //preps pickup
    }

    public void updateAutonomous() {
        //updates stuff durring auto e.g. sensor feeds
    }
    
    
    

    public void setupTeleop() {
        System.out.println("Pickup prepared for teleop");
        //preps pickup
    }

    public void updateTeleop(boolean useJoystick) {
       
    }

   

    

    
    
    
    
    
    
    
    
}
