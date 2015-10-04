/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

/**
 *
 * @author Honza
 */
public class Time {
    
    //Nanoseconds in a second (1 s = 1000000000 ns)
    public static final long SECOND = 1000000000L;
    
    //Time differ
    private static double delta;
    
    /**
     * Returns current System time
     * @return current time in nanoseconds
     */
    public static long getTime() {
        return System.nanoTime();
    }
    
    /**
     * Returns the time difference
     * @return time difference
     */
    public static double getDelta() {
        return delta;
    }
    
    /**
     * Sets current time difference
     * @param delta current time difference
     */
    public static void setDelta(double delta) {
        Time.delta = delta;
    }
    
}
