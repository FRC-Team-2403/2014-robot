/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.sensors.SensorPack;

/**
 * This class handles pickup operations of the robot
 * @author Jim
 */
public class Pickup implements Mechanism{
    
    public Pickup(ControlPack controls, SensorPack sensors){
        
        System.out.println("Pickup online");
    }
    /**
     * raise the pickup mechanism
     * @return when the action is completed
     */
    public boolean raise(){
        return true;
    }
    
    /**
     * lower the pickup mechanism
     * @return when the action is completed
     */
    public boolean lower(){
        return true;
    }
    
    /**
     * pickup the ball
     * @return when the action is completed
     */
    public boolean forward(){
        return true;
    }
    
    /**
     * stop the pickup
     * @return when the action is completed
     */
    public boolean stop(){
        return true;
    }
    
    /**
     * reverse the pickup 
     * @return when the action is completed
     */
    public boolean reverse(){
        return true;
    }

    public void disable() {
        
    }

    public void setupAutonomous() {
        
    }

    public void updateAutonomous() {
    
    }
    
    
    

    public void setupTeleop() {
        
    }

    public void updateTeleop() {
        
    }

    

    
    
    
    
    
    
    
    
}
