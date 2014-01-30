/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.plasmarobotics.jim;

/**
 *
 * @author Master Hacker
 */
public final class Logger {
    private static int priority = 0;
    
    public static void log(String msg, Object obj, int priority){
        if(Logger.priority <= priority) { // high enough priority to log
            String name = obj.getClass().getName();
            name = name.substring(name.lastIndexOf('.') + 1, name.length());
            System.out.println("[" + name + "]: " + msg);
        }
    }

    private static void log(String msg){
        if(Logger.priority >= priority){ // TODO: always?
            System.out.println("[Logger]: " + msg);
        }
    }
    
    public static void printPriority() {
        Logger.log("Current priority level: " + Logger.priority);
    }
    
    public static void raisePriority(){
        if(Logger.priority < 5)
            Logger.priority++;
        Logger.log("Logger priority raised.");
        printPriority();
    }
    
    public static void lowerPriority(){
        if(Logger.priority > 0)
            Logger.priority--;
        Logger.log("Logger priority lowered.");
        printPriority();
    }
}
