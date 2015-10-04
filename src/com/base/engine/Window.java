/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 *
 * @author Honza
 */
public class Window {
    
    /**
     * Creates the window. Params set the dimensions and title of the window.
     * @param width width of the window
     * @param height height of the window
     * @param title title of the window
     */
    public static void createWindow(int width, int height, String title) {
        Display.setTitle(title);
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
            Keyboard.create();
            Mouse.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Destroys the window as part of the {@link MainComponent#cleanUp()} method.
     */
    public static void dispose() {
        
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }
    
    /**
     * Takes what was put to the buffer and draws it into the window
     */
    public static void render() {
        Display.update();
    }
    
    /**
     * Checks whether close was called on window
     * @return true if close was called
     */
    public static boolean isCloseRequested() {
        return Display.isCloseRequested();
    }
    
    /**
     * Returns width of window
     * @return width of window
     */
    public static int getWidth() {
        return Display.getDisplayMode().getWidth();
    }
    
    /**
     * Return height of window
     * @return height of window
     */
    public static int getHeight() {
        return Display.getDisplayMode().getHeight();
    }
    
    /**
     * Returns title of window
     * @return title of window
     */
    public static String getTitle() {
        return Display.getTitle();
    }

}
