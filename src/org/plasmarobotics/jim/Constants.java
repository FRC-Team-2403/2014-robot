/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;

/**
 *This class holds all constant values within Frobo
 * @author Jim
 */
public final class Constants {
     
    //joystick ports
    public static final int LEFT_STICK_PORT = 1;
    public static final int RIGHT_STICK_PORT = 2;
    public static final int GAMEPAD_PORT = 3;
    
    //Drive motors
    public static final int FRONT_LEFT_DRIVE_CHANNEL = 4;
    public static final int BACK_LEFT_DRIVE_CHANNEL = 2;
    public static final int FRONT_RIGHT_DRIVE_CHANNEL = 3;
    public static final int BACK_RIGHT_DRIVE_CHANNEL = 1;
    
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *Shooter
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */
    
    public static final double SHOOT_RANGE = 48;
    public static final double SHOOT_TOLERANCE  = 6;
    //Shooting ports
    public static final int FRONT_SHOOT_CHANNEL = 5;
    public static final int BACK_SHOOT_CHANNEL = 6;
    
    //Shoot kicker
    public static final int SHOOT_KICKER_FORWARD_CHANNEL = 8;
    public static final int SHOOT_KICKER_REVERSE_CHANNEL = 9;
    
    //left shoot
    public static final int LEFT_SOLENOID_FORWARD_CHANNEL = 1;
    public static final int LEFT_SOLENOID_REVERSE_CHANNEL = 2;
    
    //right shoot
    public static final int RIGHT_SOLENOID_FORWARD_CHANNEL = 3;
    public static final int RIGHT_SOLENOID_REVERSE_CHANNEL = 4;
    
    //Climb Victors
    public static final int LEFT_CLIMB_VICTOR_CHANNEL = 14;
    public static final int RIGHT_CLIMB_VICTOR_CHANNEL = 15;
    
    //Motor speeds
    public static final double FRONT_SHOOT_MOTOR_SPEED = 1;
    public static final double BACK_SHOOT_MOTOR_SPEED = 1;

    //Climbing servo
    public static final int CLIMB_SERVO_CHANNEL = 10;
    
    public static final double CLIMB_SERVO_OPEN = .25,
            CLIMB_SERVO_CLOSE = 0;
    
    //Joystick buttons
    public static final int JOYSTICK_TRIGGER_BUTTON = 1, //shoots frisbee
            JOYSTICK_MIDDLE_THUMB_BUTTON = 3; //toggles shoot motors
    
    //Gamepad buttons
    public static final int GAMEPAD_RIGHT_TRIGGER = 1, 
                        GAMEPAD_LEFT_TRIGGER = 2,
                        GAMEPAD_TRIANGLE = 3,
                        GAMEPAD_CIRCLE = 4, 
                        GAMEPAD_SQUARE = 5,
                        GAMEPAD_CROSS = 6,
                        GAMEPAD_START = 7;
        
    //Gamepad thumbsticks
    public static final int GAMEPAD_LEFT_X_AXIS = 1,
                        GAMEPAD_LEFT_Y_AXIS = 2,
                        GAMEPAD_RIGHT_X_AXIS = 3,
                        GAMEPAD_RIGHT_Y_AXIS = 4;

    
    //Joystick Buttons
    public static final int SHOOT_TRIGGER_BUTTON = 1;
    
    //Compressor
    public static final int COMPRESSOR_PRESSURE_SWITCH_CHANNEL = 8;
    public static final int COMPRESSOR_RELAY_CHANNEL = 1;
    
    //Encoder
    public static final int LEFT_ENCODER_A_CHANNEL = 4;
    public static final int LEFT_ENCODER_B_CHANNEL = 5;
    
    public static final int RIGHT_ENCODER_A_CHANNEL = 2;
    public static final int RIGHT_ENCODER_B_CHANNEL = 3;
    
    public static final double ENCODER_DISTANCE_PER_PULSE = 0.05138;
    
    //gyro
    public static final int GYRO_CHANNEL = 1;
    public static final double GYRO_SENSITIVITY = 0.00698;
    
    //Range finder
    public static final double RANGE_CONSTANT = .0098;
    public static final int RANGE_CHANNEL = 2;
    
    //controls
    public static final boolean USE_JOYSTICK = false;
    
    //switches
    public static final int SHOOT_LOADED_CHANNEL = 1;
    public static final int PICKUP_RAISED_CHANNEL = 13;
    public static final int PICKUP_LOWERED_CHANNEL = 14;
    
    //shooter wait times
    public static final long TRUSS_SHOT_WAIT_TIME = 100;
    public static final long GOAL_SHOT_WAIT_TIME = 1000;
    public static final long PASS_WAIT_TIME = 50;
    
    //pickup 
    public static final int RIGHT_PICKUP_ROLLER_CHANNEL = 6;
    public static final int LEFT_PICKUP_ROLLER_CHANNEL = 7;
    public static final int PICKUP_RAISE_LOWER_CHANNEL = 5;
    

    
    public static final int PICKUP_ENCODER_A_CHANNEL = 7;
    public static final int PICKUP_ENCODER_B_CHANNEL = 9;
       
            
            
}