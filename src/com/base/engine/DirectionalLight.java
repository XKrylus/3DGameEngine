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
public class DirectionalLight {
    
    private BaseLight base;
    private Vector3f direction;
    
    public DirectionalLight(BaseLight base, Vector3f direction) {
        this.base = base;
        this.direction = direction.normalize();
    } 

    /**
     * @return the base
     */
    public BaseLight getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(BaseLight base) {
        this.base = base;
    }

    /**
     * @return the direction
     */
    public Vector3f getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
    
    
    
}
