/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.plasmarobotics.jim;

import org.plasmarobotics.jim.mechanisms.Drive;
import org.plasmarobotics.jim.mechanisms.MechanismPack;
import org.plasmarobotics.jim.sensors.PlasmaGyro;
import org.plasmarobotics.jim.sensors.SensorPack;
import org.plasmarobotics.jim.sensors.SonicRange;

/**
 * gets robot in position to shoot
 * @author jim
 */
public class Aimbot {
    private SonicRange rangeFinder;
    private PlasmaGyro gyro; //unique to Aimbot
    
    private Drive drive;
    private byte step = 0; //iteration through process
    
    /**
     * Puts the robot in the perfect place for shooting
     * @return true when it is in position
     */
    public boolean aim(){
        switch(step){
            case 0:
                System.out.println("Facing wall...");
                step++;
                break;
                
            case 1:
                if(faceWall())
                    step++;
                break;
                
            case 2:
                System.out.println("Getting range...");
                step++;
                break;
                
            case 3:
                if(this.setRange())
                    step++;
                break;
                
            case 4:
                
                System.out.println("Ready to shoot.");
                return true;
                
            default:
                System.err.println("STATE MACHINE BROKE IN AIMBOT");
        }
        return false;
        
    }
    
    /**
     * Creates a new instance of AimBot
     * @param sensors sensor pack used with the rest of robot
     * @param mechanisms mechanism pack used with rest of robot
     */
    public Aimbot(SensorPack sensors, MechanismPack mechanisms){
        rangeFinder = sensors.getRangeFinder();
        drive = mechanisms.getDrive();
        
        System.out.println("Aimbot online");
        //unique to the robot, cannot be reset every action in auto
        gyro = sensors.getGyro();
    }
    
    /**
     * Orients the robot towards the wall
     * @return true when action is complete
     */
    public boolean faceWall(){
        System.out.println("Zero: " + gyro.getZero());
        System.out.println("Angle: " + gyro.getAbsoluteAngle());
        return drive.turn(gyro.getZero());
    }
    
    /**
     * Gets robot to perfect range off the wall
     * @return true when action complete. false when not in range
     */
    public boolean setRange(){
        System.out.println("Range: " + rangeFinder.getDistance());
        if(rangeFinder.isClose(Constants.SHOOT_RANGE, Constants.SHOOT_TOLERANCE)){
            drive.drive(0, 0);
            return true;
        }
            
        
        //TODO: Calculate distances to travel
        if(rangeFinder.getDistance() - Constants.SHOOT_RANGE > 0){//robot is to far
            drive.drive(-.3, 12*4);//drive backwards
            System.out.println("Driving forwards");
            
            
        } else { //robot is too close
            drive.drive(.3, 120); //drive backwards
            System.out.println("Driving reverse");
        }
        
        return false;
    }
    
    /**
     * resets the aimbot
     */
    public void reset(){
        this.step = 0;
    }
}
