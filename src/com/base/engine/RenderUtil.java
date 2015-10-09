/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 *
 * @author Honza
 */
public class RenderUtil {
    
    /**
     * Erases everything on screen
     */
    public static void clearScreen() {
        
        //TODO: stencil buffer
        
        //clears color and depth buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);    
    }
    
    /**
     * Sets GL_TEXTURE_2D enabled or disabled
     * @param enabled 
     */
    public static void setTextures(boolean enabled) {
        
        if(enabled) {
            glEnable(GL_TEXTURE_2D);
        }
        else {
            glDisable(GL_TEXTURE_2D);
        }
    }
    
    /**
     * Unbinds GL_TEXTURE_2D
     */
    public static void unbindTextures() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    
    /**
     * Sets clear color for given vector
     * @param color given vector
     */
    public static void setClearColor(Vector3f color) {
        
        glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
    }
    
    /**
     * Sets graphics to engine default values (these are custom selected)
     */
    public static void initGraphics() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        
        //Every face drawn in clock-wise order will be front face.
        glFrontFace(GL_CW);
        //Culls (removes) the back face
        glCullFace(GL_BACK);
        
        //glEnable(GL_CULL_FACE);
        
        //Keeps track of every pixel drawn in z coordinates FROM camera, which
        // allows for these pixels to overlap correctly
        glEnable(GL_DEPTH_TEST);
        
        //Enables Textures
        glEnable(GL_TEXTURE_2D);
        
        //TODO: depth clamp
        
        
        //Allows free gamma correction, ie. sends colors in exponential, rather
        // then linear
        glEnable(GL_FRAMEBUFFER_SRGB);
    }
    
    /**
     * Returns OpenGL version currently in use
     * @return 
     */
    public static String getOpenGLVersion() {
        return glGetString(GL_VERSION);
    }
    
}
