/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim;

/**
 *This class holds all constant values within Frobo
 * @author Jim
 */
public final class Channels {
     
    //joystick ports
    public static final int LEFT_STICK_PORT = 1;
    public static final int RIGHT_STICK_PORT = 2;
    public static final int GAMEPAD_PORT = 3;
    
    /*~~~~~~~~~~~~~~~~~~~~~~~~~
    *PWM OUTPUTS
    ~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    //Drive motors
    public static final int BACK_RIGHT_DRIVE_CHANNEL = 1;
    public static final int BACK_LEFT_DRIVE_CHANNEL = 2;
    public static final int FRONT_RIGHT_DRIVE_CHANNEL = 3;
    public static final int FRONT_LEFT_DRIVE_CHANNEL = 4;
    
       
    //pickup 
    public static final int PICKUP_RAISE_LOWER_CHANNEL = 5;
    public static final int RIGHT_PICKUP_ROLLER_CHANNEL = 6;
    public static final int LEFT_PICKUP_ROLLER_CHANNEL = 7;
    
    /*~~~~~~~~~~~~~~~~~~~~~~~~
    *DIGITAL IO
    ~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    
    //switches
    public static final int SHOOT_LOADED_CHANNEL = 1;
    public static final int PICKUP_RAISED_CHANNEL = 13;
    public static final int PICKUP_LOWERED_CHANNEL = 14;
    
    //Gyroscope
    public static final int GYRO_CHANNEL = 1;
    
    //rangefinder
    public static final int RANGE_CHANNEL = 2;
    
    //Compressor
    public static final int COMPRESSOR_PRESSURE_SWITCH_CHANNEL = 8;
    
    //Drive Encoders
    public static final int RIGHT_ENCODER_A_CHANNEL = 11;
    public static final int RIGHT_ENCODER_B_CHANNEL = 12;
    
    public static final int LEFT_ENCODER_A_CHANNEL = 13;
    public static final int LEFT_ENCODER_B_CHANNEL = 14;
    
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * RELAYS
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static final int COMPRESSOR_RELAY_CHANNEL = 1;
    public static final int SWAG_LIGHT_PORT = 8;
    
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *PNEUMATICS
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    //left shoot
    public static final int LEFT_SOLENOID_FORWARD_CHANNEL = 1;
    public static final int LEFT_SOLENOID_REVERSE_CHANNEL = 2;
    
    //right shoot
    public static final int RIGHT_SOLENOID_FORWARD_CHANNEL = 3;
    public static final int RIGHT_SOLENOID_REVERSE_CHANNEL = 4;
   
    
       
}