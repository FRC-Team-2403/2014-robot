/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.sensors;

import edu.wpi.first.wpilibj.CounterBase;
import org.plasmarobotics.jim.Channels;

/**
 * A neat little bundle for sensors on the robot
 * @author Jim
 */
public final class SensorPack {
    private static final double ENCODER_DISTANCE_PER_PULSE = 0.05138;
    
    private static SensorPack pack = null;
    
    private PlasmaGyro gyro = new PlasmaGyro(Channels.GYRO_CHANNEL);
    
    private SonicRange rangeFinder = new SonicRange(Channels.RANGE_CHANNEL);
    
    private Vision vision = new Vision();
    
    private PlasmaEncoder leftEncoder = new PlasmaEncoder(Channels.LEFT_ENCODER_A_CHANNEL, 
                Channels.LEFT_ENCODER_B_CHANNEL, 
                false, //normal direction
                CounterBase.EncodingType.k4X,
                ENCODER_DISTANCE_PER_PULSE); 
    
    private PlasmaEncoder rightEncoder = new PlasmaEncoder(Channels.RIGHT_ENCODER_A_CHANNEL, 
                Channels.RIGHT_ENCODER_B_CHANNEL, 
                true, //reverse direction
                CounterBase.EncodingType.k4X,
                ENCODER_DISTANCE_PER_PULSE); 

    /**
     * prevents instantiation
     */
    private SensorPack(){
        
    }
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
