/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.gamemode;

import edu.wpi.first.wpilibj.CounterBase;
import org.plasmarobotics.jim.Constants;
import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.mechanisms.Drive;
import org.plasmarobotics.jim.mechanisms.MechanismPack;
import org.plasmarobotics.jim.mechanisms.Pickup;
import org.plasmarobotics.jim.mechanisms.Shoot;
import org.plasmarobotics.jim.sensors.PlasmaEncoder;
import org.plasmarobotics.jim.sensors.PlasmaGyro;
import org.plasmarobotics.jim.sensors.SonicRange;
import org.plasmarobotics.jim.sensors.Vision;

/**
 *
 * @author Jim
 */
public class Autonomous {
    Drive drive;
    Shoot shooter;
    Pickup pickup;
    byte setting; //which autonomous?
    
    
    public Autonomous(MechanismPack mechanisms){
        
        drive = mechanisms.getDrive();
        shooter = mechanisms.getShooter();
        pickup = mechanisms.getPickup();
        
        
        
    }
    
    /**
     * This is the code that runs continously during auto
     */
    public void run(){
        
    }
    
    
}
