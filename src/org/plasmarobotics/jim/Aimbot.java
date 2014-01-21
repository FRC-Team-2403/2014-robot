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
    
    /**
     * Puts the robot in the perfect place for shooting
     * @return true when it is in position
     */
    public boolean aim(){
        //turn to zero 
        //go to correct range
        return true;
    }
    
    /**
     * Creates a new instance of AimBot
     * @param sensors sensor pack used with the rest of robot
     * @param mechanisms mechanism pack used with rest of robot
     */
    public Aimbot(SensorPack sensors, MechanismPack mechanisms){
        rangeFinder = sensors.getRangeFinder();
        drive = mechanisms.getDrive();
        
        //unique to the robot, cannot be reset every action in auto
        gyro = new PlasmaGyro(Constants.GYRO_CHANNEL, Constants.GYRO_SENSITIVITY);
    }
}
