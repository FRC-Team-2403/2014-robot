/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import org.plasmarobotics.jim.Channels;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import org.plasmarobotics.jim.Logger;
import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.controls.PlasmaGamepad;
import org.plasmarobotics.jim.sensors.PlasmaEncoder;
import org.plasmarobotics.jim.sensors.PlasmaGyro;
import org.plasmarobotics.jim.sensors.SensorPack;


/**
 *This class controls the drive system 
 * @author Jim
 */

public class Drive implements Mechanism{
    
    private Joystick leftJoystick,
            rightJoystick;
    
    private PlasmaGamepad gamepad;
   
    private RobotDrive chassis;
    
    private PlasmaEncoder LeftEncoder,
            RightEncoder;
    
    private PlasmaGyro gyro;
    
    private boolean resetNeeded;
    
    /**
     * Used to create a FroboDrive object to control all driving operations
     *
     */
    public Drive(){
        //Binds the joysticks...
        this.rightJoystick = ControlPack.getInstance().getRightJoystick();
        this.leftJoystick = ControlPack.getInstance().getLeftJoystick(); 
        this.gamepad = ControlPack.getInstance().getGamepad();
        
        
        //Creates a RobotDrive...
        chassis = new RobotDrive(Channels.FRONT_LEFT_DRIVE_CHANNEL, 
                Channels.BACK_LEFT_DRIVE_CHANNEL, 
                Channels.FRONT_RIGHT_DRIVE_CHANNEL, 
                Channels.BACK_RIGHT_DRIVE_CHANNEL);
        
        //setting up encoders
        this.LeftEncoder = SensorPack.getInstance().getLeftEncoder();
        this.RightEncoder = SensorPack.getInstance().getRightEncoder();
                
        //gyro
        this.gyro = SensorPack.getInstance().getGyro();
        
        // resetNeeded = true;
        // ^ gyro already reset during init
        System.out.println("Drive online");
    }
    
    
    
    /**
     * Meant to be run once before teleop. 
     * -sets all motor direction
     */
    public void setupTeleop(){
//        getChassis().setInvertedMotor(RobotDrive.MotorType.kFrontLeft,true);
//        getChassis().setInvertedMotor(RobotDrive.MotorType.kRearLeft,true);
//        getChassis().setInvertedMotor(RobotDrive.MotorType.kFrontRight,true);
//        getChassis().setInvertedMotor(RobotDrive.MotorType.kRearRight,true);
        System.out.println("Drive prepared for teleop");
    }
    
    /**
     * Meant to be run once before autonomous
     * -sets all motor direction
     * resets the encoders and gyro
     */
    public void setupAutonomous(){
//        getChassis().setInvertedMotor(RobotDrive.MotorType.kFrontLeft,false);
//        getChassis().setInvertedMotor(RobotDrive.MotorType.kRearLeft,false);
//        getChassis().setInvertedMotor(RobotDrive.MotorType.kFrontRight,false);
//        getChassis().setInvertedMotor(RobotDrive.MotorType.kRearRight,false);
        reset();
        System.out.println("Drive prepared for autonomous");
    }
  
    /*
     * called periodically during teleop
     */
    public void updateTeleop(){
        if (ControlPack.USE_JOYSTICK) {
            getChassis().tankDrive(leftJoystick, rightJoystick); 
        } else {
            getChassis().arcadeDrive(-gamepad.getLeftJoystickYAxis(), -gamepad.getRightJoystickXAxis());
            //chassis.tankDrive(gamepad.getLeftJoystickYAxis(), gamepad.getRightJoystickYAxis());
        }
        
        
    }

    /**
     * called periodically during autonomous
     */
    public void updateAutonomous() {
    
    }

    
    
    
    
    
    /**
     * The robot will drive in a straight line for a given distance using two encoders and a gyro
     * @param speed Speed of motors (-1 to 1 scale)
     * @param distance Distance (in inches) to drive
     * @return true when operation is completed, false otherwise
     * @author jim
     */      

    public boolean drive(double speed, double distance){
        reset();
        double distTraveled = -(LeftEncoder.getDistance() + RightEncoder.getDistance())/2;
        
        
        if(distTraveled < distance){
            if(speed  > 0)
                getChassis().drive(speed, (gyro.getModdedAngle() * .03));//stay on track with .03 curve 
            else
                getChassis().drive(speed, -(gyro.getModdedAngle() * .03));//stay on track backwards with .03 curve 
            
            return false;
        } else{
            resetNeeded = true;
            getChassis().drive(0, 0);//stop robot
            return true;
           
        }
        
            
    }
    
    /**
     * Turns the robot (needs upside down gyro)
     * @param degrees angle to rotate (degrees, not radians) positive is counter clockwise.
     * @return true when operation is complete
     * @author jim
     */
    
    public boolean smartTurn(double degrees){
        reset();
        double distanceToTurn = degrees - gyro.getModdedAngle();
        if(distanceToTurn > 4 ){ //TODO: test if works
            getChassis().drive(.3, 1); //turn at .3 motor speed
            Logger.log("turning left", this, 4);
        } else if (distanceToTurn < -4  ){ //TODO: test if works
            getChassis().drive(.3, -1);
            Logger.log("turning right", this, 4);
        } else{
            getChassis().drive(0, 0);
            Logger.log("stopping robot", this, 4);
            resetNeeded = true;
            return true;
        }
        
        Logger.log("distanceToTurn:" + distanceToTurn, this, 4);
        
        return false;
        

    }
    
    /**
     * Turns a given angle
     * @param degrees angle to rotate (degrees, not radians) positive is counter clockwise. 
     * @return true when the action is completed. false otherwise.
     * @author jim
     */
    public boolean stupidTurn(double degrees){
        reset();
        if((degrees - gyro.getAngle()) < -4)
            getChassis().drive(3, -1);
        else if((degrees - gyro.getAngle()) > 4)
            getChassis().drive(.3, 1);
        else{
            getChassis().drive(0, 0);
            return true;
        }
        return false;
    }
    
    /**
     * tells drive its okay to reset
     * @author Cathy
     */
    public void resetNeeded () {
        this.resetNeeded = true;
    }
    /**
     * resets the sensors on the robot
     * @author jim
     */
    public void reset(){
        if(resetNeeded){
            LeftEncoder.reset();
            RightEncoder.reset();
            gyro.reset();
            resetNeeded = false;
            Logger.log("resetting...", this, 5);
        }
        
    }

    /**
     * Method called to clean up and disable the drive system.
     */
    public void disable() {
       
    }

    /**
     * @return the chassis
     */
    public RobotDrive getChassis() {
        return chassis;
    }
    
    
}