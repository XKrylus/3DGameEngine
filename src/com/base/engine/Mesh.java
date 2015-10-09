/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author Honza
 */
public class Mesh {
    
    //Serves as pointer to vertices
    //Pointer in Java...!! NOOOOOOooooOOOOooooOOOooo...
    private int vbo;
    
    private int ibo;
    
    //Actual data size of vertices
    private int size;
    
    public Mesh() {
        vbo = glGenBuffers();
        ibo = glGenBuffers();
        size = 0;
    }
    
    public void addVerticies(Vertex[] verticies, int[] indices) {
        addVerticies(verticies, indices, false);
    }
    
    /**
     * Adds vertices to buffer
     * @param data vertices to be added
     */
    public void addVerticies(Vertex[] verticies, int[] indices, boolean calcNormals) {
        
        if(calcNormals) {
            calcNormals(verticies, indices);
        }
        
        
       
        //Calculates total size of vertices to be stored
        size = verticies.length * Vertex.SIZE;
        
        //But we want actual size of indices. Because, many points can be
        //reused now! (and this took me about an hour to even notice...)
        size = indices.length;
        
        //Adding data to buffer - stores data on graphic card
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(verticies), GL_STATIC_DRAW);
        
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
    }
    
    /**
     * Handles drawing of the scene
     */
    public void draw() {
        
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        
        // Offset - 3 (number of position coords) times 4 (size of float in 
        // bytes)
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
        
        glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);
        
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
        
        
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);

    }
    
    /**
     * Calculates normals for input vertices, based on given indeces
     * @param vertices
     * @param indices 
     */
    private void calcNormals(Vertex[] vertices, int[] indices) {
        for(int i = 0; i < indices.length; i += 3) {
            int i0 = indices[i];
            int i1 = indices[i + 1];
            int i2 = indices[i + 2];
            
            Vector3f v1 = vertices[i1].getPos().sub(vertices[i0].getPos());
            Vector3f v2 = vertices[i2].getPos().sub(vertices[i0].getPos());
            
            Vector3f normal = v1.cross(v2).normalize();
            
            vertices[i0].setNormal(vertices[i0].getNormal().add(normal));
            vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
            vertices[i2].setNormal(vertices[i2].getNormal().add(normal));
        }
        
        for(int i = 0; i < vertices.length; i++) {
            vertices[i].setNormal(vertices[i].getNormal().normalize());
        }
    }
    
}
