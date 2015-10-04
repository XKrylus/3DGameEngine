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
public class BaseLight {
    
    private Vector3f color;
    private float intensity;
    
    public BaseLight(Vector3f color, float intensity) {
        this.color = color;
        this.intensity = intensity;
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

    /**
     * @return the intensity
     */
    public float getIntensity() {
        return intensity;
    }

    /**
     * @param intensity the intensity to set
     */
    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }
    
}
