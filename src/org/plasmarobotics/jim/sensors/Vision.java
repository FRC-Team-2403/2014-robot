/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.plasmarobotics.jim.sensors;

import edu.wpi.first.wpilibj.networktables.NetworkTable;


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
    
    public void update(){
      //  System.out.println("Distance " + server.getDouble("Distance", 0.0));
    }
}




