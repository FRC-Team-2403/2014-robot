/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.gamemode;

import org.plasmarobotics.jim.Logger;
import org.plasmarobotics.jim.mechanisms.Drive;
import org.plasmarobotics.jim.mechanisms.MechanismPack;
import org.plasmarobotics.jim.mechanisms.Pickup;
import org.plasmarobotics.jim.mechanisms.Shoot;

/**
 *
 * @author Jim
 */
public class Autonomous {
    Drive drive;
    Shoot shooter;
    Pickup pickup;
    byte setting; //which autonomous?
    byte step;
    
    
    public Autonomous(MechanismPack mechanisms){
        
        drive = mechanisms.getDrive();
        shooter = mechanisms.getShooter();
        pickup = mechanisms.getPickup();
        
        drive.setupAutonomous();
        shooter.setupAutonomous();
        pickup.setupAutonomous();
        
        step = 0;
        
        //get setting 
        
    }
    
    public void autoInit(){
        drive.setupAutonomous();
        shooter.setupAutonomous();
        pickup.setupAutonomous();
    }
    /**
     * This is the code that runs continously during auto
     */
    public void run(){
        switch(step){
            case 0:
                if(drive.turn(-90))
                    Logger.log("turn complete", this, 4);
                
                break;
                
            case 1:
                
                
                break;
                
            case 2:
                if(drive.drive(.5, 12 * 5))
                    step++;
                break;
                
            default:
                drive.drive(0, 0); //stops the robot
                break;
                
                
        }
            
    }
    
    public void reset(){
        this.step = 0;
    }
    
    
}
