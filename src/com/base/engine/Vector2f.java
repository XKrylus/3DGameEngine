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
public class Vector2f {
    
    private float x;
    private float y;
    
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * 
     * @return length of vector 
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }
    
    /**
     * Calculates dot product of vector (CZ = skalarni soucin)
     * @param r input Vector
     * @return dot product of input Vector
     */
    public float dot(Vector2f r) {
        return x * r.getX() + y * r.getY();
    }
    
    /**
     * Does the normalization of current vector by dividing its members by its
     * length, which is obtained by method {@link length()}.
     * @return normalized Vector 
     */
    public Vector2f normalize() {
        float length = length();
        x /= length;
        y /= length;
        
        return this;
    }
    
    /**
     * Does the rotation of vector under the given angle
     * @param angle by how many degrees the rotation occurs
     * @return new, rotated Vector
     */
    public Vector2f rotate(float angle) {
        
        float rad = (float) Math.toRadians(angle);
        double sin = Math.sin(rad);
        double cos = Math.cos(rad);
        
        return new Vector2f((float)(x * cos - y * sin), (float)(x * sin + y * cos));
    }
    
    public Vector2f add(Vector2f r) {
        return new Vector2f(x + r.getX(), y + r.getY());
    }
    
    public Vector2f add(float r) {
        return new Vector2f(x + r, y + r);
    }
    
    public Vector2f sub(Vector2f r) {
        return new Vector2f(x - r.getX(), y - r.getY());
    }
    
    public Vector2f sub(float r) {
        return new Vector2f(x - r, y - r);
    }
    
    public Vector2f mul(Vector2f r) {
        return new Vector2f(x * r.getX(), y * r.getY());
    }
    
    public Vector2f mul(float r) {
        return new Vector2f(x * r, y * r);
    }
    
    public Vector2f div(Vector2f r) {
        return new Vector2f(x / r.getX(), y / r.getY());
    }
    
    public Vector2f div(float r) {
        return new Vector2f(x / r, y / r);
    }
    
    
    public String toString() {
        return "(" + x + " " + y + ")";
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
    
}
