/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.sensors;

import edu.wpi.first.wpilibj.Encoder;

/**
 * A modified Encoder class that utilizes cool methods
 * @author Jim
 */
public class PlasmaEncoder extends Encoder{
  
    /**
     * Creates an instance of PlasmaEncoder
     * @param aChannel aChannel on the side car
     * @param bChannel bChannel on the side car
     * @param reverseDirection whether or not the encoders were put on backwards
     * @param encodingType type of encoding
     * @param distancePerPulse constant to determine distance traveled
     */
    public PlasmaEncoder(int aChannel, int bChannel, boolean reverseDirection, EncodingType encodingType, double distancePerPulse) {
        super(aChannel, bChannel, reverseDirection, encodingType);
        this.setDistancePerPulse(.04112);
        this.start();
    }
    
    

    
}
