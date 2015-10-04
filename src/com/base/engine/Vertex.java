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
public class Vertex {
    
    public static final int SIZE = 8;
    
    private Vector3f pos;       // 3
    private Vector2f texCoord;  // 2
    private Vector3f normal;
    
    public Vertex(Vector3f pos) {
        this(pos, new Vector2f(0,0));
    }
    
    public Vertex(Vector3f pos, Vector2f texCoord) {
        this(pos, texCoord, new Vector3f(0,0,0));
    }
    
    public Vertex(Vector3f pos, Vector2f texCoord, Vector3f normal) {
        this.pos = pos;
        this.texCoord = texCoord;
        this.normal = normal;
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
     * @return the texCoord
     */
    public Vector2f getTexCoord() {
        return texCoord;
    }

    /**
     * @param texCoord the texCoord to set
     */
    public void setTexCoord(Vector2f texCoord) {
        this.texCoord = texCoord;
    }

    /**
     * @return the normal
     */
    public Vector3f getNormal() {
        return normal;
    }

    /**
     * @param normal the normal to set
     */
    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }
    
    
    
}
