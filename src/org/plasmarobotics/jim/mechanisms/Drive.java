/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import org.plasmarobotics.jim.Constants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.sensors.PlasmaEncoder;
import org.plasmarobotics.jim.sensors.PlasmaGyro;
import org.plasmarobotics.jim.sensors.SensorPack;


/**
 *This class controls the drive system 
 * @author Jim
 */

public class Drive {
    
    private Joystick leftJoystick,
            rightJoystick;
   
    private RobotDrive chassis;
    
    private PlasmaEncoder LeftEncoder,
            RightEncoder;
    
    private PlasmaGyro gyro;
    
    private boolean resetNeeded;
    
    /**
     * Used to create a FroboDrive object to control all driving controls
     * 
     * @param leftJoystick Left joystick for tank drive
     * @param rightJoystick Right joystick for tank drive
     * @param RobotNameHere Instance of the main class
     */
    public Drive(ControlPack controls, SensorPack sensors){
        //Binds the joysticks...
        this.rightJoystick = controls.getRightJoystick();
        this.leftJoystick = controls.getLeftJoystick();
        
        //Creates a RobotDrive...
        chassis = new RobotDrive(Constants.FRONT_LEFT_DRIVE_CHANNEL, 
                Constants.BACK_LEFT_DRIVE_CHANNEL, 
                Constants.FRONT_RIGHT_DRIVE_CHANNEL, 
                Constants.BACK_RIGHT_DRIVE_CHANNEL);
        
        //setting up encoders
        this.LeftEncoder = sensors.getLeftEncoder();
        this.RightEncoder = sensors.getRightEncoder();
                
        //gyro
        this.gyro = sensors.getGyro();
        
        resetNeeded = true;
    }
    
    
    
    /**
     * Meant to be run once before teleop. 
     * -sets all motor direction
     */
    public void setupTeleop(){
        chassis.setInvertedMotor(RobotDrive.MotorType.kFrontLeft,true);
        chassis.setInvertedMotor(RobotDrive.MotorType.kRearLeft,true);
        chassis.setInvertedMotor(RobotDrive.MotorType.kFrontRight,true);
        chassis.setInvertedMotor(RobotDrive.MotorType.kRearRight,true);
    }
    
    /**
     * Meant to be run once before autonomous
     * -sets all motor direction
     * resets the encoders and gyro
     */
    public void setupAutonomous(){
        chassis.setInvertedMotor(RobotDrive.MotorType.kFrontLeft,false);
        chassis.setInvertedMotor(RobotDrive.MotorType.kRearLeft,false);
        chassis.setInvertedMotor(RobotDrive.MotorType.kFrontRight,false);
        chassis.setInvertedMotor(RobotDrive.MotorType.kRearRight,false);
        reset();
    }
    /*
     * Method called in teleop loop to provide tank drive for the robot
     */
    public void updateTeleop(){
        chassis.tankDrive(leftJoystick, rightJoystick);
        
    }
    
    
    /**
     * The robot will drive in a straight line for a given distance
     * @param distance Distance (in inches) to drive
     * @param speed Speed of motors (-1 to 1 scale)
     */
    

    public boolean drive(double speed, double distance){
        if(resetNeeded){
            reset();
            resetNeeded = false;
        }
        
        if(((LeftEncoder.getDistance() + RightEncoder.getDistance())/2) < distance){
            chassis.drive(speed, (gyro.getAngle() * .03));//stay on track with .03 curve
            return false;
        } else{
            resetNeeded = true;
            chassis.drive(0, 0);//stop robot
            return true;
           
        }
        
            
    }
    
    public boolean turn(float degrees){
        if(resetNeeded){
            reset();
            resetNeeded = false;
        }
        degrees = -degrees;
        
        double difference = degrees - gyro.getAngle();
        
        System.out.println("Gyro: " + gyro.getAngle());
        
        System.out.println(gyro.getAngle());
        
        if(Math.abs(difference) > 5){
            if(difference > 0){
                chassis.drive(.3, -1);//turn left
                
                return false;
            } else {
                chassis.drive(.3, 1);//turn right
                
                return false;
            } 

        } else {
            chassis.drive(0,0); //stop the robot
            resetNeeded = true;
            
            return true;
        }           

    }
    
    public void reset(){
        LeftEncoder.reset();
        RightEncoder.reset();
        gyro.reset();
    }
}