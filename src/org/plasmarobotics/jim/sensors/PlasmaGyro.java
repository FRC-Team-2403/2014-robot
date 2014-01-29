/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;
import org.plasmarobotics.jim.Constants;
import org.plasmarobotics.jim.Logger;

/**
 *This class outputs information from they gyro
 * @author Jim
 */
public class PlasmaGyro extends Gyro{
    private double zero = 0; //keeps track of where the zero is
    double angle; //current angle of the gyro
    
    /**
     * Creates an instance of the PlasmaGyro
     * @param channel AnalogChannel where the gyro is connected
     */
    public PlasmaGyro(AnalogChannel channel) {
        super(channel);
    }

    /**
     * Creates an instance of the PlasmaGyro
     * @param channel Channel number where the gyro is connected
     */
    public PlasmaGyro(int channel) {
        super(channel);
    }

    /**
     * Creates an instance of the PlasmaGyro
     * @param slot Slot on the cRIO
     * @param channel Channel number where the gyro is connected
     */
    public PlasmaGyro(int slot, int channel) {
        super(slot, channel);
    }
    
    /**
     * Creates an instance of the PlasmaGyro and sets the sensitivity, and resets it
     * @param channel Channel number where the gyro is connected
     * @param sensitivity Sensitivity of thy gyro
     */
    public PlasmaGyro(int channel, double sensitivity){
        super(channel);
        this.setSensitivity(sensitivity);
        this.reset();
    }
    
    /**
     * Gets the angle that the robot is facing
     * @return getAngle % 360 (0-360)
     */
    public double getAbsoluteAngle(){
            angle = -super.getAngle()%360;
            
            if(angle < 0)
                angle = 360 + angle%360;
            
            
            return angle;
    }

    public void reset() {
        zero = this.zero - this.getAbsoluteAngle();
        Logger.log("reset", this, 4);
        
        super.reset(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the zero
     */
    public double getZero() {
        return zero;
    }


   
    
    
    
}
