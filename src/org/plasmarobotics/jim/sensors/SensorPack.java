/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.sensors;

import edu.wpi.first.wpilibj.CounterBase;
import org.plasmarobotics.jim.Constants;

/**
 * A neat little bundle for sensors on the robot
 * @author Jim
 */
public final class SensorPack {
    private static SensorPack pack = null;
    
    private PlasmaGyro gyro = new PlasmaGyro(Constants.GYRO_CHANNEL);
    
    private SonicRange rangeFinder = new SonicRange(Constants.RANGE_CHANNEL, 
            Constants.RANGE_CONSTANT);
    
    private Vision vision = new Vision();
    
    private PlasmaEncoder leftEncoder = new PlasmaEncoder(Constants.LEFT_ENCODER_A_CHANNEL, 
                Constants.LEFT_ENCODER_B_CHANNEL, 
                false, //normal direction
                CounterBase.EncodingType.k4X,
                Constants.ENCODER_DISTANCE_PER_PULSE); 
    
    private PlasmaEncoder rightEncoder = new PlasmaEncoder(Constants.LEFT_ENCODER_A_CHANNEL, 
                Constants.LEFT_ENCODER_B_CHANNEL, 
                true, //reverse direction
                CounterBase.EncodingType.k4X,
                Constants.ENCODER_DISTANCE_PER_PULSE); 

    /**
     * @return the gyro
     */
    public PlasmaGyro getGyro() {
        return gyro;
    }

    /**
     * @return the rangeFinder
     */
    public SonicRange getRangeFinder() {
        return rangeFinder;
    }

    /**
     * @return the vision
     */
    public Vision getVision() {
        return vision;
    }

    /**
     * @return the leftEncoder
     */
    public PlasmaEncoder getLeftEncoder() {
        return leftEncoder;
    }

    /**
     * @return the rightEncoder
     */
    public PlasmaEncoder getRightEncoder() {
        return rightEncoder;
    }
    
    public static SensorPack getInstance(){
        if(pack == null){
            pack = new SensorPack();
        }
        return pack;
    }

    
    
}
