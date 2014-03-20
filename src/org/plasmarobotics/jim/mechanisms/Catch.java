/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.plasmarobotics.jim.mechanisms;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.plasmarobotics.jim.Channels;
import org.plasmarobotics.jim.controls.ControlPack;

/**
 * This is an object to handle the closing and opening of the catch mechanism
 * @author Jim
 */
public class Catch implements Mechanism{
    
    DoubleSolenoid catchSolenoid;
    
    /**
     * Creates an instance of catch
     */
    public Catch(){
        this.catchSolenoid = new DoubleSolenoid(Channels.CATCH_SOLENOID_FORWARD_CHANNEL, Channels.CATCH_SOLENOID_REVERSE_CHANNEL);
        
    }
    
    public void disable(){
        catchSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void setupTeleop() {
        
    }

    public void updateTeleop() {
        if(ControlPack.getInstance().getCatchButton().get()){
            catchSolenoid.set(DoubleSolenoid.Value.kForward);
        }else{
            catchSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void setupAutonomous() {
        
    }
    
}
