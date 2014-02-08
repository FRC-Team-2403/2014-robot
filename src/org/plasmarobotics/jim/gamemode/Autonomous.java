/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.gamemode;

import org.plasmarobotics.jim.Aimbot;
import org.plasmarobotics.jim.mechanisms.Drive;
import org.plasmarobotics.jim.mechanisms.MechanismPack;
import org.plasmarobotics.jim.mechanisms.Pickup;
import org.plasmarobotics.jim.mechanisms.Shoot;

/**
 * manages actions during autonomous mode
 * @author cathy 
 */
public class Autonomous {
    Drive drive;
    Shoot shooter;
    Pickup pickup;
    byte setting; //which autonomous?
    byte step;
    Aimbot aimbot;
    
    /**
     * creates an autonomous object that handles all things autonomous
     * @param mechanisms mechanismPack.getInstance()
     * 
     * @author Jim
     */
    public Autonomous(MechanismPack mechanisms){
        
        drive = mechanisms.getDrive();
        shooter = mechanisms.getShooter();
//        pickup = mechanisms.getPickup();
        
        drive.setupAutonomous();
        shooter.setupAutonomous();
//        pickup.setupAutonomous();
        
        step = 0;
        
        //get setting 
        
    }
    /**
     * prepares the robot for autonomous
     * @author Jim
     */
    public void autoInit(){
        drive.setupAutonomous();
        shooter.setupAutonomous();
//        pickup.setupAutonomous();
    }
    /**
     * This is the code that runs continously during auto
     */
    public void run(){
        
            
    }
    /**
     * moves 3 ft during autonomous
     * @author cathy
     */
    public void autoRunOne (){
        switch(step) {
            case 0:
                if(drive.drive(.3, 36))
                    step++;
                break;
            
            default:
                drive.drive(0, 0);
        }
    }
    /**
     * moves forward and shoots for the goal
     * @author cathy
     */
    public void autoRunTwo () {
        switch(step){
            case 0: 
                if(drive.drive(.3, 36))
                    step++;
                break;
            case 1:
                if (aimbot.aim())
                    step++;
                break;
            case 2:
                shooter.shoot(0);
                break;
                
            default:
                drive.drive(0, 0);
        }
    }
    /**
     * moves forward, shoots, then picks up ball to shoot again
     * @author cathy
     */
    public void autoRunThree () {
        switch(step) {
            case 0:
                //TODO: This distance MAY need to be adjusted based on the shooters ability 
                // to hit at that distance or not.  
                
                if (drive.drive(.3, 36))
                    step++;
                break;
            case 1:
                if (aimbot.aim())
                    step++;
                break;
            case 2:
                if (shooter.shoot(0))
                    step++;
                break;
            case 3:
                if (pickup.lower())
                    step++;
                break;
            case 4:
                if (pickup.forward())
                    step++;
                break;
            case 5:
                //TODO: This distance MAY need to be adjusted based on the shooters ability 
                // to hit at that distance or not.  
                if (drive.drive(.3, -42))
                    step++;
                break;
            case 6:
                if (aimbot.aim())
                    step++;
                break;
            case 7:
                if (shooter.shoot(0)){
                    pickup.stop();
                    step++;
                }
                break;
            default:
                drive.drive(0, 0);
                break;
                
        }
    }
    /**
     * turns, moves forward and shoots
     * @author cathy
     */
    public void autoRunFour () {
        switch(step){
            case 0:
                if (drive.turn(-90))
                    step++;
                break;
            case 1:
                if (drive.drive(.3, 36))
                    step++;
                break;
            case 2:
                if (aimbot.aim())
                    step++;
                break;
            case 3:
                if (shooter.shoot(0))
                    step++;
                break;
            default:
                drive.drive(0, 0);
                break;
            
        }
    }
    
    public void reset(){
        this.step = 0;
    }
    
    
}
