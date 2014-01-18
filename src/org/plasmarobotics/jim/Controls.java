/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.plasmarobotics.jim;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author jim
 */
public class Controls {
    private Joystick leftJoystick,
            rightJoystick;
    
    public Joystick getLeftJoystick(){
        return leftJoystick;
    }
    
    public Joystick getRightJoystick(){
        return rightJoystick;
    }
    
}
