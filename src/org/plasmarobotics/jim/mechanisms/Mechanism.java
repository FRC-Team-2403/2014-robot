/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.plasmarobotics.jim.mechanisms;

/**
 * Interface for all mechanisms
 * @author jim
 */
public interface Mechanism {
    
    
    public abstract void setupAutonomous();
    public abstract void updateAutonomous();
    
    public abstract void setupTeleop();
    public abstract void updateTeleop();
    
    public abstract void disable();
    
    
}
