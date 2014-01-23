/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;
import org.plasmarobotics.jim.Constants;

/**
 *This class outputs information from they gyro
 * @author Jim
 */
public class PlasmaGyro extends Gyro{
    double zero = 0; //keeps track of where the zero is
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
     * @return getAngle % 360
     */
    public double getAbsoluteAngle(){
       //make this work
            return 0;
    }

    public void reset() {
        zero = zero - this.getAbsoluteAngle();
        
        super.reset(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return angle%360
     */
    public double getAngle() {
        //make this work
        return 0;
    }

   
    
    
    
}
