/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Honza
 */
public class Input {
    
    //Standard ASCII keyboard
    public static final int NUM_KEYCODES = 256;
    public static final int NUM_MOUSEBUTTONS = 5;
    
    //Arrays keep track of current pressed keys, keys still down and keys now up
    private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
    private static ArrayList<Integer> upKeys = new ArrayList<Integer>();
    
    private static ArrayList<Integer> currentMouse = new ArrayList<Integer>();
    private static ArrayList<Integer> downMouse = new ArrayList<Integer>();
    private static ArrayList<Integer> upMouse = new ArrayList<Integer>();
    

    
    /**
     * Does update over arrays of keys/mousebuttons for current frame. Checks
     * which buttons are pressed, and which buttons were released since last
     * frame. Also clears all arrays prior to their update ({@link currentKeys
     * } and {@link currentMouse} lists are cleared as the last ones).
     */
    public static void update() {
        
        //Key input handling
        
        //Up Keys
        upKeys.clear();
        
        for(int i = 0; i < NUM_KEYCODES; i++) {
            if(!getKey(i) && currentKeys.contains(i)) 
                upKeys.add(i);
        }
        
        //Down Keys
        downKeys.clear();
        
        for(int i = 0; i < NUM_KEYCODES; i++) {
            if(getKey(i) && !currentKeys.contains(i)) 
                downKeys.add(i);
        }
        
        //Current Keys
        currentKeys.clear();
        
        //Keeps track of every single key (for current frame) that is down
        for(int i = 0; i < NUM_KEYCODES; i++) {
            if(getKey(i)) 
                currentKeys.add(i);
        }
        
        //Mouse input handling
        
        //Up Mouse Buttons
        upMouse.clear();
        
        for(int i = 0; i < NUM_MOUSEBUTTONS; i++) {
            if(!getMouse(i) && currentMouse.contains(i)) 
                upMouse.add(i);
        }
        
        //Down Mouse Buttons
        downMouse.clear();
        
        for(int i = 0; i < NUM_MOUSEBUTTONS; i++) {
            if(!getMouse(i) && currentMouse.contains(i)) 
                downMouse.add(i);
        }
        
        //Current Mouse Buttons
        currentMouse.clear();
        for(int i = 0; i < NUM_MOUSEBUTTONS; i++) {
            if(getMouse(i)) 
                currentMouse.add(i);
        }
    }
    
    public static boolean getKey(int keyCode) {  
        return Keyboard.isKeyDown(keyCode);
    }
    
    public static boolean getKeyDown(int keyCode) {        
        return downKeys.contains(keyCode);
    }
    
    public static boolean getKeyUp(int keyCode) {
        return upKeys.contains(keyCode);
    }
    
    public static boolean getMouse(int mouseButton) {
        return Mouse.isButtonDown(mouseButton);
    }
    
    public static boolean getMouseDown(int mouseButton) {
        return downMouse.contains(mouseButton);
    }
    
    public static boolean getMouseUp(int mouseButton) {
        return upMouse.contains(mouseButton);
    }
    
    public static Vector2f getMousePosition() {
        return new Vector2f(Mouse.getX(), Mouse.getY());
    }
    
    /**
     * Sets Mouse position to given pos
     * @param pos given pos
     */
    public static void setMousePosition(Vector2f pos) {
        Mouse.setCursorPosition((int)pos.getX(), (int)pos.getY());
    }
    
    /**
     * Enables the cursor showing (visible) in application
     * @param enabled true when enabled, false when disabled
     */
    public static void setCursor(boolean enabled) {
        Mouse.setGrabbed(!enabled);
    }
    
}
