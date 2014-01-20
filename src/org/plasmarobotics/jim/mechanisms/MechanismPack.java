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
    private SensorPack sensors = new SensorPack();
    private ControlPack controls = new ControlPack();
    
    private Drive drive = new Drive(controls, sensors);
    private Shoot shooter = new Shoot(controls);
    private Pickup pickup = new Pickup(controls, sensors);

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
    
    
}
