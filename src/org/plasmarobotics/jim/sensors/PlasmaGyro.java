/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.sensors;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *This class outputs information from they gyro
 * @author Jim
 */
public class PlasmaGyro extends Gyro{
    private double zero = 113.73704707223966; //keeps track of where the zero is
    private double angle; //current angle of the gyro
//    public static final double GYRO_SENSITIVITY = 0.00698;
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
     * @return getAngle % 360 (0-360)
     * @author jim
     */
    public double getModdedAngle(){
        return super.getAngle()%360;
            
    }
    
    
    /*  TODO: fix stuff. resets gyro @ beginning of every turn
        turn as far as parameter provided, keep going until
        reach thingy
    */
    
    /**
     * Resets the gyro and records where zero is
     * @author Allek
     */
    public void reset() {
        double old_zero = this.zero;
      
        this.zero =(this.zero - this.getModdedAngle()) %360;
                
        double diff = this.zero - old_zero;
        
        SmartDashboard.putNumber("Gyro zero: ", this.getZero());
        SmartDashboard.putNumber("Gyro angle: ", this.getModdedAngle());
        
        super.reset(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the zero
     */
    public double getZero() {
        return this.zero;
    }


   
    
    
    
}
