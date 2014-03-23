/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.plasmarobotics.jim;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.gamemode.Autonomous;
import org.plasmarobotics.jim.gamemode.Teleop;
import org.plasmarobotics.jim.mechanisms.MechanismPack;
import org.plasmarobotics.jim.sensors.SensorPack;
import org.plasmarobotics.jim.sensors.Vision;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RichardSimmons extends IterativeRobot {
    
    private Autonomous auto;
    private Teleop teleop;
    private Compressor compressor;
    Vision vis = new Vision();
    
    private Relay swagLights;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        auto = new Autonomous(MechanismPack.getInstance());
        
        teleop = new Teleop(ControlPack.getInstance(), 
                MechanismPack.getInstance(), 
                SensorPack.getInstance());
        
        compressor = new Compressor(Channels.COMPRESSOR_PRESSURE_SWITCH_CHANNEL, Channels.COMPRESSOR_RELAY_CHANNEL);
        compressor.start();
        SensorPack.getInstance().getGyro().reset();
        
        swagLights = new Relay(Channels.SWAG_LIGHT_PORT);
        swagLights.set(Relay.Value.kOn);//TODO:fix swaglights
        
        System.out.println("Robot initilization complete.");
        
        
    }

    public void autonomousInit() {
        auto.autoInit();
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        auto.run();
    }

    public void teleopInit() {
        teleop.teleopInit();
        
    }

    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        teleop.run();
        vis.update();
    }

    public void disabledInit() {
        auto.reset();
    }

    public void disabledContinuous() {
        
    }

    public void disabledPeriodic() {
        auto.disabled();
    }
    
    
    
    
    
}
