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
    
    private static final double DISTANCE_TO_SHOOT = 40,//TODO: adjust
            TOLERANCE_FOR_SHOT = 5;
    
    private SonicRange rangeFinder;
    private PlasmaGyro gyro; //unique to Aimbot
    
    private Drive drive;
    private byte step = 0; //iteration through process
    
    /**
     * Puts the robot in the perfect place for shooting
     * @param mode 0 for teleop 1 for auto
     * @return true when it is in position
     */
    public boolean aim(int mode){
        switch(step){
            case 0: // nothing yet
                Logger.log("Facing wall...", this, 4);
                step++;
                break;
                
            case 1: // nothing yet
//                if(faceWall())
//                    step++;
                step++;
                break;
                
            case 2:
                Logger.log("Setting Range...", this, 4);
                step++;
                break;
                
            case 3:
                if(this.setRange(mode))
                    step++;
                break;
                
            case 4:
                
                Logger.log("Ready to shoot", this, 4);
                return true;
                
            default:
                Logger.log("STATE MACHINE IS BROKEN ON AIMBOT", this, 5);
                drive.drive(0, 0);
        }
        return false;
        
    }
    
    /**
     * Creates a new instance of AimBot
     * @param sensors sensor pack used with the rest of robot
     * @param mechanisms mechanism pack used with rest of robot
     */
    public Aimbot(){
        rangeFinder = SensorPack.getInstance().getRangeFinder();
        drive = MechanismPack.getInstance().getDrive();
        
        Logger.log("Aimbot online.", this, 5);
        //unique to the robot, cannot be reset every action in auto
        gyro = SensorPack.getInstance().getGyro();
    }
    
    public void logGyroAngles() {
        Logger.log("Zero: " + gyro.getZero(), this, 5);
        Logger.log("Angle: " + gyro.getModdedAngle(), this, 5);
    }
    
    /**
     * Orients the robot towards the wall
     * @return true when action is complete
     */
    public boolean faceWall(){
        // problem in here somewhere: never stops turning
        logGyroAngles();
        return drive.smartTurn(gyro.getZero());
    }
    
    /**
     * Gets robot to perfect range off the wall
     * @param mode 0 for teleop 1 for auto
     * @return true when action complete. false when not in range
     */
    public boolean setRange(int mode){
        Logger.log("Range: " + rangeFinder.getDistance(), this, 1);
        if(rangeFinder.isClose(DISTANCE_TO_SHOOT, TOLERANCE_FOR_SHOT)){
            drive.drive(0, 0);
            return true;
        }
            
        double difference = rangeFinder.getDistance() - DISTANCE_TO_SHOOT;
        //TODO: Calculate distances to travel
        if(difference > 0 || mode == 1){//robot is to far
            drive.drive(.3, Math.abs(difference));//drive backwards
            Logger.log("Driving backwards...", this, 4);
            
        } else { //robot is too close
            drive.drive(-.3, Math.abs(difference)); //drive forwards
            Logger.log("Driving forwards...", this, 4);
        }
        
        return false;
    }
    
    /**
     * resets the aimbot
     */
    public void reset(){
        Logger.log("Resetting...", this, 5);
        this.step = 0;
        drive.resetNeeded();
    }
}
