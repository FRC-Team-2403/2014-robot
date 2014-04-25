/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.gamemode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.plasmarobotics.jim.Aimbot;
import org.plasmarobotics.jim.mechanisms.Catch;
import org.plasmarobotics.jim.mechanisms.Drive;
import org.plasmarobotics.jim.mechanisms.MechanismPack;
import org.plasmarobotics.jim.mechanisms.Pickup;
import org.plasmarobotics.jim.mechanisms.Shoot;
import org.plasmarobotics.jim.sensors.SensorPack;

/**
 * manages actions during autonomous mode
 * @author cathy 
 */
public class Autonomous {
    private static final int SWITCH_ONE_PORT = 6,
            SWITCH_TWO_PORT = 7;
    
    String verbAutoModes[] = {"Drive Forward", "Move 'n' shoot", "2 balls", "avoid blocker"};
    
    Drive drive;
    Shoot shooter;
    Pickup pickup;
    byte setting = 0, //which autonomous?
        step;
    Aimbot aimbot;
    int check = 0;
    boolean optionsSwitchOne,
            optionSwitchTwo; //autonomous mode selection switches
    /**
     * creates an autonomous object that handles all things autonomous
     * @param mechanisms mechanismPack.getInstance()
     * 
     * @author Jim
     */
    public Autonomous(MechanismPack mechanisms){
        
        drive = mechanisms.getDrive();
        shooter = mechanisms.getShooter();
        pickup = mechanisms.getPickup();
        
        drive.setupAutonomous();
        shooter.setupAutonomous();
        pickup.setupAutonomous();
        
        step = 0;
        aimbot = new Aimbot();
        //get setting 
//        optionsSwitchOne = DriverStation.getInstance().getDigitalIn(SWITCH_ONE_PORT);
//        optionSwitchTwo = DriverStation.getInstance().getDigitalIn(SWITCH_TWO_PORT);
                
    }
    /**
     * prepares the robot for autonomous
     * @author Jim
     */
    public void autoInit(){
         //get setting 
        optionsSwitchOne = DriverStation.getInstance().getDigitalIn(SWITCH_ONE_PORT);
        optionSwitchTwo = DriverStation.getInstance().getDigitalIn(SWITCH_TWO_PORT);
        
        drive.setupAutonomous();
        shooter.setupAutonomous();
        pickup.setupAutonomous();
//        if(optionsSwitchOne)
//            setting += 1;
//        
//        if(optionSwitchTwo)
//            setting += 2;
       
        System.out.println("autoInit");
//        SmartDashboard.putString("Auto mode:", verbAutoModes[setting]);
    }
    /**
     * This is the code that runs continously during auto
     */
    public void run(){
       check = 0; 
        switch(setting){
           
            case 0:
                driveForwardAuto();
                break;
            case 1:
                moveNShootAuto();
                break;
            case 2:
                shootSecondBallAuto();
                break;
            case 3:
                avoidBlockerAuto();
                break;
            default:
                break;
                      
        }
//              SmartDashboard.putNumber("Gyro angle:", SensorPack.getInstance().getGyro().getModdedAngle());
        SmartDashboard.putNumber("left encoder dist", SensorPack.getInstance().getLeftEncoder().getDistance());
        SmartDashboard.putNumber("right encoder dist", SensorPack.getInstance().getRightEncoder().getDistance());
        SmartDashboard.putNumber("Range:", SensorPack.getInstance().getRangeFinder().getDistance());
    }
    /**
     * moves 3 ft during autonomous
     * @author cathy
     */
    public void driveForwardAuto (){
        switch(step) {
            case 0:
//                drive.getChassis().drive(.5, 0);
//                try{
//                    Thread.sleep(4000);
//                } catch (InterruptedException e){
//                    e.printStackTrace();
//                }
                if(drive.drive(.5, Preferences.getInstance().getDouble("driveForwardDistance", 48)))
                    step++;
                break;
            
            default:
                drive.drive(0, 0);
                System.out.println("stopped");
                break;
        }
    }
    /**
     * moves forward and shoots for the goal
     * @author cathy
     */
    public void moveNShootAuto () {
        
        switch(step){
            case 1: 
                if(drive.drive(.6, Preferences.getInstance().getDouble("moveNShootDriveDist", 220)))
                    step++;
                SmartDashboard.putString("Progress", "Driving");
                break;
            case 2:
                 SmartDashboard.putString("Progress", "Aimbotting");
                if (aimbot.aim(1))
                    step++;
               
                break;
            case 0:
                SmartDashboard.putString("Progress", "Lowering");
                pickup.lower(.2);
                
                try{
                    Thread.sleep(500);
                    step++;
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                break;
            case 3:
                shooter.shoot(0);
                SmartDashboard.putString("Progress", "Shooting");
                try{
                    Thread.sleep(500);
                    step++;
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                
                break;
            case 4:
//                SmartDashboard.putString("Progress", "Raising pickup");
//                pickup.lower(.4);
//                try{
//                    Thread.sleep(50);
//                }catch (InterruptedException e){
//                    
//                }
                pickup.disable();
                step++;
                break;
//            case 5:
//                shooter.retract();
////                SmartDashboard.putString("Progress", "retracting Shoot");
//                step++;
//                break;
            case 5:
                SmartDashboard.putString("Progress", "Done.");
                
                step++;
                break;
            case 6:
                shooter.retract();
//                step++;
                break;
            default:
                drive.drive(0, 0);
                SmartDashboard.putString("Progress", "ERROR");
        }
    }
    /**
     * moves forward, shoots, then picks up ball to shoot again
     * @author cathy
     */
    public void shootSecondBallAuto () {
        switch(step) {
            case 0:
                pickup.lower(2);
                try{
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                
                if (drive.drive(.3, 36) && pickup.lower(.2)){
                    pickup.disable();
                    pickup.forward();
                    step++;
                }
                    
                break;
            case 1:
                if (aimbot.aim(1))
                    step++;
                break;
            case 2:
                if (shooter.shoot(0))
                    step++;
                break;
            
            case 3:
                pickup.forward();
                step++;
                
                break;
            case 4:

                if (drive.drive(.3, -42))
                    step++;
                break;
            case 6:
                if (aimbot.aim(1))
                    step++;
                break;
            case 7:
                if (shooter.shoot(0)){
                    pickup.disable();
                    step++;
                }
                break;
            default:
                drive.drive(0, 0);
                break;
                
        }
    }
    /**
     * turns, moves forward and shoots
     * @author cathy
     */
    public void avoidBlockerAuto () {
        switch(step){
            case 0:
                if (drive.smartTurn(-90))
                    step++;
                break;
            case 1:
                if (drive.drive(.3, 36))
                    step++;
                break;
            case 2:
                if (aimbot.aim(1))
                    step++;
                break;
            case 3:
                if (shooter.shoot(0))
                    step++;
                break;
            default:
                drive.drive(0, 0);
                break;
            
        }
    }
    
    public void reset(){
        this.step = 0;
        aimbot.reset();
        autoInit();
    }
    
    public void disabled(){
         //get setting 
//        optionsSwitchOne = DriverStation.getInstance().getDigitalIn(SWITCH_ONE_PORT);
//        optionSwitchTwo = DriverStation.getInstance().getDigitalIn(SWITCH_TWO_PORT);
        
//        setting = 0;
//        if(optionsSwitchOne)
//            setting += 1;
//        
//        if(optionSwitchTwo)
//            setting += 2;
        
//        System.out.println(setting);
        try{
            SmartDashboard.putString("Auto mode:", verbAutoModes[setting]);
        }catch(ArrayIndexOutOfBoundsException e){
            SmartDashboard.putString("Auto mode:", "No move");
        }
        
        setting = (byte) Preferences.getInstance().getInt("amode", 127);
        
        drive.disable();
        shooter.disable();
        pickup.disable();
        
        if(check == 0){
            reset();
            check++;
        }
        
    }
}
