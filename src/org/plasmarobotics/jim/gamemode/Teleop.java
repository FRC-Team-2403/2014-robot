/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.gamemode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.plasmarobotics.jim.Aimbot;
import org.plasmarobotics.jim.Constants;
import org.plasmarobotics.jim.Logger;
import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.controls.PlasmaGamepad;
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
    
    private PlasmaGamepad gamePad;
    
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
        
        this.aimbot = new Aimbot(sensors, mechanisms);
          
        
    }
    
    /**
     * used to get the robot ready for teleop
     */
    public void teleopInit(){
        drive.setupTeleop();
        shooter.setupTeleop();
    //    pickup.setupTeleop();
    }
    /**
     * This gets called periodically during teleop
     */
    boolean aimbotNeedReset = false;
    public void run(){
        
        
        if(leftJoystick.getSix().isPressed())
            Logger.raisePriority();
        
        if(leftJoystick.getSeven().isPressed())
            Logger.lowerPriority();
        
        
        if(ControlPack.getInstance().getAimbotButton().get()){
            aimbot.aim();
            aimbotNeedReset = true;
            Logger.log("aimbotting", this, 5);
        } else {
            
            if(aimbotNeedReset){
                Logger.log("Aimbot stopped", this, 5);
                aimbot.logGyroAngles();
                aimbot.reset(); // set AimBot state to 0
                aimbotNeedReset = false;
            }
            
          
            //Logger.log("angle: " + SensorPack.getInstance().getGyro().getAbsoluteAngle(), this, 3);
            drive.updateTeleop(); // back to tank (AimBot took over
            shooter.updateTeleop();
            pickup.updateTeleop();
            SmartDashboard.putNumber("RANGE: ", SensorPack.getInstance().getRangeFinder().getDistance());
           
        
        }
        
    }
    
    
    
}
