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
public class Material {
    
    private Texture texture;
    private Vector3f color;
    private float specularIntensity;
    
    public Material(Texture texture, Vector3f color) {
        
        this.texture = texture;
        this.color = color;
    }

    /**
     * @return the texture
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * @param texture the texture to set
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    /**
     * @return the color
     */
    public Vector3f getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Vector3f color) {
        this.color = color;
    }
    
}
