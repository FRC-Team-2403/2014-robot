/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.gamemode;

import edu.wpi.first.wpilibj.Gyro;
import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.controls.PlasmaJoystick;
import org.plasmarobotics.jim.mechanisms.Drive;
import org.plasmarobotics.jim.mechanisms.MechanismPack;
import org.plasmarobotics.jim.mechanisms.Pickup;
import org.plasmarobotics.jim.mechanisms.Shoot;
import org.plasmarobotics.jim.sensors.SensorPack;
import org.plasmarobotics.jim.sensors.SonicRange;
import org.plasmarobotics.jim.sensors.Vision;

/**
 *
 * @author Jim
 */
public class Teleop{
    private PlasmaJoystick leftJoystick,
            rightJoystick;
    
    private Drive drive;
    private Shoot shooter;
    private Pickup pickup;
    
    private Gyro gyro;
    private Vision vision;
    private SonicRange rangeFinder;
    
    
    public Teleop(ControlPack controls, MechanismPack mechanisms, SensorPack sensors){
    
        //controls
        this.leftJoystick = controls.getLeftJoystick();
        this.rightJoystick = controls.getRightJoystick();
        
        //mechanisms
        this.drive = mechanisms.getDrive();
        this.shooter = mechanisms.getShooter();
        this.pickup = mechanisms.getPickup();
        
        //sensors
        this.gyro = sensors.getGyro();
        this.vision = sensors.getVision();
        this.rangeFinder = sensors.getRangeFinder();
        
        
    }
    
    /**
     * This gets called periodically during teleop
     */
    public void run(){
        
    }
    
    
}
