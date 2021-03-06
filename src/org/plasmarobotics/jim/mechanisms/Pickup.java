 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.plasmarobotics.jim.mechanisms;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Victor;
import org.plasmarobotics.jim.Channels;
import org.plasmarobotics.jim.controls.ControlPack;

/**
 * This class handles pickup operations of the robot
 * @author jim
 */
public class Pickup implements Mechanism{
    double raisePower,
            lowerPower;
    Victor pickupRoller,
        rightPickupUpDown,
        leftPickupUpDown;

  
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
        rightPickupUpDown = new Victor(Channels.PICKUP_RAISE_LOWER_RIGHT_CHANNEL);
        leftPickupUpDown = new Victor(Channels.PICKUP_RAISE_LOWER_LEFT_CHANNEL);//was (1, CONSTANT)
        pickupRoller = new Victor(Channels.PICKUP_ROLLER_CHANNEL);
                
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
    public boolean lower(double power){
        //not lifted
//        if(!pickupLiftedSwitch.get()){
            leftPickupUpDown.set(power);
            rightPickupUpDown.set(-power);
                 
//            return false;
//        } else{//it is lifted
//            leftPickupUpDown.set(0);
//            return true;
//        }
        return true;          
    }
    
    /**
     * raise the pickup mechanism
     * @return true if the mechanism is raised, false otherwise
     */
    public boolean raise(double power){
        //not lowered
//        if(!pickupLoweredSwitch.get()){
            leftPickupUpDown.set(-power);
            rightPickupUpDown.set(power);
//            return false;
//        } else{
//            leftPickupUpDown.set(0);
//            return true;
//        }
            return true;
    }
    
//    public void stahp(){
//        leftPickupUpDown.set(Victor.);
//    }
    /**
     * pickup the ball
     */
    public void forward(){
        pickupRoller.set(1);
       
        
    }
    
   
    
    /**
     * reverse the pickup 
     * @return if the pickup is in reverse
     */
    public void reverse(){
        pickupRoller.set(-1);
        
        
    }

    /**
     * Stops the pickup and lowers it
     */
    public void disable() {
        pickupRoller.set(0);
        rightPickupUpDown.set(0);
        leftPickupUpDown.set(0);
        
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
        raisePower = Preferences.getInstance().getDouble("raisePower", .5);
        lowerPower = Preferences.getInstance().getDouble("lowerPower", .5);
        leftPickupUpDown.set(0);
        rightPickupUpDown.set(0);
        if(ControlPack.getInstance().getRaisePickupButton().get())
            this.raise(raisePower);
        
        if(ControlPack.getInstance().getLowerPickupButton().get())
            this.lower(lowerPower);
        
        
       //moving the rollers
       pickupRoller.set(controls.getGamepad().getTriggerAxis());
       
           
        
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
