/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.util.HashMap;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

/**
 *
 * @author Honza
 */
public class Shader {
    
    private int program;
    private HashMap<String, Integer> uniforms;
    
    public Shader() {
        program = glCreateProgram();
        uniforms = new HashMap<String, Integer>();
        
        if(program == 0) {
            System.err.println("Shader creation failed.");
            System.exit(1);
        }
    }
    
    /**
     * Adds new uniform binding to program
     * @param uniform name of the uniform
     */
    public void addUniform(String uniform) {
        
        //Looks for uniform name in given program
        int uniformLocation = glGetUniformLocation(program, uniform);
        
        //Uniform not found
        if(uniformLocation == -1) {
            System.err.println("Error: Could not find uniform: " + uniform);
            new Exception().printStackTrace();
            System.exit(1);
        }
        
        uniforms.put(uniform, uniformLocation);
    }
    
    /**
     * Sets new integer value for given uniform
     * @param uniformName name of the uniform
     * @param value new value
     */
    public void setUniformi(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }
    
    /**
     * Sets new float value for given uniform
     * @param uniformName name of the uniform
     * @param value new value
     */
    public void setUniformf(String uniformName, float value) {
        glUniform1f(uniforms.get(uniformName), value);
    }
    
    /**
     * Sets new Vector3f value for given uniform
     * @param uniformName name of the uniform
     * @param value new value
     */
    public void setUniform(String uniformName, Vector3f value) {
        glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());
    }
    
    /**
     * Sets new Matrix4f value for given uniform
     * @param uniformName name of the uniform
     * @param value new value
     */
    public void setUniform(String uniformName, Matrix4f value) {
        //boolean -> affects the major order by row/column 
        glUniformMatrix4(uniforms.get(uniformName), true, Util.createFlippedBuffer(value));
    }
    
    /**
     * Binds shaders to program
     */
    public void bind() {
        glUseProgram(program);
    }
    
    /**
     * Updates all uniforms in shaders
     * @param worldMatrix
     * @param projectedMatrix 
     */
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
        
    }
    
    /**
     * Adds Vertex shader. Calls {@link addProgram()} method to do the actual
     * job, only passes type of shader.
     * @param text shader contents
     */
    public void addVertexShader(String text) {
        addProgram(text, GL_VERTEX_SHADER);
    }
    
    /**
     * Adds Geometry shader. Calls {@link addProgram()} method to do the actual
     * job, only passes type of shader. Requires OpenGL v 3.2 + !
     * @param text shader contents
     */
    public void addGeometryShader(String text) {
        addProgram(text, GL_GEOMETRY_SHADER);
        
    }
    
    /**
     * Adds Fragment shader. Calls {@link addProgram()} method to do the actual
     * job, only passes type of shader.
     * @param text shader contents
     */
    public void addFragmentShader(String text) {
        addProgram(text, GL_FRAGMENT_SHADER);
    }
    
    /**
     * Links and validates program
     */
    public void compileShader() {
        
        //Does all the linking
        glLinkProgram(program);
        
        if(glGetProgram(program, GL_LINK_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(program, 1024));
            System.exit(1);
        }
        
        //Validation check for potentional errors
        glValidateProgram(program);
        
        if(glGetProgram(program, GL_VALIDATE_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(program, 1024));
            System.exit(1);
        }
    }
    
    /**
     * Adds Shader to OpenGL program space.
     * @param text shader contents
     * @param type type of shader (vertex, geometry, fragment)
     */
    private void addProgram(String text, int type) {
        
        //Creates empty Shader
        int shader = glCreateShader(type);
        if(shader == 0) {
            System.err.println("Could not add current shader " + type + ".");
            System.exit(1);
        }
        
        //Sets Shader source to input text variable
        glShaderSource(shader, text);
        glCompileShader(shader);
        
        if(glGetShader(shader, GL_COMPILE_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(shader, 1024));
            System.exit(1);
        }
        
        //Attaches shader to the corrent program
        glAttachShader(program, shader);
    }
    
}
