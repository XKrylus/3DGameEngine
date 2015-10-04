/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

/**
 * (CZ Quaternion = ctverice)
 * @author Honza
 */
public class Quaternion {
    
    private float x;
    private float y;
    private float z;
    private float w;
    
    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    /**
     *
     * @return length of quaternion
     */
    public float length() {
        return (float)Math.sqrt(x * x + y * y + z * z + w * w);
    }
    
    /**
     * Does normalization over quaternion by dividing its members by its length,
     * which is obtained by method {@link length()}.
     * @return normalized quaternion
     */
    public Quaternion normalize() {
        float length = length();
        
        x /= length;
        y /= length;
        z /= length;
        w /= length;
        
        return this;
    }
    
    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }
    
    /**
     * This is some next level shit... multiplication of a quaternion by
     * another quaternion. Someone help..
     * 
     * @param r input Quaternion
     * @return current Quaternion multiplied by the Quaternion r
     */
    public Quaternion mul(Quaternion r) {
        
        float w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
        float x_ = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
        float y_ = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
        float z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();
        
        return new Quaternion(x_, y_, z_, w_);
    }
    
    /**
     * This is also gold... multiplication of a quaternion by 3D vector. Easier?
     * Nono...
     * @param r input Vector3f
     * @return current Quaternion multiplied by the Vector3f r
     */
    public Quaternion mul(Vector3f r) {
        
        float x_ = w * r.getX() + y * r.getZ() - z * r.getY();
        float y_ = w * r.getY() + z * r.getX() - x * r.getZ();
        float z_ = w * r.getZ() + x * r.getY() - y * r.getX();
        float w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
        
        return new Quaternion(x_, y_, z_, w_);
    }
    

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return the z
     */
    public float getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(float z) {
        this.z = z;
    }

    /**
     * @return the w
     */
    public float getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(float w) {
        this.w = w;
    }
    
    
    
}
