/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.sensors.SensorPack;

/**
 *
 * @author Jim
 */
public final class MechanismPack {
    private static MechanismPack pack;
    
    private SensorPack sensors = SensorPack.getInstance();
    private ControlPack controls = ControlPack.getInstance();
    
    private Drive drive = new Drive();
    private Shoot shooter = new Shoot();
    private Pickup pickup = new Pickup();

    /**
     * prevents instantiation
     */
    private MechanismPack(){
        
    }
    /**
     * @return the drive
     */
    public Drive getDrive() {
        return drive;
    }

    /**
     * @return the shooter
     */
    public Shoot getShooter() {
        return shooter;
    }

    /**
     * @return the pickup
     */
    public Pickup getPickup() {
        return pickup;
    }
    
    public static MechanismPack getInstance(){
        if(pack == null){
            pack = new MechanismPack();
        }
        return pack;
    }
    
}
