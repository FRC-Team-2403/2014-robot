/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.gamemode;

import org.plasmarobotics.jim.Aimbot;
import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.controls.Gamepad;
import org.plasmarobotics.jim.controls.PlasmaJoystick;
import org.plasmarobotics.jim.mechanisms.Drive;
import org.plasmarobotics.jim.mechanisms.MechanismPack;
import org.plasmarobotics.jim.mechanisms.Pickup;
import org.plasmarobotics.jim.mechanisms.Shoot;
import org.plasmarobotics.jim.sensors.SensorPack;

/**
 *
 * @author Jim
 */
public class Teleop{
    private PlasmaJoystick leftJoystick,
            rightJoystick;
    
    private Gamepad gamePad;
    
    private Drive drive;
    private Shoot shooter;
    private Pickup pickup;
    
    private Aimbot aimbot;
    
    public Teleop(ControlPack controls, MechanismPack mechanisms, SensorPack sensors){
    
        //controls
        this.leftJoystick = controls.getLeftJoystick();
        this.rightJoystick = controls.getRightJoystick();
        this.gamePad = controls.getGamepad();
        
        //mechanisms
        this.drive = mechanisms.getDrive();
        this.shooter = mechanisms.getShooter();
        this.pickup = mechanisms.getPickup();
        
        drive.setupTeleop();
        shooter.setupTeleop();
        pickup.setupTeleop();
          
        
    }
    
    /**
     * This gets called periodically during teleop
     */
    public void run(){
        drive.updateTeleop();
        shooter.updateTeleop();
        pickup.updateTeleop();
        
    }
    
    
}
