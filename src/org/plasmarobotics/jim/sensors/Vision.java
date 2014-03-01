/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.plasmarobotics.jim.sensors;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *This class handles the receiving of visual information
 * @author jim
 */
public class Vision {
    private NetworkTable server;
    private double distance; //distance from the target
    private int pixelsOff; //negative for left positive for right
    
    public Vision(){
        server = NetworkTable.getTable("SmartDashboard");
        System.out.println("The Eye is open");
        
        
    }
    
    /**
     * called constantly during autonomous
     */
    public void update(){
        SmartDashboard.putNumber("Hot: ", server.getNumber("Hot", 0.0));
        
    }
    
    /**
     * @return whether the goal is hot or not
     */
    public boolean isHot(){
        double serverInput = server.getNumber("Hot", 0.0);
        if(serverInput == 1)
            return true;
        else 
            return false;
    }
}




