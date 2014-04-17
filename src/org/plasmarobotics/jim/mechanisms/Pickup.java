 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.plasmarobotics.jim.Channels;
import org.plasmarobotics.jim.controls.ControlPack;

/**
 * This class handles pickup operations of the robot
 * @author jim
 */
public class Pickup implements Mechanism{
    
    //victor motor controlers, reset ports
    Victor leftPickupRoller,
        rightPickupRoller,
        pickupUpDown;

  
    //check port for limit switchs | digital IO
//    DigitalInput pickupLoweredSwitch,
//            pickupLiftedSwitch;
    
//    Encoder pickupEncoder;
    
    ControlPack controls;
    
    boolean isRaised = true, //keep track of where pickup is
            isPickupering = false;
            
    /**
     * Creates a pickup object using the controlPack and sensorPack
     */
    public Pickup(){
        //motors
        rightPickupRoller = new Victor(Channels.RIGHT_PICKUP_ROLLER_CHANNEL);
        leftPickupRoller = new Victor(Channels.LEFT_PICKUP_ROLLER_CHANNEL);
        pickupUpDown = new Victor(Channels.PICKUP_RAISE_LOWER_CHANNEL);//was (1, CONSTANT)
        
//        pickupEncoder = new Encoder(Channels.PICKUP_ENCODER_A_CHANNEL, Channels.PICKUP_ENCODER_B_CHANNEL);
//        sensors
//        pickupLoweredSwitch = new DigitalInput(Channels.PICKUP_LOWERED_CHANNEL);
//        pickupLiftedSwitch = new DigitalInput(Channels.PICKUP_RAISED_CHANNEL);
        
        controls = ControlPack.getInstance();
        System.out.println("Pickup online");
    }
    
    /**
     * lower the pickup mechanism
     * @return true when the pickup is lowered false otherwise
     * @author jim
     */
    public boolean lower(){
        //not lifted
//        if(!pickupLiftedSwitch.get()){
            pickupUpDown.set(.6);
//            return false;
//        } else{//it is lifted
//            pickupUpDown.set(0);
//            return true;
//        }
        return true;          
    }
    
    /**
     * raise the pickup mechanism
     * @return true if the mechanism is raised, false otherwise
     */
    public boolean raise(){
        //not lowered
//        if(!pickupLoweredSwitch.get()){
            pickupUpDown.set(-.6);
//            return false;
//        } else{
//            pickupUpDown.set(0);
//            return true;
//        }
            return true;
    }
    
    /**
     * pickup the ball
     * @return if the pickup is picking up
     */
    public void forward(){
        leftPickupRoller.set(1);
        rightPickupRoller.set(1);
        
    }
    
    /**
     * stop the pickup
     * 
     */
    public void stop(){
        leftPickupRoller.set(0);
        rightPickupRoller.set(0);
        pickupUpDown.set(0);
    }
    
    /**
     * reverse the pickup 
     * @return if the pickup is in reverse
     */
    public void reverse(){
        leftPickupRoller.set(-1);
        rightPickupRoller.set(-1);
        
    }

    /**
     * Stops the pickup and lowers it
     */
    public void disable() {
        this.stop();
        this.lower();
        
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
//               this.raise();
//           else
//               this.lower();
//           
//           isRaised = !isRaised;
//       }
//      
        pickupUpDown.set(0);
        
        if(ControlPack.getInstance().getRaisePickupButton().get())
            pickupUpDown.set(-1);
        
        if(ControlPack.getInstance().getLowerPickupButton().get())
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
