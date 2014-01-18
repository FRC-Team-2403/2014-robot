/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.plasmarobotics.jim.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *This class reads input from a supersonic rangefinder as an analog input
 * @author jim
 * 
 */
public class SonicRange {
    private AnalogChannel sensor;
    private double rangeConstant; 
    
    /**
     * Creates an instance of SonicRange at the specified channel with 
     * default rangeConstant of .0098
     * @param channel PWM channel of the sensor
     */
    public SonicRange(int channel){
        sensor = new AnalogChannel(channel);
        rangeConstant = .0098;
    }
    
    /**
     * Creates an instance of SonicRange at the specified channel along
     * with constant for range
     * @param channel PWM channel of the sensor
     * @param rangeConstant Constant used with voltage to calculate distance
     */
    public SonicRange(int channel, double rangeConstant){
        sensor = new AnalogChannel(channel);
        this.rangeConstant = rangeConstant;
    }
    
    /**
     * Sets the rangeConstant of the sensor
     * @param rangeConstant Constant used with voltage to calculate distance
     */
    public void setRangeConstant(float rangeConstant){
        this.rangeConstant = rangeConstant;
    }
    
    /**
     * gets the current voltage of the sensor
     * @return the voltage of the sensor 
     */
    public double getVoltage(){
        return sensor.getVoltage();
    }
    
    /**
     * Determines how far away the sensor is from the target
     * @return Distance (inches)
     */
    public double getDistance(){
        return sensor.getVoltage()/rangeConstant;
    }
}
