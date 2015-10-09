/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import org.lwjgl.input.Keyboard;

/**
 *
 * @author Honza
 */
public class Camera {
    
    // Absolute up (world up)
    private static final Vector3f yAxis = new Vector3f(0, 1, 0);
    
    // Camera position
    private Vector3f pos;
    
    // Represents which direction is forward/up 
    private Vector3f forward;
    private Vector3f up;
    
    
    public Camera() {
        this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
    }
    
    public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
        
        this.pos = pos;
        this.forward = forward;
        this.up = up;
        
        up.normalize();
        forward.normalize();
    }
    
    /**
     * Takes care of camera movement set by keys. W,A,S,D for moving in space, 
     * arrow keys for rotation in space.
     */
    public void input() {
        
        float movAmt = (float)(10 * Time.getDelta());
        float rotAmt = (float)(100 * Time.getDelta());
        
        if(Input.getKey(Keyboard.KEY_W))
            move(getForward(), movAmt);
        if(Input.getKey(Keyboard.KEY_S))
            move(getForward(), -movAmt);
        if(Input.getKey(Keyboard.KEY_A))
            move(getLeft(), movAmt);
        if(Input.getKey(Keyboard.KEY_D))
            move(getRight(), movAmt);
        
        if(Input.getKey(Keyboard.KEY_UP))
            rotateX(-rotAmt);
        if(Input.getKey(Keyboard.KEY_DOWN))
            rotateX(rotAmt);
        if(Input.getKey(Keyboard.KEY_LEFT))
            rotateY(-rotAmt);
        if(Input.getKey(Keyboard.KEY_RIGHT))
            rotateY(rotAmt);
    }
    
    /**
     * moves camera in set direction a by set ammount
     * @param dir direction in which camera moves
     * @param amt ammount of distance by which camera moves
     */
    public void move(Vector3f dir, float amt) {
        
        pos = pos.add(dir.mul(amt));
    }
    
    /**
     * Rotates camera around Y axis
     * @param angle ammount by which the camera rotates
     */
    public void rotateY(float angle) {
        
        //Horizontal axis
        Vector3f Haxis = yAxis.cross(forward);
        Haxis.normalize();
        
        forward.rotate(angle, yAxis);
        forward.normalize();
        
        up = forward.cross(Haxis);
        up.normalize();
    }
    
    /**
     * Rotates camera around X axis
     * @param angle ammount by which the camera rotates
     */
    public void rotateX(float angle) {
        
        //Horizontal axis
        Vector3f Haxis = yAxis.cross(forward);
        Haxis.normalize();
        
        forward.rotate(angle, Haxis);
        forward.normalize();
        
        up = forward.cross(Haxis);
        up.normalize();
    }
    
    /**
     * Calculates left rotation (by rule of hand...)
     * @return normalized left rotation
     */
    public Vector3f getLeft() {
        
        Vector3f left = forward.cross(up);
        left.normalize();
        
        return left;
    }
    
    /**
     * Calculates right rotation (by rule of hand...)
     * @return normalized right rotation
     */
    public Vector3f getRight() {
        
        Vector3f right = up.cross(forward);
        right.normalize();
        
        return right;
    }

    /**
     * @return the pos
     */
    public Vector3f getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    /**
     * @return the forward
     */
    public Vector3f getForward() {
        return forward;
    }

    /**
     * @param forward the forward to set
     */
    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    /**
     * @return the up
     */
    public Vector3f getUp() {
        return up;
    }

    /**
     * @param up the up to set
     */
    public void setUp(Vector3f up) {
        this.up = up;
    }
    
}
