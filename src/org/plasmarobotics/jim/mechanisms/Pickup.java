 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import org.plasmarobotics.jim.Constants;
import org.plasmarobotics.jim.controls.ControlPack;
import org.plasmarobotics.jim.sensors.SensorPack;

/**
 * This class handles pickup operations of the robot
 * @author bryce
 */
public class Pickup implements Mechanism{
    /**
     * all final ports need to be added to updated Constants class after testing
     * on new robot
     */
    
    //victor motor controlers, reset ports
    Victor leftPickupRoller,
            rightPickupRoller,
            pickupUpDown;

  
    //check port for limit switchs | digital IO
    DigitalInput pickupLoweredSwitch,
            pickupLiftedSwitch;
    
//    Encoder pickupEncoder;
    
    ControlPack controls;
    
    boolean isRaised = true, //keep track of where pickup is
            isPickupering = false;
            
    public Pickup(ControlPack controls, SensorPack sensors){
        //motors
        rightPickupRoller = new Victor(Constants.RIGHT_PICKUP_ROLLER_CHANNEL);
        leftPickupRoller = new Victor(Constants.LEFT_PICKUP_ROLLER_CHANNEL);
        pickupUpDown = new Victor(1, Constants.PICKUP_RAISE_LOWER_CHANNEL);
        
//        pickupEncoder = new Encoder(Constants.PICKUP_ENCODER_A_CHANNEL, Constants.PICKUP_ENCODER_B_CHANNEL);
        //sensors
//        pickupLoweredSwitch = new DigitalInput(Constants.PICKUP_LOWERED_CHANNEL);
//        pickupLiftedSwitch = new DigitalInput(Constants.PICKUP_RAISED_CHANNEL);
        
        this.controls = ControlPack.getInstance();
        System.out.println("Pickup online");
    }
    
    /**
     * raise the pickup mechanism
     * @return true when the pickup is raised false otherwise
     */
    public boolean raise(){
        //not lifted
        if(!pickupLiftedSwitch.get()){
            pickupUpDown.set(.3);
            return false;
        } else{//it is lifted
            pickupUpDown.set(0);
            return true;
        }
            
    }
    
    /**
     * lower the pickup mechanism
     * @return true if the mechanism is lowered, false otherwise
     */
    public boolean lower(){
        //not lowered
        if(!pickupLoweredSwitch.get()){
            pickupUpDown.set(-.3);
            return false;
        } else{
            pickupUpDown.set(0);
            return true;
        }
    }
    
    /**
     * pickup the ball
     * @return if the pickup is picking up
     */
    public boolean forward(){
        leftPickupRoller.set(1);
        rightPickupRoller.set(1);
        return true;
    }
    
    /**
     * stop the pickup
     * @return when the action is completed
     */
    public boolean stop(){
        leftPickupRoller.set(0);
        rightPickupRoller.set(0);
        return true;
    }
    
    /**
     * reverse the pickup 
     * @return if the pickup is in reverse
     */
    public boolean reverse(){
        leftPickupRoller.set(-1);
        rightPickupRoller.set(-1);
        return true;
    }

    public void disable() {
        this.stop();
        this.raise();
        
    }

    public void setupAutonomous() {
        System.out.println("Pickup prepared for autonomous");
        //preps pickup
    }

    public void updateAutonomous() {
        //updates stuff durring auto e.g. sensor feeds
    }
    
    
    

    public void setupTeleop() {
        System.out.println("Pickup prepared for teleop");
        //preps pickup
    }

    public void updateTeleop() {
//       if(controls.getRaiseLowerShooterButton().isPressed()){
//           if(isRaised)
//               this.lower();
//           else
//               this.raise();
//           
//           isRaised = !isRaised;
//       }
//      
        pickupUpDown.set(0);
        
        if(controls.getGamepad().getAButton().get())
            pickupUpDown.set(-1);
        
        if(controls.getGamepad().getBButton().get())
            pickupUpDown.set(1);
        
        
       //moving the rollers
       leftPickupRoller.set(controls.getGamepad().getTriggerAxis());
       rightPickupRoller.set(controls.getGamepad().getTriggerAxis());
           
        
//       if(controls.getBackwardPickUpButton().isPressed()){
//           if(isPickupering){
//               this.stop();
//           } else{
//               this.reverse();
//           }
//           
//           isPickupering = !isPickupering;
//               
//       }
//           
//       
//       if(controls.getFowardPickUpButton().isPressed()){
//           if (isPickupering) {
//               this.stop();
//           } else {
//               this.forward();
//           }
//           
//           isPickupering = !isPickupering;
//       }
          
       
       
                   
                   
    }
    
    
}
