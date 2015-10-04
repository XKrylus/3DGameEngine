/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Honza
 */
public class Texture {
    
    private int id;
    
    
    public Texture(int id) {
        
        this.id = id;
    }
    
    /**
     * Binds id to texture
     */
    public void bind() {
        
        glBindTexture(GL_TEXTURE_2D, id);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
