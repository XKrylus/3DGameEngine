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
public class Vector3f {
    
    private float x;
    private float y;
    private float z;
    
    
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * 
     * @return length of vector 
     */
    public float length() {
        return (float)Math.sqrt(x * x + y * y + z * z);
    }
    
    /**
     * Calculates dot product of vector (CZ = skalarni soucin)
     * @param r input Vector
     * @return dot product of input Vector
     */
    public float dot(Vector3f r) {
        return x * r.getX() + y * r.getY() + z * r.getZ();
    }
    
    /**
     * Calculates cross product of vector (CZ = vektorovy soucin)
     * @param r input Vector
     * @return cross product of input Vector
     */
    public Vector3f cross(Vector3f r) {
        float x_ = y * r.getZ() - z * r.getY();
        float y_ = z * r.getX() - x * r.getZ();
        float z_ = x * r.getY() - y * r.getX();
        
        return new Vector3f(x_, y_, z_);
    }
    
    /**
     * Does the normalization of current vector by dividing its members by its
     * length, which is obtained by method {@link length()}.
     * @return normalized Vector 
     */
    public Vector3f normalize() {
        float length = length();
        x /= length;
        y /= length;
        z /= length;
        
        return this;
    }
    
    /**
     * Does the rotation of a Vector (??)
     * @param angle
     * @param axis
     * @return 
     */
    public Vector3f rotate(float angle, Vector3f axis) {
        
        float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
        float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));
        
        // Conversion to Quaternion
        float rX = axis.getX() * sinHalfAngle;
        float rY = axis.getY() * sinHalfAngle;
        float rZ = axis.getZ() * sinHalfAngle;
        float rW = cosHalfAngle;
        
        Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
        Quaternion conjugate = rotation.conjugate();
        
        //Vector is rotated by given Quaterinon
        Quaternion w = rotation.mul(this).mul(conjugate);
        
        x = w.getX();
        y = w.getY();
        z = w.getZ();
        
        return this;
    }
    
    public Vector3f add(Vector3f r) {
        return new Vector3f(x + r.getX(), y + r.getY(), z + r.getZ());
    }
    
    public Vector3f add(float r) {
        return new Vector3f(x + r, y + r, z + r);
    }
    
    public Vector3f sub(Vector3f r) {
        return new Vector3f(x - r.getX(), y - r.getY(), z - r.getZ());
    }
    
    public Vector3f sub(float r) {
        return new Vector3f(x - r, y - r, z - r);
    }
    
    public Vector3f mul(Vector3f r) {
        return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
    }
    
    public Vector3f mul(float r) {
        return new Vector3f(x * r, y * r, z * r);
    }
    
    public Vector3f div(Vector3f r) {
        return new Vector3f(x / r.getX(), y / r.getY(), z / r.getZ());
    }
    
    public Vector3f div(float r) {
        return new Vector3f(x / r, y / r, z / r);
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
    
    
    
}
