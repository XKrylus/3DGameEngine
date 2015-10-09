/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.engine;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Honza
 */
public class InputControl {
    
    public static boolean lockUpdate = true;
    
    public InputControl() {
        
    }
    
    /**
     * Input control for testing engine enviroment
     */
    public void input() {
        
        //Stops all game update actions
        if(Input.getKeyDown(Keyboard.KEY_SPACE)) {
            InputControl.lockUpdate = !InputControl.lockUpdate;
        }
    }
    
}
